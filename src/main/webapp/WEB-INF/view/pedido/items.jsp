<%-- 
    Document   : form
    Created on : 23/09/2017, 19:51:12
    Author     : Lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="Itens do Pedido"/>

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
                                <md-layout md-flex="10">
                                    <md-input-container>
                                        <label>ID Pedido</label>
                                        <md-input value="${model.id}" readonly></md-input>
                                    </md-input-container>  
                                </md-layout>
                                <md-layout md-flex="90">
                                    <md-input-container>
                                        <label>Endereço</label>
                                        <md-input value="${model.enderecoEntrega}" readonly></md-input>
                                    </md-input-container>  
                                </md-layout> 
                            </md-layout>
                            <md-layout :md-gutter="true">
                                <md-layout md-flex="100">
                                    <md-input-container>
                                        <label>Cliente</label>
                                        <md-input value="${model.cliente.nome}" readonly></md-input>
                                    </md-input-container>  
                                </md-layout>
                            </md-layout>
                        </form>

                        <md-toolbar>
                            <h1 class="md-title">Roupas adicionadas</h1>
                        </md-toolbar>

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

