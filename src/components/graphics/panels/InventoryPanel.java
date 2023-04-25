package components.graphics.panels;

import components.graphics.wrappers.AgentWrapper;
import components.graphics.wrappers.Wrapper;
import components.utils.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryPanel extends JTabbedPane {
    JPanel material;
    JPanel gear;
    JPanel crafted;
    JPanel active;

    /**
     * Az egyes inventory panelek inicializálása
     */
    public InventoryPanel(Dimension d) {
        material = new JPanel();
        gear = new JPanel();
        crafted = new JPanel();
        active = new JPanel();
        material.setSize(d);
        gear.setSize(d);
        crafted.setSize(d);
        active.setSize(d);
        //setBounds(50,50,200,200);
        material.setLayout(new FlowLayout());
        gear.setLayout(new FlowLayout());
        crafted.setLayout(new FlowLayout());
        active.setLayout(new FlowLayout());
        add("Material", material);
        add("Gear", gear);
        add("Crafted", crafted);
        add("Active", active);
        update();
    }

    /**
     * A panel frissítése
     */
    public void update() {
        fillPanel(material, Game.getCurrentScientistMaterials(),false);
        fillPanel(gear, Game.getCurrentScientistGears(),false);
        fillPanel(crafted, Game.getCurrentScientistCrafted(),false);
        fillPanel(active, Game.getCurrentScientistActives(),true);
        invalidate();
    }

    /**
     * Megjeleníti a soron lévő Scientist-től az egyes inventory-ban tárolt objektumjainak Wrapperjét
     * @param panel Melyik panelen jelenjenek meg, vagyis milyen típus rajtzolódjon ki
     * @param wrappers Melyik wrapperek jelenjenek meg
     * @param b ha igaz, akkor nem kattinthatók az ágensek
     */
    private void fillPanel(JPanel panel, List<Wrapper> wrappers, boolean b) {
        panel.removeAll();
        for (Wrapper w : wrappers) {
            JButton butt = new JButton(new ImageIcon(w.getTexture()));
            if(w.toString().contains("Axe")) {
                butt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] args = {""};
                        Game.kill(args);
                    }
                });
            } else if((w.toString().contains("Dementia") || w.toString().contains("Craziness") || w.toString().contains("Immunity") || w.toString().contains("Stun")) && !b){
                butt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Game.use(((AgentWrapper)w).getAgent());
                    }
                });
            }
            butt.setPreferredSize(new Dimension(130, 130));
            panel.add(butt);
        }
    }
}
