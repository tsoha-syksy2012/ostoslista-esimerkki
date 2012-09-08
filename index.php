<?php
require_once 'avusteet.php';

varmista_kirjautuminen();

$lista = $kyselija->oletus_lista($sessio->kayttaja_id);
ohjaa('lista.php?lista=' . $lista->id);
