<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<script src="/scripts/matchDisplayer.js"></script>
<title>Les Délices du Palet - Détails de match</title>
<style>
dt {
	margin:2px;
	float: left;
	clear: left;
	width: 8em;
}

dd {
	float: left;
	margin:2px;
}

</style>
</head>
<body onload="fetch_match('<%=request.getParameter("match-id")%>')">
	<%@include file="WEB-INF/templates/menu.jsp"%>

	<div id="main-content">
		<div class="left-panel">
			<dl>
				<dt>Début du match</dt>
				<dd id="air-time"></dd>

				<dt>Status</dt>
				<dd id="status"></dd>
			</dl>
			<div class="clear"></div>
			<div id="home">
				<h4 id="home-name"></h4>
				<dl>
					<dt>Conférence</dt>
					<dd id="home-conference"></dd>

					<dt>Ville</dt>
					<dd id="home-market"></dd>

					<dt>games_played</dt>
					<dd id="home-games_played"></dd>

					<dt>wins</dt>
					<dd id="home-wins"></dd>

					<dt>losses</dt>
					<dd id="home-losses"></dd>

					<dt>points</dt>
					<dd id="home-points"></dd>

					<dt>win_pct</dt>
					<dd id="home-win_pct"></dd>

					<dt>goals_for</dt>
					<dd id="home-goals_for"></dd>

					<dt>goals_against</dt>
					<dd id="home-goals_against"></dd>

					<dt>goals_diff</dt>
					<dd id="home-goals_diff"></dd>
				</dl>
			</div>
			<div class="clear"></div>
			<div id="away">
				<h4 id="away-name"></h4>

				<dl>
					<dt>Conférence</dt>
					<dd id="away-conference"></dd>

					<dt>Ville</dt>
					<dd id="away-market"></dd>


					<dt>games_played</dt>
					<dd id="away-games_played"></dd>

					<dt>wins</dt>
					<dd id="away-wins"></dd>

					<dt>losses</dt>
					<dd id="away-losses"></dd>

					<dt>points</dt>
					<dd id="away-points"></dd>

					<dt>win_pct</dt>
					<dd id="away-win_pct"></dd>

					<dt>goals_for</dt>
					<dd id="away-goals_for"></dd>

					<dt>goals_against</dt>
					<dd id="away-goals_against"></dd>

					<dt>goals_diff</dt>
					<dd id="away-goals_diff"></dd>
				</dl>
			</div>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>