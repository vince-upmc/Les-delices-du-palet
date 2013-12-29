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
			<%  
				DateFormat tableFormat = new SimpleDateFormat("dd-MM hh:mm");
				String param = request.getParameter("timeDiff");
				int timeDiff = Integer.parseInt(param);
				
				Calendar c1 = Calendar.getInstance();
				c1.add(Calendar.DATE, timeDiff);
				Date d1 = c1.getTime();
				Calendar c2 = (Calendar) c1.clone();
				c2.add(Calendar.DATE, 7);
				Date d2 = c2.getTime();
				
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				Query q = pm.newQuery(Match.class);
				q.setFilter("startingTime >= d1 && startingTime <= d2");
				q.declareParameters("java.util.Date d1, java.util.Date d2");
				q.setOrdering("startingTime asc");
				
			%>
			<h1><%=c1.get(Calendar.DAY_OF_MONTH)+" "+c1.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
					+" - "+c2.get(Calendar.DAY_OF_MONTH)+" "+c2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
					%></h1>
			<div class="nav">
			<a href="/calendar.jsp?timeDiff=<%=timeDiff - 7%>">Previous 7 days</a>
			<a href="/calendar.jsp?timeDiff=<%=timeDiff + 7%>">Next 7 days</a>
			</div>
			
			<table class="calendar">
			<tr class=calendar-header">
				<td>Date</td>
				<td>Home Team</td>
				<td>Visiting Team</td>
			</tr>
			<%
			
			   for (Match m : (List<Match>) q.execute(d1, d2)) {
				   c2.setTime(m.getStartingTime());
				   Team home = pm.getObjectById(Team.class, m.getHome());
				   Team away = pm.getObjectById(Team.class, m.getAway());
			%>
				<tr>
					<td class="game-time">
						<%=tableFormat.format(c2.getTime())
							/*c.get(Calendar.DAY_OF_MONTH)
							+ "-" + c.get(Calendar.MONTH)
							+ " " + c.get(Calendar.HOUR_OF_DAY)
							+ ":"*/
						%></td>
					<td class="left-team"><a href="/team.jsp?team-id=<%=home.getId()%>"><%=home.getName() %></a></td>
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