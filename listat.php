<?php
require_once 'avusteet.php';

$ostoslistat = $kyselija->hae_listat($sessio->kayttaja_id);

$otsikko = 'Ostoslista - Kaikki ostoslistat';
require 'avusteet/yla.php';
?>
  <h2>Kaikki ostoslistat</h2>
  <ul>
  <?php foreach($ostoslistat as $lista) { ?>
  <li><a href="lista.php?lista=<?php echo $lista->id; ?>"><?php echo $lista->name; ?></a><?php if ($lista->is_default) echo ' (oletus)'; ?></li>
  <?php } ?>
  </ul>
  <form action="uusilista.php" method="POST">
    <p><input type="submit" value="Luo uusi lista" /></p>
  </form>
<?php require 'avusteet/ala.php'; ?>
