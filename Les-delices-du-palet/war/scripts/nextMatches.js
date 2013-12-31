function fetch_next_matches() {
	$.ajax({
		type : "GET",
		url : "/next_matchs",
		dataType : "json",
		async : true,
		error : display_matches_error,
		success : display_matches
	});
}

function display_matches_error() {
	console.log("erreur, impossible d'afficher les matchs");
}

function createEntry(homename, awayname, date, link) {
	var entry = document.createElement('a');
	$(entry).addClass('match_entry').attr("href", link);

	$(entry).append('<div class="date">' + date + '</div>').append(
			'<div class="teams">' + homename + " vs " + awayname + '</div>');

	return $(entry);
}

function display_matches(data /* , textstatus, jqxhr */) {
	data.matchs.forEach(function(e) {
		$("#matchs").append(
				createEntry(e.home.name, e.away.name, e.startingTime,
						"match.jsp?match-id=" + e.id));
	});
}

fetch_next_matches();