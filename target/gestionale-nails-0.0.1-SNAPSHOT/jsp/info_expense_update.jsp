<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%-- @elvariable id="product" type="it.gestionalenails.data" --%>
<body>
   <br><br>
  <div>
   <div class="container">
     <c:choose>
        <c:when test="${update eq true}">
          <div class="alert alert-primary w-75 p-3  mx-auto text-center" style="width: 500px;" role="alert">
                <strong>Spesa per Articolo/i  ${product.getProductName()} aggiornata Correttamente!</strong>&emsp;
                 <div class="text-center">
                   <h4 class="text-info">Vuoi cercare altre spese effuttuate?</h4>&emsp;
                   <a class="btn btn-success" href='
                   <c:url value="dispatcher">
                      <c:param name="page" value="expense_search"/>
                   </c:url >' role="button">Si</a>
                 <a class="btn btn-danger" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">NO</a>  
               </div>
          </div>
        </c:when>
        <c:when test="${update eq false }">
          <div class="alert alert-primary w-75 p-3  mx-auto text-center" style="width: 500px;" role="alert">
              <strong>Nussuna modifica per Articolo/i  ${product.getProductName()} !</strong>&emsp;
               <div class="text-center">
                 <h4 class="text-info">Vuoi Cercare altre Spese effuttuate?</h4>&emsp;
                 <a class="btn btn-success" href='
                   <c:url value="dispatcher">
                      <c:param name="page" value="expense_search"/>
                   </c:url >' role="button">Si</a>
                 <a class="btn btn-danger" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">NO</a>  
               </div>
          </div>
        </c:when>
     </c:choose>
   </div>
  </div>
 
  