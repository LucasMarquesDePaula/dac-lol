<%-- 
    Document   : toolbar
    Created on : 23/09/2017, 21:03:09
    Author     : Lucas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<st:css res="include/toolbar/toolbar.css"/>

<div id="toolbar">
    <md-toolbar>
        <c:if test="${sessionScope.Role != null}">
            <md-button class="md-icon-button" @click="toggleSidenavLeft">
                <md-icon>menu</md-icon>
            </md-button>
        </c:if>
        <h1 class="md-title" style="flex: 1;">
            <c:out value="${param.title != null ? param.title : app.name}"/>
        </h1>
        <c:if test="${sessionScope.Role != null}">
            <md-menu md-direction="bottom left">
                <md-button :md-menu-trigger="true" class="md-icon-button">
                    <md-icon>filter_list</md-icon>
                </md-button>
                <md-menu-content>
                    <md-menu-item>${Authenticable.username}</md-menu-item>
                    <md-menu-item @click="logout()"><a href="#">Logout</a></md-menu-item>
                </md-menu-content>
            </md-menu>
        </c:if>
        <c:if test="${sessionScope.Role == null}">
            <a href="../../lol/login">
                <md-button class="md-icon-button">
                    <md-icon>cancel</md-icon>
                </md-button>
            </a>
        </c:if>
    </md-toolbar>
</div>
<st:js res="include/toolbar/toolbar.js"/>

