package components.graphics.windows;

import components.graphics.panels.GamePanel;
import components.utils.Game;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow {
    private final JFrame mainFrame;
    private ArrayList<String> playerNames;

    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Főablak létrehozása
     */
    public MainWindow(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        mainFrame=new JFrame();
        mainFrame.setSize(500,384);
        mainFrame.setTitle("BRAINDEAD");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int x = (screenSize.width - mainFrame.getWidth()) / 2;
        int y = (screenSize.height - mainFrame.getHeight()) / 2;

        mainFrame.setLocation(x, y);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    /**
     * Menü inicializálása
     */
    public void initializeMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4,0));

        JLabel jt = new JLabel("Braindead Scientists");
        jt.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(jt);

        JButton start = new JButton("Start Game");
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.addActionListener(e -> initalizeSetup());
        menuPanel.add(start);

        JButton exit = new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(e -> System.exit(0));
        menuPanel.add(exit);
        mainFrame.add(menuPanel);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * A játékosok neveit ezen a panelen lehet megadni
     */
    public void initalizeSetup() {
        playerNames = new ArrayList<>();
        mainFrame.getContentPane().removeAll();
        JPanel setup = new JPanel();
        setup.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel jl = new JLabel();
        jl.setText("<html><h1>Setup Players</h1></html>");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        setup.add(jl,c);

        JLabel players = new JLabel();
        players.setText("<html><h3>Players</h3></html>");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        setup.add(players,c);

        char ch1 = '\u0000';
        for(int i = 2;i<8;i++) {
            players = new JLabel(""+ch1);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = i;
            setup.add(players,c);
        }

        JTextField playerName = new JTextField("[name]",20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        setup.add(playerName, c);

        JButton addPlayer = new JButton("Add");
        addPlayer.addActionListener(e -> {
            if(!(playerName.getText().equals("[name]") ||
                    playerName.getText().equals("") ||
                    playerNames.contains(playerName.getText())) &&
                    playerNames.size() < 6 &&
                    playerName.getText().length() < 15) {
                playerNames.add(playerName.getText());
                JLabel player = new JLabel(playerName.getText());
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 1 + playerNames.size();
                setup.add(player,c);
                mainFrame.validate();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        setup.add(addPlayer, c);

        JButton start = new JButton("Start Game");
        start.addActionListener(e -> {
            //game = new Game();
            //game.setup((String[])playerNames.toArray());
            if(playerNames.size() > 1) {
                try {
                    Game.setup(playerNames);
                } catch (IOException | ParseException ex) {
                    throw new RuntimeException(ex);
                }
                initializeGame();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 1;
        c.gridwidth = 3;
        c.gridy = 8;
        setup.add(start,c);
        mainFrame.add(setup);
        mainFrame.validate();
    }

    /**
     * A játék létrehozása
     */
    public void initializeGame() {
        mainFrame.getContentPane().removeAll();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        mainFrame.setSize(screenSize);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        GamePanel gamePanel = new GamePanel(mainFrame.getWidth(), mainFrame.getHeight());
        Game.setMainWindow(gamePanel);
        mainFrame.add(gamePanel);
        gamePanel.update();
    }
}
