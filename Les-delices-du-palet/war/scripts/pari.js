function validate(e) {
	e.preventDefault();
	var form = $("#bet-form");
	var response = $("#reponse");

	$.ajax({
		url : '/pari',
		type : 'GET',
		dataType : 'html',
		data : form.serialize(),
		beforeSend : function() {
			form.hide();
			response.show();
			response.html('Enregistrement du pari...');
		},
		success : function(data) {
			response.html('Pari correctement enregistré.');
			var credit = $("#credit");
			credit.text(parseInt(credit.text()) - $("#mise").val());
		},
		error : function(xhr) {
			console.log('Erreur: ' + xhr.responseText)
			form.hide();
		}
	});
}

function disableDifference(e) {
	if (e == "tie") {
		$("#difference").prop('disabled', true);
	} else {
		$("#difference").prop('disabled', false);
	}
}

function calculateModifier() {
	var modifier = $("#modifier");
	var diff = $("#difference").val();
	var mise = $("#mise").val();

	var res = mise;

	switch (diff) {
	case 'none':
		res *= 1;
		break;
	case 'oneToThree':
		res *= 2;
		break;
	case 'fourToSeven':
		res *= 3;
		break;
	case 'eightOrMore':
		res *= 4;
		break;
	}

	modifier.text(res);
}

$(document).ready(function() {
	$("#bet-form").on("submit", validate);
	calculateModifier();
});
