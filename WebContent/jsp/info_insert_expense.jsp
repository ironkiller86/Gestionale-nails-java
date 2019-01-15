<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%-- @elvariable id="product" type="it.gestionalenails.data" --%>
<body>
   <br><br>
   <div>
      <div class="container">
        <div class="alert alert-primary w-75 p-3  mx-auto text-center" style="width: 500px;" role="alert">
            <fmt:setLocale value="it_IT"/>
              <strong>Spesa per Articolo/i  ${product.getProductName()} di 
              <fmt:formatNumber type="currency" value="${product.getPrice()}" currencySymbol="&euro;"/>  inserita con Successo!</strong>&emsp;
               <div class="text-center">
                 <h4 class="text-info">Vuoi Aggiungere altra Spesa?</h4>&emsp;
                 <a class="btn btn-success" href='
                   <c:url value="dispatcher">
                      <c:param name="page" value="add_expense"/>
                   </c:url >' role="button">Si</a>
                 <a class="btn btn-danger" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">NO</a>  
               </div>
        </div>
     </div>
   </div>
 
  