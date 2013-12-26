/**
 * 
 */
function fetch_next_matches() {
	$.ajax({
		type : "GET",
		url : "/nextMatchs",
		dataType : "json",
		async : true,
		error : display_error,
		success : display_matches
	});
}

function display_error() {

}

function createEntry(homename, awayname, date, link) {
	var entry = document.createElement('a');
	console.log(date);
	$(entry).addClass('match_entry').attr("href", "/"); // Todo with link

	$(entry).append('<div class="date">' + date + '</div>').append(
			'<div class="teams">' + homename + " vs " + awayname + '</div>');

	return $(entry);
}

function display_matches(data /* , textstatus, jqxhr */) {
	data.matchs.forEach(function(e) {
		console.log(e);
		$("#matchs").append(
				createEntry(e.home.name, e.away.name, e.startingTime));
	});
}

fetch_next_matches();