<?php
require_once 'avusteet.php';

if (isset($sessio->kayttaja_id)) {
  $lista = $kyselija->oletus_lista($sessio->kayttaja_id);
  ohjaa('lista.php?lista=' . $lista->id);
} else {
  ohjaa('kirjautuminen.php');
}
