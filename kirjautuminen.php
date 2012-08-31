<?php
$otsikko = 'Ostoslista - kirjautuminen';
require 'avusteet/yla.php';
?>
<h2>Kirjautuminen</h2>
<p>Ole hyvä ja kirjaudu sisään ennenkuin voit käyttää sovellusta.</p>
<form action="kirjaudu.php?sisaan" method="POST">
  <fieldset>
    <legend>Kirjaudu sisään</legend>
    <label for="tunnus">Käyttäjätunnus:</label>
    <input type="text" name="tunnus" id="tunnus" />
    <label for="salasana">Salasana:</label>
    <input type="password" name="salasana" id="salasana" />
    <input type="submit" value="Kirjaudu" />
  </fieldset>
</form>
<?php require 'avusteet/ala.php'; ?>
