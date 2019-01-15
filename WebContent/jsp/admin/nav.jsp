<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<nav>
   <div id="left" class="list-group">
  <ul class="list-group text-center">
  <li class="list-group-item list-group-item-dark">
    <a class="nav-link" href="adminController?page=homePage">Home </a>
  </li>
  <li class="list-group-item list-group-item-dark">
     <a class="nav-link text-success" href="adminController?page=userCreation">Crea Nuovo Utente </a>
  </li>
  <li class="list-group-item list-group-item-dark">
    <abbr title="Effettua il Logout">
      <button type="button" class="btn btn-light btn-sm " id="logOut">
          <img src="static/images/door.png"  alt="Logout"> 
      </button>
    </abbr> 
  </li>
  <li class="list-group-item list-group-item-dark">This is a secondary list group item</li>
  <li class="list-group-item list-group-item-dark">This is a success list group item</li>
  <li class="list-group-item list-group-item-dark">This is a danger list group item</li>
  <li class="list-group-item list-group-item-dark">This is a warning list group item</li>
  <li class="list-group-item list-group-item-dark">This is a info list group item</li>
  <li class="list-group-item list-group-item-dark">This is a light list group item</li>
  <li class="list-group-item list-group-item-dark">This is a dark list group item</li>
</ul>
</div>
</nav>

