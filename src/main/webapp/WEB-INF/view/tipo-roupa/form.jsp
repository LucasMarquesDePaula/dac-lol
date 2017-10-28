<%-- 
    Document   : form
    Created on : 30/09/2017, 21:10:44
    Author     : Lucas
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="/WEB-INF/static.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="Tipo Roupa" />

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
                        <form method="POST" action="${contextPath}/${basePath}/${empty model.id ? "create" : "update"}/${model.id}" accept-charset="ISO-8859-1">
                            <md-card-content>
                                <md-layout :md-gutter="true">
                                    <md-layout md-flex="30">
                                        <md-input-container>
                                            <label>Código</label>
                                            <md-input :readonly="true" name="id" type="number" value="${model.id}"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout>
                                        <md-input-container class="${empty messages.nome ? '' : 'md-input-invalid'}">
                                            <label>Nome</label>
                                            <md-input name="nome" :required="true" value="${model.nome}"></md-input>
                                            <span class="md-error"><c:out value="${messages.nome}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>
                                <md-layout :md-gutter="true">
                                    <md-layout>
                                        <md-input-container class="${empty messages.descricao ? '' : 'md-input-invalid'}">
                                            <label>Descrição</label>
                                            <md-input name="nome" :required="true" value="${model.descricao}"></md-input>
                                            <span class="md-error"><c:out value="${messages.descricao}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>
                                <md-layout :md-gutter="true">
                                    <md-layout>
                                        <md-input-container md-has-password class="${empty messages.prazoLavagem ? '' : 'md-input-invalid'}">
                                            <md-icon>av time</md-icon>
                                            <label>Prazo Lavagem</label>
                                            <md-input v-model.lazy="prazoLavagem" v-money="{}" value="<fmt:formatNumber value="${messages.precoLavagem}"/>"></md-input>
                                            <span class="md-error"><c:out value="${messages.prazoLavagem}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout>
                                        <md-input-container md-has-password class="${empty messages.precoLavagem ? '' : 'md-input-invalid'}">
                                            <md-icon>attach money</md-icon>
                                            <label>Preço Lavagem</label>
                                            <md-input v-model.lazy="precoLavagem" v-money="{}" value="<fmt:formatNumber value="${messages.precoLavagem}"/>"></md-input>
                                            <span class="md-error"><c:out value="${messages.precoLavagem}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>

                                <div class="hidden">
                                    <input name="prazoLavagem" type="hidden"/>
                                    <input name="precoLavagem" type="hidden"/>
                                    <input name="ativo" type="hidden" value="1"/>
                                </div>

                                <md-card-actions>
                                    <md-button type="submit" class="md-raised md-primary bt-align">Salvar</md-button>	
                                </md-card-actions>
                            </md-card-content>
                        </form>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="vue-money/vue-money.js"/>
        <st:js res="view/${basePath}/form.js"/>
    </body>
</html>
