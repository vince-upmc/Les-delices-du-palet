<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<link rel="stylesheet" href="/styles/form.css" type="text/css">
<title>Les Délices du Palet - Test</title>
</head>
<body>
	<%@include file="WEB-INF/templates/menu.jsp"%>
	<div id="main-content">
		<div class="left-panel">
			<h1>Test</h1>
			<form method="GET" action="/testdisplay">
				<input type="submit" value="display" />
			</form>

			<form method="GET" action="/testupdate">
				<input type="submit" value="update" />
			</form>

			<form method="GET" action="/testpari">
				<legend>Test de création de pari</legend>

				<label for="matchid">Match id</label> <input type="text"
					id="matchid" name="matchid" value="c488998b-bc50-4d70-8f14-d0b5b1e7dc2a" /> 
					
				<label for="mise">Mise</label> 
				<select	name="mise" id="mise">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select>

				<input class="unlabeled" type="submit" value="Creer le pari" />

			</form>

		</div>

		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>