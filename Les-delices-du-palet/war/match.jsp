<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/templates/head.jsp"%>
<script src="/scripts/matchDisplayer.js"></script>
<script src="/scripts/pari.js"></script>
<title>Les Délices du Palet - Détails de match</title>
<style>
td {
	text-align: center;
	padding: 2px;
}

dt {
	clear: both;
	float: left;
	width: 120px;
}

dd {
	float: left;
}
</style>
</head>
<body onload="fetch_match('<%=request.getParameter("match-id")%>')">
	<%@include file="WEB-INF/templates/menu.jsp"%>

	<div id="main-content">
		<div class="left-panel">
			<dl>
				<dt>Début du match</dt>
				<dd id="air-time"></dd>

				<dt>Status</dt>
				<dd id="status"></dd>
			</dl>
			<div class="clear"></div>
			<table>
				<tr>
					<th><h4 class="home-name"></h4></th>
					<th></th>
					<th><h4 class="away-name"></h4></th>
				</tr>
				<tr>
					<th><h3 id="home-result"></h3></th>
					<th>Résultat</th>
					<th><h3 id="away-result"></h3></th>
				</tr>
				<tr>
					<td id="home-conference"></td>
					<td>Conférence</td>
					<td id="away-conference"></td>
				</tr>
				<tr>
					<td id="home-market"></td>
					<td>Ville</td>
					<td id="away-market"></td>
				</tr>
				<tr>
					<td id="home-games_played"></td>
					<td>Matchs joués</td>
					<td id="away-games_played"></td>
				</tr>
				<tr>
					<td id="home-wins"></td>
					<td>Victoires</td>
					<td id="away-wins"></td>
				</tr>
				<tr>
					<td id="home-losses"></td>
					<td>Défaites</td>
					<td id="away-losses"></td>
				</tr>
				<tr>
					<td id="home-points"></td>
					<td>Points</td>
					<td id="away-points"></td>
				</tr>
				<tr>
					<td id="home-win_pct"></td>
					<td>% de victoire</td>
					<td id="away-win_pct"></td>
				</tr>
				<tr>
					<td id="home-goals_for"></td>
					<td>Total de buts</td>
					<td id="away-goals_for"></td>
				</tr>
				<tr>
					<td id="home-goals_against"></td>
					<td>Total de buts encaissés</td>
					<td id="away-goals_against"></td>
				</tr>
				<tr>
					<td id="home-goals_diff"></td>
					<td>Différences de but</td>
					<td id="away-goals_diff"></td>
				</tr>
				<tr>
					<td colspan=3>
					<button id="pari_button" onclick="fetch_pari()">Afficher les paris</button>
					</td>
				</tr>
			</table>
			
			<div id="pari_box" style="visibility:hidden; padding-bottom:50px;">
			<%try{ user.getUserId();%>
				<form action="parier(this, '<%=request.getParameter("match-id")%>', <%=user.getUserId()%>)">
				<%}catch(Exception e){%>
				<form action="parier(this, '<%=request.getParameter("match-id")%>', -1)">
				<%}%>
				
				<input type="radio" name="type_pari" value="victoire_home"/> Parier sur la victoire de <span class="home-name"></span><br/>
				
				<input type="radio" name="type_pari" value="victoire_away"/> Parier sur la victoire de <span class="away-name"></span><br/>
				
				<input type="radio" name="type_pari" value="match_nul"/> Parier sur un match nul<br/>
				
				<input type="radio" name="type_pari" value="victoire_home_ecart"/> Parier sur la victoire de <span class="home-name"></span> 
				avec un écart de <select name ="ecart"><option value="1-3">1 à 3 points</option><option value="4-7">4 à 7 points</option>
				<option value="8+">8 points ou plus</option></select><br/>
				
				<input type="radio" name="type_pari" value="victoire_away_ecart"/> Parier sur la victoire de <span class="away-name"></span> 
				avec un écart de <select name ="ecart"><option value="1-3">1 à 3 points</option><option value="4-7">4 à 7 points</option>
				<option value="8+">8 points ou plus</option></select><br/>
				</form>
			</div>
		</div>
		<%@include file="WEB-INF/templates/dayMatches.jsp"%>
		<div class="clear"></div>
	</div>
	<%@include file="WEB-INF/templates/footer.jsp"%>
</body>
</html>