<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<body>
<c:choose>
 <c:when test="${requestScope.flag_costumer == 'ok' }">
   <br><br>
   <div>
      <div class="container">
        <div class="alert alert-dark w-75 p-3  mx-auto" style="width: 500px;" role="alert" >
           <strong>Cliente Creato Corretamente</strong>&emsp;
           <a class="btn btn-success" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">OK</a>
        </div>
     </div>
   </div>
   </c:when>
   <c:otherwise>
    <br><br>
   <div>
      <div class="container">
        <div class="alert alert-warning w-75 p-3  mx-auto" style="width: 500px;" role="alert" >
           <strong>Attenzione nessun Nominativo trovato!</strong>&emsp;
           <a class="btn btn-success" href="<%=request.getContextPath() +"/dispatcher?page=home"%>" role="button">OK</a>
        </div>
     </div>
   </div>
   </c:otherwise>
   
</c:choose>