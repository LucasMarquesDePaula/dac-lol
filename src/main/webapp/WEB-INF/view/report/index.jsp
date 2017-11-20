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
                <md-layout md-flex="60">
                    <c:if test="${!empty message}">
                        <md-card class="${messages == null || messages.length == 0 ? 'md-primary' : 'md-warn'}">
                            <md-card-header>
                                <div class="md-title">${message}</div>
                            </md-card-header>
                        </md-card>
                    </c:if>
                    <md-card>
                        <md-card-content>
                            <form action="${contextPath}/${basePath}/clientes" target="_blank">
                                <md-card>
                                    <md-card-header>
                                        <div class="md-title">Clientes</div>
                                    </md-card-header>
                                    <md-card-actions>
                                        <md-button type="submit" class="md-raised md-primary">Gerar</md-button>
                                    </md-card-actions>
                                </md-card>
                            </form>
                            
                            <form action="${contextPath}/${basePath}/clientes-fieis" target="_blank">
                                <md-card>
                                    <md-card-header>
                                        <div class="md-title">Clientes Fieis</div>
                                    </md-card-header>
                                    <md-card-actions>
                                        <md-button type="submit" class="md-raised md-primary">Gerar</md-button>
                                    </md-card-actions>
                                </md-card>
                            </form>

                            <form action="${contextPath}/${basePath}/receitas" target="_blank">
                                <md-card>
                                    <md-card-header>
                                        <div class="md-title">Receitas</div>
                                    </md-card-header>
                                    <md-card-content>
                                        <md-input-container>
                                            <label>Data Inicial</label>
                                            <md-input type="date" name="dataInicial"></md-input>
                                        </md-input-container>

                                        <md-input-container>
                                            <label>Data Final</label>
                                            <md-input type="date" name="dataFinal"></md-input>
                                        </md-input-container>
                                    </md-card-content>
                                    <md-card-actions>
                                        <md-button type="submit" class="md-raised md-primary">Gerar</md-button>
                                    </md-card-actions>
                                </md-card>
                            </form>
                                
                            <form action="${contextPath}/${basePath}/pedidos" target="_blank">
                                <md-card>
                                    <md-card-header>
                                        <div class="md-title">Pedidos</div>
                                    </md-card-header>
                                    <md-card-content>
                                        <md-input-container>
                                            <label>Data Inicial</label>
                                            <md-input type="date" name="dataInicial"></md-input>
                                        </md-input-container>
                                        <md-input-container>
                                            <label>Data Final</label>
                                            <md-input type="date" name="dataFinal"></md-input>
                                        </md-input-container>
                                    </md-card-content>
                                    <md-card-actions>
                                        <md-button type="submit" class="md-raised md-primary">Gerar</md-button>
                                    </md-card-actions>
                                </md-card>
                            </form>
                        </md-card-content>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="view/${basePath}/index.js"/>
    </body>
</html>

