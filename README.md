# Harjoitustyo

Sovelluksella voi ylläpitää yksinkertaista uutissivustoa. Sovellus sisältää uutisten hallintaan tarkoitetun hallintapaneelin, jonka avulla voi muokata, lisätä tai poistaa uutisia.

Sovellus käyttää PostgreSQL, Java ja Spring ohjelmistoja. Sovellus on yhdistetty githubin kautta Travis Ci palveluun (https://travis-ci.org/AlecSiikaluoma/Harjoitustyo). Siellä ajetaan testit ja niiden mennessä läpi sovellus päivitetään Heroku palveluun.

Sovelluksen etusivulla näytetään 5 uusinta artikkelia. Sivupalkissa on kaksi listaa. Toisessa näytetään uusimmat artikkelit ja toisessa viikon luetuimmat. 

## Demo 
Löytyy osoitteesta https://harjoitustyo.herokuapp.com

## Uutiset
Jokaisella uutisella on seuraavanlainen tietokantataulu:

- id
- otsikko
- ingressi
- teksti
- kuva
- kirjoittajat (joka säilytetään tietokannassa yhdessä kentässä)
- kategoriat (joilla on oma tietokanta taulu)

## Parennnusehdotuksia 
- luo kirjoittajille oma tietokantataulu
- haku toiminto
