<%-- 
    Document   : toolbar
    Created on : 23/09/2017, 21:03:09
    Author     : Lucas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>
<div id="toolbar">
    <md-toolbar>
        <md-button class="md-icon-button" @click="toggleSidenavLeft">
            <md-icon>menu</md-icon>
        </md-button>
        <h1 class="md-title">
            <c:out value="${param.title != null ? param.title : app.name}"/>
        </h1>
    </md-toolbar>
</div>
<st:js res="include/toolbar/toolbar.js"/>

