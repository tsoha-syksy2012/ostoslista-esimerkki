<?php
require_once 'avusteet.php';

varmista_kirjautuminen();

$lista_id = $kyselija->luo_uusi_lista($sessio->kayttaja_id);

if ($lista_id) {
  ohjaa("lista.php?lista=$lista_id");
} else {
  die('Odottamaton virhe');
}
