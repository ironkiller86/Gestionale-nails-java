<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
	<title>Login Page</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="static/loginFile/images/icons/favicon.ico">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/loginFile/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/loginFile/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/loginFile/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/loginFile/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="static/loginFile/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/loginFile/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/loginFile/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="static/loginFile/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/loginFile/css/util.css">
	<link rel="stylesheet" type="text/css" href="static/loginFile/css/main.css">
<!--===============================================================================================-->

<!--======================Jquery=========================================================================-->
 <script src="static/fullcalendar/lib/jquery.min.js"></script> 
<!--===============================================================================================-->

<!--==================================Jquery UI=============================================================-->
   <link rel="stylesheet" href="jsp/admin/static/jquery-ui-1.12.1/jquery-ui.min.css">
   <script src="jsp/admin/static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
   <script src="jsp/admin/static/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<!-- Bootstrap CSS -->
       <link rel="stylesheet" href="static/css/admin.css"> 


</head>
<body>
	<div class="limiter">
		<div class="container-login100" id ="sfondo">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
                 <span class="login100-form-title p-b-32">
						Login  Admin
						<script type="text/javascript">
                         
                            $(document).ready(function() {  
                            $( "#target" ).click(function() {
                            	 var user = $("#usr").val();      //recupero utente
                            	 var pass = $("#psw").val();  //recupero password
                            	 if(user != '' && pass != '' ){        //controllo campi
                            	    var jsonLogin = {utente: user, password: pass};
                            	    $.ajax({
                                   	  //async:false,
                                   	  contentType: "application/json",
                                         type:"POST",
                                         url:"adminController?action=loginControll",
                                        // dataType:"json",
                                         data: JSON.stringify(jsonLogin),
                                         success:function(response){   //leggo in caso di success di ajax la il parametro scritto in response lato server
                                           var resp = response;
                                               if(resp == 'noAdmin'){
                                        		 $( function() {                        //funzione dialog jquery
                                     			    $( "#dialog-role" ).dialog({
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
                                               else if(resp === 'noMatch'){
                                            	   $( function() {                        //funzione dialog jquery
                                        			    $( "#dialog-match" ).dialog({
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
                                               else if(resp === 'admin'){
                                            	   window.location.href ='adminController?page=homePage';
                                               }
                                               
                                        	
                                         }
                            	        
                                         
                                    });
                            	 }
                            	 else {
                            		  $( function() {                        //se uno dei campi e vuoto, chiamata jquery ui dialog
                            			    $( "#dialog-confirm" ).dialog({
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
                            	 
                            	 
                            	 
                            	
                            	
                            	});
                            });
                       </script>
                     
                  
					</span>

					<span class="txt1 p-b-11">
						Username
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome Utente non specificato">
						<input class="input100" type="text" name="username" id="usr" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password non specificata">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="password" id="psw" >
						<span class="focus-input100"></span>
					</div>
					
					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">
								Ricordami
							</label>
						</div>

					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn" id="target">
							Login
						</button>
				   </div>
				
                    
				<!--  </form>-->
				<div class="text-right">
                   <a href="dispatcher?page=loginPage">
                     <abbr title="Vai alla Home Utente">
                      <img src="static/images/go-home.png"  alt="UserHome"> 
                     </abbr> 
                  </a>
	            </div>
			</div>
		</div>
	</div>
<div id="dropDownSelect1"></div>
	

<!--===============================================================================================-->
	<script src="static/loginFile/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="static/loginFile/vendor/bootstrap/js/popper.js"></script>
	<script src="static/loginFile/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="static/loginFile/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="static/loginFile/vendor/daterangepicker/moment.min.js"></script>
	<script src="static/loginFile/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="static/loginFile/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="static/loginFile/js/main.js"></script>

<!-- dialog jquery ui campi login -->
 <div id="dialog-confirm" title="Attenzione" style="display:none;">
   <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
     I campi Username o Password non possono essere vuoti
   </p>
</div>
<!-- dialog jquery ui  login no permessi -->
<div id="dialog-role" title="Attenzione" style="display:none;">
   <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
    Non hai permessi per accedere a questa sezione!
   </p>
</div>
<!-- dialog jquery ui  login no Mach -->
<div id="dialog-match" title="Attenzione" style="display:none;">
   <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
    Username o Password errati!
   </p>
</div>
</body>

</html>