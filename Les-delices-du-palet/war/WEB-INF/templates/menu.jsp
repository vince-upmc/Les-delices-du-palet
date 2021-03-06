<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="javax.jdo.JDOObjectNotFoundException"%>
<%@page import="com.delices.datastore.PMF"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<header>
	<div class="logo"></div>

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
				com.delices.datastore.contents.User dbuser = null;
				PersistenceManager pm = PMF.get().getPersistenceManager();

				if (user != null) {
					pageContext.setAttribute("user", user);

					//Si l'user n'existe pas encore dans la base de donn�e, on l'ins�re.
					try {
						dbuser = pm.getObjectById(
								com.delices.datastore.contents.User.class,
								user.getUserId());
					} catch (JDOObjectNotFoundException e) {
						Key k = KeyFactory.createKey(
								com.delices.datastore.contents.User.class
										.getSimpleName(), user.getUserId());

						dbuser = new com.delices.datastore.contents.User(k);
						pm.makePersistent(dbuser);
					}
			%>
			<li><a style="font-size: 14px" href="/profil.jsp">Bonjour <%=user.getNickname()%></a></li>
			<li><span id="credit"><%=dbuser.getCredit()%></span><img
				style="position: relative; top: 2px" src="/images/credit.png" /></li>
			<li><a href="<%=userService.createLogoutURL("/")%>">D�connexion</a></li>

			<%
				} else {
					String s;
					if (request.getQueryString() != null) {
						s = request.getRequestURI() + "?"
								+ request.getQueryString();
					} else {
						s = request.getRequestURI();
					}
			%>
			<li><a href="<%=userService.createLoginURL(s)%>">Connexion</a></li>
			<%
				}
			%>
		</ul>
	</div>
</header>