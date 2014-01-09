<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.delices.datastore.contents.Match"%>
<%@page import="com.delices.datastore.contents.Team"%>
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
				Team t = pm.getObjectById(Team.class, request.getParameter("team-id"));
				Query q1 = pm.newQuery(Match.class);
				q1.setFilter("home == tKey");
				q1.declareParameters("com.google.appengine.api.datastore.Key tKey");
				q1.setOrdering("startingTime");
				List<Match> matchs = (List<Match>) q1.execute(t.getKey());
				Query q2 = pm.newQuery(Match.class);
				q2.setFilter("away == tKey");
				q2.declareParameters("com.google.appengine.api.datastore.Key tKey");
				q2.setOrdering("startingTime");
				List<Match> matchs2 = (List<Match>) q2.execute(t.getKey());
				/*matchs.addAll(matchs2);*/
			%>
		
			<h1><%=t.getName()%></h1>
			
			<%
				for (Match m : matchs) {
					Team t1 = pm.getObjectById(Team.class, m.getHome());
					Team t2 = pm.getObjectById(Team.class, m.getAway());
					%>
						<div><%=t1.getName() + " - " + t2.getName() %></div>
					<%
				}
			%>
			
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>