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
	$("." + which + "name").text(team.name);
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

function colorize(home, away) {
	var fields = [ "wins", "losses", "points", 
	                "win_pct", "goals_for", "goals_against", 
	                "goals_diff" ];
	for (x in fields){
		var field = fields[x];
		var isGt = function(a, b){return a > b};
		var isLw = function(a, b){return a < b};
		
		if (isNaN(home[field])) continue;
		
		if (field == "goals_against" || field == "losses"){
			var tmp = isGt;
			isGt = isLw;
			isLw = tmp;
		}
		
		if (isGt(home[field], away[field])){
			$("#home-"+field).css("color", "green");
			$("#away-"+field).css("color", "red");
		} else if (isLw(home[field], away[field])){
			$("#home-"+field).css("color", "red");
			$("#away-"+field).css("color", "green");
		} else {
			$("#home-"+field).css("color", "blue");
			$("#away-"+field).css("color", "blue");
		}
	}
}
function colorizeGoals(data){
	if (data.boxScore.homeScore > data.boxScore.awayScore){
		$("#home-result").css("color", "green");
		$("#away-result").css("color", "red");
	} else if (data.boxScore.homeScore < data.boxScore.awayScore) {
		$("#home-result").css("color", "red");
		$("#away-result").css("color", "green");
	} else {
		$("#home-result").css("color", "blue");
		$("#away-result").css("color", "blue");
	}
	
}

function display_match(data /* , textstatus, jqxhr */) {
	$("#status").text(data.status);
	$("#air-time").text(data.startingTime);
	if (data.status == "closed"){
		$("#score-time").text(data.boxScore.homeScore + " - " + data.boxScore.awayScore);
		/*colorizeGoals(data);*/
	} else {
		$("#score-time").text(data.startingTime);
	}
	fill_fields("home-", data.home);
	fill_fields("away-", data.away);
	colorize(data.home, data.away);
	
}