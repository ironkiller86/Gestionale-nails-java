<body>
  <form class="container" name="search_expense" action="accountingServlet?operation=search" method="POST">  <!-- non scrivo scriptlet perche l'ide mette il contentePath per me  -->
     <div class="form-row col-md-9 ml-md-auto ">
        <h2 class="text-center text-info">Ricerca Spesa per Prodotti Acquistati:</h2><hr>
     </div>
     <hr>

     <div class="form-row col-md-9 ml-md-auto">
        <div class="1 form-group col-xs-4">
           <label for="product"><b class="ml-5">Nome Prodotto:</b></label>
           <input type="text" id="product" name="product"  class="form-control" />
        </div>
     </div>
     <div class="form-row col-md-9 ml-md-auto ">
         <div class="2 form-group col-xs-4">
           <label for="dataInsert"><b class="ml-5">Dal :</b></label>
           <input type="date" id="dataInsert" name="dataInsert" value="" min="2018-01-01" max="2100-12-31"  class="form-control" />
        </div>
        <div class="2 form-group col-xs-4">
           <label for="dataEnd"><b class="ml-5">al:</b></label>
           <input type="date" id="dataEnd" name="dataEnd" value="" min="2018-01-01" max="2100-12-31"  class="form-control" />
        </div>
     </div>
     <hr>
       <div class="form-row col-md-9 ml-md-auto text-right">
         <div class="form-group col-md-3"><br>
           <button type="submit" class="btn btn-info">Cerca</button>
         </div>
      </div>   
  </form>   