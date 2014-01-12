<%@page import="com.delices.datastore.updaters.GameUpdater"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.delices.datastore.contents.Pari"%>
<%@page import="javax.jdo.Query"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.delices.datastore.contents.Match"%>
<%@page import="com.delices.datastore.contents.Team"%>

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

			<%
				Match m = pm.getObjectById(Match.class,
						"8ad01387-cd94-4c0c-938f-d7d0c552b34b");
				if (request.getParameter("bla") != null){
					pm.currentTransaction().begin();
					new GameUpdater(m).updateContent();
					pm.currentTransaction().commit();
				}
				Team home = pm.getObjectById(Team.class, m.getHome());
				Team away = pm.getObjectById(Team.class, m.getAway());
			%>
			<div><%="status : " + m.getStatus() + " - " + home.getName()
					+ " vs " + away.getName() + " result : "
					+ m.getBoxScore().getHomeScore() + " - "
					+ m.getBoxScore().getAwayScore()%></div>

			<form method="GET" action="/testdisplay">
				<input type="submit" value="display" />
			</form>

			<form method="GET" action="/testupdate">
				<input type="submit" value="update" />
			</form>

			<form method="GET" action="/pari">
				<fieldset>
					<legend>Test de création de pari</legend>

					<label for="matchid">Match id</label> <input type="text"
						id="matchid" name="matchid"
						value="c488998b-bc50-4d70-8f14-d0b5b1e7dc2a" /> <label for="mise">Mise</label>
					<select name="mise" id="mise">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select> <input class="unlabeled" type="submit" value="Creer le pari" />
				</fieldset>
			</form>

		</div>

		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>