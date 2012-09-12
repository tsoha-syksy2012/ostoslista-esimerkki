<%@include file="yla.jspf" %>
<h2>Muokkaa listaa: Viikonlopun h�t�vara</h2>
<p><a href="lista">Takaisin listaan</a></p>
<form action="muokkaalistaa" method="POST">
    <fieldset>
    <legend>Listan tietojen muokkaus</legend>
    <label for="nimi">Listan nimi:</label>
    <input id="nimi" name="nimi" type="text" />
    <label for="oletus">Onko ensisijainen?</label>
    <input type="checkbox" id="oletus" name="oletus" />
    <input type="submit" value="Tallenna listan muutokset" />
    </fieldset>
</form>
<%@include file="ala.jspf" %>