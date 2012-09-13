<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="yla.jspf" %>
<h2>Kaikki ostoslistat</h2>
<ul>
    <c:forEach var="lista" items="${listat}">
        <li><a href="lista?lista=${lista.id}">${lista.nimi}</a><c:if test="${lista.oletus}"> (oletus)</c:if></li>
    </c:forEach>
</ul>
<form action="lista" method="POST">
    <p><input type="submit" value="Luo uusi lista" /></p>
</form>
<%@include file="ala.jspf" %>