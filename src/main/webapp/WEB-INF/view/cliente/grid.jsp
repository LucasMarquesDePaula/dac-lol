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
        <st:css res="page/pedido/form.css"/>
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
                                    <md-table-head md-sort-by="ativo">Ativo</md-table-head>
                                    <md-table-head md-sort-by="cpf">CPF</md-table-head>
                                    <md-table-head md-sort-by="email">E-mail</md-table-head>
                                    <md-table-head md-sort-by="endereco">Endereço</md-table-head>
                                </md-table-row>
                            </md-table-header>
                            <md-table-body>
                                <c:forEach var = "item" items="items">
                                    <md-table-row>
                                        <md-table-cell><c:out value = "${item.id}"/><p></md-table-cell>
                                        <md-table-cell><c:out value = "${item.nome}"/><p></md-table-cell>
                                        <md-table-cell><c:out value = "${item.ativo}"/><p></md-table-cell>
                                        <md-table-cell><c:out value = "${item.cpf}"/><p></md-table-cell>
                                        <md-table-cell><c:out value = "${item.email}"/><p></md-table-cell>
                                        <md-table-cell><c:out value = "${item.endereco}"/><p></md-table-cell>
                                    </md-table-row>
                                </c:forEach>
                            </md-table-body>
                        </md-table>
                        <md-table-pagination md-size="5" md-total="10" md-page="1" md-label="Nº de Linhas" md-separator="of" :md-page-options="[5, 10, 25, 50]"></md-table-pagination>
                    </md-table-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="page/pedido/form.js"/>
    </body>
</html>