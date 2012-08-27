# OSTOSLISTA

Esimerkkisovellus tietokantasovellus kurssille.

## Tietokannan käsittely

### Tietokantataulujen luonti:

    psql < sql/luontilauseet.sql

### Esimerkkidatan vienti kantaan:

    psql < sql/esimerkkidata.sql

### Tietokantataulujen poisto:

    psql < sql/poistolauseet.sql

## Sovelluskoodit

Sovellus toteutetaan eri kielillä ja alustoilla. Kullekin toteutukselle tehdään oma haaransa, jotka näkyvät esimerkiksi sivulla: https://github.com/tsoha-syksy2012/ostoslista-esimerkki/branches

Gitin päähaara eli master sisältää kaikille yhtenäiset osat, kuten sql-lauseet ja mockupit yms.

## Sovelluksen käynnistäminen

Aseta .env-tiedoston muuttujat kohdilleen.

Aja `foreman start` ja mene selaimessa osoitteeseen http://localhost:portti (oletuksena portti on 4567).

## Esimerkkisovellus

Löytyy osoitteesta: http://ostoslista-esimerkki-ruby.herokuapp.com/

SQL-lauseista löytyvät tunnukset toimivat.
