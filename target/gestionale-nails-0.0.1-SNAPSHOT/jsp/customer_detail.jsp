
</body>
<form class="container" name="createCustomer" action="<%=request.getContextPath() + "/customer?action=updates_customer" %>" method="POST">
  <div class="form-row col-md-9 ml-md-auto ">
      <h2>Dettaglio Cliente:</h2>
  </div>
  <div class="form-row col-md-9 ml-md-auto ">
    <div class=" form-group col-md-3">
      <label for="cognome">Cognome</label>
      <input type="text" class="form-control" value="${customer.getCognome()}" name="cognome" id="cognome" required="required">
    </div>
    <div class="form-group col-md-3">
      <label for="name">Nome</label>
      <input type="text" class="form-control" value="${customer.getNome()}" name="nome" id="nome"  required="required">
    </div>
  </div>
  
  <div class="form-row col-md-9 ml-md-auto">
  <div class=" form-group col-md-3">
    <label for="phone_one">Numero di Cellulare</label>
    <input type="text" class="form-control" value="${customer.getTelefono_one()}" name="phone_one" id="phone_one" required="required">
    
  </div>
  <div class="form-group col-md-3">
    <label for="phone_Two">Numero di Telefono</label>
    <input type="text" class="form-control" value="${customer.getTelefono_two()}" name="phone_two" id="phone_two">
  </div>
  </div>
  <div class="form-row col-md-9 ml-md-auto">
    <div class="form-group col-md-4">
      <label for="email">Email</label>
      <input type="email" class="form-control" value="${customer.getEmail()}" name="email" id="email" >
      <input name="id" type="hidden" value="${customer.getId_cliente()}">
    </div>
  </div>
 
  <div class="form-row col-md-9 ml-md-auto ">
    <button type="submit" class="btn btn-primary">Salva</button>
  </div>
</form>
	