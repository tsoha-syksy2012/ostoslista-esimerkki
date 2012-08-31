<?php

class Kyselyt {

  private $_pdo;

  public function __construct($pdo) {
    $this->_pdo = $pdo;
  }

  public function kirjaudu($tunnus, $salasana) {
    $kysely = $this->valmistele('SELECT id FROM users WHERE username = ? AND password = ?');
    if ($kysely->execute(array($tunnus, $salasana)) && $id = $kysely->fetchObject()->id !== null) {
      die('success');
    } else {
      die('failure');
    }
  }

  private function valmistele($sqllause) {
    return $this->_pdo->prepare($sqllause);
  }

}

require dirname(__file__).'/../asetukset.php';

$kyselija = new Kyselyt($pdo);
