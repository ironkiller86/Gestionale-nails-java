<body>
    <form class="container" name="appointmet_search" action="businessAppointment?action=appointment_search" method="POST">
       <div class="form-row col-md-9 ml-md-auto ">
          <h2 class="rounded text-primary text-center">Ricerca Appuntamento:</h2>
     </div>
    <hr>
    <div class="form-row col-md-9 ml-md-auto ">
        <div class=" form-group col-xs-4">
           <label id="l1" for="customer"><b>Nominativo Cliente:</b></label>
           <input type="text" class="form-control" name="nominative_search" value="" id="data2">
        </div>
        <div class="form-group col-md-3">
           <label id="l2" for="data"><b>Appuntamento del:</b></label>  
           <input type="date" id="data1" name="data_app" value="" min="2018-01-01" max="2100-12-31" class="form-control" />
       </div>
    </div>
    <div class="form-row col-md-9 ml-md-auto ">
        <div class="form-group form-check">
           <input type="checkbox" class="form-check-input" name="checkEnd" id="checkEnd" value="finish" >
           <label class="form-check-label" for="exampleCheck1"><b>Appuntamenti Terminati</b></label>
        </div>
    </div>
    <hr>
    <div class="form-row col-md-9 ml-md-auto ">
        <div class="form-group col-md-6 offset-3">
          <abbr title="Trova Appuntamento"> 
            <button type="submit" class="btn btn-light">
              <img src="<%=request.getContextPath() + "/static/images/search.png" %>"alt="Cerca Appuntamento">
            </button>
          </abbr>  
       </div>
    </div>
    </form>
 </body>
