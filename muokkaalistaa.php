<?php
require_once 'avusteet.php';

$ostoslista = $kyselija->hae_lista($sessio->kayttaja_id, $_GET['lista']);

if (!$ostoslista) {
  die('P&auml;&auml;sy kielletty');
}

if (isset($_POST['nimi']) && isset($_POST['oletus'])) {
}

$otsikko = 'Ostoslista - ' . $ostoslista->name . ' - muokkaus';
require 'avusteet/yla.php';
?>
  <h2>Moukkaa listaa: <?php echo $ostoslista->name; ?></h2>
  <p><a href="lista.php?lista=<?php echo $ostoslista->id; ?>">Takaisin listaan</a></p>
  <form action="muokkaalistaa.php?lista=<?php echo $ostoslista->id; ?>" method="POST">
    <fieldset>
      <legend>Listan tietojen muokkaus</legend>
      <label for="nimi">Listan nimi:</label>
      <input id="nimi" name="nimi" type="text" value="<?php echo $ostoslista->name; ?>" />
      <label for="oletus">Onko ensisijainen?</label>
      <input type="checkbox" id="oletus" name="oletus" <?php if ($ostoslista->is_default) echo 'checked="checked"'; ?> />
      <input type="submit" value="Tallenna listan muutokset" />
    </fieldset>
  </form>
<?php require 'avusteet/ala.php'; ?>
