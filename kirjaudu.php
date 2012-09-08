<?php
require_once 'avusteet.php';

if (isset($_GET['sisaan'])) {
  $kayttaja = $kyselija->tunnista($_POST['tunnus'], $_POST['salasana']);
  if ($kayttaja) {
    $sessio->kayttaja_id = $kayttaja->id;
    ohjaa('index.php');
  } else {
    ohjaa('kirjautuminen.php');
  }
} elseif (isset($_GET['ulos'])) {
  unset($sessio->kayttaja_id);
  ohjaa('index.php');
} else {
  die('Laiton toiminto!');
}
