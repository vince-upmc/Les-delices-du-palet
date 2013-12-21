<%@page import="java.util.Date"%>
<%@ page import="com.delices.model.Match" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<div class="right-panel" id="matchs">
		<h4><center>Matchs du jour</center></h4>
			<%
				Match dm1 = new Match("m1",new Date(),
					"Les petits pédestres",
					"Les grosses pédales",
					0,
					0);
				Match dm2 = new Match("m2",new Date(),
					"Les petits pédestres qui beaucou du ronquqq",
					"Les grosses pédales",
					0,
					0);
				List<Match> dayMatchs = new ArrayList<Match>();
				dayMatchs.add(dm1);dayMatchs.add(dm2);
				
			%>
			<%
				for (Match m : dayMatchs) {
			%>
					<div class="matchPanel">
						<div class="team_score">
							<div class="teamLabel">
								<%=m.getTeam1()%>
							</div>
							<div class="scoreLabel">
								<%=m.getScore1()%>
							</div>
							<div class="clear"></div>
						</div>
						<div class="team_score">
							<div class="teamLabel">
								<%=m.getTeam2()%>
							</div>
							<div class="scoreLabel">
								<%=m.getScore2()%>
							</div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
			<%
				}
			%>
</div>