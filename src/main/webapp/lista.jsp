<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="yla.jspf" %>
<h2>${lista.nimi}</h2>
<p><a href="muokkaalistaa">Muokkaa listaa</a></p>
<c:choose>
    <c:when test="${!tuotteet.isEmpty()}">
        <ul>
            <c:forEach var="tuote" items="${tuotteet}">
                <li>${tuote.nimi} <a href="poistalistalta?tuote=${tuote.id}">[poista]</a></li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>Listassa ei ole tuotteita</p>
    </c:otherwise>
</c:choose>
<form action="lisaalistaan?lista=${lista.id}" method="POST">
    <fieldset>
        <legend>Uusi tuote listaan</legend>
        <label for="tuote">Tuotteen nimi:</label>
        <input id="tuote" name="tuote" type="text" />
        <input type="submit" value="Lis�� tuote" />
    </fieldset>
</form>
<p><a href="listat">N�yt� kaikki ostoslistat</a></p>
<%@include file="ala.jspf" %>