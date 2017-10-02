<%-- 
    Document   : form
    Created on : 23/09/2017, 19:51:12
    Author     : Lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="/WEB-INF/static.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="${app.name}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${title}" /></title>
<jsp:include page="../../include/vue-material.jsp"/>
<st:css res="view/index/index.css" />
</head>
<body>
	<jsp:include page="../../include/layout.jsp">
		<jsp:param name="title" value="${title}" />
	</jsp:include>
	<div id="app"></div>
	<st:js res="view/index/index.js" />
</body>
</html>

