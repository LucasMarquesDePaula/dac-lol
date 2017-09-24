<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>

<c:set var="title" value="Pedidos"/>

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
                                    <md-table-head md-sort-by="id">Nº Pedido</md-table-head>
                                    <md-table-head md-sort-by="prazo">Prazo de Entrega</md-table-head>
                                    <md-table-head md-sort-by="preco">Preço</md-table-head>
                                    <md-table-head md-sort-by="status">Status</md-table-head>
                                    <md-table-head md-sort-by="motivo">Motivo</md-table-head>
                                </md-table-row>
                            </md-table-header>

                            <md-table-body>
                                <md-table-row v-for="(row, index) in 2" :key="index">
                                    <md-table-cell>{{(Math.random() * 1000000).toFixed()}}</md-table-cell>
                                    <md-table-cell>01/01/1901</md-table-cell>
                                    <md-table-cell>R$ 1,99</md-table-cell>
                                    <md-table-cell><font color="red">Finalizado e não entregue</font></md-table-cell>
                                    <md-table-cell>Endereço de entrega incorreto</md-table-cell>
                                </md-table-row>
                                <md-table-row v-for="(row, index) in 3" :key="index">
                                    <md-table-cell>{{(Math.random() * 1000000).toFixed()}}</md-table-cell>
                                    <md-table-cell>01/01/1901</md-table-cell>
                                    <md-table-cell>R$ 1,99</md-table-cell>
                                    <md-table-cell>Finalizado e entregue</md-table-cell>
                                    <md-table-cell>-</md-table-cell>
                                </md-table-row>
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