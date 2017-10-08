<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="title" value="Clientes"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="view/cliente/grid.css"/>
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}"/>
        </jsp:include>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="66">
                    <md-table-card>
                        <form>
                            <md-layout md-gutter>
                                <md-layout>
                                    <md-input-container>
                                        <label>Filtrar...</label>
                                        <md-select>
                                            <md-option value="aberto">Pedidos em aberto</md-option>
                                            <md-option value="entregue">Finalizado e entregue</md-option>
                                            <md-option value="naoentregue">Finalizado e não entregue</md-option>
                                            <md-option value="todos">Todos</md-option>
                                        </md-select>
                                    </md-input-container>
                                </md-layout>
                                <md-toolbar>
                                    <md-button type="submit" class="md-icon-button">
                                        <md-icon>search</md-icon>
                                    </md-button>
                                </md-toolbar>
                            </md-layout>
                        </form>

                        <md-table md-sort="name" md-sort-type="desc">
                            <md-table-header>
                                <md-table-row>
                                    <md-table-head md-sort-by="id">Cód.</md-table-head>
                                    <md-table-head md-sort-by="nome">Nome</md-table-head>
                                    <md-table-head md-sort-by="cpf">CPF</md-table-head>
                                    <md-table-head md-sort-by="email">E-mail</md-table-head>
                                    <md-table-head md-sort-by="endereco">Endereço</md-table-head>
                                </md-table-row>
                            </md-table-header>
                            <md-table-body>
                                <c:forEach var="item" items="${queryResult.list}">
                                    <md-table-row>
                                        <md-table-cell><c:out value = "${item.id}"/></md-table-cell>
                                        <md-table-cell><c:out value = "${item.nome}"/></md-table-cell>
                                        <md-table-cell><c:out value = "${item.cpf}"/></md-table-cell>
                                        <md-table-cell><c:out value = "${item.email}"/></md-table-cell>
                                        <md-table-cell><c:out value = "${item.endereco}"/></md-table-cell>
                                        <md-table-cell>
                                            <md-button class="md-icon-button" href="/lol/cliente/form/${item.id}">
                                                <md-icon>edit</md-icon>
                                            </md-button>
                                        </md-table-cell>
                                        <md-table-cell>
                                            <md-button class="md-icon-button" href="/lol/cliente/form/${item.id}">
                                                <md-icon>visibility</md-icon>
                                            </md-button>
                                        </md-table-cell>
                                    </md-table-row>
                                </c:forEach>
                            </md-table-body>
                        </md-table>
                        <md-table-pagination 
                            md-size="<fmt:formatNumber value="${limit - offset}" maxFractionDigits="0"/>" 
                            md-page="<fmt:formatNumber value="${offset / queryResult.count}" maxFractionDigits="0"/>" 
                            md-separator="de" 
                            md-total="<fmt:formatNumber value="${queryResult.count / (limit - offset)}" maxFractionDigits="0"/>" 
                            md-label="Nº de Linhas" 
                            @pagination="onPagination"
                            @page="onPagination"
                            @size="onPagination">
                        </md-table-pagination>
                    </md-table-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="view/cliente/grid.js"/>
    </body>
</html>