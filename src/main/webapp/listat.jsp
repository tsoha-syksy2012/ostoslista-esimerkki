<%@include file="yla.jspf" %>
<h2>Kaikki ostoslistat</h2>
<ul>
    <li><a href="lista?lista=123">Viikonlopun h�t�vara</a> (oletus)</li>
    <li><a href="lista?lista=124">Remontti</a></li>
    <li><a href="lista?lista=125">Syntt�ritarjoilut</a></li>
</ul>
<form action="lista" method="POST">
    <p><input type="submit" value="Luo uusi lista" /></p>
</form>
<%@include file="ala.jspf" %>