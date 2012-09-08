<?php
require_once 'avusteet.php';

varmista_kirjautuminen();

$lista_id = $kyselija->poista_listalta($_GET['tuote']);

ohjaa("lista.php?lista=$lista_id");
