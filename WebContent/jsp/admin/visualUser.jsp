<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
    
<div>
<br>
   <div class="mx-auto text-center">
     <h3 class="rounded text-dark ">ELENCO UTENTI:</h3><br>
     </div>
 <div class="container" id="visualUser" style="overflow-x: auto; overflow-y: auto;">
 <form name="delete" action="adminController?action=delete_users" method="POST">
    <table class="table table-bordered">
      <thead>
       <tr>
         <th scope="col">N</th>
         <th scope="col">Id</th>
         <th scope="col">Data Creazione</th>
         <th scope="col">Cognome</th>
         <th scope="col">Nome</th>
         <th scope="col">Cod Fiscale</th>
         <th scope="col">Data di Nascita</th>
         <th scope="col">Citta di Nascita</th>
         <th scope="col">Indirizzo</th>
         <th scope="col">Citta Residenza</th>
         <th scope="col">Cap</th>
         <th scope="col">Telefono</th>
         <th scope="col">Email</th>
         <th scope="col">Username</th>
         <th scope="col">Password</th>
         <th scope="col">Data Attivazione</th>
         <th scope="col">Data Scadenza</th>
         <th scope="col">Ruolo</th>
         <th scope="col">Dettaglio</th>
         <th scope="col">Elimina</th>
       </tr>
     </thead>
     <tbody>
     <c:if test="${sessionScope.listaAllUser != null }">                                              
       <c:forEach items="${sessionScope.listaAllUser}" var="user" varStatus="row" begin="0">       
       <tr>
         <td>${row.count}</td>
         <fmt:formatDate value="${user.getDataCreazione().getTime()}" type="DATA" pattern="dd/MM/y" var="data"/>
         <td>${user.getId()}</td>
         <td>${data}</td>
         <td>${user.getCognome()}</td>
         <td>${user.getNome()}</td>     
         <td>${user.getCodFiscale()}</td>
         <fmt:formatDate value="${user.getDataDiNascita().getTime()}" type="DATA" pattern="dd/MM/y" var="data"/>
         <td>${data}</td>
         <td>${user.getCittaDiNascita()} </td>
         <td>${user.getIndirizzo()}</td>
         <td>${user.getCittaResidenza()}</td>
         <td>${user.getCap()}</td>
         <td>${user.getTelefono()}</td>
         <td>${user.getEmail()}</td> 
         <td>${user.getUsername()}</td>
         <td>${user.getPassword()}</td>
         <fmt:formatDate value="${user.getDataAttiv().getTime()}" type="DATA" pattern="dd/MM/y" var="data"/>
         <td>${data}</td>
         <fmt:formatDate value="${user.getDataScad().getTime()}" type="DATA" pattern="dd/MM/y" var="data"/>
         <td>${data}</td> 
         <c:choose>
           <c:when test="${user.isRole()}">
              <td><abbr title="Admin"><img alt="Admin" src="static/images/admin.png"></abbr></td>
           </c:when>
           <c:when test="${!user.isRole()}">
              <td><abbr title="Utente"><img alt="Admin" src="static/images/customer.png"></abbr></td>
           </c:when>
         </c:choose>  
          <td>
             <abbr title="Clicca per dettaglio Utente">
                <a href='<c:url value="adminController">
                           <c:param name="page" value="userDetail"/>
                           <c:param name="rowApp" value="${row.count}"/>
                           <c:param name="type" value="list"/>
                        </c:url>'>
                   <img src="static/images/processes.png" alt="Dettaglio Utente"> 
                </a> 
             </abbr>
          </td>
          <td>
            <input type="checkbox" name="my_check" class="myCheck" onclick="activeCheckbox()" value="${row.count}">
          </td>
        </tr>
       </c:forEach>  
     </c:if>
     </tbody>
    </table>
    <div id="delete">
        <abbr title="Elimina Utente selezionato">
           <button type="submit" id="button" class="btn btn-ligh text-right" style="display:none;">
              <img src="<%=request.getContextPath() + "/static/images/delete.png" %>"alt="Elimina Appuntamento">
           </button>
         </abbr>
     </div>
  <hr>
 </form>
 
</div>
</div>
<c:if test="${requestScope.flag != null}">
   <div id="flag" style="display:none;">${flag}</div>
</c:if>
<!-- dialog jquery delete -->
 <div id="dialog-delete" title="Avviso" style="display:none;">
   <p><span class="ui-icon ui-icon-check" style="float:left; margin:12px 12px 20px 0;"></span>
     Utente Eliminato/i!
   </p>
</div>




</body>
</html>