<%-- 
    Document   : form-items
    Created on : 16/11/2017, 23:57:31
    Author     : Lucas
--%>

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
                <c:forEach var="item" items="${model.pedidoTiposRoupa}">
                    <md-table-row>
                        <md-table-cell><c:out value="${item.tipoRoupa.nome}"/></md-table-cell>
                        <md-table-cell><fmt:formatNumber value="${item.tipoRoupa.prazoLavagem}" type="number"/></md-table-cell>
                        <md-table-cell><fmt:formatNumber value="${item.quantidade}" type="number" maxFractionDigits="0"/></md-table-cell>                                          
                        <md-table-cell><fmt:formatNumber value="${item.valorUnitario}" type="currency"/></md-table-cell>
                        <md-table-cell><fmt:formatNumber value="${item.valorUnitario * item.quantidade}" type="currency"/></md-table-cell>
                        <form method="POST" action='${contextPath}/${basePath}/remove-item/${model.id}' accept-charset="ISO-8859-1">
                            <input type="hidden" name="tipoRoupa" value="${item.tipoRoupa.id}" />
                            <md-button class="md-icon-button" type="submit">
                                <md-icon>delete</md-icon>
                            </md-button>
                        </form>
                    </md-table-row>
                </c:forEach>
                <md-table-row>
                    <md-table-cell>Total</md-table-cell>
                    <md-table-cell>Total</md-table-cell>
                    <md-table-cell>Total</md-table-cell>
                    <md-table-cell><fmt:formatNumber value="${model.valorTotal}" type="currency"/></md-table-cell>
                </md-table-row>
            </md-table-body>
        </md-table>
        <md-card-actions>
            <c:if test="${model.cancelado == 0 && model.orcamentoConfirmado == 0}">
                <form method="POST" action='${contextPath}/${basePath}/confirm-order/${model.id}'>
                    <md-button type="submit" class="md-raised md-primary">
                        Confirmar Orçamento
                    </md-button>
                </form>
                <form method="POST" action='${contextPath}/${basePath}/cancel/${model.id}'>
                    <md-button type="submit" class="md-raised md-primary">
                        Cancelar
                    </md-button>
                </form>
            </c:if>
        </md-card-actions>
    </md-table-card>
</c:if>