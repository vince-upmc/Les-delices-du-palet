<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<%@ page import="com.delices.model.Match" %>
<title>Les Délices du Palet - Calendrier</title>
</head>
<body>
	<%@include file="WEB-INF/templates/menu.jsp"%>
	<div id="main-content">
		<div class="left-panel">
			<h1>Calendar</h1>
			<%
			
				Match m1 = new Match("m1",new Date(),
					"Les petits pédestres",
					"Les grosses pédales",
					0,
					0);
				Match m2 = new Match("m2",new Date(),
					"Les petits pédestres qui beaucou du ronquqq",
					"Les grosses pédales",
					0,
					0);
				List<Match> matchs = new ArrayList<Match>();
				matchs.add(m1);matchs.add(m2);
			%>
			
				<table class="calendar">
				<%
					for (Match m : matchs) {
				%>
						<tr class="calendar-item">
							 <td class="game-time"><%=m.getDate().getDay() + " " + m.getDate().getMonth() %></td>
							 <td class="left-team"><%=m.getTeam1() %></td>
							 <td class="right-team"><%=m.getTeam2() %></td>
						</tr>
				<%
					}
				%>
				</table>
		</div>
		
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
		
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>