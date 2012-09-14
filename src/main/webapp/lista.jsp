<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="yla.jspf" %>
<h2>${lista.nimi}</h2>
<p><a href="muokkaalistaa?lista=${lista.id}">Muokkaa listaa</a></p>
<c:choose>
    <c:when test="${empty tuotteet}">
        <p>Listassa ei ole tuotteita</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="tuote" items="${tuotteet}">
                <li>${tuote.nimi} <a href="poistalistalta?tuote=${tuote.id}">[poista]</a></li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
<form action="lista?lista=${lista.id}" method="POST">
    <fieldset>
        <legend>Uusi tuote listaan</legend>
        <label for="tuote">Tuotteen nimi:</label>
        <input id="tuote" name="tuote" type="text" />
        <input type="submit" value="Lisää tuote" />
    </fieldset>
</form>
<p><a href="listat">Näytä kaikki ostoslistat</a></p>
<%@include file="ala.jspf" %>