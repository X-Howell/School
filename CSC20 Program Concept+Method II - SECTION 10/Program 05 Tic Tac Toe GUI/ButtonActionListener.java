/**
Xavier Howell
ButtonActionListener
MOD5
**/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {

    private TTTBoard theBoard;
    private char user = 'X';
    private char computer = 'O';
    private JLabel message;
    private JButton[][] jButtons;
    private boolean cheatMode = true;

    public ButtonActionListener(TTTBoard theBoard, JLabel message, JButton[][] jButtons) {
        this.theBoard = theBoard;
        this.message = message;
        this.jButtons = jButtons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (theBoard.winner() != ' ') {
            return;
        }

        int index = Integer.parseInt(e.getActionCommand());
        if (theBoard.get(index / 4, index % 4) != ' ' && !cheatMode) {
            return;
        }

        JButton source = (JButton) e.getSource();
        source.setEnabled(cheatMode || false);
        source.setText("" + user);
        theBoard.set(index / 4, index % 4, user);

        if (theBoard.winner() == 'X') {
            message.setText("You Won!");
        } else if (theBoard.isFull()) {
            message.setText("It’s a draw!");
        } else {
            message.setText("Computer's Turn");

            TTTAI.move(theBoard, computer);

            if (theBoard.winner() == 'O') {
                message.setText("Sorry, the computer won!");
            } else if (theBoard.isFull()) {
                message.setText("It’s a draw!");
            } else {
                message.setText("Your Turn");
            }
        }

        for (int i = 0; i < theBoard.size; i++) {
            for (int j = 0; j < theBoard.size; j++) {
                jButtons[i][j].setText("" + theBoard.get(i, j));
                jButtons[i][j].setEnabled(cheatMode || theBoard.get(i, j) == ' ');
            }
        }
    }
}
