
<body>
  <form class="container" name="accounting" action="accountingServlet?operation=resume" method="POST">  <!-- non scrivo scriptlet perche l'ide mette il contentePath per me  -->
     <div class="form-row col-md-9 ml-md-auto ">
        <h2 class="text-center text-info">Visualizza Entrate per Periodo:</h2><hr>
     </div>
     <hr>

     <div class="form-row col-md-9 ml-md-auto ">
        <div class="1 form-group col-xs-4">
           <label for="customer"><b id="l1">Appuntamenti dal :</b></label>
           <input type="date" id="data1" name="data_start" value="" min="2018-01-01" max="2020-12-31"   class="form-control" />
        </div>
        <div class="2 form-group col-xs-4">
           <label for="customer"><b id="l2">al :</b></label>
           <input type="date" id="data2" name="data_end" value="" min="2018-01-01" max="2020-12-31"  class="form-control" />
        </div>
     </div> 
     <div class="form-row col-md-9 ml-md-auto ">   
        <div class="form-group form-check">
           <input type="checkbox" class="form-check-input" name="checkEnd" id="checkEnd" value="to_day" onclick="disable()" >
           <label class="form-check-label" for="exampleCheck1"><b>Incasso giornata</b></label>
        </div>
      
     </div><hr>
       <div class="form-row col-md-9 ml-md-auto text-right ">
         <div class="form-group col-md-3"><br>
           <button type="submit" class="btn btn-info">Cerca</button>
         </div>
      </div>   
  </form>   
    
    
   