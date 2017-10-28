<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="Funcionarios"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="view/${basePath}/grid.css"/>
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
                            <md-layout :md-gutter="true">
                                <md-layout md-flex="10">
                                    <md-input-container>
                                        <label>Código</label>
                                        <md-input name="id" value="${param.id}"></md-input>
                                    </md-input-container>
                                </md-layout>
                                <md-layout md-flex="30">
                                    <md-input-container>
                                        <label>Nome</label>
                                        <md-input name="nome" value="${param.nome}"></md-input>
                                    </md-input-container>
                                </md-layout>
                                <md-layout md-flex="20">
                                    <md-input-container>
                                        <label>Data Nascimento</label>
                                        <md-input name="dataNascimento" type="date" value="<fmt:formatDate value="${param.dataNascimento}" pattern="yyyy-MM-dd"/>"></md-input>
                                    </md-input-container>
                                </md-layout>
                                <md-layout md-flex="20">
                                    <md-input-container>
                                        <label>E-mail</label>
                                        <md-input name="email" value="${param.email}"></md-input>
                                    </md-input-container>
                                </md-layout>
                                <md-layout md-flex="10">
                                    <md-toolbar>
                                        <md-button type="submit" class="md-icon-button">
                                            <md-icon>search</md-icon>
                                        </md-button>
                                    </md-toolbar>
                                </md-layout>
                            </md-layout>
                        </form>
                        <md-table @sort="onSort" md-sort="${param.sortField}" md-sort-type="${param.sortDirection}">
                            <md-table-header>
                                <md-table-row>
                                    <md-table-head></md-table-head>
                                    <md-table-head md-sort-by="id">Cód.</md-table-head>
                                    <md-table-head md-sort-by="nome">Nome</md-table-head>
                                    <md-table-head md-sort-by="dataNascimento">Data Nascimento</md-table-head>
                                    <md-table-head md-sort-by="email">E-mail</md-table-head>                                    
                                </md-table-row>
                            </md-table-header>
                            <md-table-body>
                                <c:forEach var="item" items="${queryResult.list}">
                                    <md-table-row>
                                        <md-table-cell>
                                            <md-avatar>
                                                <img src="${item.foto}" alt="Foto">
                                            </md-avatar>
                                        </md-table-cell>
                                        <md-table-cell><c:out value="${item.id}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.nome}"/></md-table-cell>
                                        <md-table-cell><fmt:formatDate value="${item.dataNascimento}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.email}"/></md-table-cell>
                                        <md-table-cell>
                                            <md-button class="md-icon-button" href="${contextPath}/${basePath}/form/${item.id}" target="_blank">
                                                <md-icon>edit</md-icon>
                                            </md-button>
                                        </md-table-cell>
                                    </md-table-row>
                                </c:forEach>
                            </md-table-body>
                        </md-table>
                        <div class="md-table-pagination">
                            <span class="md-table-pagination-label">Nº de Linhas: </span>
                            <span>${offset} - ${offset + limit > queryResult.count ? queryResult.count : offset + limit} de ${queryResult.count}</span>
                            <md-button class="md-icon-button" @click="prevPage(${limit}, ${offset}, ${queryResult.count})"  :disabled="${offset <= 0}">
                                <md-icon>keyboard_arrow_left</md-icon>
                            </md-button>
                            <md-button class="md-icon-button" @click="nextPage(${limit}, ${offset}, ${queryResult.count})" :disabled="${offset + limit > queryResult.count}">
                                <md-icon>keyboard_arrow_right</md-icon>
                            </md-button>
                        </div>
                    </md-table-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="view/${basePath}/grid.js"/>
    </body>
</html>