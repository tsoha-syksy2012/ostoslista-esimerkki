<?php
require_once 'avusteet.php';

varmista_kirjautuminen();

$lista_id = $_GET['lista'];
if (isset($_POST['tuote']) && !empty($_POST['tuote'])) {
  $kyselija->lisaa_tuote($lista_id, $_POST['tuote']);
}

ohjaa("lista.php?lista=$lista_id");
