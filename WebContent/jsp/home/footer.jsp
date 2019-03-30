<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
 <footer class="footer-distributed">
    <c:if test="${sessionScope.userLog != null }">   
			<div class="footer-left">
              <h3>${userLog.getCognome()} ${userLog.getNome()}<span>Nails</span></h3>
			</div>

			<div class="footer-center">

				<div>
					<i class="fa fa-map-marker"></i>
					<p><span> ${userLog.getIndirizzo()}</span> ${userLog.getCittaResidenza()},  ${userLog.getCap()}</p>
				</div>

				<div>
					<i class="fa fa-phone"></i>
					<p> ${userLog.getTelefono()}</p>
				</div>

				<div>
					<i class="fa fa-envelope"></i>
					<p> ${userLog.getEmail()}</p>
				</div>

			</div>

			<div class="footer-right">
               <p class="footer-company-about"></p>
					<span class="footer-company-about">Info Software:</span>
					<ul class="footer-company-about" >
					   <li>Id Utente N ${userLog.getId()}</li>
					   <fmt:formatDate value="${userLog.getDataAttiv().getTime()}" type="DATA" pattern="dd/MM/y" var="data"/>
					   <li>Attivazione Licenza: ${data}</li>
					   <fmt:formatDate value="${userLog.getDataScad().getTime()}" type="DATA" pattern="dd/MM/y" var="data"/>
					   <li>Scadenza Licenza: ${data}</li>
                   </ul>
			</div>
     </c:if>
		</footer>

	</body>

</html>