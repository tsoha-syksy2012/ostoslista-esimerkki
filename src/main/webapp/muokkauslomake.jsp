<%@include file="yla.jspf" %>
<h2>Moukkaa listaa: Viikonlopun hätävara</h2>
<p><a href="lista.html">Takaisin listaan</a></p>
<form action="lista.html" method="POST">
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