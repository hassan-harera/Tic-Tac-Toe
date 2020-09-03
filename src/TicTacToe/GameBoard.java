package TicTacToe;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameBoard extends JFrame {

    JPanel[][] panels = new JPanel[3][3];
    JLabel[][] labels = new JLabel[3][3];
    int playCount = 1;

    public GameBoard() {
        setTitle("T I C  T A C  T O I  G A M E ");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                panels[i][j] = new JPanel();
                panels[i][j].setBackground(Color.RED);
                panels[i][j].setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(TitledBorder.BOTTOM)));
                panels[i][j].addMouseListener(new MouseAction(i, j));

                labels[i][j] = new JLabel("", JLabel.CENTER);
                labels[i][j].setFont(new Font("BOLD", Font.BOLD, 16));

                panels[i][j].add(labels[i][j]);
                add(panels[i][j]);
            }
        }

        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setVisible(true);
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
            dispose();
            new Winner(curr).setVisible(true);
            return;
        }
        if (!c00.equals("") && !c22.equals("") && c00.equals(c11) && c11.equals(c22)) {
            dispose();
            new Winner(curr).setVisible(true);
            return;
        }

        if (c >= 3 || r >= 3) {
            dispose();
            new Winner(curr).setVisible(true);
            return;
        }
        if (playCount > 9) {
            dispose();
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
                labels[row][col].setText("X");
//                panels[row][col].repaint();
                ++playCount;
            } else if (playCount % 2 == 0 && labels[row][col].getText().equals("")) {
                labels[row][col].setText("O");
                playCount++;
            }
            checkWin(row, col, labels[row][col].getText());
        }
    }
}
