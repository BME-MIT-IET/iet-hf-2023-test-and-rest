package components.graphics.panels;

import components.graphics.wrappers.Wrapper;
import components.scientist.ActnLabel;
import components.scientist.Scientist;
import components.utils.Game;
import components.utils.RoundState;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class InfoAndActionPanel extends JPanel {

    private JLabel round;
    private JLabel playerName;
    private JLabel pic;

    private JButton move;
    private JButton rob;
    private JButton touch;
    private JButton craft;

    public InfoAndActionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        this.round = new JLabel();
        round.setText("<html><h3>ROUND " + Game.round + "</h3></html>");
        c.gridx = 0; c.gridy = 0; c.gridwidth = 7; c.gridheight = 2; c.weightx = 0.5; c.weighty = 0.3;
        add(round,c);

        JButton pass = new JButton("Pass");
        pass.addActionListener(ae -> Game.pass());
        c.gridx = 7; c.gridy = 0; c.gridwidth = 2; c.gridheight = 2; c.weightx = 0.5; c.weighty = 0.3;
        add(pass, c);

        JLabel current = new JLabel();
        current.setText("Current player:");
        c.gridx = 0; c.gridy = 2; c.gridwidth = 4; c.gridheight = 1; c.weightx = 0.6; c.weighty = 0.1;
        add(current,c);

        this.playerName = new JLabel();
        playerName.setText(Game.getCurrentScientist().getName());
        c.gridx = 4; c.gridy = 2; c.gridwidth = 5; c.gridheight = 1; c.weightx = 0.4; c.weighty = 0.1;
        add(playerName,c);

        this.pic = new JLabel(new ImageIcon(Game.getCurrentScientist().getTexture()));
        c.gridx = 7; c.gridy = 2; c.gridwidth = 5; c.gridheight = 1; c.weightx = 0.4; c.weighty = 0.1;
        add(pic,c);

       /* JLabel sep = new JLabel();
        sep.setText(""+'\u0000');
        c.gridx = 7; c.gridy = 0; c.gridwidth = 2; c.gridheight = 2; c.weightx = 0.2; c.weighty = 0.8;
        add(sep,c);*/

        move = new JButton("Move");
        move.addActionListener(ae -> Game.move());
        c.gridx = 0; c.gridy = 3; c.gridwidth = 4; c.gridheight = 3; c.weightx = 0.5; c.weighty = 0.3;
        add(move, c);

        rob = new JButton("Rob");
        rob.addActionListener(ae -> Game.rob());
        c.gridx = 4; c.gridy = 3; c.gridwidth = 4; c.gridheight = 3; c.weightx = 0.5; c.weighty = 0.3;
        add(rob, c);

        touch = new JButton("Touch");
        touch.addActionListener(ae -> Game.touch());
        c.gridx = 0; c.gridy = 6; c.gridwidth = 4; c.gridheight = 3; c.weightx = 0.5; c.weighty = 0.3;
        add(touch, c);

        craft = new JButton("Craft");
        craft.addActionListener(ae -> Game.craft());
        c.gridx = 4; c.gridy = 6; c.gridwidth = 4; c.gridheight = 3; c.weightx = 0.5; c.weighty = 0.3;
        add(craft, c);
    }

    public void update() {
        round.setText(("<html><h3>ROUND " + new Integer(Game.getCurrentRound()).toString() + "</h3></html>"));
        playerName.setText(Game.getCurrentScientist().getName());
        this.pic.setIcon(new ImageIcon(Game.getCurrentScientist().getTexture()));
        RoundState state = Game.getRoundState();
        Scientist curr = Game.getCurrentScientist().getScientist();
        move.setEnabled(!state.getMove() && curr.validateAction(ActnLabel.MOVE) != ActnLabel.NO_ACTN);
        rob.setEnabled(!state.getRob() && curr.validateAction(ActnLabel.STD_ACTN) != ActnLabel.NO_ACTN);
        touch.setEnabled(!state.getTouch() && curr.validateAction(ActnLabel.STD_ACTN) != ActnLabel.NO_ACTN);
        craft.setEnabled(!state.getCraft() && curr.validateAction(ActnLabel.CRAFT) != ActnLabel.NO_ACTN);
        invalidate();
    }
}
