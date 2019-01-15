<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body onload="multiplication()">
  <form class="container" name="expense" action="accountingServlet?operation=expense_update" method="POST">  <!-- non scrivo scriptlet perche l'ide mette il contentePath per me  -->
     <div class="form-row col-md-9 ml-md-auto ">
        <h2 class="text-center text-info">Modifica il Prodotto Acquistato:</h2><hr>
     </div>
     <hr>
     <div class="form-row col-md-9 ml-md-auto ">
        <div class="1 form-group col-xs-4">
           <label for="data_acquisto"><b id="l1">Data di Acquisto :</b></label>
              <input type="date" id="data_acquisto" name="data_acquisto" value="${data}" min="2018-01-01" max="2020-12-31" required="required" class="form-control" />
        </div>
     </div>
     <div class="form-row col-md-9 ml-md-auto ">   
        <div class="2 form-group col-xs-4">
           <label for="Qt"><b id="qt">Quantita':</b></label>
           <select name="qt" class="form-control" required="required" id="myList" onclick="multiplication()"  >
              <option value="${product.getQuantity()}">${product.getQuantity()}</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
           </select>
        </div>
        <div class="2 form-group col-xs-4">
           <label for="nome_prodotto"><b class="ml-5">Nome Prodotto:</b></label>
           <input type="text" class="form-control" name="nome_prodotto" id="customer" value="${product.getProductName()}" required="required">
        </div>
     </div>
     <div class="form-row col-md-9 ml-md-auto">  
        <div class="2 form-group col-xs-4">
            <label for=""><b>Costo Unit:</b></label>
            <input type="number" id="amount" style='width:100px;' min="0.00" max="1000.00" step="0.50" value="${product.getPrice()/product.getQuantity()}" 
            class="form-control" name="amount" placeholder="0,00" required="required" size="10" onchange="multiplication()" onkeypress="multiplication()">  
        </div>
         &emsp;&emsp;&emsp;&emsp;&emsp;
         <div class="2 form-group col-xs-4">
             <label for="totale"><b class="ml-5">Totale</b></label>
             <input type="text"  style="text-align: center;" class="form-control" name="totale" id="total_field" value="" readonly="readonly" size="10px">
         </div>    
     </div>
     <hr>
       <div class="form-row col-md-9 ml-md-auto text-right">
         <div class="form-group col-md-3"><br>
           <abbr title="Salva/Modifica Spesa per Prodotto">
            <button type="submit" class="btn btn-light">
              <img src="static/images/floppy-disk.png" alt="Aggiungi spesa">
            </button>
           </abbr> 
         </div>
      </div>
      <input name="id_product" type="hidden" value="${product.getIdProduct()}"/>   
  </form>   