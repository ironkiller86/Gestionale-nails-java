<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
  
<!doctype html>
<html lang="it">
  <head>
   <meta name="keywords" content="AJAX,JSP,Jquery" />
    <meta charset="utf-8">  
    <!-- Required meta tags -->
      
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- file footer -->
   
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="footer, address, phone, icons" />
   
	<link rel="stylesheet" href=<%=request.getContextPath() + "/static/css/footer/footer.css"%>>

    <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="static/css/bootstrap/bootstrap.min.css"> 

    <!-- Bootstrap CSS footer-->
     
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    
    
    <!--  link alle mie funzioni javascript personalizzate -->
    <script type="text/javascript" src="static/js/function.js"></script>
    <script type="text/javascript" src="static/js/altro.js"></script> 
    <script type="text/javascript" src="static/js/function_one.js"></script>
     <script type="text/javascript" src="static/js/utility.js"></script>
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/static/css/img.css"%>"> 


 <!-- ----------------------------full calendar-------------------------------------------- -->
    <link rel='stylesheet' href="static/fullcalendar/fullcalendar.css"/>
    <script src="static/fullcalendar/lib/jquery.min.js"></script> 
    <script src="static/fullcalendar/lib/moment.js"></script>
    <script src="static/fullcalendar/fullcalendar.min.js"></script>
    <script src="static/fullcalendar/locale/it.js"></script>
  
    <script>
    $(document).ready(function() {

        var calendar = $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
                    },
                selectable: true,
                height: 650,
                selectHelper: true,

            select: function(start, end, allDay) {
                    var title = prompt('Event Title:');
                    if (title) {
                        calendar.fullCalendar('renderEvent',
                        {
                            title: title,
                            start: start,
                            end: end,
                            allDay: allDay
                        },
                        true // make the event "stick"
                        );
                        }
                        calendar.fullCalendar('unselect');
                    },
                            editable: true,

                            eventSources: [
                                {
                                        url: 'updateApp',
                                        type: 'POST',
                                        color: 'pink',   // an option!
                                        textColor: 'black', // an option!
                                       /* data: {
                                            start: 'dataApp',
                                            end: 'end',
                                            id: 'nominativo',
                                            title: 'descrizione',
                                            allDay: 'allDay'
                                        },*/
                                        error: function () {
                                          alert('there was an error while fetching events!');
                                        }
                                }
                        ]      
                });
        });
       
     </script>
    


<!-- -------------------------------------------------------->
 
    <title>Home Gestionale</title>
    
  
    
  </head>
   
  <header>
   <nav class="navbar navbar-expand-sm navbar navbar-dark bg-dark" > 
   <c:if test="${sessionScope.userLog != null }">
     <p class="navbar-brand">Utente: ${userLog.getUsername().toUpperCase()}</p>
   </c:if>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"> <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath() +"/dispatcher?page=home"%>">Home</a>
      </li>
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Gestione Clienti
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=request.getContextPath() +"/dispatcher?page=create_costumer"%>">Crea Nuovo Cliente</a>
          <a class="dropdown-item" href="<%=request.getContextPath() + "/dispatcher?page=appointment_search"%>">Cerca appuntamento </a>
        </div>
     </li>
      <li class="nav-item dropdown">
         <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
           Area Contabilita'
         </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href='<c:url value="dispatcher">
            <c:param name="page" value="add_expense"/>
           </c:url>'>Aggiungi Spesa</a>
            <a class="dropdown-item" href='<c:url value="dispatcher">
            <c:param name="page" value="expense_search"/>
           </c:url>'>Cerca/Modifica Spesa</a>
            <a class="dropdown-item" href='<c:url value="dispatcher">
              <c:param name="page" value="accounting"/>
              </c:url>'>Visualizza Entrate
            </a>
            <a class="dropdown-item" href='<c:url value="dispatcher">
            <c:param name="page" value="profit"/>
           </c:url>'>Visualizza Utili</a>
          </div>
      </li>
     
      <!-- Button che  attiva la finestra modal -->
    <li class="nav-item">
        <abbr title="Effettua il Logout">
          <button type="button" class="btn btn-light btn-sm" data-toggle="modal" data-target="#exampleModal">
          <img src="static/images/door.png"  alt="Logout"> 
          </button>
        </abbr>  
      </li>      
    </ul>
     
   
    <!----------------------------- Modal--------------------------------- -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Attenzione</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Effettuare il logout?
      </div>
      <div class="modal-footer">
      <form action="<%=request.getContextPath() + "/logout"%>" method="GET">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">NO</button>
        <input type="submit" value="SI" class="btn btn-primary">
     </form>
      </div>
    </div>
  </div>
</div>
    
  <!------------------------------------------------------------------------------------->
    
      

    
    
    <form class="form-inline my-2 my-lg-0" name="customer_search" action="<%=request.getContextPath() + "/customer?action=customer_search" %>" method="POST" >
      <input class="form-control mr-sm-2" type="search" required="required"  aria-label="Search" name="customer">
        <abbr title="Effettua ricerca per Cognome, Nome, Numero di telefono o Email">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Ricerca cliente</button>
        </abbr>
    </form>
  </div>
</nav>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
   <!--   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
   
  </header>
  
</html>