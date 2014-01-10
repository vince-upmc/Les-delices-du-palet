<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.delices.datastore.contents.Match"%>
<%@page import="com.delices.datastore.contents.Team"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<script src="/scripts/matchDisplayer.js"></script>
<script src="/scripts/pari.js"></script>
<title>Les Délices du Palet - Détails de match</title>
</head>
<body onload="fetch_match('<%=request.getParameter("match-id")%>')">
	<%@include file="WEB-INF/templates/menu.jsp"%>

	<div id="main-content">
		<div class="left-panel">

			<%
				DateFormat format = new SimpleDateFormat("dd MMMM yyyy\nhh:mm");
				Match m = pm.getObjectById(Match.class,
						request.getParameter("match-id"));
				Team home = pm.getObjectById(Team.class, m.getHome());
				Team away = pm.getObjectById(Team.class, m.getAway());

				if (m.getStatus().equals("closed")) {
			%>
			<a target="_blank"
				href="http://www.nhl.com/ice/search.htm?q=<%=home.getName() + "+" + away.getName()%>&tab=video">Rechercher
				les résumés du match sur NHL.com</a>
			<%
				}
			%>

			<table class="match-panel">
				<tr class="match-header">
					<th><a class="home-name"
						href="/team.jsp?team-id=<%=home.getId()%>"></a></th>
					<th id="score-time"></th>
					<th><a class="away-name"
						href="/team.jsp?team-id=<%=away.getId()%>"></a></th>
				</tr>
				<tr>
					<td id="home-market"></td>
					<td class="match-category">Ville</td>
					<td id="away-market"></td>
				</tr>
				<tr>
					<td id="home-conference"></td>
					<td class="match-category">Conférence</td>
					<td id="away-conference"></td>
				</tr>
				<tr>
					<td id="home-games_played"></td>
					<td class="match-category">Matchs joués</td>
					<td id="away-games_played"></td>
				</tr>
				<tr>
					<td id="home-wins"></td>
					<td class="match-category">Victoires</td>
					<td id="away-wins"></td>
				</tr>
				<tr>
					<td id="home-losses"></td>
					<td class="match-category">Défaites</td>
					<td id="away-losses"></td>
				</tr>
				<tr>
					<td id="home-points"></td>
					<td class="match-category">Points</td>
					<td id="away-points"></td>
				</tr>
				<tr>
					<td id="home-win_pct"></td>
					<td class="match-category">% de victoire</td>
					<td id="away-win_pct"></td>
				</tr>
				<tr>
					<td id="home-goals_for"></td>
					<td class="match-category">Total de buts</td>
					<td id="away-goals_for"></td>
				</tr>
				<tr>
					<td id="home-goals_against"></td>
					<td class="match-category">Total de buts encaissés</td>
					<td id="away-goals_against"></td>
				</tr>
				<tr>
					<td id="home-goals_diff"></td>
					<td class="match-category">Différences de but</td>
					<td id="away-goals_diff"></td>
				</tr>
				<%
					if (Calendar.getInstance().getTime().compareTo(m.getStartingTime()) < 0) {
				%>

				<tr>
					<td colspan=3>

						<button id="pari_button" onclick="fetch_pari()">Afficher
							les paris</button>
					</td>
				</tr>
				<%
					}
				%>
			</table>

			<div id="pari_box" style="visibility: hidden; padding-bottom: 50px;">

				<input type="radio" id="bet0" name="type_pari" value="victoire_home"
					checked /> Parier sur la victoire de <span class="home-name"></span><br />
				<input type="radio" id="bet1" name="type_pari" value="victoire_away" />
				Parier sur la victoire de <span class="away-name"></span><br /> <input
					type="radio" id="bet2" name="type_pari" value="match_nul" />
				Parier sur un match nul<br /> <input type="radio" id="bet3"
					name="type_pari" value="victoire_home_ecart" /> Parier sur la
				victoire de <span class="home-name"></span> avec un écart de <select
					id="ecart_home" name="ecart"><option value="1-3">1
						à 3 points</option>
					<option value="4-7">4 à 7 points</option>
					<option value="8+">8 points ou plus</option></select><br /> <input
					type="radio" id="bet4" name="type_pari" value="victoire_away_ecart" />
				Parier sur la victoire de <span class="away-name"></span> avec un
				écart de <select id="ecart_away" name="ecart"><option
						value="1-3">1 à 3 points</option>
					<option value="4-7">4 à 7 points</option>
					<option value="8+">8 points ou plus</option></select><br /> <br /> Mise: <input
					type="text" id="mise" name="mise" value="1" />

				<%
					try {
						user.getUserId();
				%>
				<input type="button" value="Valider le pari"
					onclick="parier('<%=request.getParameter("match-id")%>', <%=user.getUserId()%>)" />
				<%
					} catch (Exception e) {
				%>
				<input type="button" value="Valider le pari"
					onclick="parier('<%=request.getParameter("match-id")%>', -1)" />
				<%
					}
				%>


			</div>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>