<?php

require_once 'avusteet/kyselyt.php';

if (isset($_GET['sisaan'])) {
  $kyselija->kirjaudu($_POST['tunnus'], $_POST['salasana']);
} else {
  die('Laiton toiminto!');
}
