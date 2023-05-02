package main;
import components.graphics.windows.MainWindow;
import components.utils.Game;
import controls.Skeleton;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Main {
    public static BufferedReader stdin;
    public static MainWindow mainWindow;
    public static void main(String[] args){
        Skeleton.setup=true;
        /*
        try {
            ArrayList<String> names = new ArrayList<>();
            names.add("V1");
            names.add("V2");
            Game.setup(names);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        Game.start();
         */

        mainWindow = new MainWindow();
        mainWindow.initializeMenu();
    }

    /**
     * A prototípus idején ez a metódus olvassa be és dolgozza fel
     * a felhasználói bemenetet. A feldolgozás után a saját interfészén
     * a megfelelő függvényt hívja meg, ezeket a függvényeket később
     * már a grafikus felület fogja használni.
     */
    /*public static void parseStdIn() throws IOException, ParseException {
        stdin = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            String line = stdin.readLine();
            if (line == null) {
                continue;
            }
            String[] cmd = line.split(" ");
            try {
                switch (cmd[0]) {
                    case "loadmap":
                        Game.loadmap(cmd);
                        break;
                    case "start":
                        Game.start();
                        break;
                    case "show":
                        Game.show(cmd);
                        break;
                    case "move":
                        Game.move(cmd);
                        break;
                    case "touch":
                        Game.touch(cmd);
                        break;
                    case "craft":
                        Game.craft(cmd);
                        break;
                    case "use":
                        Game.use(cmd);
                        break;
                    case "rob":
                        Game.rob(cmd);
                        break;
                    case "set":
                        Game.set(cmd);
                        break;
                    case "has":
                        Game.has(cmd);
                        break;
                    case "give":
                        Game.give(cmd);
                        break;
                    case "kill":
                        Game.kill(cmd);
                        break;
                    case "isAlive":
                        Game.isAlive(cmd);
                        break;
                    case "exit":
                        exit = true;
                        break;
                }
            } catch (ConcurrentModificationException c) {

            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
        stdin.close();
    }*/
}
