<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Controllo</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!--==================================Jquery UI=============================================================-->
  
   <link rel="stylesheet" href="jsp/admin/static/jquery-ui-1.12.1/jquery-ui.min.css">
   <script src="jsp/admin/static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
   <script src="jsp/admin/static/jquery-ui-1.12.1/jquery-ui.min.js"></script>   
   
<!-- funzione logOut -->
     <script type="text/javascript" src="static/js/logout.js"></script>
<!-- funzione controlli Creazione user -->
     <script type="text/javascript" src="static/js/controllCreateUser.js"></script>     
<!-- funzione finestre avvisi user -->
     <script type="text/javascript" src="static/js/userSave.js"></script>     
<!-- funzione attiva tasto elimina utenti -->    
     <script type="text/javascript" src="static/js/function_one.js"></script>          
<!-- Bootstrap CSS -->
     <link rel="stylesheet" href="static/css/admin.css">      
</head>
<body>
<header>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <p class="navbar-brand text-success">DashBoard</p>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  
    
  </div>
  <div id="search">
  <form name="creazione utente" class="form-inline my-2 my-lg-0"  action="adminController?action=searchUser" method="POST" >
      <input class="form-control mr-sm-2" type="search" required="required" name="field" aria-label="Search">
        <abbr title="Effettua ricerca per Cognome, Nome, Numero di telefono o Email">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Ricerca Utente</button>
        </abbr>
    </form>
    </div>
  <div class="nav-item">
    <c:if test="${sessionScope.userLogAdmin != null }">
      <p class="text-success">Admin: ${userLogAdmin.getUsername().toUpperCase()}</p>
    </c:if>
  </div>
</nav>
<c:if test="${requestScope.flag != null}">
   <div id="flag2" style="display:none">${flag}</div>
</c:if>
<!-- Dialog logOut -->
<div id="dialog-logOut" title="LogOut" style="display:none;" >
   <p>
     <span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
      Vuoi Uscire dalla Dashboard?
   </p>
</div> 
<!-- dialog jquery info ricerca -->
 <div id="dialog-infoRicerca" title="Avviso" style="display:none;">
   <p><span class="ui-icon ui-icon-check" style="float:left; margin:12px 12px 20px 0;"></span>
     Nessuno Utente trovato in base al campo di ricerca!
   </p>
</div>

</header>
