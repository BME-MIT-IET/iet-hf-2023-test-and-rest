package components.graphics.wrappers;

import components.agent.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AgentWrapper extends Wrapper {
    private Agent agent;

    public AgentWrapper(Agent a){
        super("agent.png");
        agent=a;
        BufferedImage newTex = new BufferedImage(texture.getWidth(), texture.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newTex.createGraphics();
        g2.drawImage(texture, 0, 0, null);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 20));
        g2.drawString(agent.toString(), 5, 20);
        texture = newTex;
    }

    public Agent getAgent() {
        return agent;
    }

    @Override
    public String toString() {
        return agent.toString();
    }
}
