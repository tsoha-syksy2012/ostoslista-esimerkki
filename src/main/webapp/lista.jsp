<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="tyyli.css" />
    <title>Ostoslista - Viikonlopun h‰t‰vara</title>
  </head>
  <body>
    <img src="logo.jpg" title="Ostoslista" alt="Ostoslistan logo" />
    <h1>Ostoslista</h1>
    <p><a href="kirjautuminen.html">Kirjaudu ulos</a></p>
    <h2>Viikonlopun h‰t‰vara</h2>
    <p><a href="muokkaalistaa.html">Muokkaa listaa</a></p>
    <ul>
      <li>Maitoa <a href="poistalistalta.html?tuote=123">[poista]</a></li>
      <li>Leip‰‰ <a href="poistalistalta.html?tuote=124">[poista]</a></li>
      <li>Jauhoja <a href="poistalistalta.html?tuote=125">[poista]</a></li>
    </ul>
    <form action="lista.html" method="POST">
      <fieldset>
        <legend>Uusi tuote listaan</legend>
        <label for="tuote">Tuotteen nimi:</label>
        <input id="tuote" name="tuote" type="text" />
        <input type="submit" value="Lis‰‰ tuote" />
      </fieldset>
    </form>
    <p><a href="listat.html">N‰yt‰ kaikki ostoslistat</a></p>
    <footer>
    <p>Samin esimerkkitsoha, l‰hdekoodi: <a href="https://github.com/tsoha-syksy2012/ostoslista-esimerkki">Githubissa</a></p>
    </footer>
  </body>
</html>