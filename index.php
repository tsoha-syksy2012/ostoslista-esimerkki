<?php
require_once 'avusteet.php';

if ($sessio->kayttaja_id) {
  ohjaa('lista.php?lista=1');
} else {
  ohjaa('kirjautuminen.php');
}
