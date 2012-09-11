<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="tyyli.css" />
    <title>Ostoslista - kirjautuminen</title>
  </head>
  <body>
    <img src="logo.jpg" title="Ostoslista" alt="Ostoslistan logo" />
    <h1>Ostoslista</h1>
    <h2>Kirjautuminen</h2>
    <p>Ole hyv‰ ja kirjaudu sis‰‰n ennenkuin voit k‰ytt‰‰ sovellusta.</p>
    <form action="kirjautuminen" method="POST">
      <fieldset>
        <legend>Kirjaudu sis‰‰n</legend>
        <label for="tunnus">K‰ytt‰j‰tunnus:</label>
        <input type="text" name="tunnus" id="tunnus" />
        <label for="salasana">Salasana:</label>
        <input type="password" name="salasana" id="salasana" />
        <input type="submit" value="Kirjaudu" />
      </fieldset>
    </form>
    <footer>
    <p>Samin esimerkkitsoha, l‰hdekoodi: <a href="https://github.com/tsoha-syksy2012/ostoslista-esimerkki">Githubissa</a></p>
    <p><a href="http://www.flickr.com/photos/lizatko/1351377013/" title="something is rotten in the state of... basket by katka koscova, on Flickr">Logon kuva</a></p>
    </footer>
  </body>
</html>