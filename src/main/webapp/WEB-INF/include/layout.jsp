<%-- 
    Document   : layout
    Created on : 24/09/2017, 00:42:41
    Author     : Lucas
--%>

<jsp:include page="toolbar.jsp">
    <jsp:param name="title" value="${param.title}"/>
</jsp:include>
<jsp:include page="sidenav-left.jsp" />
<jsp:include page="loading-overlay.jsp" />