<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body><br>
  <div class="mx-auto text-center" >
     <h3 class="rounded text-dark">RISULTATO DELLA RICERCA:</h3><br>
  </div>
  <div class="container">
  <form name="delete_expense" id="delete" action="accountingServlet?operation=deleteExpense" method="POST"> 
      <table class="table table-bordered margin-right: 1rem ">   
        <thead>
          <tr class="text-dark">
           <th scope="col" class="text-center">N</th>
           <th scope="col" class="text-center">Data</th>
            <th scope="col" class="text-center">Qty</th>
           <th scope="col" class="text-center">Descrizione</th>
           <th scope="col" class="text-center">Importo</th>
           <th scope="col" class="text-center">Modifica</th>
           <th scope="col" class="text-center">Elimina</th>
         </tr>
       </thead>
       <tbody class="text-center">
         <c:forEach items="${expenseList}" var="expense" varStatus="row" begin="0">  
           <tr>
            <th scope="row">${row.count}</th>
               <fmt:formatDate value="${expense.getDate().getTime()}" type="DATE" pattern="dd/MM/y" var="data"/>
                 <td>${data}</td>
                 <td>${expense.getQuantity()}</td> 
                 <td>${expense.getProductName()}</td>
                 <td>
                    <fmt:setLocale value="it_IT"/>
                    <fmt:formatNumber type="currency" value="${expense.getPrice()}" currencySymbol="&euro;"/>
                 </td>
                 <td>
                   <abbr title="Modifica Prodotto Acquistato">
                     <a  href='<c:url value="accountingServlet">
                         <c:param name="operation" value="expense_modification"/>
                         <c:param name="row" value="${row.count}"/>
                       </c:url>' target="_blank">
                       <img src="static/images/expense_edit.png" alt="Modifica Spesa">
                     </a>
                   </abbr>
                 </td>
                 <td>
                   <input type="checkbox" name="my_check" class="myCheck" onclick="activeCheckbox()" value="${row.count}">
                 </td>
           </tr>
         </c:forEach> 
       </tbody>
     </table>
  </form>  
       <fmt:setLocale value="it_IT"/>
      <div class="text-danger text-center font-weight-bold">
       <h2>
        Totale Uscite:
        <fmt:formatNumber type="currency" value="${totalExpense}" currencySymbol="&euro;"/>
       </h2><hr>
      </div>
      <div class="text-center">
        <div  class="form-group col-md-3 ml-md-auto"> 
         <abbr title="Elimina Prodotto selezionato">
           <button type="submit" form="delete" id="button"  class="btn btn-ligh text-right" style="display:none;">  <!--  -->
              <img src="<%=request.getContextPath() + "/static/images/delete.png" %>"alt="Elimina prodotto">
           </button>
         </abbr>
       </div>
       </div>
       <div class="text-center">
         <span>
           <abbr title="Sarica File PDF">
             <a class="btn btn-light" href='
                 <c:url value="accountingServlet">
                   <c:param name="operation" value="total_expense_pdf"/>
                 </c:url>' role="button">
               <img src="static/images/pdf.png" alt="creazione pdf">
             </a>
           </abbr> 
         </span>
            <h4 class="text-info">Vuoi effettuare un altra Ricerca?</h4>&emsp;
         <a class="btn btn-success btn-sm" href="<%=request.getContextPath() + "/dispatcher?page=expense_search"%>" role="button">SI</a>  
         <a class="btn btn-danger btn-sm" href="<%=request.getContextPath() + "/dispatcher?page=home"%>" role="button">NO</a> 
      </div>  
    </div>
