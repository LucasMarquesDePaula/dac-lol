<%-- 
    Document   : sidenav-left
    Created on : 23/09/2017, 21:22:08
    Author     : Lucas
--%>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>

<st:css res="include/loading-overlay/loading-overlay.css" />
<div id="loading-overlay" :class="{ hidden: hidden }">Carregando...&#8230;</div>
<st:js res="include/loading-overlay/loading-overlay.js" />