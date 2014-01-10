<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.delices.datastore.contents.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="javax.jdo.Query"%>
<%@page import="com.delices.datastore.PMF"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<title>Les Délices du Palet - Calendrier</title>
</head>
<body>
	<%@include file="/WEB-INF/templates/menu.jsp"%>
	<div id="main-content">
		<div class="left-panel">
			<%
				int timeDiff = 0;
				String param = "";
				try {
					param = request.getParameter("timeDiff");
					if (param == null) {
						timeDiff = 0;
					} else {
						timeDiff = Integer.parseInt(param);
					}
				} catch (NumberFormatException e) {
					timeDiff = 0;
				}
				Calendar c1 = Calendar.getInstance();
				c1.add(Calendar.DATE, timeDiff);
				Date d1 = c1.getTime();
				Calendar c2 = (Calendar) c1.clone();
				c2.add(Calendar.DATE, 7);
				Date d2 = c2.getTime();

				Query q = pm.newQuery(Match.class);
				q.setFilter("startingTime >= d1 && startingTime <= d2");
				q.declareParameters("java.util.Date d1, java.util.Date d2");
				q.setOrdering("startingTime asc");

				List<Match> matchList = (List<Match>) q.execute(d1, d2);
			%>
			<h1><%=c1.get(Calendar.DAY_OF_MONTH)
					+ " "
					+ c1.getDisplayName(Calendar.MONTH, Calendar.LONG,
							Locale.getDefault())
					+ " - "
					+ c2.get(Calendar.DAY_OF_MONTH)
					+ " "
					+ c2.getDisplayName(Calendar.MONTH, Calendar.LONG,
							Locale.getDefault())%></h1>
			<div class="nav">
				<a href="/calendar.jsp?timeDiff=<%=timeDiff - 7%>">Previous 7
					days</a> <a href="/calendar.jsp?timeDiff=<%=timeDiff + 7%>">Next 7
					days</a>
			</div>

			<div class="calendar-wrapper" style="width: 90%; margin: auto">
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

		<%@include file="/WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>

	</div>
	<%@include file="/WEB-INF/templates/footer.jsp"%>
</body>
</html>