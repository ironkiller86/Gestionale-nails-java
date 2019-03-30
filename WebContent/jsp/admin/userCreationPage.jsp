<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>     
<div id="main">
    <main class="my-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8" style="height: 609px; overflow-y: scroll;">
                    <div class="card text-dark">
                    <c:choose>
                      <c:when test="${requestScope.userDetail != null}">
                        <div class="card-header">Dettaglio Utente</div>
                      </c:when>
                       <c:when test="${requestScope.userDetail eq null}">
                        <div class="card-header">Dati nuovo Utente</div>
                      </c:when>
                    </c:choose>
                        <div class="card-body">
                                <div class="form-group row">
                                    <label for="cognome" class="col-md-4 col-form-label text-md-right">Cognome</label>
                                    <div class="col-md-6">
                                        <input type="text" id="cognome" class="form-control" name="cognome" value="${userDetail.getCognome()}" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="nome" class="col-md-4 col-form-label text-md-right">Nome</label>
                                    <div class="col-md-6">
                                        <input type="text" id="nome" class="form-control" name="nome" value="${userDetail.getNome()}" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="cod_fiscale" class="col-md-4 col-form-label text-md-right">Codice Fiscale</label>
                                    <div class="col-md-6">
                                        <input type="text" id="codFiscale" class="form-control" name="cod_fiscale" value="${userDetail.getCodFiscale()}" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="dataNascita" class="col-md-4 col-form-label text-md-right">Data di Nascita</label>
                                    <div class="col-md-6">
                                         <fmt:formatDate value="${userDetail.getDataDiNascita().getTime()}" type="DATA" pattern="y-MM-dd" var="data"/>
                                        <input type="date" id="dataNascita" class="form-control" name="dataNascita" value="${data}" required="required">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="cittaNascita" class="col-md-4 col-form-label text-md-right">Luogo di Nascita</label>
                                    <div class="col-md-6">
                                        <input type="text" id="luogoNascita" class="form-control" name="cittaNascita" value="${userDetail.getCittaDiNascita()}" required="required"  placeholder="Città">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="indirizzo" class="col-md-4 col-form-label text-md-right">Indirizzo</label>
                                    <div class="col-md-6">
                                        <input type="text" id="indirizzo" class="form-control" name="indirizzo" value="${userDetail.getIndirizzo()}" required="required" placeholder="via e numero civico">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label for="cittaResidenza" class="col-md-4 col-form-label text-md-right">Citta</label>
                                    <div class="col-md-6">
                                        <input type="text" id="citta" class="form-control" name="cittaResidenza" value="${userDetail.getCittaResidenza()}" required="required" placeholder="città residenza">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="cap" class="col-md-4 col-form-label text-md-right">CAP</label>
                                    <div class="col-md-6">
                                        <input type="text" id="cap" class="form-control" name="cap" value="${userDetail.getCap()}" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="telefono" class="col-md-4 col-form-label text-md-right">Telefono</label>
                                    <div class="col-md-6">
                                        <input type="text" id="telefono" class="form-control" name="telefono" value="${userDetail.getTelefono()}" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-md-4 col-form-label text-md-right">E-Mail</label>
                                    <div class="col-md-6">
                                        <input type="email" id="email" class="form-control" name="email" value="${userDetail.getEmail()}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="username" class="col-md-4 col-form-label text-md-right">UserName</label>
                                    <div class="col-md-6">
                                        <input type="text" id="username" class="form-control" name="username" value="${userDetail.getUsername()}" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                    <div class="col-md-6">
                                      <c:choose>
                                        <c:when test="${requestScope.userDetail eq null}">
                                          <input type="password" id="password" class="form-control" name="password" required="required">
                                        </c:when>
                                        <c:when test="${requestScope.userDetail != null}">
                                          <input type="text" id="password" class="form-control" name="password" value="${userDetail.getPassword()}" required="required">
                                        </c:when>
                                      </c:choose>  
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="dataAttivazione" class="col-md-4 col-form-label text-md-right">Data Attivazione</label>
                                    <div class="col-md-6">
                                        <fmt:formatDate value="${userDetail.getDataAttiv().getTime()}" type="DATA" pattern="y-MM-dd" var="data"/>
                                        <input type="date" id="dataAttivazione" class="form-control" name="dataAttivazione"  value="${data}" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="dataScadenza" class="col-md-4 col-form-label text-md-right">Data Scadenza</label>
                                    <div class="col-md-6">
                                        <fmt:formatDate value="${userDetail.getDataScad().getTime()}" type="DATA" pattern="y-MM-dd" var="data"/>
                                        <input type="date" id="dataScadenza" class="form-control" name="dataScadenza" value="${data}" required="required">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="permessi" class="col-md-4 col-form-label text-md-right">Permessi</label>
                                    <div class="col-md-6">
                                    <c:choose>
                                      <c:when test="${requestScope.userDetail != null}">
                                         <select class="custom-select" id="role" required="required" name="role">
                                         <option selected >Seleziona...</option>
                                           <c:if test="${userDetail.isRole() == true}">
                                             <option selected="${Admin}" value="admin">Admin</option>
                                             <option value="user">User</option>
                                           </c:if>
                                           <c:if test="${not userDetail.isRole()}">
                                             <option selected="${User}" value="user">User</option>
                                             <option value="admin">Admin</option>
                                           </c:if>
                                          </select>
                                      </c:when>
                                      <c:when test="${requestScope.userDetail eq null}">
                                         <select class="custom-select" id="role" required="required" name="role">
                                         <option selected>Seleziona...</option>
                                         <option value="admin">Admin</option>
                                         <option value="user">User</option>
                                         </select>
                                      </c:when>
                                    </c:choose>
                                   </div>
                                </div>
                                    <div class="col-md-6 offset-md-4">
                                    <c:choose>
                                       <c:when test="${requestScope.userDetail != null}">  
                                          <button type="submit" class="btn btn-primary" id="save" style="display:none;">
                                            Salva
                                          </button>
                                          </c:when>
                                       <c:when test="${requestScope.userDetail eq null}">  
                                          <button type="submit" class="btn btn-primary" id="save"  style="display:none;">
                                            Registra
                                          </button>
                                       </c:when>  
                                    </c:choose>
                                    </div>
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
<!-- dialog jquery ui success-user -->
 <div id="dialog-success" title="Avviso" style="display:none;">
   <p><span class="ui-icon ui-icon-check" style="float:left; margin:12px 12px 20px 0;"></span></p>
       <div id="msg"> <!-- div per messaggio personalizzato -->
       
       </div>
  
</div>
<!-- dialog jquery ui modifica -->
 <div id="dialog-modify" title="Avviso" style="display:none;">
   <p><span class="ui-icon ui-icon-check" style="float:left; margin:12px 12px 20px 0;"></span>
     Modifica Salvata!
   </p>
</div>

 <c:if test="${requestScope.userDetail != null}">     <!-- metto l'id in un div per recuperarlo con jquery per fare i controlli sull'univocità del nome utente -->
  <div id="idUser" style="display:none;">
     ${userDetail.getId()}
  </div>
  </c:if>
</main>
   
</body>
</html>