package br.ufpr.tads.dac.lol.staticx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "StaticResourceServlet", urlPatterns = {"/static/*"})
public class StaticResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final long ONE_SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private static final String ETAG_HEADER = "W/\"%s-%s\"";
    private static final String CONTENT_DISPOSITION_HEADER = "inline;filename=\"%1$s\"; filename*=UTF-8''%1$s";

    public static final long DEFAULT_EXPIRE_TIME_IN_MILLIS = TimeUnit.DAYS.toMillis(30);
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 102400;

    private File folder;

    @Override
    public void init() throws ServletException {
        folder = new File(getServletContext().getRealPath("/"));
    }

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response, true);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response, false);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response, boolean head) throws IOException {
        response.reset();
        StaticResource resource;

        try {
            resource = getStaticResource(request);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String fileName = URLEncoder.encode(resource.getFileName(), StandardCharsets.UTF_8.name());
        boolean notModified = setCacheHeaders(request, response, fileName, resource.getLastModified());

        if (notModified) {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        setContentHeaders(response, fileName, resource.getContentLength());

        if (head) {
            return;
        }

        writeContent(response, resource);
    }

    /**
     * Returns the static resource associated with the given HTTP servlet
     * request. This returns <code>null</code> when the resource does actually
     * not exist. The servlet will then return a HTTP 404 error.
     *
     * @param request The involved HTTP servlet request.
     * @return The static resource associated with the given HTTP servlet
     * request.
     * @throws IllegalArgumentException When the request is mangled in such way
     * that it's not recognizable as a valid static resource request. The
     * servlet will then return a HTTP 400 error.
     */
    private StaticResource getStaticResource(HttpServletRequest request) throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException {

        String url = request.getRequestURL().toString();
        String context = "/static/";
        String pathInfo = url.substring(url.indexOf(context));

        if (pathInfo == null || pathInfo.isEmpty() || "/".equals(pathInfo)) {
            throw new IllegalArgumentException(String.format("Invalid path: %s", url));
        }

        String name = URLDecoder.decode(pathInfo.substring(1), StandardCharsets.UTF_8.name());
        final File file = new File(folder, name);

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("File not found: %s, %s", file.getAbsoluteFile(), pathInfo));
        }

        return new StaticResource() {
            @Override
            public long getLastModified() {
                return file.lastModified();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file);
            }

            @Override
            public String getFileName() {
                return file.getName();
            }

            @Override
            public long getContentLength() {
                return file.length();
            }
        };
    }

    private boolean setCacheHeaders(HttpServletRequest request, HttpServletResponse response, String fileName, long lastModified) {
        String eTag = String.format(ETAG_HEADER, fileName, lastModified);
        response.setHeader("ETag", eTag);
        response.setDateHeader("Last-Modified", lastModified);
        response.setDateHeader("Expires", System.currentTimeMillis() + DEFAULT_EXPIRE_TIME_IN_MILLIS);
        return notModified(request, eTag, lastModified);
    }

    private boolean notModified(HttpServletRequest request, String eTag, long lastModified) {
        String ifNoneMatch = request.getHeader("If-None-Match");

        if (ifNoneMatch != null) {
            String[] matches = ifNoneMatch.split("\\s*,\\s*");
            Arrays.sort(matches);
            return (Arrays.binarySearch(matches, eTag) > -1 || Arrays.binarySearch(matches, "*") > -1);
        } else {
            long ifModifiedSince = request.getDateHeader("If-Modified-Since");
            return (ifModifiedSince + ONE_SECOND_IN_MILLIS > lastModified); // That second is because the header is in seconds, not millis.
        }
    }

    private void setContentHeaders(HttpServletResponse response, String fileName, long contentLength) {
        response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
        response.setHeader("Content-Disposition", String.format(CONTENT_DISPOSITION_HEADER, fileName));

        if (contentLength != -1) {
            response.setHeader("Content-Length", String.valueOf(contentLength));
        }
    }

    private void writeContent(HttpServletResponse response, StaticResource resource) throws IOException {
        try (
                ReadableByteChannel inputChannel = Channels.newChannel(resource.getInputStream());
                WritableByteChannel outputChannel = Channels.newChannel(response.getOutputStream());) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(DEFAULT_STREAM_BUFFER_SIZE);
            long size = 0;

            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                size += outputChannel.write(buffer);
                buffer.clear();
            }

            if (resource.getContentLength() == -1 && !response.isCommitted()) {
                response.setHeader("Content-Length", String.valueOf(size));
            }
        }
    }

    interface StaticResource {

        /**
         * Returns the file name of the resource. This must be unique across all
         * static resources. If any, the file extension will be used to
         * determine the content type being set. If the container doesn't
         * recognize the extension, then you can always register it as
         * <code>&lt;mime-type&gt;</code> in <code>web.xml</code>.
         *
         * @return The file name of the resource.
         */
        public String getFileName();

        /**
         * Returns the last modified timestamp of the resource in milliseconds.
         *
         * @return The last modified timestamp of the resource in milliseconds.
         */
        public long getLastModified();

        /**
         * Returns the content length of the resource. This returns
         * <code>-1</code> if the content length is unknown. In that case, the
         * container will automatically switch to chunked encoding if the
         * response is already committed after streaming. The file download
         * progress may be unknown.
         *
         * @return The content length of the resource.
         */
        public long getContentLength();

        /**
         * Returns the input stream with the content of the resource. This
         * method will be called only once by the servlet, and only when the
         * resource actually needs to be streamed, so lazy loading is not
         * necessary.
         *
         * @return The input stream with the content of the resource.
         * @throws IOException When something fails at I/O level.
         */
        public InputStream getInputStream() throws IOException;

    }
}
