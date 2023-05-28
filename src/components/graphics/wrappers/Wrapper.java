package components.graphics.wrappers;

import components.graphics.panels.MapPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;

public abstract class Wrapper {
    protected BufferedImage texture;
    private static final Logger LOGGER = Logger.getLogger(MapPanel.class.getName());
    private static final String pathPrefix = "assets/icons/";

    Wrapper(String s) {
        System.out.println(s);
        try {
            texture = ImageIO.read(new File(pathPrefix + s));
        } catch (Exception e) {
            LOGGER.severe("An error occurred read the File\n");
        }
    }

    Wrapper(){texture=null;}

    public BufferedImage getTexture(){ return texture; }

    public abstract String toString();
}
