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
           <th scope="col" class="text-center">ENTRATE TOTALI</th>
           <th scope="col" class="text-center">USCITE TOTALI</th>
         </tr>
       </thead>
       <tbody class="text-center">
         <tr>
            <td style="color:green;font-weight:bold;">
               <fmt:formatNumber type="currency" value="${entry}" currencySymbol="&euro;"/>
            </td>
            <td style="color:red;font-weight:bold;">
               <fmt:formatNumber type="currency" value="${expense}" currencySymbol="&euro;"/>
            </td>    
         </tr>
       </tbody>
     </table>
    <c:choose>
       <c:when test="${profit ge 0}">
         <div class="text-success text-center font-weight-bold">
           <h2>
             Utile:
              <fmt:formatNumber type="currency" value="${profit}" currencySymbol="&euro;"/>
           </h2><hr>
         </div>
      </c:when>
      <c:when test="${profit le 0}">
         <div class="text-danger text-center font-weight-bold">
           <h2>
             Utile:
               <fmt:formatNumber type="currency" value="${profit}" currencySymbol="&euro;"/>
           </h2><hr>
          </div>
      </c:when>
    </c:choose>
       <div class="text-center">
         <span>
           <abbr title="Sarica File PDF">
             <a class="btn btn-light" href='
                 <c:url value="accountingServlet">
                   <c:param name="operation" value="total_profit_pdf"/>
                 </c:url>' role="button">
               <img src="static/images/pdf.png" alt="creazione pdf">
             </a>
           </abbr> 
         </span>
            <h4 class="text-info">Vuoi effettuare un altra Ricerca?</h4>&emsp;
         <a class="btn btn-success btn-sm" href="<%=request.getContextPath() + "/dispatcher?page=profit"%>" role="button">SI</a>  
         <a class="btn btn-danger btn-sm" href="<%=request.getContextPath() + "/dispatcher?page=home"%>" role="button">NO</a> 
      </div>  
    </div>
