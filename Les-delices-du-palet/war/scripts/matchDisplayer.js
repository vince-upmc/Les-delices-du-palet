function fetch_match(id) {
	$.ajax({
		type : "GET",
		url : "/display_match?match-id=" + id,
		dataType : "json",
		async : true,
		error : display_match_error,
		success : display_match
	});
}

function display_match_error() {
	console.log("Erreur, impossible d'afficher le match");
}

function fill_fields(which, team) {
	$("#" + which + "name").text(team.name);
	$("#" + which + "market").text(team.market);
	$("#" + which + "conference").text(team.conference);
	$("#" + which + "games_played").text(team.games_played);
	$("#" + which + "wins").text(team.wins);
	$("#" + which + "losses").text(team.losses);
	$("#" + which + "points").text(team.points);
	$("#" + which + "win_pct").text(team.win_pct);
	$("#" + which + "goals_for").text(team.goals_for);
	$("#" + which + "goals_against").text(team.goals_against);
	$("#" + which + "goals_diff").text(team.goals_diff);
}

function display_match(data /* , textstatus, jqxhr */) {
	$("#status").text(data.status);
	$("#air-time").text(data.startingTime);
	fill_fields("home-", data.home);
	fill_fields("away-", data.away);
}