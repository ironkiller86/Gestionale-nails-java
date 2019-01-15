   
<body>
 <br><br>
   <div>
      <div class="container">
        <div class="alert alert-primary w-75 p-3  mx-auto text-center" style="width: 500px;" role="alert" >
           <strong>Nessun Spesa trovato in base al criterio di Ricerca!</strong>&emsp;
           <p>Cerca Ancora?</p>
           <a class="btn btn-success" href="<%=request.getContextPath() + "/dispatcher?page=expense_search"%>" role="button">SI</a>  
           <a class="btn btn-danger" href="<%=request.getContextPath() + "/dispatcher?page=home"%>" role="button">NO</a> 
        </div>
     </div>
   </div>
  