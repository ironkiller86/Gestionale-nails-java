<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<body><br>
   <div class="mx-auto text-center">
     <h3 class="rounded text-dark ">RISULTATO DELLA RICERCA:</h3><br>
     </div>
 <div class="container">
 <form name="delete" action="businessAppointment?action=delete_appointment" method="POST">
    <table class="table table-bordered">
      <thead>
       <tr>
         <th scope="col">N</th>
         <th scope="col">Data/Orario Appuntamento </th>
         <th scope="col">Cognome</th>
         <th scope="col">Nome</th>
         <th scope="col">Descrizione Lavoro</th>
         <th scope="col">Totale</th>
         <th scope="col">Immagine</th>
         <th scope="col">Stato</th>
         <th scope="col">Azione</th>
         <th scope="col">Elimina</th>
       </tr>
     </thead>
     <tbody>
     <c:if test="${sessionScope.List_appointment_find != null }">                                              <!-- controllo che la lista appuntamenti in sessione è valida -->
       <c:forEach items="${sessionScope.List_appointment_find}" var="appointment" varStatus="row" begin="0">       <!-- ciclo la lista appuntament -->
       <tr>
        <td>${row.count}</td>
            <!-- formato la data appuntamento  -->
            <fmt:formatDate value="${appointment.getDataAppuntamento().getTime()}" type="BOTH" pattern="dd/MM/y kk:mm " var="data"/>
             <td>${data}</td>
           <c:forEach items="${sessionScope.costumerList}" var="customer" varStatus="row_costumer"  begin="0"> <!-- cicli nidificati per trovare in base all' id_app il nominativo del cliente -->
              <c:if test="${appointment.getIdClienteApp() eq customer.getId_cliente()}">                 <!-- se id_cliente_app è uguale all'id della lista cliente ho trovato il  cognome e nome che mi interessano -->
                  <td>${customer.getCognome()}</td>
                  <td><c:out value="${customer.getNome()}"/></td><!--******-->
              </c:if>
           </c:forEach>
                  <td>${appointment.getDescrizioneLavoro()}</td>
                  
           <c:choose>
              <c:when test="${appointment.isStatoAppuntamento() eq 'true'}">
              <td>
              <fmt:setLocale value="it_IT"/>
              <fmt:formatNumber type="currency" value="${appointment.getPrezzo()}" currencySymbol="&euro;"></fmt:formatNumber>
              </td>
               <td><img id="image" src="businessAppointment?action=read_image&path=${appointment.getPathImage()}" width="40px" height="40px"></td>
                  <td><abbr title="Lavoro Completato"><img src="<%=request.getContextPath() + "/static/images/working_complete.png" %>" alt="Lavoro Completo"></abbr></td>
                  <td>//</td>
                  <td>//</td>
              </c:when>
              <c:otherwise>
                   <td>//</td> 
                  <td>//</td>
                  <td><abbr title="Lavoro da effettuare"><img src="<%=request.getContextPath() + "/static/images/working_inprogress.png" %>"alt="Lavoro da Completare"></abbr></td>
                  <td>
                    <abbr title="Clicca per modifica l'Appuntamento">
                      <a href='<c:url value="dispatcher">
                              <c:param name="page" value="appointment_modify"/>
                              <c:param name="rowApp" value="${row.count}"/>
                              <c:param name="type" value="list"/>
                           </c:url>'>
                           <img src="<%=request.getContextPath() + "/static/images/processes.png" %>" alt="Modifica Appuntamento"> 
                      </a> 
                    </abbr>
                  </td>
                  <td><input type="checkbox" name="my_check" class="myCheck" onclick="activeCheckbox()" value="${row.count}"></td> <!-- passo il numero di riga al dispatche come parametro  -->
              </c:otherwise>
           </c:choose>
        </tr>
       </c:forEach>  
     </c:if>
     </tbody>
    </table>
  <hr>
  <div class="form-row col-md-9 ml-md-auto">
     <div  class="form-group col-md-3 offset-3"> 
         <abbr title="Effettua Nuova Ricerca">
            <a class="btn btn-light" href="<%=request.getContextPath() + "/dispatcher?page=appointment_search"%>" role="button">
              <img src="<%=request.getContextPath() + "/static/images/search.png" %>"alt="Effettua Nuova Ricerca">
            </a>  
        </abbr>     
     </div>
     <div  class="form-group col-md-3"> 
         <abbr title="Elimina Appuntamento selezionato">
           <button type="submit" id="button" class="btn btn-ligh text-right" style="display:none;">
              <img src="<%=request.getContextPath() + "/static/images/delete.png" %>"alt="Elimina Appuntamento">
           </button>
         </abbr>
     </div>
  </div>
</form>
 
 
</div>

