<%-- 
    Document   : form-actions
    Created on : 16/11/2017, 23:57:10
    Author     : Lucas
--%>

<c:if test="${!empty model.id && model.cancelado == 0 && sessionScope.Authenticable == br.ufpr.tads.dac.lol.filter.Role.FUNCIONARIO}">
    <md-card-actions>
        <c:if test="${model.realizado == 0 && empty model.entregaId}">
            <form method="POST" action='${contextPath}/${basePath}/post-delivery/${model.id}'>
                <md-button type="submit" class="md-raised md-primary">
                    Gerar Entrega
                </md-button>
            </form>
        </c:if>

        <c:if test="${model.orcamentoConfirmado == 1 && model.recebido == 0}">
            <form method="POST" action='${contextPath}/${basePath}/confirm-receivement/${model.id}'>
                <md-button type="submit" class="md-raised md-primary">
                    Confirmar Recebimento
                </md-button>
            </form>
        </c:if>

        <c:if test="${model.recebido == 1 && model.realizado == 0}">
            <form method="POST" action='${contextPath}/${basePath}/done/${model.id}'>
                <md-button type="submit" class="md-raised md-primary">
                    Confirmar Realizacao
                </md-button>
            </form>
        </c:if>

        <c:if test="${model.recebido == 0}">
            <form method="POST" action='${contextPath}/${basePath}/cancel/${model.id}'>
                <md-button type="submit" class="md-raised md-primary">
                    Cancelar
                </md-button>
            </form>
        </c:if>
    </md-card-actions>
</c:if>