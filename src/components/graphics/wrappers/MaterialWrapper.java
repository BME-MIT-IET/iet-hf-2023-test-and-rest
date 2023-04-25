package components.graphics.wrappers;

import components.agent.Material;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MaterialWrapper extends Wrapper {
    private Material material;


    public MaterialWrapper(Material mat){
        super(mat.toString().substring(0, mat.toString().indexOf('(')).toLowerCase() + ".png");
        material=mat;
        BufferedImage newTex = new BufferedImage(texture.getWidth(), texture.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newTex.createGraphics();
        g2.drawImage(texture, 0, 0, null);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 14));
        g2.drawString(material.toString(), 5, 30);
        texture = newTex;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString(){
        return material.toString();
    }
}
