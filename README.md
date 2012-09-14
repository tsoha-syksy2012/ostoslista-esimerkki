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

## Esimerkkisovellus

Löytyy osoitteesta: http://t-saada.users.cs.helsinki.fi/ostoslista/

SQL-lauseista löytyvät tunnukset toimivat.

### context.xml

    <Resource name="jdbc/ostoslista" auth="Container"
        type="javax.sql.DataSource" removeAbandoned="true"
        removeAbandonedTimeout="30" maxActive="100"
        maxIdle="30" maxWait="10000" username="saada"
        password="SALASANA"
        driverClassName="org.postgresql.Driver"
        url="jdbc:postgresql://localhost/saada" />
