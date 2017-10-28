<%-- 
    Document   : form
    Created on : 30/09/2017, 21:10:44
    Author     : Lucas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="/WEB-INF/static.tld"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="Novo Pedido" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><c:out value="${title}" /></title>
        <jsp:include page="../../include/vue-material.jsp"/>
        <st:css res="view/${basePath}/form.css" />
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
                        <form method="POST" action='${contextPath}/${basePath}/${empty model.id ? "create" : "update"}/${model.id}' accept-charset="ISO-8859-1">
                            <md-card-content>
                                <md-layout :md-gutter="true">
                                    <md-layout md-flex="100">
                                        <md-input-container class="${empty messages.enderecoEntrega ? '' : 'md-input-invalid'}">
                                            <label>Endereço</label>
                                            <md-input name="enderecoEntrega" :required="true" value="${model.enderecoEntrega}"></md-input>
                                            <span class="md-error"><c:out value="${messages.enderecoEntrega}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>

                                <md-layout :md-gutter="true">
                                    <md-layout md-flex="100">
                                        <md-input-container class="${empty messages.observacaoCliente ? '' : 'md-input-invalid'}">
                                            <label>Observação</label>
                                            <md-input name="observacaoCliente" :required="false" value="${model.observacaoCliente}"></md-input>
                                            <span class="md-error"><c:out value="${messages.observacaoCliente}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>

                                <md-card-actions>
                                    <md-button type="submit" class="md-fab md-fab-bottom-right">
                                        <md-icon>save</md-icon>
                                    </md-button>
                                </md-card-actions>
                            </md-card-content>

                        </form>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="vue-the-mask/vue-the-mask.js"/>
        <st:js res="view/${basePath}/form.js"/>
    </body>
</html>