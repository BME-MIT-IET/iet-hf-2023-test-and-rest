package components.graphics.wrappers;

import components.gear.Gear;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GearWrapper extends Wrapper {
    private Gear gear;

    public GearWrapper(Gear g){
        super( g.toString().indexOf('(') > 0 ?
                g.toString().substring(0, g.toString().indexOf('(')).toLowerCase() + ".png" :
                g.toString().toLowerCase() + ".png");
        gear=g;
        BufferedImage newTex = new BufferedImage(texture.getWidth(), texture.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newTex.createGraphics();
        g2.drawImage(texture, 0, 0, null);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 20));
        g2.drawString(gear.toString(), 5, 20);
        texture = newTex;
    }

    private String parseName(Gear g) {
        int idx = g.toString().indexOf('(');
        if (idx > 0)
            return g.toString().substring(0, g.toString().indexOf('(')).toLowerCase();
        return g.toString().toLowerCase();
    }

    public Gear getGear() {
        return gear;
    }

    @Override
    public String toString() {
        return gear.toString();
    }
}
