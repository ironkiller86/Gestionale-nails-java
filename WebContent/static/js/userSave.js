//lettura dati creazione user
$(document).ready(function() {
	$( "#save" ).click(function() {
		var jsonUser = {id:$("#idUser").html(),
				cognome: $("#cognome").val(),
				nome: $("#nome").val(),
				codFisc: $("#codFiscale").val(),
				dataNascita:  $("#dataNascita").val(),
				luogoNascita: $("#luogoNascita").val(),
				indirizzo: $("#indirizzo").val(),
				citta: $("#citta").val(),
				cap: $("#cap").val(),
				telefono: $("#telefono").val(),
				email: $("#email").val(),
				username: $("#username").val(),
				password: $("#password").val(),
				dataAttiv: $("#dataAttivazione").val(),
				dataScad: $("#dataScadenza").val(),
				ruolo: $("#role").val(),

		};


		$.ajax({                         //chiamata ajax per creazione user
			contentType: "application/json",
			type:"POST",
			url:"adminController?action=userSave",
			data: JSON.stringify(jsonUser),
			success: function(response) {
				if(response == 'modify'){
					$( "#dialog-modify" ).dialog({
						resizable: false,
						height: "auto",
						width: 400,
						modal: true,
						buttons: {
							OK: function() {
								$( this ).dialog( "close" );
								window.location.href ='adminController?page=visualAllUser';
							}
						}
					});

				}
					else if(response =='create'){
						jQuery("#msg").text('Utente creato!'); //passaggio al div del dialog del nome e cognome ritornati nella response
						$( "#dialog-success" ).dialog({
							resizable: false,
							height: "auto",
							width: 400,
							modal: true,
							buttons: {
								OK: function() {
									$( this ).dialog( "close" );
									window.location.href ='adminController?page=visualAllUser';
								}
							}
						});

					}
				
				else if(response == 'existing' ){
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
			}
		});
	});

});