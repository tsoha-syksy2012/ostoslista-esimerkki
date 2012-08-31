<?php

class Sessio {

  public function __construct() {
    session_start();
  }

  public function __set($avain, $arvo) {
    $_SESSION[$avain] = $arvo;
  }

  public function __get($avain) {
    return $_SESSION[$avain];
  }

}

$sessio = new Sessio();
