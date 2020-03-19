package TicTacToe;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameBoard extends JFrame {

    final JFrame frame = new JFrame("T I C  T A C  T O I  G A M E ");
    final JPanel[][] panels = new JPanel[3][3];
    final Label[][] labels = new Label[3][3];
    int playCount = 1;

    public GameBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                panels[i][j] = new JPanel();
                panels[i][j].setBackground(Color.red);
                panels[i][j].setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman", Font.PLAIN, 48), Color.yellow));

                labels[i][j] = new Label();
                labels[i][j].setLocation(panels[i][j].getX(), panels[i][j].getY() / 2);
                labels[i][j].setFont(new Font(labels[i][j].getName(), Font.BOLD, 12));

                panels[i][j].add(labels[i][j]);
                panels[i][j].addMouseListener(new MouseAction(i, j));

                frame.getContentPane().add(panels[i][j]);
            }
        }

        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
    }

    private void checkWin(int row, int col, String curr) {
        int c = 0;
        int r = 0;

        for (int i = 0; i < 3; i++) {
            if (labels[i][col].getText().equals("")) {
                break;
            }
            if (labels[i][col].getText().equals(curr)) {
                r++;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (labels[row][i].getText().equals("")) {
                break;
            }
            if (labels[row][i].getText().equals(curr)) {
                c++;
            }
        }

        String c00 = labels[0][0].getText();
        String c02 = labels[0][2].getText();
        String c11 = labels[1][1].getText();
        String c20 = labels[2][0].getText();
        String c22 = labels[2][2].getText();

        if (!c02.equals("") && !c20.equals("") && c02.equals(c20) && c20.equals(c11)) {
            frame.dispose();
            new Winner(curr).setVisible(true);
            return;
        }
        if (!c00.equals("") && !c22.equals("") && c00.equals(c11) && c11.equals(c22)) {
            frame.dispose();
            new Winner(curr).setVisible(true);
            return;
        }

        if (c >= 3 || r >= 3) {
            frame.dispose();
            new Winner(curr).setVisible(true);
            return;
        }
        if (playCount > 9) {
            frame.dispose();
            new NoWinner().setVisible(true);
        }
    }

    class MouseAction implements MouseListener {

        int row, col;

        public MouseAction(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (playCount % 2 == 1 && labels[row][col].getText().equals("")) {
                labels[row][col].setText("X" + "");
                panels[row][col].repaint();
                ++playCount;
            } else if (playCount % 2 == 0 && labels[row][col].getText().equals("")) {
                labels[row][col].setText("O" + "");
                playCount++;
            }
            checkWin(row, col, labels[row][col].getText());
        }
    }
}
