<?php
require_once 'avusteet.php';

$ostoslista = $kyselija->hae_lista($sessio->kayttaja_id, $_GET['lista']);

if (!$ostoslista) {
  die('P&auml;&auml;sy kielletty');
}

$otsikko = 'Ostoslista - ' . $ostoslista->name;
require 'avusteet/yla.php';
?>
<?php require 'avusteet/ala.php'; ?>
