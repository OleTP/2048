# Mitt program

Se [oppgaveteksten](./OPPGAVETEKST.md) til semesteroppgave 2. Denne README -filen kan du endre som en del av dokumentasjonen til programmet, hvor du beskriver for en bruker hvordan programmet skal benyttes.

Programmet mitt er en replika av spillet "2048" som ble utgitt i 2014.
Spillet er et enkelt single-player spill som kan forklares som et "skyveblokkpuslespill".
Spillet spilles på et 4x4 rutenett, der av 2 av de 16 rutene har nummererte fliser fra start(Enten med verdi 2 eller 4).
Flisene på brette kan skyves i fire retninger; opp, ned, venstre eller høyre. Når spiller har gjort et trekk vil alle flisene i rutenettet forskyve seg så langt de kommer til de treffer enten kanten av rutenettet eller en annen flis.
Dersom to fliser av samme verdi berører hverandre mens et trekk er gjort vil de slå seg sammen til én flis med et nummer som er summen av de to opprinnelige flisene.
Etter hvert trekk er blitt gjort vil en ny flis med verdi 2 eller 4 dukke opp på i en tilfeldig ledig rute i rutenettet. Det er er en 10% sjanse for at flisen som dukker opp har verdien 4, så det er som oftest en flis med verdi 2.
Spillet fortsetter helt frem til hele rutenettet er fullt, og ingen fliser har tilstøtende nummer. Dette vil si helt frem til ingen fliser har mulighet til å forskyve seg i noen av de fire retningene.
Hovedmålet er som navnet tilsier, å få flisen med verdi 2048. Dersom spilleren får flisen 2048, har h*n fremdeles mulighet til å fortsette spillet frem til ingen lovlige trekk lenger er tilgjengelige.
Poengscoren til spilleren blir avgjort av summen av alle flissammenslåinger gjort under spillet. Desto flere sammenslåinger, desto høyere flisverdi, desto høyere poengsum. Det kan derfor være lurt å ha en strategi for å oppnå høye flisverdier på brettet.


Lenke til forklaringsvideo: https://www.youtube.com/watch?v=co5uHVPMMfY