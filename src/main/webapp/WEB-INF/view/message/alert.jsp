<%-- 
    Document   : alert
    Created on : 01/10/2017, 00:32:14
    Author     : Lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>

<c:set var="title" value="Alerta"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="view/message/success.css"/>
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}"/>
        </jsp:include>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="66">
                    <md-card>
                        ${message}
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="view/message/success.js"/>
    </body>
</html>