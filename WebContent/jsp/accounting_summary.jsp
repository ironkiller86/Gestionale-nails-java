<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body><br>
  <div class="mx-auto text-center" >
     <h3 class="rounded text-dark">RISULTATO DELLA RICERCA:</h3><br>
  </div>
  <div class="container">
    <table class="table table-bordered margin-right: 1rem ">   
       <thead>
        <tr class="text-dark">
          <th scope="col">N</th>
          <th scope="col">Data</th>
          <th scope="col">Cognome</th>
          <th scope="col">Nome</th>
          <th scope="col">Descrizione</th>
          <th scope="col">Pagato</th>
        </tr>
       </thead>
       <tbody>
         <c:forEach items="${requestScope.accounting_appointment}" var="appointment" varStatus="row" begin="0">  <!-- recupero dalla servlet gli appuntamenti che mi interessano -->
           <tr>
            <th scope="row">${row.count}</th>
              <c:forEach items="${requestScope.customer_accounting}" var="customer"  begin="0">     <!-- recupero dalla request tutti i clienti nel db -->           
                <c:if test="${appointment.getIdClienteApp() eq customer.getId_cliente()}">  <!-- li ciclo per estrarmi il nominativo del cliente -->
                  <fmt:formatDate value="${appointment.getDataAppuntamento().getTime()}" type="BOTH" pattern="dd/MM/y kk:mm" var="data"/>
                  <td>${data}</td> 
                  <td>${customer.getCognome()}</td>
                  <td>${customer.getNome()}</td>
                </c:if>
              </c:forEach>
                  <td>${appointment.getDescrizioneLavoro()}</td>
                  <td>
                   <fmt:setLocale value="it_IT"/>
                   <fmt:formatNumber type="currency" value="${appointment.getPrezzo()}" currencySymbol="&euro;"/> 
                  </td>
           </tr>
         </c:forEach> 
       </tbody>
    </table>
       <fmt:setLocale value="it_IT"/>
      <div class="text-success text-center font-weight-bold">
       <h2>
        Totale Incassato:
        <fmt:formatNumber type="currency" value="${sessionScope.totalMoney}" currencySymbol="&euro;"/>
       </h2><hr>
      </div>
      <div class="text-center">
         <span>
           <abbr title="Sarica File PDF">
             <a class="btn btn-light" href='
                 <c:url value="accountingServlet">
                   <c:param name="operation" value="total_amount"/>
                 </c:url>' role="button">
               <img src="static/images/pdf.png" alt="Effettua Nuova Ricerca">
             </a>
               <c:set var="appointmentList" value="${requestScope.accounting_appointment}" scope="session"/> <!-- meetto in session gli appuntamenti del conteggio per creare pdf -->
               <c:set var="costumerList" value="${requestScope.customer_accounting}" scope="session"/>       <!-- meetto in session i clienti del conteggio per creare pdf -->
           </abbr> 
         </span>
            <h4 class="text-info">Vuoi effettuare un altra Ricerca?</h4>&emsp;
         <a class="btn btn-success btn-sm" href="<%=request.getContextPath() + "/dispatcher?page=accounting"%>" role="button">SI</a>  
         <a class="btn btn-danger btn-sm" href="<%=request.getContextPath() + "/dispatcher?page=home"%>" role="button">NO</a> 
      </div>  
 </div> 
