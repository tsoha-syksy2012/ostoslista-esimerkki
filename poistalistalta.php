<?php
require_once 'avusteet.php';

varmista_kirjautuminen();

$lista_id = $kyselija->poista_listalta($sessio->kayttaja_id, $_GET['tuote']);

if ($lista_id) {
  ohjaa("lista.php?lista=$lista_id");
} else {
  ohjaa('index.php');
}
