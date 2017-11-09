<%-- 
    Document   : form
    Created on : 23/09/2017, 19:51:12
    Author     : Lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="Pedido Itens"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="view/${basePath}/form.css"/>
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}" />
        </jsp:include>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="66">
                    <md-table-card>
                        <form>
                            <md-layout :md-gutter="true">
                                <md-layout md-flex="20">
                                    <md-input-container>
                                        <label>Pedido</label>
                                        <md-input value="${model.id}" readonly></md-input>
                                    </md-input-container>  
                                </md-layout>
                                <md-layout md-flex="20">
                                    <md-input-container>
                                        <label>Endereço</label>
                                        <md-input value="${model.enderecoEntrega}" readonly></md-input>
                                    </md-input-container>  
                                </md-layout> 
                            </md-layout>
                            <md-layout :md-gutter="true">
                                <md-layout md-flex="33">
                                    <md-input-container>
                                        <label>Cliente</label>
                                        <md-input value="${model.cliente.nome}" readonly></md-input>
                                    </md-input-container>  
                                </md-layout>
                            </md-layout>
                            <md-layout :md-gutter="true">
                                <md-layout>
                                    <md-input-container>
                                        <label>Tipo de Roupa</label>
                                        <md-select>
                                            <md-option value="camiseta">Camiseta</md-option>
                                            <md-option value="jaqueta">Jaqueta</md-option>
                                            <md-option value="moletom">Moletom</md-option>
                                        </md-select>
                                    </md-input-container>
                                </md-layout>
                                <md-layout>
                                    <md-input-container>
                                        <label>Quatidade</label>
                                        <md-input type="number"></md-input>
                                    </md-input-container>
                                </md-layout>
                            </md-layout>
                            <md-button class="md-add md-fab">
                                <md-icon>add</md-icon>
                            </md-button>
                        </form>

                        <md-toolbar>
                            <h1 class="md-title">Roupas adicionadas</h1>
                        </md-toolbar>

                        <md-table md-sort="name" md-sort-type="desc">
                            <md-table-header>
                                <md-table-row>
                                    <md-table-head md-sort-by="roupa">Tipo de Roupa</md-table-head>
                                    <md-table-head md-sort-by="qtde">Quantidade</md-table-head>
                                    <md-table-head md-sort-by="prazo">Prazo de Lavagem</md-table-head>
                                    <md-table-head md-sort-by="preco">Preço</md-table-head>
                                </md-table-row>
                            </md-table-header>

                            <md-table-body>
                                <md-table-row v-for="(row, index) in 2" :key="index">
                                    <md-table-cell>Camiseta</md-table-cell>
                                    <md-table-cell>5</md-table-cell>
                                    <md-table-cell>01/01/1901</md-table-cell>
                                    <md-table-cell>R$ 1,99</md-table-cell>
                                </md-table-row>
                            </md-table-body>
                        </md-table>
                        <md-card-actions>
                            <md-button class="md-raised md-primary">Confirmar Pedido</md-button>
                            <md-button class="md-raised md-primary">Cancelar Pedido</md-button>
                        </md-card-actions>
                    </md-table-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="view/${basePath}/form.js"/>
    </body>
</html>

