<%@page import="org.omg.CORBA.UserException"%>
<%@page import="com.delices.datastore.contents.Pari"%>
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
			<h1>Profil</h1>
			<div>
				Crédit actuel :
				<%=dbuser.getCredit()%><img style="position: relative; top: 2px"
					src="/images/credit.png" />
			</div>
			<%
				Query q = pm.newQuery(Pari.class);
				q.setFilter("user == currentUser");
				q.declareParameters("com.delices.datastore.contents.User currentUser");
				
				@SuppressWarnings("unchecked")
				List<Pari> paris = (List<Pari>) q.execute(dbuser.getKey());
				int wins = 0;
				int cpt = 0;
			%>

			<div>
				Paris engagés :
				<ul>
					<%
						for (Pari p : paris) {
							if (p.getStatus() != Pari.Estatus.Done) {
					%>
					<li><a href="/match.jsp?match-id=<%=p.getMatch().getName()%>"><%=p.getDate()%></a>
					</li>
					<%
						}
						}
					%>
				</ul>
			</div>
			<div>
				Paris terminés :
				<ul>
				<%
				for (Pari p : paris) {
					cpt++;
					if (p.getStatus() == Pari.Estatus.Done) {
			%>
				<li><a href="/match.jsp?match-id=<%=p.getMatch().getName()%>"><%=p.getDate()%></a>
				</li>
				<%
					}
					}
					Double percent =  cpt > 0 ? ((double) wins) / ((double) cpt) * 100 : 100;
				%>
				</ul>
			</div>
			<div>Pourcentage de réussite : <%= percent.toString() %>%</div>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
	<% } %>
</body>
</html>