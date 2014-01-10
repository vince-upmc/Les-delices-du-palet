<%@page import="com.delices.backend.ScoreUpdater"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.delices.datastore.contents.*"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="javax.jdo.Query"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<title>Les Délices du Palet - Détails de l'équipe</title>
</head>
<body>
	<%@include file="WEB-INF/templates/menu.jsp"%>
	<div id="main-content">
		<div class="left-panel">
			<%
				Calendar c1 = Calendar.getInstance();
				Calendar c2 = Calendar.getInstance();
				Team t = pm.getObjectById(Team.class,
						request.getParameter("team-id"));
				Query q1 = pm.newQuery(Match.class);
				q1.setFilter("home == tKey && startingTime > d");
				q1.declareParameters("com.google.appengine.api.datastore.Key tKey, java.util.Date d");
				List<Match> matchList = (List<Match>) q1.execute(t.getKey(),
						c1.getTime());
				Query q2 = pm.newQuery(Match.class);
				q2.setFilter("away == tKey");
				q2.declareParameters("com.google.appengine.api.datastore.Key tKey");
				List<Match> matchs2 = (List<Match>) q2.execute(t.getKey());
				matchList = new ArrayList<Match>(matchList);
				matchList.addAll(matchs2);
				Collections.sort(matchList);
			%>

			<div class="team-logo">
				<img src="images/teamLogos/<%=t.getName().replace(' ', '_')%>2.png">
			</div>
			<h1 class="team-name"><%=t.getMarket() + " " + t.getName()%></h1>
			<div class="clear"></div>

			Todo? : ajouter les stats
			<div class="team-content">
				<div class="team-credits"></div>
				<div class="team-calendar">
					<!-- Hard include, sinon ça déploy pas -->
					<table class="calendar">
						<%
							DateFormat hourFormat = new SimpleDateFormat("hh : mm");
							DateFormat calendarDateFormat = new SimpleDateFormat("EEEE dd MMMM");
							c1.setTime(c2.getTime());
							c2.add(Calendar.DAY_OF_MONTH, 1);
							Calendar c = Calendar.getInstance();
							c.set(2014, 1, 1, 0, 0, 0);
							c.set(Calendar.MONTH, Calendar.JANUARY);
							for (Match m : matchList) {
								if (m.getStartingTime().compareTo(c.getTime()) < 0)
									continue;
								c2.setTime(m.getStartingTime());
								Team home = pm.getObjectById(Team.class, m.getHome());
								Team away = pm.getObjectById(Team.class, m.getAway());
								if (c1.get(Calendar.DAY_OF_MONTH) != c2
										.get(Calendar.DAY_OF_MONTH)) {
						%>
						<tr>
							<td class="calendar-date"><%=calendarDateFormat.format(c2.getTime())%>
							</td>
						</tr>
						<%
							}
								c1.setTime(c2.getTime());
						%>
						<tr class="calendar-match">
							<td>
								<div class="left-team">
									<a href="/team.jsp?team-id=<%=home.getId()%>"><%=home.getName()%><img
										style="position: relative; top: 2px; margin-left: 5px"
										src="images/teamLogos/<%=home.getName().replace(' ', '_')%>.png"></a>
								</div>
								<div class="score-time">
									<a href="/match.jsp?match-id=<%=m.getId()%>"> <%
 	if (m.getStatus().equals("closed")) {
 %> <%=m.getBoxScore().getHomeScore() + " - "
							+ m.getBoxScore().getAwayScore()%> <%
 	} else {
 %><%=hourFormat.format(c2.getTime())%> <%
 	}
 %></a>
								</div>
								<div class="right-team">
									<a href="/team.jsp?team-id=<%=away.getId()%>"><img
										style="position: relative; top: 2px; margin-right: 5px"
										src="images/teamLogos/<%=away.getName().replace(' ', '_')%>.png"><%=away.getName()%></a>
								</div>
							</td>
						</tr>

						<%
							}
						%>
					</table>
				</div>
			</div>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>