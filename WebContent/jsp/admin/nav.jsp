<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<nav>
   <div id="left" class="list-group">
  <ul class="list-group text-center">
  <li class="list-group-item list-group-item-dark">
    <abbr title="Torna alla Home">
     <a class="nav-link" href="adminController?page=homePage">
       <img src="static/images/go-home.png"  alt="Home"> 
     </a>
    </abbr>
    
  </li>
   <li class="list-group-item list-group-item-dark">
     <abbr title="Visualizza tutti gli utenti">
      <button type="button" class="btn btn-link btn-sm " id="allUsers">
          <img src="static/images/userList.png"  alt="AllUsers"> 
      </button>
     </abbr>
  </li>
  <li class="list-group-item list-group-item-dark">
     <abbr title="Crea un Nuovo Utente">
      <a class="nav-link text-success" href="adminController?page=userCreation">
        <img src="static/images/addUser.png"  alt="Crea Utente"> 
     </a>
    </abbr>
  </li>
  <li class="list-group-item list-group-item-dark">
    <abbr title="Effettua il Logout">
      <button type="button" class="btn btn-link btn-sm " id="logOut">
          <img src="static/images/exit.png"  alt="Logout"> 
      </button>
    </abbr> 
  </li>
</ul>
</div>
</nav>

