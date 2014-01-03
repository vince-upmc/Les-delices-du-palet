<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<header>
	<!-- TODO : mettre en dur le titre dans l'image -->
	<div class="logo">Les délices du palet</div>

	<div class="menu">
		<ul class="left-menu">
			<li><a href="index.jsp">Accueil</a></li>
			<li><a href="calendar.jsp?timeDiff=0">Calendrier</a></li>
			<li><a href="ranking.jsp">Classement</a></li>
		</ul>
		<ul class="right-menu">
			<%
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				if (user != null) {
					pageContext.setAttribute("user", user);
			%>
			<li>Bonjour <%=user.getNickname()%></li>
			<li><a
				href="<%=userService.createLogoutURL(request.getRequestURI())%>">Déconnexion</a></li>

			<%
				} else {
			%>
			<li><a
				href="<%=userService.createLoginURL(request.getRequestURI())%>">Connexion</a></li>
			<%
				}
			%>
		</ul>
		<!-- 
			<ul class="right-menu">
				<li><a href="subscribe.jsp">Inscription</a></li>
				<li><a href="login.jsp">Connexion</a></li>
			</ul>
			 -->
	</div>
</header>