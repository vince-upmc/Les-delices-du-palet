<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.delices.datastore.contents.Match"%>
<%@page import="com.delices.datastore.contents.Team"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="javax.jdo.Query"%>
<%@page import="com.delices.datastore.PMF"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
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
			<h1>Calendar</h1>
			<table class="calendar">
			
			<% 
			Calendar c = Calendar.getInstance();
			Date d1 = c.getTime();
			c.add(Calendar.DATE, 7);
			Date d2 = c.getTime();
			DateFormat format = new SimpleDateFormat("dd-MM hh:mm");
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(Match.class);
			q.setFilter("startingTime >= d1 && startingTime <= d2");
			q.declareParameters("java.util.Date d1, java.util.Date d2");
			q.setOrdering("startingTime asc");
			
			%>
			
			<tr class=calendar-header">
				<td>Date</td>
				<td>Home Team</td>
				<td>Visting Team</td>
			</tr>
			
			<%
			
			   for (Match m : (List<Match>) q.execute(d1, d2)) {
				   c.setTime(m.getStartingTime());
				   Team home = pm.getObjectById(Team.class, m.getHome());
				   Team away = pm.getObjectById(Team.class, m.getAway());
			%>
				<tr>
					<td class="game-time">
						<%=format.format(c.getTime())
							/*c.get(Calendar.DAY_OF_MONTH)
							+ "-" + c.get(Calendar.MONTH)
							+ " " + c.get(Calendar.HOUR_OF_DAY)
							+ ":"*/
						%></td>
					<td class="left-team"><a href="#"><%=home.getName() %></a></td>
					<td class="right-team"><a href="#"><%=away.getName() %></a></td>
				</tr>
			<%}%>
			</table>
		</div>

		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>

	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>