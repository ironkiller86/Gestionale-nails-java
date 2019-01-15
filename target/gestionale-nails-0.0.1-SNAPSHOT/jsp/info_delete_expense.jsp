<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%-- @elvariable id="product" type="it.gestionalenails.data" --%>
<body>
   <br><br>
   <div>
      <div class="container">
        <div class="alert alert-primary w-75 p-3  mx-auto text-center" style="width: 500px;" role="alert">
           <strong>Spesa selezionata eliminata con Successo!</strong>&emsp;
               <div class="text-center">
                 <h4 class="text-info">Vuoi cercare altra Spesa?</h4>&emsp;
                 <a class="btn btn-success" href='
                   <c:url value="dispatcher">
                      <c:param name="page" value="expense_search"/>
                   </c:url >' role="button">Si</a>
                 <a class="btn btn-danger" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">NO</a>  
               </div>
        </div>
     </div>
   </div>
 
  