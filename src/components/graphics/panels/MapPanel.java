package components.graphics.panels;

import components.graphics.wrappers.FieldWrapper;
import components.graphics.wrappers.ScientistWrapper;
import components.scientist.Scientist;
import components.utils.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;


public class MapPanel extends JPanel {
    BufferedImage map;
    private static final Logger LOGGER = Logger.getLogger(MapPanel.class.getName());

    /**
     * A pálya képének betöltése a mapPanelre
     */
    public MapPanel() {
        try {
            map = ImageIO.read(new File("assets/maps/map1.png"));
        } catch (Exception e) {
            LOGGER.severe("An error occurred while scanning the map\n");
        }
    }

    /**
     * Kirajzoljuk a virológusokat a megadott pontokba a Game wrapperei alapján
     * @param g ahová rajzolunk
     */
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(map,0,0,1270,1025,null);
        List<FieldWrapper> fields=Game.getFields();
        List<ScientistWrapper> scientistWrappers = Game.getScientists();
        for(FieldWrapper fw: fields){
            List<Scientist> scientists = fw.getField().getScientists();
            for(ScientistWrapper scWrapper : scientistWrappers) {
                for(int i = 0;i<scientists.size();i++) {
                    if(scWrapper.getScientist().equals(scientists.get(i))) {
                        if(i == 0) {
                            g2d.drawImage(scWrapper.getTexture(), (int)(fw.getP1().x * (1275/1536.0)), (int)(fw.getP1().y * (1025/1080.0)), 128/2, 128/2, null);
                        } else {
                            g2d.drawImage(scWrapper.getTexture(), (int)(fw.getP2().x * (1275/1536.0)), (int)(fw.getP2().y * (1025/1080.0)), 128/2, 128/2, null);
                        }
                    }
                }
            }
        }
    }
}
