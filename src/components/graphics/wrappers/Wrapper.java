package components.graphics.wrappers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Wrapper {
    protected BufferedImage texture;

    private static final String pathPrefix = "assets/icons/";

    Wrapper(String s) {
        System.out.println(s);
        try {
            texture = ImageIO.read(new File(pathPrefix + s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Wrapper(){texture=null;}

    public BufferedImage getTexture(){ return texture; }

    public abstract String toString();
}
