
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


//upercase cognome e controllo
$(document).ready(function() {
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
				$("button").show();
			}
			else {
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
		if(username != '') {
			$("#username").val(username.trim());
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
	
	

});

