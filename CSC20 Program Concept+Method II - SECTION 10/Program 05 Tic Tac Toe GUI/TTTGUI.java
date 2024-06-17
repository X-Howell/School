/**
Xavier Howell
TTTGUI
MOD5
**/

import javax.swing.*;
import java.awt.*;

public class TTTGUI {

    JButton[][] jButtons = new JButton[4][4];
    JLabel status;
    TTTBoard tttBoard;

    public void play() {
        tttBoard = new TTTBoard(4);

        JFrame jFrame = new JFrame("Tic Tac Toe TTTGUI");
        jFrame.setSize(400, 500);
        jFrame.setLayout(null);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4, 4));
        jFrame.add(jPanel);
        jPanel.setBounds(0, 0, 400, 400);

        status = new JLabel("Your Turn");

        ButtonActionListener buttonActionListener = new ButtonActionListener(tttBoard, status, jButtons);
        for (int i = 0; i < 16; i++) {
            JButton jButton = new JButton();
            jButton.setSize(100, 100);
            jButton.setText(" ");
            jButton.setActionCommand("" + i);
            jButton.addActionListener(buttonActionListener);
            jButtons[i / 4][i % 4] = jButton;
            jPanel.add(jButton);
        }

        status.setSize(400, 50);
        status.setHorizontalAlignment(SwingConstants.CENTER);
        jFrame.add(status);
        status.setBounds(0, 400, 400, 50);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TTTGUI().play();
    }
}
