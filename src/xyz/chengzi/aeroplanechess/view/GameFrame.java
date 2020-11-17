package xyz.chengzi.aeroplanechess.view;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.listener.GameStateListener;

import javax.swing.*;

public class GameFrame extends JFrame implements GameStateListener {
    private static final String[] PLAYER_NAMES = {"Yellow", "Blue", "Green", "Red"};

    private final JLabel statusLabel;

    public GameFrame(GameController controller) {
        controller.registerListener(this);

        setTitle("2020 CS102A Project Demo");
        setSize(772, 810);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        statusLabel = new JLabel();
        statusLabel.setLocation(0, 758);
        statusLabel.setSize(200, 12);
        add(statusLabel);

        JButton button = new JButton("roll");
        button.addActionListener((e) -> {
            int dice = controller.rollDice();
            if (dice != -1) {
                statusLabel.setText(String.format("[%s] Rolled a %c (%d)",
                        PLAYER_NAMES[controller.getCurrentPlayer()], '\u267F' + dice, dice));
            } else {
                JOptionPane.showMessageDialog(this, "You have already rolled the dice");
            }
        });
        button.setLocation(710, 755);
        button.setSize(60, 20);
        add(button);
    }

    @Override
    public void onPlayerStartRound(int player) {
        statusLabel.setText(String.format("[%s] Please roll the dice", PLAYER_NAMES[player]));
    }

    @Override
    public void onPlayerEndRound(int player) {

    }
}
