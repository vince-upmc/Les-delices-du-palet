<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<title>Les Délices du Palet - Accueil</title>
</head>
<body>
	<%@include file="WEB-INF/templates/menu.jsp"%>
	<div id="main-content">
		<div class="left-panel">
			<h1>Accueil</h1>
			Le site pour faire des paris de beau-gosse.
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>