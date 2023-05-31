# CI pipeline készítése a projekthez

## Maven

A szoftver Java nyelvre épül. Alapértelmezetten manuálisan kellett a forráskódot Javac fordítóval futtatható állománnyá. Ez nem egy effektív megoldás, főleg nagyobb projektek esetében, így érdemes egy build system használata. Java környezetben kettő népszerű megoldás létezik erre: Maven és Gradle. Ezek közül a Mavenre esett a választás, de valamennyi képes azon feladatok megoldására, melyeket meg kell oldani. Ide tartozik természetesen a forrásállományok fordítása, az alkalmazás tesztjeinek futtatása, valamint a lefordított alkalmazás csomagolása egy kész, futtatható állománnyá. 

A Maven build rendszer létrehozásához szükség volt elsősorban egy `pom.xml` fájlra, mely a Maven konfigurációt. A Maven rendelkezik egy alapértelmezett elvárással a mappastruktúrát illetően, melyet az alkalmazás - tekintve, hogy Maven nélkül készült - nem vesz figyelembe. Szerencsére ez konfigurálható, így nem szükséges a szoftver struktúrájának átalakítása. 
XML fájl lévén, a `pom.xml` több elemből, objektumból áll. Ezek közül a legnagyobb a `<build>` objektum, mely leírja, maga a fordítás és csomagolás hogyan történik. Itt lehet beállítani többek között a már említett strukturális információkat is: `<sourceDirectory>` adja meg a forráskód helyét, `<testSourceDirectory>` a tesztek helyét, `<outputDirectory>` a fordított bájtkód helyét, a `<directory>` pedig a Maven saját generált fájljait tartalmazza. 

Az alkalmazáshoz, a forráskódon túl tartoznak még különböző asset-ek, képek és JSON állományok, melyeket másolni kell a fordított alkalmazás mellé, mivel nélkülük nem működne. Ezek megadhatók egy `<resources>` blokkban. Ami erőforrásokat itt megadunk, azokkal nem történik több, csupán pont ennyi: Másolásra kerülnek, alapértelmezetten a fordított állomány gyökerébe, viszont a `<targetPath>` megadásával ez is konfigurálható, amennyiben a szükség úgy kívánja, hogy máshová kell helyezni őket. Ez a helyzet például a képekkel, melyeknek egy "assets" mappába kell kerülniük. A "map.json" állomány helye a gyökérben van, ami alapértelmezés, így itt nem szükséges ezt külön beállítani. 

Célunk többek között az, hogy a folyamat végére egy `jar` állományunk legyen. Sajnos a Maven alapértelmezetten nem képes jar fájl létrehozására, viszont funkcionalitásai kibővíthetők pluginokkal. Több plugin is felhasználásra került a folyamathoz:

- maven-jar-plugin
    Ez a plugin felelős a jar fájl létrehozásáért. Sok konfigurációt nem igényel. Elegendő a működéséhez annyi, hogy a `pom.xml` konfigurációban beállítottuk, hogy egy jar fájlt szeretnénk a folyamat végén kapni, valamint hogy melyik osztály a Main osztályunk
- jar-with-dependencies
    Mivel az alkalmazásnak van egy függősége - egy könyvtár, mely JSON állományok olvasásáért és értelmezéséért felel - szükség van erre a függőségre a jar fájlban is. Sajnos nem kerül bele automatikusan, így szükség van erre a pluginra, mely hozzáadja a szükséges függőség bájtkódjait a jar állományhoz. 
- maven-resources-plugin
    Sajnos alapértelmezetten csak fájlok mint resource-ok másolását teszi lehetővé a Maven, ezen segít számunkra ez a plugin. Segítségével megadható egy teljes mappa, melyet megadott helyre szeretnénk másolni a lefordított-csomagolt alkalmazás mappájába
    
Emellett a későbbiekben hozzáadásra került egy `jacoco-maven-plugin`, mely code report-ot generál a tesztek futása után.

Adhatók meg továbbá különböző változók a `<properties>` részben. Ezek lehetnek olyan változók, melyeket a pom.xml fájlon belül használunk (például elérési útvonalak, hogy ne kelljen többször leírni őket), de olyan változók is megadhatók, például SonarCloud számára, mely alapján az adott szoftver hozzáfér a csomagunkhoz és - jelen esetben - statikus analízist végezhessen rajta.

A pom.xml állományban adhatók meg továbbá a fügőségek is.

Mindezeket összevéve mondható, hogy a teljes alkalmazás fordítási és tesztelési folyamatát sikeresen le tudtuk írni egy konfigurációs állományban. Ennek előnye, hogy innentől bárhol, bármely fejlesztő számítógépén elvégezhetőek egyszerűen ugyanazon műveletek, valamint mivel tekinthető egy "source of truth"-nak, építhető köré automatizáció is, hiszen le van írva minden lépés egy gép által is feldolgozható formában.

## CI Pipeline

Miután elkészült a `pom.xml` állomány, hozható létre Github-on egy pipeline, mely megadott események bekövetkezte esetén (például egy beérkező pull request) lefut, és elvégzi a megadott feladatokat (job) a `pom.xml` alapján. Ez a workflow tehát elvégzi az alkalmazás fordítását, tesztelését és a jar generálását, miközben egyéb feladatokat is elláthat, esetünkben ilyen a SonarCube által elvégzett statikus analízis a forráskódon. E statikus analízis eredményéről továbbá intézhető további feladat, ilyen például a tesztek eredményének publikálása a Pull request kommentjei között.
