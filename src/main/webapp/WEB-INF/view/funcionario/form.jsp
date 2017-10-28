<%-- 
    Document   : form
    Created on : 30/09/2017, 21:10:44
    Author     : Lucas
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="/WEB-INF/static.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="Funcionario" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><c:out value="${title}" /></title>
        <jsp:include page="../../include/vue-material.jsp"/>
        <st:css res="view/cliente/form.css" />
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}" />
        </jsp:include>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="66">
                    <c:if test="${!empty message}">
                        <md-card class="${messages == null || messages.length == 0 ? 'md-primary' : 'md-warn'}">
                            <md-card-header>
                                <div class="md-title">${message}</div>
                            </md-card-header>
                        </md-card>
                    </c:if>
                    <md-card>
                        <form method="POST" action='${contextPath}/funcionario/${empty model.id ? "create" : "update"}/${model.id}' accept-charset="ISO-8859-1">
                            <md-card-content>
                                <md-layout :md-row="true">
                                    <md-layout :md-column="true" :md-flex="25">
                                        <div class="avatar-container">
                                            <div class="avatar-overlay" @click="fotoClicked()"><md-icon class="md-size-2x">edit</md-icon></div>
                                            <div class="avatar-image" :style="{ 'background-image': 'url(' + foto + ')' }"></div>
                                        </div>
                                    </md-layout>
                                    <md-layout :md-column="true" :md-flex="75">
                                        <md-layout :md-gutter="true">
                                            <md-layout md-flex="30">
                                                <md-input-container>
                                                    <label>Código</label>
                                                    <md-input :readonly="true" name="id" type="number" value="${model.id}"></md-input>
                                                </md-input-container>
                                            </md-layout>
                                            <md-layout>
                                                <md-input-container class="${empty messages.nome ? '' : 'md-input-invalid'}">
                                                    <label>Nome</label>
                                                    <md-input name="nome" :required="true" value="${model.nome}"></md-input>
                                                    <span class="md-error"><c:out value="${messages.nome}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                        </md-layout>
                                        <md-layout :md-gutter="true">
                                            <md-layout>
                                                <md-input-container class="${empty messages.email ? '' : 'md-input-invalid'}">
                                                    <md-icon>mail</md-icon>
                                                    <label>Email</label>
                                                    <md-input name="email" :required="true" value="${model.email}"></md-input>
                                                    <span class="md-error"><c:out value="${messages.email}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                            <md-layout>
                                                <md-input-container class="${empty messages.dataNascimento ? '' : 'md-input-invalid'}">
                                                    <label>Data Nascimento</label>
                                                    <md-input name="dataNascimento" type="date" :required="true" value="<fmt:formatDate value="${model.dataNascimento}" pattern="yyyy-MM-dd"/>"></md-input>
                                                    <span class="md-error"><c:out value="${messages.dataNascimento}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                        </md-layout>
                                        <md-layout :md-gutter="true">
                                            <md-layout>
                                                <md-input-container md-has-password class="${empty messages.senha ? '' : 'md-input-invalid'}">
                                                    <md-icon>lock</md-icon>
                                                    <label>Senha</label>
                                                    <md-input name="senha"></md-input>
                                                    <span class="md-error"><c:out value="${messages.senha}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                            <md-layout>
                                                <md-input-container md-has-password class="${empty messages.confirmarsenha ? '' : 'md-input-invalid'}">
                                                    <md-icon>lock</md-icon>
                                                    <label>Confirmar Senha</label>
                                                    <md-input name="confirmarsenha"></md-input>
                                                    <span class="md-error"><c:out value="${messages.confirmarsenha}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                        </md-layout>
                                    </md-layout>
                                </md-layout>

                                <div class="hidden">
                                    <input name="ativo" type="hidden" value="1"/>/
                                    <input name="foto" type="hidden" value="${model.foto}"/>
                                </div>

                                <md-card-actions>
                                    <md-button type="submit" class="md-raised md-primary bt-align">Salvar</md-button>	
                                </md-card-actions>
                            </md-card-content>
                        </form>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="vue-the-mask/vue-the-mask.js"/>
        <st:js res="view/cliente/form.js"/>
    </body>
</html>
