<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>

<!-- We assume List<Match> matchList, Calendar c1 and Calendar c2 existing  -->

<table class="calendar">
				<%
					DateFormat hourFormat = new SimpleDateFormat("hh : mm");
					DateFormat calendarDateFormat = new SimpleDateFormat("EEEE dd MMMM");
					c1.setTime(c2.getTime());
					c2.add(Calendar.DAY_OF_MONTH, 1);
					for (Match m : matchList) {
						c2.setTime(m.getStartingTime());
						Team home = pm.getObjectById(Team.class, m.getHome());
						Team away = pm.getObjectById(Team.class, m.getAway());
						if (c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH)) {
						%>
						<tr><td class="calendar-date">
						<%=calendarDateFormat.format(c2.getTime())%>
						</td></tr>
						<%
						}
						c1.setTime(c2.getTime());
				%>
				<tr class="calendar-match"><td>
					<div class="left-team"><a href="/team.jsp?team-id=<%=home.getId()%>"><%=home.getName()%></a></div>
					<div class="score-time"><a href="/match.jsp?match-id=<%=m.getId()%>">
						<%if(m.getStatus().equals("closed")){%>
							<%=m.getBoxScore().getHomeScore() + " - " + m.getBoxScore().getAwayScore()%>
						<%}else{%><%=hourFormat.format(c2.getTime())%><%}%></a></div>
					<div class="right-team"><a href="/team.jsp?team-id=<%=away.getId()%>"><%=away.getName()%></a></div>
				</td></tr>
					
				<%
					}
				%>
			</table>