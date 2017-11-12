<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="Pedidos"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="view/${basePath}/edit.css"/>
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}"/>
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
                                        <md-input-container class="${empty messages.id ? '' : 'md-input-invalid'}">
                                            <label>Número do Pedido</label>
                                            <md-input name="id" :required="true" value="${model.id}"></md-input>
                                            <span class="md-error"><c:out value="${messages.id}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>

                                <md-layout :md-gutter="true">
                                    <md-layout md-flex="100">
                                        <md-checkbox name="check1" v-model="checkbox" class="md-primary">Confirmar Pagamento</md-checkbox>
                                        <md-checkbox name="check2" v-model="checkbox" class="md-primary">Confirmar Lavagem</md-checkbox>
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
        <st:js res="view/${basePath}/edit.js"/>
    </body>
</html>