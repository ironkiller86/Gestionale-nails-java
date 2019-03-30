<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>         
<!doctype html>
<html lang="en">
<head>
	<title>Login Gestionale</title>
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

<!--==================================Jquery UI=============================================================-->
  
   <link rel="stylesheet" href="jsp/admin/static/jquery-ui-1.12.1/jquery-ui.min.css">
   <script src="jsp/admin/static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
   <script src="jsp/admin/static/jquery-ui-1.12.1/jquery-ui.min.js"></script>   
<!-- funzione controlli Creazione user -->
     <script type="text/javascript" src="static/js/controllCreateUser.js"></script> 

<script type="text/javascript">
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};





</script>

</head>
<body class="sfondo">
	
	<div class="limiter">
		<div class="container-login100 sfondo">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" action="<%=request.getContextPath() + "/loginValidation" %>" method="POST" name="login" >
					<span class="login100-form-title p-b-32">
						Area d'Accesso
						<script type="text/javascript">
                         var x = getUrlParameter("message");
                            if( x == "error/"){
	                           // alert("Nome Utente o Password Errati!")
                            	document.write("<br><p style='color:red'>Nome Utente o Password Errati!</p>");
                            }
                       </script>
                     
                  
					</span>

					<span class="txt1 p-b-11">
						Username
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome Utente non specificato">
						<input class="input100" type="text" name="username" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password non specificata">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="password" >
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
						<button class="login100-form-btn">
							Login
						</button>
					</div>
					
                    
				</form>
				<div class="text-right">
                  <a href="adminController?page=login" >
                     <abbr title="Accesso Dashboard Admin">
                      <img src="static/images/icon_admin.png"  alt="admin"> 
                     </abbr> 
                  </a>
	            </div>
			</div>
		</div>
	</div>
<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<!-- <script src="static/loginFile/vendor/jquery/jquery-3.2.1.min.js"></script> -->
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

<!-- dialog jquery end licenze -->
 <div id="dialog-endLicenze" title="Attenzione" style="display:none;">
   <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
     Licenza Software scaduta! Contatta Tuzzolino Donato.
   </p>
</div>
<c:if test="${sessionScope.flag != null}">
   <div id="flag3" style="display:none">${flag}</div>
</c:if>
</body>

</html>