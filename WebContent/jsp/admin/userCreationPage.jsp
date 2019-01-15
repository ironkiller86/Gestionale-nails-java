<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div id="main">
    <main class="my-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8" style="height: 609px; overflow-y: scroll;">
                    <div class="card text-dark">
                        <div class="card-header">Dati Nuovo Utente</div>
                        <div class="card-body">
                            <form name="my-form" action="adminController?action=userSave" method="POST">
                                <div class="form-group row">
                                    <label for="cognome" class="col-md-4 col-form-label text-md-right">Cognome</label>
                                    <div class="col-md-6">
                                        <input type="text" id="cognome" class="form-control" name="cognome" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="nome" class="col-md-4 col-form-label text-md-right">Nome</label>
                                    <div class="col-md-6">
                                        <input type="text" id="nome" class="form-control" name="nome" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="cod_fiscale" class="col-md-4 col-form-label text-md-right">Codice Fiscale</label>
                                    <div class="col-md-6">
                                        <input type="text" id="codFiscale" class="form-control" name="cod_fiscale" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="dataNascita" class="col-md-4 col-form-label text-md-right">Data di Nascita</label>
                                    <div class="col-md-6">
                                        <input type="date" id="dataNascita" class="form-control" name="dataNascita" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="cittaNascita" class="col-md-4 col-form-label text-md-right">Luogo di Nascita</label>
                                    <div class="col-md-6">
                                        <input type="text" id="luogoNascita" class="form-control" name="cittaNascita" required="required"  placeholder="Città">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="indirizzo" class="col-md-4 col-form-label text-md-right">Indirizzo</label>
                                    <div class="col-md-6">
                                        <input type="text" id="indirizzo" class="form-control" name="indirizzo" required="required" placeholder="via e numero civico">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="cittaResidenza" class="col-md-4 col-form-label text-md-right">Citta</label>
                                    <div class="col-md-6">
                                        <input type="text" id="citta" class="form-control" name="cittaResidenza" required="required" placeholder="città residenza">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="cap" class="col-md-4 col-form-label text-md-right">CAP</label>
                                    <div class="col-md-6">
                                        <input type="text" id="cap" class="form-control" name="cap" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="telefono" class="col-md-4 col-form-label text-md-right">Telefono</label>
                                    <div class="col-md-6">
                                        <input type="text" id="telefono" class="form-control" name="telefono" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-md-4 col-form-label text-md-right">E-Mail</label>
                                    <div class="col-md-6">
                                        <input type="email" id="email" class="form-control" name="email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="username" class="col-md-4 col-form-label text-md-right">UserName</label>
                                    <div class="col-md-6">
                                        <input type="text" id="username" class="form-control" name="username" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                    <div class="col-md-6">
                                        <input type="password" id="password" class="form-control" name="password" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="dataAttivazione" class="col-md-4 col-form-label text-md-right">Data Attivazione</label>
                                    <div class="col-md-6">
                                        <input type="date" id="dataAttivazione" class="form-control" name="dataAttivazione" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="dataScadenza" class="col-md-4 col-form-label text-md-right">Data Scadenza</label>
                                    <div class="col-md-6">
                                        <input type="date" id="dataScadenza" class="form-control" name="dataScadenza" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="permessi" class="col-md-4 col-form-label text-md-right">Permessi</label>
                                    <div class="col-md-6">
                                      <select class="custom-select" id="role" required="required" name="role">
                                        <option selected>Seleziona...</option>
                                        <option value="admin">Admin</option>
                                        <option value="user">User</option>
                                    </select>
                                   </div>
                                </div>
                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary"  style="display:none;">
                                        Registra
                                        </button>
                                    </div>
                          </form>  
                                </div>
                           
                        </div>
                    </div>
            </div>
        </div>
    </div>
<!-- dialog jquery ui cod Fiscale -->
 <div id="dialog-codFisc" title="Attenzione" style="display:none;">
   <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
     Hai inserito un codice fiscale non valido!
   </p>
</div>
<!-- dialog jquery ui password -->
 <div id="dialog-password" title="Attenzione" style="display:none;">
   <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
     Password non valida!
   </p>
</div>
</main>
   
</body>
</html>