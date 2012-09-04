<?php

class Kyselyt {

  private $_pdo;

  public function __construct($pdo) {
    $this->_pdo = $pdo;
  }

  public function tunnista($tunnus, $salasana) {
    $kysely = $this->valmistele('SELECT id FROM users WHERE username = ? AND password = ?');
    if ($kysely->execute(array($tunnus, $salasana))) {
      return $kysely->fetchObject();
    } else {
      return null;
    }
  }

  public function oletus_lista($kayttaja_id) {
    $kysely = $this->valmistele('SELECT * FROM lists WHERE user_id = ? AND is_default IS TRUE');
    if ($kysely->execute(array($kayttaja_id))) {
      return $kysely->fetchObject();
    } else {
      return null;
    }
  }

  public function hae_lista($kayttaja_id, $lista_id) {
    $kysely = $this->valmistele('SELECT * FROM lists WHERE user_id = ? AND id = ?');
    if ($kysely->execute(array($kayttaja_id, $lista_id))) {
      return $kysely->fetchObject();
    } else {
      return null;
    }
  }

  public function listan_tuotteet($lista_id) {
    $kysely = $this->valmistele('SELECT * FROM items WHERE list_id = ?');
    if ($kysely->execute(array($lista_id))) {
      $alkiot = array();
      while($alkio = $kysely->fetchObject()) {
        $alkiot[] = $alkio;
      }
      return $alkiot;
    }
    return null;
  }

  public function hae_listat($kayttaja_id) {
    $kysely = $this->valmistele('SELECT * FROM lists WHERE user_id = ? ORDER BY name');
    if ($kysely->execute(array($kayttaja_id))) {
      $listat = array();
      while($lista = $kysely->fetchObject()) {
        $listat[] = $lista;
      }
      return $listat;
    }
    return null;
  }

  public function poista_listalta($tuote_id) {
    $kysely = $this->valmistele('DELETE FROM items WHERE id = ? RETURNING list_id');
    if ($kysely->execute(array($tuote_id))) {
      return $kysely->fetchObject()->list_id;
    }
    return null;
  }

  public function lisaa_tuote($lista_id, $tuotteen_nimi) {
    $kysely = $this->valmistele('INSERT INTO items (list_id, name) VALUES (?, ?)');
    return $kysely->execute(array($lista_id, $tuotteen_nimi));
  }

  private function valmistele($sqllause) {
    return $this->_pdo->prepare($sqllause);
  }

}

require dirname(__file__).'/../asetukset.php';

$kyselija = new Kyselyt($pdo);
