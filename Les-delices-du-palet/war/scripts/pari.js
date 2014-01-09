function parier(matchid, user) {
	if (user==-1){
		window.location.href = "/_ah/login?continue=%2Findex.jsp";
	}
	else{
		var bet0 = document.getElementById("bet0");
		var bet1 = document.getElementById("bet1");
		var bet2 = document.getElementById("bet2");
		var bet3 = document.getElementById("bet3");
		var bet4 = document.getElementById("bet4");
		var mise = document.getElementById("mise").value;
		var betkind;

		if (bet0.checked == true){betkind = 0;}
		if (bet1.checked == true){betkind = 1;}
		if (bet2.checked == true){betkind = 2;}
		if (bet3.checked == true)
		{
			var select = document.getElementById("ecart_home").value;
			switch (select){
			case "1-3" : betkind = 3 ; break;
			case "4-7" : betkind = 4 ; break;
			case "8+" : betkind = 5 ; break;
			}
		}
		if (bet4.checked == true)
		{
			var select = document.getElementById("ecart_away").value;
			switch (select){
			case "1-3" : betkind = 6 ; break;
			case "4-7" : betkind = 7 ; break;
			case "8+" : betkind = 8 ; break;
			}
		}

		$.ajax({
			type : "GET",
			url : "/testpari",
			data : "matchid="+matchid+"&mise="+mise+"&dbuser="+user+"&betkind="+betkind,
			dataType : "json",
			async : true,
			error : pari_error,
			success : display_pari
		});
	}
}

function display_pari(toto){
	window.location.href = "/profil.jsp";
}

function pari_error(toto){
	alert ("error : pari non valide");
}

function fetch_pari(){
	var button = document.createElement("input");
	button.type = "button";
	button.value = "Masquer les Paris";
	button.id="pari_button";
	button.onclick=hide_pari;
	old_button= document.getElementById("pari_button");
	old_button.parentNode.replaceChild(button, old_button);

	document.getElementById("pari_box").style.visibility = 'visible';
}

function hide_pari(){
	var button = document.createElement("input");
	button.type = "button";
	button.value = "Afficher les Paris";
	button.id="pari_button";
	button.onclick=fetch_pari;
	old_button= document.getElementById("pari_button");
	old_button.parentNode.replaceChild(button, old_button);

	document.getElementById("pari_box").style.visibility = 'hidden';
}