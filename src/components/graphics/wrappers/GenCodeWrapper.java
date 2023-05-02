package components.graphics.wrappers;

import components.agent.GeneticCode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GenCodeWrapper extends Wrapper {
    private GeneticCode geneticCode;

    public GenCodeWrapper(GeneticCode gc){
        super("gc.png");
        geneticCode=gc;
        BufferedImage newTex = new BufferedImage(texture.getWidth(), texture.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newTex.createGraphics();
        g2.drawImage(texture, 0, 0, null);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 20));
        g2.drawString(geneticCode.toString(), 5, 20);
        texture = newTex;
    }

    public GeneticCode getGeneticCode() {
        return geneticCode;
    }

    @Override
    public String toString() {
        return geneticCode.toString();
    }
}
