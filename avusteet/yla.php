<?php require_once 'avusteet.php'; ?>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="tyyli.css" />
    <title><?php echo $otsikko; ?></title>
  </head>
  <body>
    <img src="logo.jpg" title="Ostoslista" alt="Ostoslistan logo" />
    <h1>Ostoslista</h1>
<?php if (on_kirjautunut()) { ?>
    <p><a href="kirjaudu.php?ulos">Kirjaudu ulos</a></p>
<?php } ?>
