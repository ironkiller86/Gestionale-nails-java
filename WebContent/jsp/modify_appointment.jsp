<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<body>
  <form class="container" name="appointmet_creation" action="<%=request.getContextPath() + "/businessAppointment?action=modify_work_appointment" %>"enctype="multipart/form-data" method="POST" >
     <div class="form-row col-md-9 ml-md-auto ">
        <h2>Modifica o completa l'Appuntamento:</h2>
     </div>
     <hr>
   <c:set var="costumerList" value="${sessionScope.costumerList}"/>        <!-- recupero dalla sessione la lista dei clienti -->
   <c:set var="appointment" value="${sessionScope.appointment_object}"/>   <!-- setto la variabile con l'appuntamento in session da visualizzare -->
     <div class="form-row col-md-9 ml-md-auto ">
        <div class=" form-group col-xs-4">
           <label for="customer"><b>Cliente:</b></label>
           <c:forEach items="${costumerList}" var="customer" begin="0">    <!-- iterp tutte le foreign key della tabella appuntamento con l'id dei clienti per estrarre dalla lista dei clienti quello da visualizzare -->
             <c:if test="${customer.getId_cliente() eq appointment.getIdClienteApp()}">
             <c:set var="customer" value="${customer}" scope="session"/>     <!-- Metto in sessione l'oggetto cliente in questione *****-->
                <input type="text" class="form-control" name="customer" id="customer" value="${customer.getCognome()} ${customer.getNome()} ">
             </c:if>
           </c:forEach>
        </div>
     </div> 
     <div class="form-row col-md-9 ml-md-auto ">   
        <div class="form-group col-md-3">
           <label for="data"><b>Data Appuntamento:</b></label>
           <fmt:formatDate value="${appointment.getDataAppuntamento().getTime()}" type="DATE" pattern="y-MM-dd" var="date"/> 
           <input type="date" id="data" name="data_app" value="${date}" min="2018-01-01" max="2100-12-31" required="required" class="form-control" />
       </div>
       <div class="form-group col-md-3"> 
           <label for="orarioApp"><b>Orario:</b></label>
           <fmt:formatDate value="${appointment.getDataAppuntamento().getTime()}" type="TIME" pattern="kk:mm" var="time"/> 
           <input type="time" id="orarioApp" name="orarioApp" value="${time}" min="8:00" max="24:00" required class="form-control" />
       </div>
     </div>
     <div class="form-row col-md-9 ml-md-auto ">  
           <label for="descrizione"><b>Tipo Lavoro:</b></label>
           <textarea class="form-control" rows="3" cols="40" name="job_description" required="required" >
           ${appointment.getDescrizioneLavoro()}
           </textarea>
         <div class="form-group col-md-3"> 
           <b>Lavoro Terminato:</b>
           <div class="form-check">
              <input class="form-check-input" type="radio" name="radiob" id="radio_one" value="end_work" onclick="functionRadioActive()">  <!-- evento che invoca la funzione per attivare il campo prezzo e carica immagine -->
              <label class="form-check-label" for="radio" >SI</label>
           </div>
           <div class="form-check">
              <input class="form-check-input" type="radio" name="radiob" id="radio_two" value="incomplete" checked="checked" onclick="functionRadioDisability()" > <!-- funzione che disattiva il campo prezzo e carica immagine -->
              <label class="form-check-label" for="radio">NO</label>
           </div>
        </div>
        <div class="form-group col-md-3"  id="importo" style="display: none">   <!-- di default elemento non visuliazzato -->
             <label for="importo"><b>Totale Euro:</b></label>
             <input type="number" min="0.00" max="1000.00" step="0.50" value="${appointment.getPrezzo()}" class="form-control" name="amount" placeholder="0,00">
        </div>        
    </div>
     <div class="form-row col-md-9 ml-md-auto ">
        <div class="form-group col-md-3"  id="load_image" style="display: none">   <!-- di default elemento non visuliazzato -->
           <b>Carica Immagine:</b>
           <input type="file" name="fname" class="form-control" >
        </div>    
     </div>
     <div class="form-row col-md-9 ml-md-auto ">
       <div class="form-group col-md-3"> 
        <input id="img" type="submit" value="salva">
         
       </div>
     </div> 
  </form>
  

