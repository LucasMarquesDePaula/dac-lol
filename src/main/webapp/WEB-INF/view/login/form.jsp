<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="../../include/vue-material.jsp" />
        <st:css res="page/login/form.css"/>
    </head>
    <body>
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="33">
                    <md-card>
                        <md-card-header>
                            <div class="md-title">
                                Login
                            </div>
                        </md-card-header>
                        <md-card-content>
                            <form>
                                <md-input-container>
                                    <label>Usuário</label>
                                    <md-input type="text" name="usuario"></md-input>
                                </md-input-container>
                                <md-input-container>
                                    <label>Senha</label>
                                    <md-input type="password" name="senha"></md-input>
                                </md-input-container>
                                <md-card-actions>
                                    <md-button class="md-raised md-primary bt-align">Logar</md-button>
                                </md-card-actions>
                            </form>
                        </md-card-content>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <st:js res="page/login/form.js"/>
    </body>
</html>