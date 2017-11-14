<%-- 
    Document   : generate
    Created on : 12/11/2017, 16:43:34
    Author     : Tom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="Relatórios"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="view/${basePath}/generate.css"/>
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}" />
        </jsp:include>
        
        <st:js res="view/${basePath}/generate.js"/>
    </body>
</html>

