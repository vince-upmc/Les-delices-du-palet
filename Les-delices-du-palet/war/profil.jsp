<%@page import="org.omg.CORBA.UserException"%>
<%@page import="com.delices.datastore.contents.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="javax.jdo.Query"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<title>Les Délices du Palet - Profil</title>
</head>
<body>
	<%@include file="WEB-INF/templates/menu.jsp"%>
	<%
	boolean userExist = false;
	if (dbuser == null)
	{
	%>
	<script>window.location.href="/";</script>
	<%
	    userExist = false;
	} 
	else{
		userExist = true;
	}
	if(userExist){
	%>
	<div id="main-content">
		<div class="left-panel">
			<div>
			<div class="title-wrapper"><h1>Mon Compte</h1></div>
			<div class="profile-credit"><h3>
				Vous avez <%=dbuser.getCredit()%><img style="position: relative; top: 2px"
					src="/images/credit.png" />
			</h3></div>
			</div>
			<%
				DateFormat profileFormat = new SimpleDateFormat("dd-MM HH:mm", Locale.FRANCE);
				Query q = pm.newQuery(Pari.class);
				q.setFilter("user == currentUser");
				q.declareParameters("com.delices.datastore.contents.User currentUser");
				
				@SuppressWarnings("unchecked")
				List<Pari> paris = (List<Pari>) q.execute(dbuser.getKey());
				int wins = 0;
				int cpt = 0;
			%>
				<div class="profile-wrapper">
				<div class="bets">
				<table>
				<tr><th>&Eacute;quipe gagnante</th><th>Diff.</th><th>Match</th><th>Date</th><th>Score</th><th>Mise</th></tr>
				<tr><td class="bet-title" colspan="6">Paris en cours</td></tr>
					<%
						for (Pari p : paris) {
							if (!p.isDone()) {
								wins++;
								Match m = pm.getObjectById(Match.class, p.getMatch());
								Team home = pm.getObjectById(Team.class, m.getHome());
								Team away = pm.getObjectById(Team.class, m.getAway());
					%>
					<tr>
					<td><%
						switch (p.getBetObjective()) {
						case Home:%><a href="/team.jsp?team-id=<%=home.getId()%>"><%=home.getName()%></a><%;break;
						case Away:%><%=away.getName()%><%;break;
						case Tie:%>Match nul<%;break;
						default:%>?<%;break;
						}
					%></td>
					<td><%=p.getDifferenceDescr()%></td>
					<td><a href="/match.jsp?match-id=<%=m.getId()%>"><%=home.getName() + " - " + away.getName()%></a></td>
					<td><%=profileFormat.format(m.getStartingTime()) %></td>
					<td>-</td>
					<td><%=p.getMise() %></td>
					</tr><%}} %>
					
					<tr><td class="bet-title" colspan="6">Anciens paris</td></tr>
					<%
						for (Pari p : paris) {
							if (p.isDone()) {
								wins++;
								Match m = pm.getObjectById(Match.class, p.getMatch());
								Team home = pm.getObjectById(Team.class, m.getHome());
								Team away = pm.getObjectById(Team.class, m.getAway());
					%>
					<tr>
					<td><%
						switch (p.getBetObjective()) {
						case Home:%><%=home.getName()%><%; break;
						case Away:%><%=away.getName()%><%; break;
						case Tie:%>Match nul<%; break;
						default:%>?<%;
						}
					%></td>
					<td><%=p.getDifferenceDescr()%></td>
					<td><a href="/match.jsp?match-id=<%=p.getMatch().getId()%>"><%=home.getName() + " - " + away.getName()%></a></td>
					<td><%=profileFormat.format(m.getStartingTime()) %>
					<td><%=m.getBoxScore().getHomeScore() +" - "+ m.getBoxScore().getAwayScore() %></td>
					<td><%=p.getMise()+" / "%><%if(p.isBetSuccessful(m)){%><%=p.getWinningSum()%><%}{%>0<%}%></td>
					</tr>
					<%
							}
						}
						Double percent =  cpt > 0 ? ((double) wins) / ((double) cpt) * 100 : 100;
					%>
				</table>
				</div>
			<div>Pourcentage de réussite : <%= percent.toString() %>%</div>
			</div>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
	<% } %>
</body>
</html>