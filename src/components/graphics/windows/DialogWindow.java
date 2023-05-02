package components.graphics.windows;

import components.graphics.panels.GamePanel;
import components.graphics.panels.InfoAndActionPanel;
import components.graphics.wrappers.Wrapper;
import components.utils.Game;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;

import static java.lang.Thread.currentThread;

public class DialogWindow {
    private JDialog dialogWindow;

    private Wrapper selected;

    public DialogWindow() {
    }

    /**
     * Hibaüzenet megjelenítését megvalósító dialógusablak
     * @param message A hibaüzenet szövege
     * @param title A hibaüzenet címe
     */
    public DialogWindow(String message, String title) {
        JOptionPane.showMessageDialog(Main.mainWindow.getMainFrame(),
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    public DialogWindow(String message, String title, int option) {
        JOptionPane.showMessageDialog(Main.mainWindow.getMainFrame(),
                message,
                title,
                option);
    }

    /**
     * Különböző Wrappereket megjelenítő ablakot létrehozó metódus, mely a kiválaszott Wrappert adja vissza
     * @param wrappers A Wrapperek listája
     * @param cb
     * @return kiválaszott Wrapper
     */
    public Wrapper select(List<Wrapper> wrappers, GamePanel cb) {
        dialogWindow=new JDialog(Main.mainWindow.getMainFrame());
        dialogWindow.setSize(450,450);
        dialogWindow.setTitle("Choose!");
        dialogWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialogWindow.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (Wrapper w : wrappers) {
            JButton butt = new JButton(new ImageIcon(w.getTexture()));
            butt.addActionListener(ae -> {
                selected = w;
                dialogWindow.dispose();
            });
            butt.setPreferredSize(new Dimension(150, 150));
            panel.add(butt);
        }
        dialogWindow.add(panel);
        dialogWindow.setModal(true);
        dialogWindow.setVisible(true);
        return selected;
    }
}
