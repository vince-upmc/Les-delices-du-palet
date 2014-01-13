<%@page import="com.delices.utils.Tools"%>
<%@page import="com.delices.datastore.contents.Pari"%>
<%@page import="javax.jdo.Query"%>
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
<link rel="stylesheet" href="/styles/form.css" type="text/css">
<script src="/scripts/matchDisplayer.js"></script>
<script src="/scripts/pari.js"></script>
<title>Les Délices du Palet - Détails de match</title>
</head>
<body onload="fetch_match('<%=request.getParameter("match-id")%>')">
	<%@include file="WEB-INF/templates/menu.jsp"%>

	<div id="main-content">
		<div class="left-panel">
			<div class="match-content">
				<%
					Match m = pm.getObjectById(Match.class,
							request.getParameter("match-id"));
					Team home = pm.getObjectById(Team.class, m.getHome());
					Team away = pm.getObjectById(Team.class, m.getAway());
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

				</table>

				<%
					if (user == null) {
				%>
				<a id="login_required"
					href="<%=userService.createLoginURL(request.getRequestURI()
						+ "?" + request.getQueryString())%>">Connectez-vous
					pour pouvoir parier</a>
				<%
					} else {
						//Check si l'utilisateur n'a pas déjà un pari d'actif sur ce match
						boolean aPariActif = false;
						Query q = pm.newQuery(Pari.class);
						q.setFilter("user == currentUser");
						q.declareParameters("com.delices.datastore.contents.User currentUser");
						@SuppressWarnings("unchecked")
						List<Pari> paris = (List<Pari>) q.execute(dbuser.getKey());
						Pari pari = null;
						for (Pari p : paris) {
							if (p.getMatch().equals(m.getKey())) {
								pari = p;
								break;
							}

						}
						if (pari != null) {
				%>
				<div>Vous avez un pari d'actif sur ce match.</div>
				<div><%=Tools.betPrettyPrinter(pari)%></div>

				<%
					} else if (dbuser.getCredit() == 0) {
				%>
				<div>Vous n'avez pas assez de crédit pour parier.</div>
				<%
					} else if (Calendar.getInstance().getTime()
								.compareTo(m.getStartingTime()) < 0) {
				%>
				<div id="reponse" style="display: none"></div>
				<form id="bet-form" action="" method="get">
					<fieldset>
						<legend>Pariez sur ce match</legend>

						<label for="team-id">Équipe gagnante</label> <select id="team-id"
							name="team-id" onchange="disableDifference(this.value)">
							<option value="<%=home.getId()%>"><%=home.getName()%></option>
							<option value="<%=away.getId()%>"><%=away.getName()%></option>
							<option value="tie">Match nul</option>
						</select> <label for="difference">Écarts de buts</label> <select
							id="difference" onchange="calculateModifier()" name="difference">
							<option value="none">-</option>
							<option value="oneToThree">1 à 3 points</option>
							<option value="fourToSeven">4 à 7 points</option>
							<option value="eightOrMore">8 points ou plus</option>
						</select> <label for="mise">Mise de départ</label> <select name="mise"
							id="mise" onchange="calculateModifier()">
							<%
								for (int i = 1; i <= dbuser.getCredit() && i <= 5; i++) {
							%>
							<option value="<%=i%>"><%=i%></option>
							<%
								}
							%>
						</select>

						<div>
							Gain potentiel : <span id="modifier"></span><img
								style="position: relative; top: 2px" src="/images/credit.png" />
						</div>


						<input type="hidden" name="match-id" value="<%=m.getId()%>" /> <input
							class="unlabeled" type="submit" value="Pariez!" />
					</fieldset>
				</form>

				<%
					}
					}

					if (m.getStatus().equals("closed")) {
				%>
				<a target="_blank"
					href="http://www.nhl.com/ice/search.htm?q=<%=home.getName() + "+" + away.getName()%>&tab=video">Rechercher
					les résumés du match sur NHL.com</a>
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