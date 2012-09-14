<%@page import="fi.cs.helsinki.saada.ostoslista.mallit.Ostoslista"%>
<%@include file="yla.jspf" %>
<h2>Muokkaa listaa: ${lista.nimi}</h2>
<p><a href="lista?lista=${lista.id}">Takaisin listaan</a></p>
<c:if test="${virheviesti != null}">
    <p class="virhe">${virheviesti}</p>
</c:if>
<form action="muokkaalistaa?lista=${lista.id}" method="POST">
    <fieldset>
        <legend>Listan tietojen muokkaus</legend>
        <label for="nimi">Listan nimi:</label>
        <input id="nimi" name="nimi" type="text" value="${lista.nimi}" />
        <label for="oletus">Onko ensisijainen?</label>
        <input type="checkbox" id="oletus" name="oletus" ${lista.oletus ? 'checked="checked"' : ''} />
        <input type="submit" value="Tallenna listan muutokset" />
    </fieldset>
</form>
<%@include file="ala.jspf" %>