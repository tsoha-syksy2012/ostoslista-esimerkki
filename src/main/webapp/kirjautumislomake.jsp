<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="yla.jspf" %>
<h2>Kirjautuminen</h2>
<p>Ole hyvä ja kirjaudu sisään ennenkuin voit käyttää sovellusta.</p>
<c:if test="${virheviesti != null}">
    <p class="virhe">${virheviesti}</p>
</c:if>
<form action="kirjautuminen" method="POST">
    <fieldset>
        <legend>Kirjaudu sisään</legend>
        <label for="tunnus">Käyttäjätunnus:</label>
        <input type="text" name="tunnus" id="tunnus" />
        <label for="salasana">Salasana:</label>
        <input type="password" name="salasana" id="salasana" />
        <input type="submit" value="Kirjaudu" />
    </fieldset>
</form>
<%@include file="ala.jspf" %>