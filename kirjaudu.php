<?php
require_once 'avusteet.php';

if (isset($_GET['sisaan'])) {
  $kayttaja = $kyselija->tunnista($_POST['tunnus'], $_POST['salasana']);
  if ($kayttaja) {
    ohjaa('index.php');
  } else {
    ohjaa('kirjautuminen.php');
  }
} else {
  die('Laiton toiminto!');
}
