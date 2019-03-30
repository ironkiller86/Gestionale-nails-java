
//funzione che sostituisce ogni primo carattere del parametro passato da lowercase a uppercase////////////////////
function upperWord(field){
	if(field != '') {
		var momArray = field.split(' ', field.lenght);
		var momString = undefined;
		var def = '';
		var i = 0;
		for(i; i < momArray.length; i++){
			var wrd = momArray[i].substring(0,1).toUpperCase();
			var momString = momArray[i].replace(momArray[i].substring(0,1),wrd);
			def += momString + ' ';
		}
		return def;
	}
}


//active dialog deelete////////////
$(document).ready(function() {
	var flag = $("#flag").html();
	if(flag != undefined && flag === 'delete'){
		$( "#dialog-delete" ).dialog({
			resizable: false,
			height: "auto",
			width: 400,
			modal: true,
			buttons: {
				OK: function() {
					$( this ).dialog( "close" );
				}
			}
		});
	}
	
	/////activate dialog utente non presente in db
	var flag2 = $("#flag2").html();
	if(flag2 != undefined && flag2 === 'empy') {
		$( "#dialog-infoRicerca" ).dialog({
			resizable: false,
			height: "auto",
			width: 400,
			modal: true,
			buttons: {
				OK: function() {
					$( this ).dialog( "close" );
				}
			}
		});
	}
	
	var flag3 = $("#flag3").html();
	if(flag3 != undefined && flag3 === 'endLicenze') {
		$( "#dialog-endLicenze" ).dialog({
			resizable: false,
			height: "auto",
			width: 400,
			modal: true,
			buttons: {
				OK: function() {
					$( this ).dialog( "close" );
				}
			}
		});
	}
	
	
	////////////////////////////funzioni controllo creazione user//////////////////////////

	$("#cognome").blur(function(){                                 //upercase cognome e controllo
		var cognome = $("#cognome").val();
		if(cognome != ''){
			$("#cognome").val(upperWord(cognome).trim());
		}
	});

	$("#nome").blur(function(){                                 //upercase nome e controllo
		var nome = $("#nome").val();
		if(nome != ''){
			$("#nome").val(upperWord(nome).trim());
		}
	});

	$("#codFiscale").blur(function(){

		var codFisc = $("#codFiscale").val().trim();
		if(codFisc != ''){
			var espressione = new RegExp('^[A-Za-z]{6}[0-9LMNPQRSTUV]{2}[A-Za-z]{1}[0-9LMNPQRSTUV]{2}[A-Za-z]{1}[0-9LMNPQRSTUV]{3}[A-Za-z]{1}$');
			if(espressione.test(codFisc)){
				$("#codFiscale").val(codFisc.toUpperCase());
				$("#codFiscale").css({"border-color":"green","box-shadow":"0px 0px 4px green",});
				$("button").show();
			}
			else {
				$("#codFiscale").val(codFisc.toUpperCase());
				$("#codFiscale").css({"border-color":"red","box-shadow":"0px 0px 4px red",});
				$("button").hide();
				$( function() {                        
					$( "#dialog-codFisc" ).dialog({
						resizable: false,
						height: "auto",
						width: 400,
						modal: true,
						buttons: {
							Chiudi: function() {
								$( this ).dialog( "close" );
							}
						}
					});
				});
			}
		}
		else {
			$("#codFiscale").css({"border":"1px solid #ced4da","box-shadow":"none",});
		}
	});	

	$("#luogoNascita").blur(function(){                                
		var luogoNascita = $("#luogoNascita").val();
		if(luogoNascita != ''){
			$("#luogoNascita").val(upperWord(luogoNascita).trim());
		}
	});

	$("#indirizzo").blur(function(){                               
		var indirizzo = $("#indirizzo").val();
		if(indirizzo != '') {
			$("#indirizzo").val(upperWord(indirizzo).trim());
		}
	});

	$("#citta").blur(function(){                               
		var citta = $("#citta").val();
		if(citta != '') {
			$("#citta").val(upperWord(citta).trim());
		}
	});

	$("#cap").blur(function(){                               
		var cap = $("#cap").val();
		if(cap != '') {
			$("#cap").val(cap.trim());
		}
	});

	$("#telefono").blur(function(){                               
		var telefono = $("#telefono").val();
		if(telefono != ''){
			$("#telefono").val(telefono.trim());
		}
	});

	$("#email").blur(function(){                               
		var email = $("#email").val();
		if(email != '') {
			$("#email").val(email.trim());
		}
	});

	$("#username").blur(function(){                               
		var username = $("#username").val();
		var id = $("#idUser").html();                //recupero id dell'utente messo nel div della pagina
		if(username != '') {
			var jsonUser = {usr: username, idUser: id};
			$.ajax({                         //chiamata ajax per creazione user
				contentType: "application/json",
				type:"POST",
				url:"adminController?action=controllUserName",
				data: JSON.stringify(jsonUser),
				success: function(response) {
					console.log(response);
					if(response == 'inUso'){
						$("#username").css({"border-color":"red","box-shadow":"0px 0px 4px red",});
						jQuery("#msg").text('Nome utente gia in uso!');
						$( "#dialog-success" ).dialog({
							resizable: false,
							height: "auto",
							width: 400,
							modal: true,
							buttons: {
								OK: function() {
									$( this ).dialog( "close" );
								}
							}
						});
					}
					if(response == 'disp'){
						$("#username").css({"border-color":"green","box-shadow":"0px 0px 4px green",});
					}
				}
			});
			$("#username").val(username.trim());
		}
		else {
			$("#username").css({"border":"1px solid #ced4da","box-shadow":"none",});
		}
	});

	/*$("#password").blur(function(){
		var password = $("#password").val().trim();
		console.log(password);
		if(password != ''){
			var espressione = new RegExp('((?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,})');
			if(espressione.test(password)) {
				$("#password").val(password);
				$("button").show();
			}
			else if(!espressione.test(password)) {
				$("button").hide();
				$( function() {                        
					$( "#dialog-password" ).dialog({
						resizable: false,
						height: "auto",
						width: 400,
						modal: true,
						buttons: {
							Chiudi: function() {
								$( this ).dialog( "close" );
							}
						}
					});
				});
			}
		}
	});	*/

	$("#password").blur(function(){                               
		var password = $("#password").val();
		if(password != '') {
			$("#password").val(password.trim());
		}
	});


	$("#allUsers").click(function(){ 
		$.ajax({                         
			async:false,
			contentType: "application/json",
			type:"GET",
			url:"adminController?page=searchAllUsers",
			success: function(response) {
				console.log(response);

			}



		});

		window.location.href ='adminController?page=visualAllUser';

	});





});

