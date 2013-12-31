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
td {
	text-align: center;
	padding: 2px;
}

dt {
	clear: both;
	float: left;
	width: 120px;
}

dd {
	float: left;
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
			<table>
				<tr>
					<th><h4 id="home-name"></h4></th>
					<th></th>
					<th><h4 id="away-name"></h4></th>
				</tr>
				<tr>
					<td id="home-conference"></td>
					<td>Conférence</td>
					<td id="away-conference"></td>
				</tr>
				<tr>
					<td id="home-market"></td>
					<td>Ville</td>
					<td id="away-market"></td>
				</tr>
				<tr>
					<td id="home-games_played"></td>
					<td>Matchs joués</td>
					<td id="away-games_played"></td>
				</tr>
				<tr>
					<td id="home-wins"></td>
					<td>Victoires</td>
					<td id="away-wins"></td>
				</tr>
				<tr>
					<td id="home-losses"></td>
					<td>Défaites</td>
					<td id="away-losses"></td>
				</tr>
				<tr>
					<td id="home-points"></td>
					<td>Points</td>
					<td id="away-points"></td>
				</tr>
				<tr>
					<td id="home-win_pct"></td>
					<td>% de victoire</td>
					<td id="away-win_pct"></td>
				</tr>
				<tr>
					<td id="home-goals_for"></td>
					<td>Total de buts</td>
					<td id="away-goals_for"></td>
				</tr>
				<tr>
					<td id="home-goals_against"></td>
					<td>Total de buts encaissés</td>
					<td id="away-goals_against"></td>
				</tr>
				<tr>
					<td id="home-goals_diff"></td>
					<td>Goals_diff (?)</td>
					<td id="away-goals_diff"></td>
				</tr>
				<tr>
					<td><button onclick="alert('t\'y a cru')">Parier</button></td>
					<td>Pari</td>
					<td><button onclick="alert('t\'y a cru')">Parier</button></td>

				</tr>
			</table>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>