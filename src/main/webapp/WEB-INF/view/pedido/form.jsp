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
<c:set var="title" value="Pedido" />

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
                                    <md-layout md-flex="15">
                                        <md-input-container>
                                            <label>Código</label>
                                            <md-input :readonly="true" name="id" type="number" value="${model.id}"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>

                                <md-input-container class="${empty messages.enderecoEntrega ? '' : 'md-input-invalid'}">
                                    <label>Endereço</label>
                                    <md-input name="enderecoEntrega" :required="true" value="${model.enderecoEntrega}"></md-input>
                                    <span class="md-error"><c:out value="${messages.enderecoEntrega}"/></span>
                                </md-input-container>

                                <md-input-container class="${empty messages.observacaoCliente ? '' : 'md-input-invalid'}">
                                    <label>Observação</label>
                                    <md-input name="observacaoCliente" :required="false" value="${model.observacaoCliente}"></md-input>
                                    <span class="md-error"><c:out value="${messages.observacaoCliente}"/></span>
                                </md-input-container>

                                <md-card-actions>
                                    <c:if test="${!empty model.id}">
                                        <md-button href="${contextPath}/${basePath}/cancel/${model.id}" class="md-raised md-primary">
                                            Cancelar
                                        </md-button>
                                    </c:if>
                                    <md-button type="submit" class="md-fab">
                                        <md-icon>save</md-icon>
                                    </md-button>
                                </md-card-actions>
                            </md-card-content>
                        </form>
                    </md-card>

                    <c:if test="${!empty model.id}">
                        <md-table-card>
                            <md-toolbar>
                                <h1 class="md-title">Itens</h1>
                            </md-toolbar>
                            <form method="POST" action='${contextPath}/${basePath}/add-item/${model.id}' accept-charset="ISO-8859-1">
                                <md-layout :md-gutter="true">
                                    <md-layout>
                                        <md-input-container>
                                            <label>Tipo de Roupa</label>
                                            <md-select name="tipoRoupa" :required="true">
                                                <c:forEach var="item" items="${tiposRoupa}">
                                                    <md-option value="${item.id}">
                                                        <c:out value="${item.nome}" />
                                                    </md-option>
                                                </c:forEach>
                                            </md-select>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout md-flex="35">
                                        <md-input-container>
                                            <label>Quatidade</label>
                                            <md-input name="quantidade" :required="true" type="number"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout md-flex="15">
                                        <md-button type="submit" class="md-icon-button md-raised md-primary">
                                            <md-icon>add</md-icon>
                                        </md-button>
                                    </md-layout>
                                </md-layout>
                            </form>
                            <md-table>
                                <md-table-header>
                                    <md-table-row>
                                        <md-table-head>Descrição</md-table-head>
                                        <md-table-head>Prazo (h)</md-table-head>
                                        <md-table-head>Qtd.</md-table-head>
                                        <md-table-head>Vlr. Unitário</md-table-head>
                                        <md-table-head>Vlr. Total</md-table-head>
                                    </md-table-row>
                                </md-table-header>

                                <md-table-body>
                                    <c:forEach var="item" items="${queryResult.list}">
                                        <md-table-row>
                                            <md-table-cell><c:out value="${item.tipoRoupa.descricao}"/></md-table-cell>
                                            <md-table-cell><fmt:formatNumber value="${item.tipoRoupa.prazoLavagem}" type="number"/></md-table-cell>
                                            <md-table-cell><fmt:formatNumber value="${item.quantidade}" type="number" maxFractionDigits="0"/></md-table-cell>                                          
                                            <md-table-cell><fmt:formatNumber value="${item.valorUnitario}" type="currency"/></md-table-cell>
                                            <md-table-cell><fmt:formatNumber value="${0}" type="currency"/></md-table-cell>
                                            <md-button class="md-icon-button" href="${contextPath}/${basePath}/remove-item/${item.id}">
                                                <md-icon>delete</md-icon>
                                            </md-button>
                                        </md-table-row>
                                    </c:forEach>
                                </md-table-body>
                            </md-table>
                        </md-table-card>
                    </c:if>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="vue-the-mask/vue-the-mask.js"/>
        <st:js res="view/${basePath}/form.js"/>
    </body>
</html>