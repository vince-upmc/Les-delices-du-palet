<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<header>
	<!-- TODO : mettre en dur le titre dans l'image -->
	<div class="logo">Les d�lices du palet</div>

	<div class="menu">
		<ul class="left-menu">
			<li><a href="index.jsp">Accueil</a></li>
			<li><a href="calendar.jsp">Calendrier</a></li>
			<li><a href="ranking.jsp">Classement</a></li>
		</ul>
		<ul class="right-menu">
			<%
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				if (user != null) {
					pageContext.setAttribute("user", user);
			%>
			<li>Bonjour ${fn:escapeXml(user.nickname)}</li>
			<li><a
				href="<%=userService.createLogoutURL(request.getRequestURI())%>">D�connexion</a></li>

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