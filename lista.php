<?php
require_once 'avusteet.php';

$ostoslista = $kyselija->hae_lista($sessio->kayttaja_id, $_GET['lista']);

if (!$ostoslista) {
  die('P&auml;&auml;sy kielletty');
}

$tuotteet = $kyselija->listan_tuotteet($ostoslista->id);

$otsikko = 'Ostoslista - ' . $ostoslista->name;
require 'avusteet/yla.php';
?>
  <h2><?php echo $ostoslista->name; ?></h2>
  <p><a href="muokkaalistaa.php?lista=<?php echo $ostoslista->id; ?>">Muokkaa listaa</a></p>
  <ul>
  <?php foreach($tuotteet as $tuote) { ?>
  <li><?php echo $tuote->name; ?> <a href="poistalistalta.php?tuote=<?php echo $tuote->id; ?>">[poista]</a></li>
  <?php } ?>
  </ul>
  <form action="lisaalistaan.php?lista=<?php echo $ostoslista->id; ?>" method="POST">
    <fieldset>
      <legend>Uusi tuote listaan</legend>
      <label for="tuote">Tuotteen nimi:</label>
      <input id="tuote" name="tuote" type="text" />
      <input type="submit" value="Lisää tuote" />
    </fieldset>
  </form>
  <p><a href="listat.php">Näytä kaikki ostoslistat</a></p>
<?php require 'avusteet/ala.php'; ?>
