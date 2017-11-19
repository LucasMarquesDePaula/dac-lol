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
        <st:css res="view/${basePath}/grid.css"/>
    </head>
    <body>
        <%@ include file="../../include/layout.jsp" %>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="90">
                    <md-table-card>
                        <form>
                            <div class="field-group">
                                <md-layout :md-gutter="true">
                                    <md-layout md-flex="30">
                                        <md-input-container>
                                            <label for="select">Opções de Visualização</label>
                                            <md-select v-model="tipoFiltro">
                                                <md-option value="pda">Pedidos Dia Atual</md-option>
                                                <md-option value="ppp">Pedidos por Período</md-option>
                                                <md-option value="pnr">Pedidos Não Resolvidos</md-option>
                                            </md-select>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout v-if="tipoFiltro === 'ppp'" md-flex="20">
                                        <md-input-container>
                                            <label>Data Inicial</label>
                                            <md-input type="date" name="dataInicial" value=""></md-input>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout v-if="tipoFiltro === 'ppp'" md-flex="20">
                                        <md-input-container>
                                            <label>Data Final</label>
                                            <md-input type="date" name="dataFinal"></md-input>
                                        </md-input-container>
                                    </md-layout>

                                    <md-layout v-if="tipoFiltro === 'pda'" md-flex="30">
                                        <div class="hidden">
                                            <jsp:useBean id="now" class="java.util.Date" />
                                            <input name="dataInicial" value="<fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />" />
                                            <input name="dataFinal" value="<fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />" />
                                        </div>
                                    </md-layout>    

                                    <md-layout md-flex="10">
                                        <md-toolbar>
                                            <md-button type="submit" class="md-icon-button">
                                                <md-icon>search</md-icon>
                                            </md-button>
                                        </md-toolbar>
                                    </md-layout>
                                </md-layout>
                            </div>
                        </form>

                        <md-table @sort="onSort" md-sort="${param.sortField}" md-sort-type="${param.sortDirection}">
                            <md-table-header>
                                <md-table-row>
                                    <md-table-head md-sort-by="id">Cód.</md-table-head>
                                    <md-table-head md-sort-by="cliente">Nome Cliente</md-table-head>
                                    <md-table-head md-sort-by="dataHoraCadastro">Data/Hora Cadastro</md-table-head>
                                    <md-table-head md-sort-by="enderecoEntrega">Endereço Entrega</md-table-head>
                                    <md-table-head md-sort-by="observacaoCliente">Obs. Cliente</md-table-head>
                                    <md-table-head md-sort-by="observacaoInterna">Obs. Interna</md-table-head>
                                    <md-table-head md-sort-by="dataHoraPrazo">Data/Hora Prazo</md-table-head>
                                    <md-table-head md-sort-by="realizado">Realizado?</md-table-head>
                                    <md-table-head md-sort-by="dataHoraRealizacao">Data/Hora Realização</md-table-head>
                                    <md-table-head md-sort-by="funcionarioRealizacao">Cód. Funcionário</md-table-head>
                                    <md-table-head md-sort-by="cancelado">Cancelado?</md-table-head>
                                    <md-table-head md-sort-by="dataHoraCancelamento">Data/Hora Cancelamento</md-table-head>
                                    <md-table-head md-sort-by="orcamentoConfirmado">Orçado?</md-table-head>
                                    <md-table-head md-sort-by="dataHoraConfirmacaoOrcamento">Data/Hora Orçamento</md-table-head>
                                    <md-table-head md-sort-by="pago">Pago?</md-table-head>
                                    <md-table-head md-sort-by="dataHoraPagamento">Data/Hora Pagamento</md-table-head>
                                    <md-table-head md-sort-by="funcionarioPagamento">Cód. Func. Pagamento</md-table-head>
                                    <md-table-head md-sort-by="recebido">Recebido?</md-table-head>
                                    <md-table-head md-sort-by="dataHoraRecebimento">Data/Hora Recebimento</md-table-head>
                                    <md-table-head md-sort-by="entregue">Entregue?</md-table-head>
                                    <md-table-head md-sort-by="entregaFrustrada">Entrega Frustrada?</md-table-head>
                                    <md-table-head md-sort-by="entregaFrustradaJustificativa">Justificativa</md-table-head>
                                </md-table-row>
                            </md-table-header>
                            <md-table-body>
                                <c:forEach var="item" items="${queryResult.list}">
                                    <md-table-row>
                                        <md-table-cell><c:out value="${item.id}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.cliente.nome}"/></md-table-cell>
                                        <md-table-cell><fmt:formatDate type="both" value="${item.dataHoraCadastro}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.enderecoEntrega}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.observacaoCliente}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.observacaoInterna}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.dataHoraPrazo}"/></md-table-cell>
                                        <md-table-cell><c:out value="${(item.realizado==1) ? 'Sim' : 'Não'}"/></md-table-cell>
                                        <md-table-cell><fmt:formatDate type="both" value="${item.dataHoraRealizacao}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.funcionarioRealizacao}"/></md-table-cell>
                                        <md-table-cell><c:out value="${(item.cancelado==1) ? 'Sim' : 'Não'}"/></md-table-cell>
                                        <md-table-cell><fmt:formatDate type="both" value="${item.dataHoraCancelamento}"/></md-table-cell>
                                        <md-table-cell><c:out value="${(item.orcamentoConfirmado==1) ? 'Sim' : 'Não'}"/></md-table-cell>
                                        <md-table-cell><fmt:formatDate type="both" value="${item.dataHoraConfirmacaoOrcamento}"/></md-table-cell>
                                        <md-table-cell><c:out value="${(item.pago==1) ? 'Sim' : 'Não'}"/></md-table-cell>
                                        <md-table-cell><fmt:formatDate type="both" value="${item.dataHoraPagamento}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.funcionarioPagamento}"/></md-table-cell>
                                        <md-table-cell><c:out value="${(item.recebido==1) ? 'Sim' : 'Não'}"/></md-table-cell>
                                        <md-table-cell><fmt:formatDate type="both" value="${item.dataHoraRecebimento}"/></md-table-cell>
                                        <md-table-cell><c:out value="${(item.entregue==1) ? 'Sim' : 'Não'}"/></md-table-cell>
                                        <md-table-cell><c:out value="${(item.entregaFrustrada==1) ? 'Sim' : 'Não'}"/></md-table-cell>
                                        <md-table-cell><c:out value="${item.entregaFrustradaJustificativa}"/></md-table-cell>
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
                            <span>${offset + 1} - ${offset + limit > queryResult.count ? queryResult.count : offset + limit} de ${queryResult.count}</span>
                            <md-button class="md-icon-button" @click="prevPage(${limit}, ${offset}, ${queryResult.count})"  :disabled="${offset <= 0}">
                                <md-icon>keyboard_arrow_left</md-icon>
                            </md-button>
                            <md-button class="md-icon-button" @click="nextPage(${limit}, ${offset}, ${queryResult.count})" :disabled="${offset + limit > queryResult.count}">
                                <md-icon>keyboard_arrow_right</md-icon>
                            </md-button>
                        </div>
                        <md-card-actions>
                            <md-button href='${contextPath}/${basePath}/form' class="md-fab" target="_blank">
                                <md-icon>add</md-icon>
                            </md-button>
                        </md-card-actions>
                    </md-table-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="view/${basePath}/grid.js"/>
    </body>
</html>