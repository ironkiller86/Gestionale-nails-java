<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<body>

  <form class="container" name="appointmet_creation" action="<%=request.getContextPath() + "/businessAppointment?action=creation_work_appointment" %>" method="POST">
     <div class="form-row col-md-9 ml-md-auto ">
        <h2>Crea un nuovo Appuntamento:</h2>
     </div>
     <hr>
   <c:if test="${ sessionScope.customerApp != null}">
     <div class="form-row col-md-9 ml-md-auto ">
        <div class=" form-group col-xs-4">
           <label for="customer"><b>Cliente:</b></label>
           <input type="text" class="form-control" name="customer" id="customer" value="${customerApp.getCognome()} ${customerApp.getNome()} ">
        </div>
     </div> 
   </c:if>
     <div class="form-row col-md-9 ml-md-auto ">   
        <div class="form-group col-md-3">
           <label for="data"><b>Data Appuntamento:</b></label>  
           <input type="date" id="data" name="data_app" value="" min="2018-01-01" max="2100-12-31" required="required" class="form-control" />
       </div>
       <div class="form-group col-md-3"> 
           <label for="orarioApp"><b>Orario:</b></label>
           <input type="time" id="orarioApp" name="orarioApp" min="8:00" max="24:00" required class="form-control" />
       </div>
     </div>
     <div class="form-row col-md-9 ml-md-auto ">  
           <label for="descrizione"><b>Tipo Lavoro:</b></label>
           <textarea class="form-control" rows="4" cols="40" name="job_description" required="required" >
           
           </textarea>
     </div>
     <div class="form-row col-md-9 ml-md-auto ">
       <div class="form-group col-md-3"><br><br>
         <abbr title="Salva Appuntamento">
           <button type="submit" class="btn btn-light">
             <img src="static/images/floppy-disk.png"  alt="Salva Appuntamento"> 
           </button>
         </abbr> 
       </div>
     </div> 
  </form>
  

