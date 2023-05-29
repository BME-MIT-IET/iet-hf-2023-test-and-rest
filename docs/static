# Dokumentáció

## Technológia fókusz

###  Statikus analízis

**Sonarqube** első analízis eredményei [itt ](https://sonarcloud.io/summary/overall?id=bmetestandrest_test) találhatóak:
![kép](https://user-images.githubusercontent.com/65022765/237048487-19824bf7-b2de-459a-b857-c0e174d576b3.png)

A sonarcloud implementálását nem lokális formában, hanem githubon keresztül végeztem el. A hozzá tartozó commit és [issue ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/issues/11)megtalálható a closed issuesban.

[Itt ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/issues/13)van az Issue a hibák javításához.

A hibák javítása után eltűntek a bugok:
![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/bb4e4d86-813b-42ee-b271-f344e0ed8ee4)

A SonarCloud még hibának írta, a Security Hotspot-okat, ezeket is javítottuk az [Issue](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/issues/27) a hiba javításához. [Pull Request](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/pull/28)
A hibák javítása után eltűntek a Security Hotspot hibák:
![Képernyőkép 2023-05-28 101812](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/100281589/fca2d1ed-cdce-40fc-a1aa-12b8fd8e0f8f)


Manuális github átvizsgáláshoz a [Game.java](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/main/src/components/utils/Game.java) file-t választottam. 

Megjegyzéseim/hibák:
* Csillag import
* [Lehetnének ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#LL62C5-L66C6)egy sorban inicializálva a definícióval.
* [Lehetne ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#LL83C9-L83C9)egy külön metódus, mondjuk populateScientists
* [Ezeket ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L92)a 4-es sorokat ha logikailag egybe tartoznak külön metódusba szedném
* [Getter](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L112),setterek bárhol legyenek de ne középen, legyenek elöl vagy lent
* Null check sehol nincsen semmire, null check mindenhova.
* Van [kettő ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L177)find egymás alatt, az egyik null-t a másik pedig new Objectet ad vissza ha nem talál elemet, a nevükben fel kéne tüntetni.
* Túl nagy, [szét kell szedni](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L202).
* [Rossz ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L207)és [rosszabb ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L216)castolások.
* Egész [switch ](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L219)lehetne egy method.
* [Nem mond semmit a függvény neve.](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L261)
* [Szintén nem mond semmit a függvény neve.](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L276)
* [Sortörés, formázás.](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L292)
* [Túl nagy metódus](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L319)
* [InstanceOf használata ??? (ez nem volt tiltott a hfben? xD)](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L370)
* [Castolás bármilyen check nélkül, mi van ha exceptiont dob?](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L403)
* [Castolás bármilyen check nélkül](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L437)
* [assert használata](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L522)
* [Sortörés](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L751)
* [Sortörés](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/blob/97cd210feaff0cbd147e17cb04d80ad991039703/src/components/utils/Game.java#L906)


Az elemzés a teljesség igénye nélkül készült, több mint 1000soros kódot review-zni rengeteg munka lenne jól.
A javításokat elvégeztem +220 sor módosítása után, ezzel **növelve az elkészült kód minőségét** a tesztek segítségével.
[Pull request hozzá
](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/pull/22)
## Termék/felhasználó fókusz

### Manuális tesztek

Manuális teszt tervek:
* Játék indítása
* Felhasználó hozzáadása
* Tapogatás
* Lépés


| ID | Test Case | Testing Steps | Expected Result | Status | Actual Result | Comments |
|--- | --- | --- | --- | --- | --- | --- |
| 1 | Játék indítása| Játék futtatása, start game megnyomása | Átlépünk a setup players mezőre | Passed | ![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/1efeac8f-066b-4f99-9651-86e761931e0b) | N/A |
| 2 | Craftolás megfelelő mennyiséggel | 1. Craft gomb lenyomása 2. Kiválasztás hogy mit készítsünk el.| Látjuk az elkészült varázslatott. | Passed | Elkészült varázslat: ![image](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/100281589/024750b6-6c6c-4060-bd1c-e5fbd95b4151)
| 3 | Varázslat felhasználása magadra | 1. Crafted fülön varázslat megnyomása 2. Kiválasztás, hogy saját magunkra kenjük | Látjuk az Active fülön a magunkra kent varázslatott | Passed | Magunkra kent varázslat: ![image](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/100281589/e0afff85-c3bf-41f1-88cb-82fc5f8d5c03)
| 4 | Játék megnyerése | 1. Move, míg lila mezőre nem érünk 2. Lila mezőn Touch 3.Majd a recept megtanulása 4. Folytatás előrről, míg az összes Lila mezőn nem jártunk | Winner felíratt látható a megfelelő játékosnál | Passed | Winner felirat: ![image](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/100281589/53efe6bf-fb51-4577-ab9f-b6d44f903858)
| 5 | Játékosok hozzáadása | A setup players oldalon 'add' gombra nyomva név megváltoztatása nélkül.  | Nem történik semmi | Passed | ![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/98bd5981-7b5a-4a3b-8952-b1b70bf99d68)
| 6 | Játék játék indítása karakterek hozzáadása után kevés karakterrel| Menyomjuk a start game gombot karakterek hozzáadása nélkül, megnyomjuk a start game gombot 1 karakter hozzádása után.  | Nem történik semmi | Passed | ![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/98bd5981-7b5a-4a3b-8952-b1b70bf99d68)
| 7 | Játékosok hozzáadása | A setup players oldalon 'add' gombra nyomva név megváltoztatásával. | Megjelenik a név a játékosok között | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/b5175d54-0cc7-4629-a251-ebb41154a47a)
| 8 | Játék játék indítása karakterek hozzáadása után | Karaktereket hozzáadása után rányomunk a start game-re. | Átirányít minket a játék oldalára. | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/1a9629d4-3a4c-4cf2-aaaf-8a13a2b2b1b9)
| 9 | Lépés | A játék menete folyamán megnyomjuk a move gombot. | A move gomb inaktívvá válik és átkerül a karakter egy másik mezőre. | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/1ee1ec04-b254-44d6-b2f1-5522788aa2bb)
| 10 | Lopás különböző mezőn | Játék folyamán ha a karakterünk egyedül van a mezőn, a rob gombot nyomjuk meg. | Megjelenik a nincs kit kirabolni felirat | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/d0ac5f93-7520-429b-b405-e0b1144b0491)
| 11 | Tapogatás | Játék közben touch gombra nyomunka | Megjelenik a mezőn lévő nyersanyag egy külön ablakon. | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/ad39e95a-9082-4737-acf0-258690371b43)
| 12 | Nyersanyag felvétele | Tapogatás után a megjelent nyersanyagra kattintunk. | A nyersanyag hozzáadódik az eszköztárunkhoz | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/c4a22ba6-2a80-4b8f-88b6-eb832f9f88bf)
| 13 | Craft gomb nyomása | Kevés nyersanyag esetén megnyomjuk a craft gombot. | Megjelenik a nem lehet craftolni ablak. | Passed | ![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/376799d8-1552-4934-9a5b-f2064820ac4d)
| 14 | Eszköztár megnézése 1 | Rákattintunk a Material mezőre | Megjelennek a nyersanyagok | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/391f3029-e1ea-4fd5-ad02-f16b6c4ac738)
| 15 | Eszköztár megnézése 2 | Rákattintunk a Gear mezőre | Megjelennek a felszerelések (ha vannak) | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/dddddb85-3f62-4cf6-8ddb-5d5beebc1249)
| 16 | Eszköztár megnézése 3 | Rákattintunk a Crafted mezőre | Megjelenneka  crafted itemek (ha vannak) | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/903d77df-442a-4b71-8644-b8adb4d75f0c)
| 17 | Eszköztár megnézése 4 | Rákattintunk az Active mezőre | Megjelennek az aktív hatások (ha vannak) | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/6c4e3359-45c9-453c-a775-2c68d3b144cf)
| 18 | Kör továbbadása | Játék menet során Pass gomb megnyomása | A következő karakter lesz aktív, megváltozik a current player neve és az alakja és színe a bábúnak a jobb oldali panelen. | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/4b2e5231-f7d8-4c13-87bd-782cf5305bb3)
| 19 | Rablás egy mezőn álló játékosokkal | Játék közben rányomunk a Rob gombra úgy hogy egy másik karakter is a mezőn áll. | Megjelenik a rablás sikerességét leíró üzenet. | Passed |![kép](https://github.com/BME-MIT-IET/iet-hf-2023-test-and-rest/assets/65022765/2802fb0f-e88e-4959-be95-b5d6a97895bc)
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
