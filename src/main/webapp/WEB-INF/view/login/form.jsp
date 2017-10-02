<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="/WEB-INF/static.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<jsp:include page="../../include/vue-material.jsp"/>
<st:css res="view/login/form.css" />
</head>
<body>
	<div id="app">
		<md-layout md-align="center">
			<md-layout md-flex="33">
				<md-card>
					<md-card-header>
						<div class="md-title">Login</div>
					</md-card-header>
					<md-card-content>
						<form method="POST" action="${context}/login">
							<md-input-container>
								<md-icon class="md-warn">
									person 
									<md-tooltip>Usuário</md-tooltip>
								</md-icon>
								<label>Usuário</label>
								<md-input type="text" name="username"></md-input>
							</md-input-container>
							<md-input-container>
								<md-icon class="md-warn">
									lock 
									<md-tooltip>Senha</md-tooltip>
								</md-icon>
								<label>Senha</label>
								<md-input type="password" name="password"></md-input>
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