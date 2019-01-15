<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
  <div class="container"><br>
    <div class="mx-auto" style="width: 500px;">
     <h3 class="rounded text-primary">RISULTATO DELLA RICERCA:</h3><br>
     </div>
   <table class="table table-bordered ">
  <thead>
    <tr>
      <th scope="col">N</th>
      <th scope="col">Cognome</th>
      <th scope="col">Nome</th>
      <th scope="col">Telefono 1</th>
      <th scope="col">Telefono 2</th>
      <th scope="col">Email</th>
      <th scope="col">Data</th>
      <th scope="col">Azione</th>
      
    </tr>
  </thead>
  <tbody>
  <c:if test="${sessionScope.costumerList != null }">                                              <!-- controllo che la lista clienti in sessione è valida -->
    <c:forEach items="${sessionScope.costumerList}" var="cliente" varStatus="row" begin="0">       <!-- ciclo la lista clienti -->
      <tr>
        <th scope="row">${row.count}</th>
        <td>${cliente.getCognome()}</td>
        <td>${cliente.getNome()}</td>
        <td>${cliente.getTelefono_one()}</td>
        <td>${cliente.getTelefono_two()}</td>
        <td>${cliente.getEmail()}</td>
        <fmt:formatDate value="${cliente.getDataCreazione()}" pattern="d/M/y" var="data"/>
        <td><abbr title="Data di creazione o ultima modifica">${data}</abbr></td>
        <td>
         <abbr title="Clicca per il dettagli del cliente">
           <a href="<%=request.getContextPath() + "/customer?action=customer_detail&index="%> + ${row.count}">
           <img src="<%=request.getContextPath() + "/static/images/worker.png" %>" alt="Modifica Cliente"></a>
         </abbr>  
        </td>
        <td>
           <abbr title="Crea un Nuovo Appuntamento">
            <button type="button" class="btn btn-light btn-sm" 
              onclick="top.location.href='<%=request.getContextPath() + "/dispatcher?page=appointment_creation&customerIndex="%>'+ ${row.count}">
              <img src="<%=request.getContextPath() + "/static/images/new_appointment.png" %>"alt="Crea nuovo Appuntamento">                            <!-- creo un button con un link nella tabella dei clienti per creare un appuntamento e passo in querystring il numeor della riga 
                                                                                                                                            appartenente al cliente in questione per visualizzarelo nell jsp appointment_creation.jsp  -->
            </button>
          </abbr> 
        </td>
      </tr>
    </c:forEach>  
  </c:if>
  </tbody>
</table>
</div>

