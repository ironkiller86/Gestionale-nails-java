<body>
<form class="container" name="createCustomer" action="<%=request.getContextPath() + "/customer?action=createcustomer" %>" method="POST">
  <div class="form-row col-md-9 ml-md-auto ">
      <h2>Crea un nuovo Cliente:</h2>
  </div>
  <hr>
  <div class="form-row col-md-9 ml-md-auto ">
    <div class=" form-group col-md-3">
      <label for="cognome">Cognome</label>
      <input type="text" class="form-control" name="cognome" id="cognome" placeholder="Cognome" required="required">
    </div>
    <div class="form-group col-md-3">
      <label for="name">Nome</label>
      <input type="text" class="form-control" name="nome" id="nome" placeholder="Nome" required="required">
    </div>
  </div>
  
  <div class="form-row col-md-9 ml-md-auto">
  <div class=" form-group col-md-3">
    <label for="phone_one">Numero di Cellulare</label>
    <input type="text" class="form-control" name="phone_one" id="phone_one" required="required">
    
  </div>
  <div class="form-group col-md-3">
    <label for="phone_Two">Numero di Telefono</label>
    <input type="text" class="form-control" name="phone_two" id="phone_two">
  </div>
  </div>
  <div class="form-row col-md-9 ml-md-auto">
    <div class="form-group col-md-4">
      <label for="email">Email</label>
      <input type="email" class="form-control" name="email" id="email" placeholder="email@gmail.com">
    </div>
  </div>
 
  <div class="form-row col-md-9 ml-md-auto ">
    <button type="submit" class="btn btn-primary">Crea</button>
  </div>
</form>
	