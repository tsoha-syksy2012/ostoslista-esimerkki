<%@include file="yla.jspf" %>
<h2>${lista.nimi}</h2>
<p><a href="muokkaalistaa">Muokkaa listaa</a></p>
<ul>
    <li>Maitoa <a href="poistalistalta?tuote=123">[poista]</a></li>
    <li>Leip‰‰ <a href="poistalistalta?tuote=124">[poista]</a></li>
    <li>Jauhoja <a href="poistalistalta?tuote=125">[poista]</a></li>
</ul>
<form action="lisaalistaan?lista=${lista.id}" method="POST">
    <fieldset>
    <legend>Uusi tuote listaan</legend>
    <label for="tuote">Tuotteen nimi:</label>
    <input id="tuote" name="tuote" type="text" />
    <input type="submit" value="Lis‰‰ tuote" />
    </fieldset>
</form>
<p><a href="listat">N‰yt‰ kaikki ostoslistat</a></p>
<%@include file="ala.jspf" %>