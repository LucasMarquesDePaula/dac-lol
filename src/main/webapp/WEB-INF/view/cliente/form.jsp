<%-- 
    Document   : form
    Created on : 30/09/2017, 21:10:44
    Author     : Lucas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="/WEB-INF/static.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="Cliente" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><c:out value="${title}" /></title>
        <jsp:include page="../../include/vue-material.jsp"/>
        <st:css res="view/${basePath}/form.css" />
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
                        <form method="POST" action='${contextPath}/${basePath}/${empty model.id ? "create" : "update"}/${model.id}' accept-charset="ISO-8859-1">
                            <md-card-content>
                                <md-layout>
                                    <md-layout :md-column="true" md-flex="20">
                                        <div class="avatar-container">
                                            <div class="avatar-overlay" @click="fotoClicked()"><md-icon class="md-size-2x">edit</md-icon></div>
                                            <div class="avatar-image" :style="{ 'background-image': 'url(' + foto + ')' }"></div>
                                        </div>
                                    </md-layout>
                                    <md-layout :md-column="true" md-flex="80">
                                        <md-layout :md-gutter="true">
                                            <md-layout md-flex="15">
                                                <md-input-container>
                                                    <label>Código</label>
                                                    <md-input :readonly="true" name="id" type="number" value="${model.id}"></md-input>
                                                </md-input-container>
                                            </md-layout>
                                            <md-layout md-flex="85">
                                                <md-input-container class="${empty messages.nome ? '' : 'md-input-invalid'}">
                                                    <label>Nome</label>
                                                    <md-input name="nome" :required="true" value="${model.nome}"></md-input>
                                                    <span class="md-error"><c:out value="${messages.nome}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                        </md-layout>
                                        <md-layout :md-gutter="true">
                                            <md-layout>
                                                <md-input-container class="${empty messages.cpf ? '' : 'md-input-invalid'}">
                                                    <md-icon>fingerprint</md-icon>
                                                    <label>CPF</label>
                                                    <md-input name="cpf" :required="true" value="${model.cpf}" v-mask="'###.###.###-##'"></md-input>
                                                    <span class="md-error"><c:out value="${messages.cpf}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                            <md-layout>
                                                <md-input-container class="${empty messages.sexo ? '' : 'md-input-invalid'}">
                                                    <label>Sexo</label>
                                                    <md-select name="sexo" :required="true" value="${model.sexo}">
                                                        <md-option value="F">Feminino</md-option>
                                                        <md-option value="M">Masculino</md-option>
                                                        <md-option value="O">Não declarar</md-option>
                                                    </md-select>
                                                    <span class="md-error"><c:out value="${messages.sexo}"/></span>
                                                </md-input-container>
                                            </md-layout>
                                            <md-layout>
                                                <md-input-container class="${empty messages.telefone ? '' : 'md-input-invalid'}">
                                                    <md-icon>phone</md-icon>
                                                    <label>Telefone</label>
                                                    <md-input name="telefone" :required="true" value="${model.telefone}" v-mask="['(##) ####-####', '(##) #####-####']"></md-input>
                                                    <span class="md-error"><c:out value="${messages.telefone}"/></span>
                                                </md-input-container>  
                                            </md-layout>
                                        </md-layout>
                                    </md-layout>
                                </md-layout>
                                <md-layout :md-gutter="true">
                                    <md-layout md-flex="66">
                                        <md-input-container class="${empty messages.endereco ? '' : 'md-input-invalid'}">
                                            <label>Endereço</label>
                                            <md-input name="endereco" :required="true" value="${model.endereco}"></md-input>
                                            <span class="md-error"><c:out value="${messages.endereco}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout md-flex="33">
                                        <md-input-container class="${empty messages.email ? '' : 'md-input-invalid'}">
                                            <md-icon>mail</md-icon>
                                            <label>Email</label>
                                            <md-input name="email" :required="true" value="${model.email}"></md-input>
                                            <span class="md-error"><c:out value="${messages.email}"/></span>
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
                                        <md-input-container md-has-password class="${empty messages.confirmarSenha ? '' : 'md-input-invalid'}">
                                            <md-icon>lock</md-icon>
                                            <label>Confirmar Senha</label>
                                            <md-input name="confirmarSenha"></md-input>
                                            <span class="md-error"><c:out value="${messages.confirmarSenha}"/></span>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>
                                <div class="hidden">
                                    <input name="ativo" type="hidden" value="1"/>/
                                    <input name="foto" type="hidden" value="${model.foto}"/>
                                </div>
                                <md-card-actions>
                                    <md-button type="submit" class="md-fab md-fab-bottom-right">
                                        <md-icon>save</md-icon>
                                    </md-button>
                                </md-card-actions>
                            </md-card-content>

                        </form>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="vue-the-mask/vue-the-mask.js"/>
        <st:js res="view/${basePath}/form.js"/>
    </body>
</html>
