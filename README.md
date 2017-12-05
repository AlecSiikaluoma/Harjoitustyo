# Harjoitustyo

Sovelluksen avulla voi ylläpitää yksinkertaista uutissivustoa. Sovellus sisältää uutisten sovelluksen hallintapaneelin, jonka avulla voi muokata, lisätä poistaa uutisia.

Sovellus käyttää PostgreSQL tietokantaa. Sovellus on myös yhdistetty githubin kautta Travis Ci. Siellä ajetaan testi ja niiden mennessä läpi sovellus päivitetään Heroku palveluun.

Sovelluksen etusivulla näytetään 5 uusinta artikkelia. Sivupalkissa on kaksi listaa. Toisessa näytetään uusimmat artikkelit ja toisessa viikon luetuimmat. 

## Uutiset
Jokaisella uutisella on:

- id
- otsikko
- ingressi
- teksti
- kuva
- kirjoittajat (joka säilytetään tietokannassa yhdessä kentässä)
- kategoriat (joilla on oma tietokanta taulu)

## Parennnusehdotuksia 
- luo kirjoittajille oma tietokanta taulu
- selaa uutisia kirjoittajien mukaan
- haku toiminto