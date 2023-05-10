package components.utils;

import components.agent.*;
import components.field.Field;
import components.field.Storage;
import components.field.Shelter;
import components.field.Lab;
import components.field.ItemPackage;
import components.gear.*;
import components.graphics.panels.GamePanel;
import components.graphics.windows.DialogWindow;
import components.graphics.windows.MainWindow;
import components.graphics.wrappers.*;
import components.scientist.ActnLabel;
import components.scientist.Scientist;
import controls.Skeleton;

import main.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;


import java.util.*;
import java.util.List;

/**
 * A játék menedzseléséért felelős osztály, mely betölti, majd felépíti a pályát, illetve
 * véget vet a játéknak, ha valaki megnyeri azt.
 */
public class Game {
    //private static Timer t;
    //private static HashSet<GeneticCode> codes;
    //private static List<Scientist> scientists;
    //private static List<Field> fields;
    private static int numGeneticCodes;

    public static Random random = new Random();
    public static GAME_STATE state;

    public static int round;

    public static int currScientistNumber;

    //for Graphics
    private static final List<FieldWrapper> fields;
    private static final List<ScientistWrapper> scientists;

    private static ScientistWrapper current;

    private static GamePanel gp;

    private static RoundState roundState;

/*    private static final Map<String, Field> fieldNames;
    private static Map<String, FieldWrapper> fieldWrapperNames;*/

    static {
        fields = new ArrayList<>();
        scientists = new ArrayList<>();
        roundState = new RoundState();
    }

    /**
     * A pálya felépítése FieldWrapperekből-ekből,
     * illetve ezen belül kerülnek az egyes mezőkre a GeneticCode, Material és Gear objektumok.
     * A Scientist objektumok véletlenszerűen kerülnek egy-egy kezdő Field-re és beállítódik az is,
     * melyik Scientist melyik játékoshoz és milyen néven tartozik.
     */
    public static void setup(ArrayList<String> names) throws IOException, ParseException {
        state=GAME_STATE.ONGOING;
        round = 1;
        currScientistNumber = 0;
        //BufferedImage texture = null;// = ImageIO.read(new File("assets/icons/scientist.png"));
        // pálya betöltése
        loadmap(new String[]{"loadmap", "map.json"});
        int sCounter = 1;
        // Scientistek létrehozása, listához adása, és Fieldre helyezése
        for (String name : names) {
            Scientist s = new Scientist();
            ScientistWrapper sw = new ScientistWrapper(s, name, sCounter);
            scientists.add(sw);
            set_scientist(new String[]{"set", "Scientist", name, "PL"+sCounter++});
        }
        // kezdő játékos beállítása
        current = scientists.get(currScientistNumber);
        // GeneticCode létrehozása, listához adása, és Fieldre helyezése
        set_numGencodes(new String[]{"set", "numGeneticCodes", "4"});
        set_gencode(new String[]{"set", "Gencode", "LA1", "Immunity", "5", "15"});
        set_gencode(new String[]{"set", "Gencode", "LA2", "Stun", "10", "15"});
        set_gencode(new String[]{"set", "Gencode", "LA3", "Dementia", "15", "5"});
        set_gencode(new String[]{"set", "Gencode", "LA4", "Craziness", "10", "10"});

        // Material létrehozása, listához adása, és Fieldre helyezése
        set_material(new String[]{"set", "Material", "ST1", "nucleotide", "50"});
        set_material(new String[]{"set", "Material", "ST2", "aminoacid", "50"});
        set_material(new String[]{"set", "Material", "ST3", "nucleotide", "50"});
        set_material(new String[]{"set", "Material", "ST4", "aminoacid", "50"});

        // Gear létrehozása, listához adása, és Fieldre helyezése
        set_gear(new String[]{"set", "Gear", "SH1", "Coat"});
        set_gear(new String[]{"set", "Gear", "SH2", "Bag"});
        set_gear(new String[]{"set", "Gear", "SH3", "Gloves"});
        set_gear(new String[]{"set", "Gear", "SH4", "Axe"});

    }

    public static void setMainWindow(GamePanel gp) {
        Game.gp = gp;
    }

    public static List<ScientistWrapper> getScientists() {
        return scientists;
    }

    public static ScientistWrapper getCurrentScientist() {
        return current;
    }

    public static int getCurrentRound() {
        return round;
    }

    public static List<FieldWrapper> getFields() {
        return fields;
    }

    public static RoundState getRoundState() {
        return roundState;
    }

    /**
     * A játékot lezáró metódus
     */
    public static void end() {
        state = GAME_STATE.END;
        DialogWindow d = new DialogWindow(current.getName()+" is the winner!","Winner", JOptionPane.INFORMATION_MESSAGE);
        fields.clear();
        scientists.clear();
        current = null;
        Main.mainWindow.getMainFrame().dispose();
        Main.mainWindow = new MainWindow();
        Main.mainWindow.initializeMenu();
    }

    /**
     * A metódus a paraméterként megadott Scientist megtanult kódjainak a számát
     * összehasonlítja az összes genetikai kód számával, ha egyenlő, akkor a
     * Scientist megnyerte a játékot, meghívódik az end metódus
     * @param s a Scientist által megtanult Genetic Code-ok száma
     */
    public static boolean won(Scientist s) {
        // ha a összes genetikai kód megvan
        if (s.getKnownGeneticCodes().size() == numGeneticCodes) {
            // Scientist V1 won the game
            end();
            return true;
        }
        return false;
    }

    public static void setNumGeneticCodes(int numGeneticCodes) {
        Skeleton.printCall("Game.setNumGeneticCodes()");
        Game.numGeneticCodes = numGeneticCodes;
        Skeleton.printReturn("void");
    }

    // Utils ---------------------------------------------------------------------------------------------

    /**
     * Megkeresi és visszaadja az adott névvel rendelkező virológust
     */
    private static ScientistWrapper findScientist(String name) {
        for(ScientistWrapper sw : scientists)
            if(Objects.equals(sw.getName(), name))
                return sw;
        return null;
    }

    /**
     * Megkeresi és visszaadja az adott id-vel rendelkező fieldet
     * @param id a field id-je
     * @return a megadott id-jű fieldre mutató referencia
     */
    public static Field findField(String id) {
        for(FieldWrapper fw:fields){
            if(fw.getId().equals(id)){
                return fw.getField();
            }
        }
        return new Field();
    }

    /**
     * Betölti a paraméterül kapott json fájlból a pályát
     * Json parser beállítása így: <a href="https://www.tutorialspoint.com/json_simple/json_simple_quick_guide.htm">https://www.tutorialspoint.com/json_simple/json_simple_quick_guide.htm</a>
     */
    public static void loadmap(String[] file) throws IOException, ParseException {
        // JSON Parser
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file[1]));
        JSONArray jsonArray = (JSONArray) jsonObject.get("fields");
        for (Object o : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) o;
            // Field id-ja
            String id = (String) jsonObject1.get("id");
            // Field típusa
            String type = (String) jsonObject1.get("type");
            //Szomszédok
            JSONArray neighbours = (JSONArray) jsonObject1.get("neighbours");
            //Fieldeken a pontok, amibe scientsiten rajzolunk
            Point p1 = new Point((int)(long)jsonObject1.get("x1"),(int)(long)jsonObject1.get("y1"));
            Point p2 = new Point((int)(long) jsonObject1.get("x2"),(int)(long)jsonObject1.get("y2"));

            switch (type) {
                // Sima mező
                case "Plain":
                    Field f = new Field();
                    for(Object nbid : neighbours) f.setNeighbour((String)nbid);
                    FieldWrapper fw = new FieldWrapper(p1,p2,id,f);
                    fields.add(fw);
                    break;

                // Storage mező
                case "Storage":
                    Storage st = new Storage();
                    for(Object nbid : neighbours) st.setNeighbour((String)nbid);
                    FieldWrapper stw = new FieldWrapper(p1,p2,id,st);
                    fields.add(stw);
                    break;

                // Shelter mező
                case "Shelter":
                    Shelter sh = new Shelter();
                    for(Object nbid : neighbours) sh.setNeighbour((String)nbid);
                    FieldWrapper shw = new FieldWrapper(p1,p2,id,sh);
                    fields.add(shw);
                    break;

                // Lab mező
                case "Lab":
                    Lab l = new Lab(false);
                    for(Object nbid: neighbours) l.setNeighbour((String)nbid);
                    FieldWrapper law = new FieldWrapper(p1,p2,id,l);
                    fields.add(law);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    /**
     * Létrehozza a virológusokat a megadott nevekkel
     */
    public static void start() {
        state = GAME_STATE.ONGOING;
        while(state == GAME_STATE.ONGOING) {
            for (ScientistWrapper s : scientists) {
                current = s;
                gp.update();
            }
        }
    }

    /**
     * Lepteti a kort a kovetkezo virologusra. Ha korbeert, akkor noveli a round szamlalot.
     */
    public static void pass() {
        //Növeljük a számlálót
        currScientistNumber++;
        //Ha >= a számláló mint ahány scientist van 0-zuk a számlálót
        if (currScientistNumber >= scientists.size()) {
            round++;
            currScientistNumber = 0;
            //ágens időtartam csökkentés és törlés ha nulla
            for(ScientistWrapper s : scientists) {
                s.getScientist().newTurn();
            }
        }
        //átállítjuk a jelenlegi scientistet a számláló szerintire
        current = scientists.get(currScientistNumber);
        roundState.reset();
        //Vítustánc elvégzése
        if(current.getScientist().validateAction(ActnLabel.NEW_TURN) == ActnLabel.CRAZY || current.getScientist().validateAction(ActnLabel.NEW_TURN) == ActnLabel.BEAR) {
            move();
            roundState.reset();
            move();
        }
        //GamePane firssítése
        gp.update();
    }

    /**
     * Metódus, amin keresztül megpróbáljuk elmozgatni a Scientistet
     */
    public static void move() {;
        // Ha mar korabban is igaz volt a round state move allapota -> mar lepett ebben a korben
        // tehat nem engedjuk ujra lepni.
        if(roundState.setMove()) {
            return;
        }
        //Elmozgatjuk a Scientist ha lehet
        current.getScientist().move();
        //GamePanel frissítése
        gp.update();
    }

    /**
     * Metódus amin keresztül a Scientist le tudja  tapogatni azt a mezőt amin áll
     */
    public static void touch() {
        // Ha mar korabban is igaz volt a round state touch allapota -> mar tapogatott ebben a korben
        // tehat nem engedjuk ujra lepni.
        if (roundState.setTouch()) {
            return;
        }
        //Letapogatjuk a mezot
        ItemPackage ip = current.getScientist().touch();
        //Ha ures akkor return, jelezzuk a felhasznalonak hogy nem sikerult.
        if(ip==null) {
            DialogWindow d = new DialogWindow("Nem tudsz/lehet semmit se felvenni!", "Sikertelen művelet");
            return;
        }
        //Wrapperbe csomagoljuk az itempackage tartalmát
        ArrayList<Wrapper> wrappers = new ArrayList<>();
        //Először a GeneticCodeokat
        GeneticCode gc = ip.getCode();
        if (gc != null) {
            wrappers.add(new GenCodeWrapper(gc));
        }

        //Aztán a materialokat
        Material m = ip.getMaterial();
        if (m != null) {
            wrappers.add(new MaterialWrapper(m));
        }

        //Végül a geareket
        for (Gear g : ip.getGears()) {
            wrappers.add(new GearWrapper(g));
        }
        //Ha üres valahogy az itempackage de nem null, akkor jelezzük ezt is,
        //de ilyennek sehogy sem kéne történnie
        if (wrappers.isEmpty()) {
            DialogWindow d = new DialogWindow("Nem lehet semmit felvenni!", "Sikertelen művelet");
            return;
        }
        //Kiválasztjuk mit szeretne felvenni a játékos
        Wrapper selected = gp.select(wrappers);
        boolean ended = false;
        //Ha bezárta az ablakot, visszatérünk
        if(selected==null)
            return;
        //Megnézzük, hogy mi a típusa és a scientistnek adjuk.
        if (selected instanceof GenCodeWrapper) {
            ended = current.getScientist().learn(((GenCodeWrapper) selected).getGeneticCode());
        }
        if (selected instanceof MaterialWrapper) {
            current.getScientist().add(((MaterialWrapper) selected).getMaterial());
        }
        //Ha 3 vagy több gear van(utóbbi lehetetlen), akkor el kell dobnia egyet.
        if (selected instanceof GearWrapper) {
            if(current.getScientist().getGears().size()>=3)
                 drop();
            //Megkapja a scientist és levesszük a fieldről az adott Geart.
            current.getScientist().add(((GearWrapper) selected).getGear());
            current.getScientist().getField().remove(((GearWrapper)selected).getGear());
        }
        //GamePanel frissítése
        if(!ended)
            gp.update();
    }

    /**
     * Metódus, amin keresztül lefut a craftolás folyamata
     */
    public static void craft() {
        // Ha mar korabban is igaz volt a round state craft allapota -> mar craftolt ebben a korben
        // tehat nem engedjuk ujra lepni.
        if (roundState.setCraft()) {
            return;
        }
        //Kiszedjük a jelenlegi scientist genetikai kódjait és wrapperbe rakjuk
        Set<GeneticCode> knownGeneticCodes = current.getScientist().getKnownGeneticCodes();
        ArrayList<Wrapper> wrappers = new ArrayList<>();
        for (GeneticCode gc : knownGeneticCodes) {
            wrappers.add(new GenCodeWrapper(gc));
        }
        //Ha nincs megtanult genetikai kód jelezzük a felhasználónak
        if (wrappers.isEmpty()) {
            new DialogWindow("Nem lehet craftolni!", "Sikertelen művelet");
            return;
        }
        //Kiválasztja melyik genetikai kódot szeretné lecraftolni a játékos
        GenCodeWrapper selected = (GenCodeWrapper) gp.select(wrappers);
        //Ha bezárta az ablakot null
        if (selected == null) {
            return;
        }
        //Lecraftoljuk a genetikai kódot ágenssé, ha nem sikerül jelezzük.
        if (!current.getScientist().craft(selected.getGeneticCode())) {
            new DialogWindow("Nem lehet craftolni, nincs elég anyag!", "Sikertelen művelet");
            return;
        }
        //Frissítjük a GamePanel-t
        gp.update();
    }


    /**
     * Metódus amin keresztül végigmegy egy ágens felkenése egy másik virológusra
     * @param a előre kiválaszott ágens amit használni fogunk
     */
    public static void use(Agent a) {
        //Megnézzük, hogy volt-e már ebben a körben rob megnyomva, ha igen akkor return
        if (roundState.setUse()) {
            return;
        }
        //Eltaroljuk a DialogWindownak a valaszthato Scientisteket wrapperbe
        List<Wrapper> choices = new ArrayList<>();
        //Kivalasztjuk a scientists-bol, hogy kik vannak a fielden
        for(Scientist d : current.getScientist().getField().getScientists()){
            for(ScientistWrapper sw:scientists){
                if(d==sw.getScientist())
                    //Ha egy olyat talalunk, aki a fielden is van akkor eltaroljuk.
                    choices.add(sw);
            }
        }
        ScientistWrapper defender = (ScientistWrapper)gp.select(choices);
        //Ha valahogy esetleg null lenne, ami elvileg lehetetlen
        if(defender!=null){
            current.getScientist().useOn(defender.getScientist(),a);
        } else {
            DialogWindow d = new DialogWindow("Nem lehet felkenni az ágenst!", "Sikertelen művelet");
        }
        //Ablak frissitese, hogy eltunjon illetve megjelnjen az agens a megfelelo helyeken
        gp.update();
    }

    /**
     * Ezen a metóduson keresztül történik a rablás
     */
    public static void rob() {
        //Megnézzük, hogy volt-e már ebben a körben rob megnyomva, ha igen akkor return
        if (roundState.setRob()) {
            return;
        }
        //Támadó és támadott megkeresése
        Scientist attacker = current.getScientist();
        Scientist victim = null;
        for (Scientist s : attacker.getField().getScientists()){
            //Mivel csak ketten vannak max egx fielden ezét elég így keresni
            if (s != attacker) {
                victim = s;
                break;
            }
        }
        //Ha nincs kit rabolni, akkor jelezzük a felhasználónak
        if (victim == null) {
            DialogWindow d = new DialogWindow("Nincs kit kirabolni!", "Sikertelen művelet");
            return;
        }
        //Megnézzük sikertelen-e a rablás, ha igen jelezzük a felhasználónak
        if (!attacker.rob(victim)) {
            DialogWindow d = new DialogWindow("Sikertelen rablás!", "Sikertelen művelet");
        }
        //GamePanelen lévő adatok frissítése
        gp.update();
    }

    /**
     * Nem használjuk csak teszteléshez
     * @param args a standard input szóközök mentén feladarabolva
     */
    public static void set(String[] args) {
        switch(args[1]) {
            case "Scientist":
                set_scientist(args);
                break;
            case "Gear":
                set_gear(args);
                break;
            case "Material":
                set_material(args);
                break;
            case "Gencode":
                set_gencode(args);
                break;
            case "Numgencodes":
                set_numGencodes(args);
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    /**
     * Ezzel lehet beállítani, hogy hány Genetic Code ismerete szükséges a győzelemhez
     * arra figyelni kell, hogy megfelelő mennyiségű laborra állítsunk be különféle Genetic Codeokat ezután
     * illetve ne legyen több a mindenkori megtalálható különböző Genetic Codeok száma.
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void set_numGencodes(String[] args) {
        numGeneticCodes = Integer.parseInt(args[2]);
        System.out.println("Number of Genetic Codes is set to "+numGeneticCodes);
    }

    /**
     * Ezzel lehet beállítani az args-ba bekapott Virológust a szintén args-ban bekapott fieldre.
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void set_scientist(String[] args) {
        ScientistWrapper sw = findScientist(args[2]);
        assert sw != null;
        Scientist s = sw.getScientist();
        Field f = findField(args[3]);
        f.accept(s);
        System.out.println("Scientist " + args[2] + " set on field " + args[3]);
    }

    /**
     * Ezzel lehet beállítani az args-ba bekapott Geart a szintén args-ban bekapott fieldre.
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void set_gear(String[] args) {
        Field f = findField(args[2]);
        switch(args[3]) {
            case "Coat":
                f.add(new Coat(100));
                break;
            case "Gloves":
                f.add(new Gloves());
                break;
            case "Bag":
                f.add(new Bag());
                break;
            case "Axe":
                f.add(new Axe());
                break;
            default:
                System.out.println("Invalid input");
                return;
        }
        System.out.println("Gear " + args[3] + " has been placed on field " + args[2]);
    }

    /**
     *  Ezzel lehet beállítani az args-ba bekapott materialt a szintén args-ban bekapott fieldre.
     *  A material mennyiségét is szintén argsból szedjük ki.
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void set_material(String[] args) {
        Storage f = (Storage)findField(args[2]);
        switch(args[3]) {
            case "nucleotide":
                f.add(new Material("nucleotide", Integer.parseInt(args[4])));
                break;
            case "aminoacid":
                f.add(new Material("aminoacid", Integer.parseInt(args[4])));
                break;
            default:
                System.out.println("Invalid input");
                return;
        }
        System.out.println("Material " + args[3] + ": " + args[4] + " has been placed on field " + args[2]);
    }

    /**
     * Ezzel lehet beállítani az args-ba bekapott Genetic Code-ot a szintén args-ban bekapott fieldre.
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void set_gencode(String[] args) {
        Lab f = (Lab)findField(args[2]);
        switch(args[3]) {
            case "Stun":
                f.add(new GeneticCode(new Stun(3),
                        new Material("aminoacid", Integer.parseInt(args[4])),
                        new Material("nucleotide", Integer.parseInt(args[5]))));
                break;
            case "Dementia":
                f.add(new GeneticCode(new Dementia(3),
                        new Material("aminoacid", Integer.parseInt(args[4])),
                        new Material("nucleotide", Integer.parseInt(args[5]))));
                break;
            case "Craziness":
                f.add(new GeneticCode(new Craziness(3),
                        new Material("aminoacid", Integer.parseInt(args[4])),
                        new Material("nucleotide", Integer.parseInt(args[5]))));
                break;
            case "Immunity":
                f.add(new GeneticCode(new Immunity(3),
                        new Material("aminoacid", Integer.parseInt(args[4])),
                        new Material("nucleotide", Integer.parseInt(args[5]))));
                break;
            default:
                System.out.println("Invalid input");
                return;
        }
        System.out.println("Genetic Code " + args[3] + " aminoacid: " + args[4] +
                " nucleotide: " + args[5] + " has been placed on field " + args[2]);
    }

    /**
     * Adott objektum meglétét ellenőrző parancs megvalósítása
     * @param args a standard input szóközök mentén feladarabolva
     */
    public static void has(String[] args) {
        switch(args[1]) {
            case "Gear":
                has_gear(args);
                break;
            case "Material":
                has_material(args);
                break;
            case "Gencode":
                has_gencode(args);
                break;
            case "Crafted":
                has_crafted(args);
                break;
            case "Active":
                has_active(args);
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    /**
     * Megadott genetikai kód meglétét ellenőrzi az adott virológuson
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void has_gencode(String[] args) {
        ScientistWrapper sw = findScientist(args[2]);
        assert sw != null;
        Scientist s = sw.getScientist();
        Set<GeneticCode> gc = s.getKnownGeneticCodes();
        for (GeneticCode g : gc) {
            if (g.getClass().getSimpleName().equals(args[3])) {
                //.out.println("Scientist " + s.getName() + " has gencode " + g.getDetails());
                return;
            }
        }
        //System.out.println("Scientist " + s.getName() + " doesn't have gencode " + args[3]);
    }

    /**
     * Annak ellenőrzése, van-e adott aktív ágens adott virológuson - kiírja, hogy van-e, és ha igen, akkor a hatásidejét is
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void has_active(String[] args) {
        ScientistWrapper sw = findScientist(args[2]);
        assert sw != null;
        Scientist s = sw.getScientist();
        List<Agent> agents = s.getActiveAgents();
        Map<Agent, Integer> map = checkHasAgent(agents, args[3]);
        Agent found = map.keySet().iterator().next();
        if (found != null) {
            System.out.println("Scientist " + args[2] + " has active agent " + args[3] + " time: " + found.getDuration());
        } else {
            System.out.println("Scientist " + args[2] + " doesn't have active agent " + args[3]);
        }
    }

    /**
     * Annak ellenőrzése, van-e adott, craftolt ágens adott virológusnál - kiírja, hogy van-e, és ha igen, akkor a mennyiségét is
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void has_crafted(String[] args) {
        ScientistWrapper sw = findScientist(args[2]);
        assert sw != null;
        Scientist s = sw.getScientist();
        List<Agent> agents = s.getCrafted();
        Map<Agent, Integer> map = checkHasAgent(agents, args[3]);
        Agent found = map.keySet().iterator().next();
        if (found != null) {
            System.out.println("Scientist " + args[2] + " has crafted agent " + args[3] + " quantity: " + map.get(found));
        } else {
            System.out.println("Scientist " + args[2] + " doesn't have crafted agent " + args[3]);
        }
    }

    /**
     * Egy ágens listában megkeresi, van-e benne a stringgel megadott típusú ágens leszármazott
     * @param agents agensek listája
     * @param arg Keresett ágens típusa
     * @return Visszaadja a legutoljára előforduló ilyen objektumot, illetve hogy mennyi van pontosan az adott ágensből
     */
    private static Map<Agent, Integer> checkHasAgent(List<Agent> agents, String arg) {
        int quantity = 0;
        Agent found = null;
        Map<Agent, Integer> ret = new HashMap<>();
        for (Agent a : agents) {
            if (a.getName().equals(arg)) {
                quantity++;
                found = a;
            }
        }
        ret.put(found, quantity);
        return ret;
    }

    /**
     * Annak ellenőrzése, van-e adott material adott virológusnál - kiírja, hogy van-e, és ha igen, akkor a mennyiségét is
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void has_material(String[] args) {
        ScientistWrapper sw = findScientist(args[2]);
        assert sw != null;
        Scientist s = sw.getScientist();
        if(s != null) {
            Map<String, Material> mats = s.getMaterials();
            Material m;
            switch(args[3]) {
                case "nucleotide":
                    m = mats.get("nucleotide");
                    if(m == null) {
                        System.out.println("Scientist " + args[2] + " doesn't have material nucleotide");
                    }
                    else {
                        // kimeneti nyelv
                        System.out.println("Scientist " + args[2] + " has material nucleotide quantity: " + m.getQuantity());
                    }
                    break;
                case "aminoacid":
                    m = mats.get("aminoacid");
                    if(m == null) {
                        System.out.println("Scientist " + args[2] + " doesn't have material aminoacid");
                    }
                    else {
                        // kimeneti nyelv
                        System.out.println("Scientist " + args[2] + " has material aminoacid quantity: " + m.getQuantity());
                    }
                    break;
            }
        }
    }

    /**
     * Megvizsgálja, van-e bizonyos védőfelszerelés az adott virológuson
     * @param args a standard input szóközök mentén feladarabolva
     */
    private static void has_gear(String[] args) {
        ScientistWrapper sw = findScientist(args[2]);
        assert sw != null;
        Scientist s = sw.getScientist();
        List<Gear> gears = s.getGears();
        Gear found = checkGear(gears, args[3]);
        boolean has = found != null;
        switch(args[3]) {
            case "Coat":
                System.out.println("Scientist " + args[2] + (has ? " has gear " : "doesn't have gear") + args[3]);
                break;
            case "Gloves":
                System.out.println("Scientist " + args[2] + (has ? " has gear " : "doesn't have gear") + args[3] + (has ? " uses: " + ((Gloves) found).getDuration() : ""));
                break;
            case "Bag":
                System.out.println("Scientist " + args[2] + (has ? " has gear " : "doesn't have gear") + args[3]);
                break;
            case "Axe":
                System.out.println("Scientist " + args[2] + (has ? " has gear " : "doesn't have gear") + args[3] + (has ? " uses: " + ((Axe) found).getDuration() : ""));
                break;
            default:
                System.out.println("Invalid input");
        }

    }

    /**
     * Egy paraméterben megadott listából (heterogén kollekcióból) visszaadja a megadott osztálybeli objektumot
     * @param gears lista
     * @param name osztály neve
     * @return megadott objektum
     */
    private static Gear checkGear(List<Gear> gears, String name) {
        for (Gear g : gears) {
            if(g.getClass().getSimpleName().equals(name)) return g;
        }
        return null;
    }

    /**
     * Objektumot ad a Scientist-hez
     * @param args parancs paraméterei
     */
    public static void give(String[] args) {
        // 1. paraméter az, hogy milyen típusú objektumot kap
        switch (args[1]) {
            case "Gear":
                giveGear(args);
                break;
            case "Material":
                giveMaterial(args);
                break;
            case "Gencode":
                giveGeneticCode(args);
                break;
            case "Crafted":
                giveCrafted(args);
                break;
            case "Active":
                giveActive(args);
                break;
        }
    }
    /**
     * Aktív ágenst ad a Scientist-hez
     * @param args parancs paraméterei give Active <player> <agent>
     */
    private static void giveActive(String[] args) {
        // 2. paraméter a Scientist neve
        String player = args[2];
        ScientistWrapper playerObjectw = findScientist(player);
        assert playerObjectw != null;
        Scientist playerObject = playerObjectw.getScientist();
        // 3. paraméter az, hogy milyen típusú objektumot kap
        switch (args[3]) {
            case "Stun":
                playerObject.addActiveAgent(new Stun(Integer.parseInt(args[4])));
                break;
            case "Dementia":
                playerObject.addActiveAgent(new Dementia(Integer.parseInt(args[4])));
                break;
            case "Craziness":
                playerObject.addActiveAgent(new Craziness(Integer.parseInt(args[4])));
                break;
            case "Immunity":
                playerObject.addActiveAgent(new Immunity(Integer.parseInt(args[4])));
                break;
            case "Bear":
                playerObject.addActiveAgent(new Bear(Integer.parseInt(args[4])));
                break;
            default:
                break;
        }
        // kimeneti nyelv
        System.out.println("Scientist " + player + " has been given active agent " + args[3] + " time: " + args[4]);
    }
    /**
     * Crafted ágenst ad a Scientist-hez
     * @param args parancs paraméterei give Crafted <player> <agent>
     */
    private static void giveCrafted(String[] args) {
        // 2. paraméter a Scientist neve
        String player = args[2];
        ScientistWrapper playerObjectw = findScientist(player);
        assert playerObjectw != null;
        Scientist playerObject = playerObjectw.getScientist();
        // 3. paraméter az, hogy milyen típusú objektumot kap
        switch (args[3]) {
            case "Stun":
                playerObject.add(new Stun(Integer.parseInt(args[4])));
                break;
            case "Dementia":
                playerObject.add(new Dementia(Integer.parseInt(args[4])));
                break;
            case "Craziness":
                playerObject.add(new Craziness(Integer.parseInt(args[4])));
                break;
            case "Immunity":
                playerObject.add(new Immunity(Integer.parseInt(args[4])));
                break;
            default:
                break;
        }
        // kimeneti nyelv
        System.out.println("Scientist " + player + " has been given crafted agent " + args[3] + " time: " + args[4]);
    }

    /**
     * GeneticCode-ot ad a Scientist-hez
     * @param args parancs paraméterei give Gencode <player> <agent> <aminoacid> <nucleotide>
     */
    private static void giveGeneticCode(String[] args) {
        // 2. paraméter a Scientist neve
        String player = args[2];
        ScientistWrapper playerObjectw = findScientist(player);
        assert playerObjectw != null;
        Scientist playerObject = playerObjectw.getScientist();
        // 3. paraméter az, hogy milyen típusú objektumot kap
        switch (args[3]) {
            case "Stun":
                playerObject.add(new GeneticCode(new Stun(3), Integer.parseInt(args[4]), Integer.parseInt(args[5])));
                break;
            case "Dementia":
                playerObject.add(new GeneticCode(new Dementia(3), Integer.parseInt(args[4]), Integer.parseInt(args[5])));
                break;
            case "Craziness":
                playerObject.add(new GeneticCode(new Craziness(3), Integer.parseInt(args[4]), Integer.parseInt(args[5])));
                break;
            case "Immunity":
                playerObject.add(new GeneticCode(new Immunity(3), Integer.parseInt(args[4]), Integer.parseInt(args[5])));
                break;
            default:
                break;
        }
        // kimeneti nyelv
        System.out.println("Scientist " + player + " has been given gencode " + args[3] + " aminoacid: " + args[4] + " nucleotide: " + args[5]);
    }

    /**
     * Material-t ad a Scientist-hez
     * @param args parancs paraméterei give Material <player> <material> <amount>
     */
    private static void giveMaterial(String[] args) {
        // 2. paraméter a Scientist neve
        String player = args[2];
        ScientistWrapper playerObjectw = findScientist(player);
        assert playerObjectw != null;
        Scientist playerObject = playerObjectw.getScientist();
        // 3. paraméter az, hogy milyen típusú objektumot kap
        switch (args[3]) {
            case "nucleotide":
                playerObject.add(new Material("nucleotide", Integer.parseInt(args[4])));
                break;
            case "aminoacid":
                playerObject.add(new Material("aminoacid", Integer.parseInt(args[4])));
                break;
            default:
                break;
        }
        // kimeneti nyelv
        System.out.println("Scientist " + player + " has been given material " + args[3] + " quantity: " + args[4]);
    }

    /**
     * Gear-t ad a Scientist-hez
     * @param args parancs paraméterei give Gear <player> <gear>
     */
    private static void giveGear(String[] args) {
        // 2. paraméter a Scientist neve
        String player = args[2];
        ScientistWrapper playerObjectw = findScientist(player);
        assert playerObjectw != null;
        Scientist playerObject = playerObjectw.getScientist();
        // 3. paraméter az, hogy milyen típusú objektumot kap
        switch (args[3]) {
            case "Coat":
                playerObject.add(new Coat(100));
                break;
            case "Gloves":
                playerObject.add(new Gloves());
                break;
            case "Bag":
                playerObject.add(new Bag());
                break;
            case "Axe":
                playerObject.add(new Axe());
                break;
            default:
                break;
        }
        // kimeneti nyelv
        System.out.println("Scientist " + player + " has been given gear " + args[3]);
    }

    /**
     * Kiválaszt egy gear-t és eldobja
     */
    public static void drop() {
        Scientist s = current.getScientist();
        ArrayList<Wrapper> wrappers = new ArrayList<>();
        for (Gear g : s.getGears()) {
            wrappers.add(new GearWrapper(g));
        }
        GearWrapper selected = (GearWrapper) gp.select(wrappers);
        if (selected != null){
            s.remove(selected.getGear());
        }
        gp.update();
    }

    /**
     * A virolgogus megöli a másik virolgógust
     */
    public static void kill(String[] args) {
        Scientist attacker = current.getScientist();
        Scientist victim = null;
        ScientistWrapper victimWrapper = null;
        for (Scientist s : attacker.getField().getScientists()){
            if (s != attacker) {
                victim = s;
                break;
            }
        }
        if (victim == null) {
            DialogWindow d = new DialogWindow("Nincs célpont!", "Sikertelen művelet");
            return;

        }

        if (attacker.kill(victim)) {
            victim.killed();
            attacker.getField().remove(victim);
            for (ScientistWrapper sw : scientists) {
                if (sw.getScientist().equals(victim)) {
                    victimWrapper = sw;
                }
            }
            scientists.remove(victimWrapper);
        } else {
            DialogWindow d = new DialogWindow("Nem lehet megölni!", "Sikertelen művelet");
        }
        gp.update();
    }

    /**
     * Annak ellenőrzése, hogy a megadott virológus életben van-e
     * @param args a standard input szóközök mentén feladarabolva
     */
    public static void isAlive(String[] args) {
        ScientistWrapper sw = findScientist(args[1]);
        assert sw != null;
        Scientist s = sw.getScientist();

        if (s == null) {
            System.out.println("Scientist " + args[1] + " is not alive");
        }
        else {
            System.out.println("Scientist " + args[1] + " is alive");
        }
    }

    /**
     * Visszaadja a koron kovetkezo virologus altal birtokolt anyagokat, wrapperbe csomagolva.
     * @return a wrapperek listaja
     */
    public static List<Wrapper> getCurrentScientistMaterials() {
        ArrayList<Wrapper> ret = new ArrayList<>();
        for (Map.Entry<String, Material> entry : current.getScientist().getMaterials().entrySet()) {
            ret.add(new MaterialWrapper(entry.getValue()));
        }
        return ret;
    }

    /**
     * Visszaadja a koron kovetkezo virologus altal birtokolt anyagokat, wrapperbe csomagolva.
     * @return a wrapperek listaja
     */
    public static List<Wrapper> getCurrentScientistActives() {
        ArrayList<Wrapper> ret = new ArrayList<>();
        for (Agent a : current.getScientist().getActiveAgents()) {
            ret.add(new AgentWrapper(a));
        }
        return ret;
    }

    /**
     * Visszaadja a koron kovetkezo virologus altal birtokolt felszereleseket, wrapperbe csomagolva.
     * @return a wrapperek listaja
     */
    public static List<Wrapper> getCurrentScientistGears() {
        ArrayList<Wrapper> ret = new ArrayList<>();
        for (Gear g : current.getScientist().getGears()) {
            ret.add(new GearWrapper(g));
        }
        return ret;
    }

    /**
     * Visszaadja a koron kovetkezo virologus altal birtokolt kraftolt, wrapperbe csomagolva.
     * @return a wrapperek listaja
     */
    public static List<Wrapper> getCurrentScientistCrafted() {
        ArrayList<Wrapper> ret = new ArrayList<>();
        for (Agent a : current.getScientist().getCrafted()) {
            ret.add(new AgentWrapper(a));
        }
        return ret;
    }
}
































