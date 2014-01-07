function parier(form, matchid, user) {/*
	$.ajax({
		type : "GET",
		url : "/TestPari",
		data : "matchid="+matchid+"&mise="+mise+"&dbuser="+user+"&betkind="+betkind,
		dataType : "json",
		async : true,
		error : pari_error,
		success : display_pari
	});*/
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