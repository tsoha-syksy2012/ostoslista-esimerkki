<?php

require_once 'avusteet/kyselyt.php';
require_once 'avusteet/sessio.php';

function ohjaa($osoite) {
  header("Location: $osoite");
  exit;
}
