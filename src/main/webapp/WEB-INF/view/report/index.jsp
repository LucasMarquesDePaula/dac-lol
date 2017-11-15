<%-- 
    Document   : index
    Created on : 12/11/2017, 16:43:34
    Author     : Tom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="Relatórios"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="view/${basePath}/index.css"/>
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}" />
        </jsp:include>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="66">
                    <c:if test="${!empty message}">
                        <md-card class="${messages == null || messages.length == 0 ? 'md-primary' : 'md-warn'}">
                            <md-card-header>
                                <div class="md-title">${message}</div>
                            </md-card-header>
                        </md-card>
                    </c:if>
                    <md-card>
                        <md-card-content>
                            <!-- Clientes Fieis -->
                            <form action='${contextPath}/${basePath}/clientes-fieis' target="_blank">
                                <md-button type="submit" class="md-raised">Clientes Fiéis</md-button>
                            </form>

                            <!-- Relatório 2 -->
                            <form action='${contextPath}/${basePath}/relatorio2' target="_blank">
                                <md-button type="submit" class="md-raised">Relatório 2</md-button>
                            </form>

                            <!-- Relatório 3 -->
                            <form action='${contextPath}/${basePath}/relatorio3' target="_blank">
                                <md-input-container>
                                    <md-input />
                                </md-input-container>
                                <md-button type="submit" class="md-raised">Relatório 3</md-button>
                            </form>
                        </md-card-content>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="view/${basePath}/index.js"/>
    </body>
</html>

