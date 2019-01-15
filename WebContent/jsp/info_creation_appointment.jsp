<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<body>
<c:choose>
 <c:when test="${requestScope.flag_appointment == 'new' }">
   <br><br>
   <div>
      <div class="container">
        <div class="alert alert-primary w-75 p-3  mx-auto" style="width: 500px;" role="alert" >
           <strong>Appuntamento creato Correttamente! </strong>&emsp;
           <a class="btn btn-success" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">OK</a>  
          
        </div>
     </div>
   </div>
   </c:when>
   <c:when test="${requestScope.message == 'no_equal' }">
    <br><br>
   <div>
      <div class="container">
        <div class="alert alert-success w-75 p-3  mx-auto" style="width: 500px;" role="alert" >
           <strong>Cliente modificato con successo!</strong>&emsp;
           <a class="btn btn-success" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">OK</a>
        </div>
     </div>
   </div>
   </c:when>
</c:choose>