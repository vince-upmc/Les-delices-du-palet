<%@page import="com.delices.datastore.contents.Team"%>
<%@page import="javax.jdo.Query"%>
<%@page import="com.delices.datastore.PMF"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<title>Les Délices du Palet - Calendrier</title>
</head>
<body>
	<%@include file="WEB-INF/templates/menu.jsp"%>
	<div id="main-content">
		<div class="left-panel">
			<h1>Ranking</h1>
			<table class="ranking">
			<tr>
				<th></th>
				<th>Team</th>
				<th>DIV</th>
				<th>P</th>
				<th>GP</th>
				<th>W</th>
				<th>L</th>
				<th>OT</th>
				<th>WP</th>
				<th>GF</th>
				<th>GA</th>
				<th>DIFF</th>
			</tr>
			<% 			
				Query q = pm.newQuery(Team.class);
				q.setOrdering("points desc");
				int cpt = 0;
				for (Team t : (List<Team>) q.execute()){
			%>
				<tr><td>
					<%=++cpt%></td>
					<td class="ranking-team">
						<a href="team.jsp?team-id=<%=t.getId()%>"><img src="images/<%=t.getName()%>.png">  <%=t.getName() %></a>
					</td>
					<td><%=t.getDivision().charAt(0)%></td>
					<td class="ranking-points"><%=t.getPoints() %></td>
					<td><%=t.getGames_played() %></td>
					<td><%=t.getWins() %></td>
					<td><%=t.getLosses() %></td>
					<td><%=t.getOvertime_losses() %></td>
					<td><%=t.getWin_pct() %></td>
					<td><%=t.getGoals_for() %></td>
					<td><%=t.getGoals_against() %></td>
					<td><%=t.getGoals_diff() %></td>
				</tr>
			<% } %>
			</table>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>