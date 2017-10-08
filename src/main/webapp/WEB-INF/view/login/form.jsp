<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="/WEB-INF/static.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="${app.name}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"/></title>
        <jsp:include page="../../include/vue-material.jsp"/>
        <st:css res="view/login/form.css" />
    </head>
    <body>
        <jsp:include page="../../include/loading-overlay.jsp" />
        <div id="app">
            <md-layout md-align="center">
                <md-layout md-flex="33">
                    <md-card>
                        <md-card-header>
                            <div class="md-title"><c:out value="${title}"/></div>
                        </md-card-header>
                        <md-card-content>
                            <div class="md-title"><c:out value="${messages.login}"/></div>
                            <form method="POST" action="login">
                                <md-input-container <c:if test="${messages.username}">class="md-input-invalid"</c:if>>
                                        <md-icon>
                                            person 
                                            <md-tooltip>Usuário</md-tooltip>
                                        </md-icon>
                                        <label>Usuário</label>
                                        <md-input type="text" name="username" required></md-input>
                                        <span class="md-error"><c:out value="${messages.username}"/></span>
                                </md-input-container>
                                <md-input-container <c:if test="${messages.username}">class="md-input-invalid"</c:if>>
                                        <md-icon>
                                            lock 
                                            <md-tooltip>Senha</md-tooltip>
                                        </md-icon>
                                        <label>Senha</label>
                                        <md-input type="password" name="password" required></md-input>
                                        <span class="md-error"><c:out value="${messages.password}"/></span>
                                </md-input-container>
                                <md-card-actions>
                                    <md-button type="submit" class="md-raised md-primary bt-align">Logar</md-button>
                                </md-card-actions>
                            </form>
                        </md-card-content> 
                    </md-card> 
                </md-layout> 
            </md-layout>
        </div>
        <st:js res="view/login/form.js" />
    </body>
</html>