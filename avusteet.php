<?php

require_once 'avusteet/kyselyt.php';

function ohjaa($osoite) {
  header("Location: $osoite");
  exit;
}
