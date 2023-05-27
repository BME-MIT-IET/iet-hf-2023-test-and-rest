package components.graphics.panels;

import components.graphics.windows.DialogWindow;
import components.graphics.wrappers.Wrapper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {

    private JLabel round;
    private JLabel playerName;

    private MapPanel mapPanel;
    private InfoAndActionPanel infoAndActionPanel;
    private InventoryPanel inventoryPanel;

    /**
     * A fő játékpanel inicializálása és létrehozása
     * @param width szélesség
     * @param height magasság
     */
    public GamePanel(int width, int height) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;


        mapPanel = new MapPanel();
        mapPanel.setSize(new Dimension(width/9*7, height));
        c.gridx = 0; c.gridy = 0; c.gridwidth = 7; c.gridheight = 8; c.weightx = 0.7; c.weighty = 0.8;
        add(mapPanel, c);

        infoAndActionPanel = new InfoAndActionPanel();
        infoAndActionPanel.setSize(new Dimension(width/9*2, height/2));
        c.gridx = 7; c.gridy = 0; c.gridwidth = 2; c.gridheight = 4; c.weightx = 0.2; c.weighty = 0.8;
        add(infoAndActionPanel, c);

        inventoryPanel = new InventoryPanel(new Dimension(width/9*2, height/2));
        inventoryPanel.setSize(new Dimension(width/9*2, height/2));
        c.gridx = 7; c.gridy = 4; c.gridwidth = 2; c.gridheight = 4; c.weightx = 0.2; c.weighty = 0.8;
        add(inventoryPanel, c);
    }

    /**
     * Frissíti a gamepanel tartalmát, vagyis a tárolt paneleket
     */
    public void update() {
        infoAndActionPanel.update();
        inventoryPanel.update();
        repaint();
    }

    /**
     * Megjelenít egy dialógusablakot, melyen belül különböző Wrapperek közül lehet választani
     * @param wrappers A Wrapperek listája, melyek közül lehet választani
     * @return A kiválasztott wrapper
     */
    public Wrapper select(List<Wrapper> wrappers) {
        DialogWindow dw = new DialogWindow();
        return dw.select(wrappers, this);
    }
}
