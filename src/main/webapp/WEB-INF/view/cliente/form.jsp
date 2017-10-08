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
        <st:css res="view/cliente/form.css" />
    </head>
    <body>
        <jsp:include page="../../include/layout.jsp">
            <jsp:param name="title" value="${title}" />
        </jsp:include>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="66">
                    <md-card>
                        <form method="POST" action='${contextPath}/cliente/${model.id == null ? "create" : "update"}/${model.id}' enctype="multipart/form-data" accept-charset="ISO-8859-1">
                            <md-card-content>
                                <md-layout :md-gutter="true">
                                    <md-layout md-flex="33">
                                        <div class="avatar-container">
                                            <div class="avatar-overlay" @click="fotoClicked()"><md-icon class="md-size-2x">edit</md-icon></div>
                                            <div v-if="fotoSrc" class="avatar-image" :style="{ 'background-image': 'url(' + fotoSrc + ')' }"></div>
                                            <div v-else class="avatar-image" style="background-image: url(${contextPath}/cliente/foto/${model.id});"></div>
                                        </div>
                                    </md-layout>
                                    <md-layout md-flex="66">
                                        <md-input-container>
                                            <label>Código</label>
                                            <md-input name="id" value="${model.id}"></md-input>
                                        </md-input-container>

                                        <md-input-container>
                                            <label>Nome</label>
                                            <md-input name="nome" value="${model.nome}"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>
                                <md-layout :md-gutter="true">
                                    <md-layout>
                                        <md-input-container>
                                            <md-icon>fingerprint</md-icon>
                                            <label>CPF</label>
                                            <md-input name="cpf" v-mask="'###.###.###-##'" value="${model.cpf}"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout>
                                        <md-input-container>
                                            <label>Sexo</label>
                                            <md-select name="sexo" value="${model.sexo}">
                                                <md-option value="F">Feminino</md-option>
                                                <md-option value="M">Masculino</md-option>
                                                <md-option value="O">Não declarar</md-option>
                                            </md-select>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>

                                <md-input-container>
                                    <md-icon>phone</md-icon>
                                    <label>Telefone</label>
                                    <md-input name="telefone" v-mask="['(##) ####-####', '(##) #####-####']"></md-input>
                                </md-input-container>

                                <md-layout :md-gutter="true">
                                    <md-layout>
                                        <md-input-container>
                                            <md-icon>mail</md-icon>
                                            <label>Email</label>
                                            <md-input name="email" value="${model.email}"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout>
                                        <md-input-container md-has-password>
                                            <md-icon>lock</md-icon>
                                            <label>Senha</label>
                                            <md-input name="senha"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                    <md-layout>
                                        <md-input-container md-has-password>
                                            <md-icon>lock</md-icon>
                                            <label>Confirmar Senha</label>
                                            <md-input name="confirmarsenha"></md-input>
                                        </md-input-container>
                                    </md-layout>
                                </md-layout>

                                <md-input-container>
                                    <label>Endereço</label>
                                    <md-input name="endereco" value="${model.endereco}"></md-input>
                                </md-input-container>
                                <div class="hidden">
                                    <md-input-container>
                                        <md-input name="ativo" type="hidden" value="1"></md-input>
                                    </md-input-container>
                                    <md-input-container>
                                        <md-file name="foto" type="file" v-model="foto" accept="image/x-png,image/jpeg"></md-file>
                                    </md-input-container>
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
