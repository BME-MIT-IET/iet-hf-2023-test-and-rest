## Házi feladat munkanapló: Test and REST
### Integrációs és ellenőrzési technikák (VIMIAC04)

**Csapattag 1** (Dongó Rajmund, ZZXQJU, [rajmunddongo](https://github.com/rajmunddongo)) Összesen kb 13 óra

*Statikus analízis elvégzése* (összesen kb. 5 óra):
- Statikus analízis elvégzése (továbbiakban részletesen)
- Sonarqube beállítása (gyakorlat előtt, ezért utánna is kellett nézni) 2 óra
- Code review a Game.java osztályhoz és a javítások elkészítése hozzá 3 óra

*Manuális tesztek* (összesen kb. 2x2 óra):
- Manuális tesztek módjának kiderítése (hogyan,mit kell) és ezen tesztek elvégzése képekkel 2x2 óra

*Minden más* (összesen kb. 4 óra):
- Github menedzselése 1 óra
- Meeting a csapattal 2 óra
- Pull request review-k 1 óra maximum
- Bónusz: Segítettem a github actions beállításában itt a [komment](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/issues/3) hozzá

**Csapattag 2** (Bidló András Rudolf, DRSREI, [BAndriss](https://github.com/BAndriss)) Összesen kb 12,5 óra

*Statikus analízis eszköz futtatása és jelzett hibák átnézése* (összesen kb. 4 óra):
- Sonarqube által jelzett hibák javítása ([lásd](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/wiki/Documentation#statikus-anal%C3%ADzis)) 2 óra
- Sonarqube tudja JUnit test covarage megjelníteni a Covarage fülön, mert jelenleg csak 0.0%-k mutatt hiába van teszt (Sajnos az általam talált ötletek nem vezettek eredményre, így ezt nem sikerült megcsinálnom, de ezen a [branchen](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/tree/sonar_code_covarage) próbálkoztam) 2 óra

*Manuális tesztek* (összesen kb. 4,5 óra):
- Manuális tesztek módjának kiderítése és egyeztettés [@rajmunddongo](https://github.com/rajmunddongo)-val, hogy hogyan kéne csinálni  3 óra
- Manuális tesztek végzése és dokumentálása ([lásd](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/wiki/Documentation#manu%C3%A1lis-tesztek)) 1,5 óra

*Minden más* (összesen kb. 4 óra):
- Intellij IDE felkonfigurálása, hogy lehessen futtatni az alkalmazást (Java verzió próblémák voltak) 1 óra 
- Meeting a csapattal 2 óra
- Pull request review-k 1 óra maximum

**Csapattag 3** (Buzás Ádám, SMLZV2, [adamkaj03](https://github.com/adamkaj03)) Összesen kb 13 óra

*Unit tesztek készítése* (összesen kb. 8 óra):
- Projekt és a dokumentáció átnézése unit teszt előtt 1 óra
- Unit tesztek készítése a játék logikáért felelős osztályaihoz 5 óra
- Unit tesztek kimeneteinek ellenőrzése és a Maven telepítése saját gépre maximum 1 óra
- Unit teszthez tartozó dokumentáció elkészítése 1 óra


*Usability tesztek készítése* (összesen kb. 2 óra):
- Usability tesztek módjának kiderítése 0.5 óra
- Usability tesztek elkészítése [@gelencser09](https://github.com/gelencser09)-el és [@zotya0601](https://github.com/zotya0601)-val 1 óra
- Usability teszthez tartozó dokumentáció elkészítése [@gelencser09](https://github.com/gelencser09)-el és [@zotya0601](https://github.com/zotya0601)-val 0.5 óra

*Minden más* (összesen kb. 3 óra):
- Meeting a csapattal 2 óra
- Pull request review-k 1 óra maximum

**Csapattag 4** (Gelencsér Ákos, RJSYLT, [gelencser09](https://github.com/gelencser09)) Összesen kb 10.5 óra

*Code coverage, automated workflow* (összesen kb. 5 óra):
- Code coverage könyvtárak keresése
- JaCoCo részletes megismerése, reportok módja
- Maven scripttel összekötés
- Github Actions workflow bekötése 

*Usability tesztek készítése* (összesen kb. 2.5 óra):
- Előkészületek, usability tesztek módjának kiderítése 1 óra
- Usability tesztek elkészítése [@adamkaj03](https://github.com/adamkaj03)-el és [@zotya0601](https://github.com/zotya0601)-val 1 óra
- Usability teszthez tartozó dokumentáció elkészítése [@adamkaj03](https://github.com/adamkaj03)-el és [@zotya0601](https://github.com/zotya0601)-val 0.5 óra

*Minden más* (összesen kb. 3 óra):
- Meeting a csapattal 2 óra
- Pull request review-k 1 óra maximum

**Csapattag 5** (Horváth Zoltán, ZFVE0X, [zotya0601](https://github.com/zotya0601)) - Összesen kb. 10 óra
*Build rendszer választása* (összesen kb. 3,5 óra):
- Ant, Maven és Gradle build systemek összehasonlítása, egyszerűség, projekthez szabhatóság és integrálhatóság szempontjából (1 óra)
- Mélyebb megismerkedés a Maven buildrendszer működésével (2,5 óra)

*Build rendszer konfigurálása* (összesen kb. 3,5 óra)
- Maven telepítése, konfigurálása oly módon, hogy működése igazodjon a szoftverprojekt felépítéséhez (1 óra)
- További igények felmérése (build mellett tesztek futtatása, futtatható JAR állomány generálása, függőségek kezelése) (0,5 óra)
- Megfelelő Maven plugin-ok keresése és konfigurálása a szükséges feladatok ellátásához (asset mappa másolására, Fat JAR készítésére) (2 óra)

*Build rendszer tesztelése* (összesen kb. fél óra)
- Ellenőrzése annak, hogy azt az eredményt kapom-e, amit a követelmények szerint elvártam (0,5 óra)

*Minden más* (összesen kb. 2,5 óra)
- Meeting a csapattal  (2 óra)
- Pull request review-ok (0,5 óra)

2023. június 2.
