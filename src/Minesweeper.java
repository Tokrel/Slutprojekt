import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Minesweeper {
int tileSize = 70;
int Rows = 8;
int collums;
int boardWidth = collums * tileSize;
int boardHeight = Rows * tileSize;

JFrame frame = new JFrame ("Minesweeper");
JLabel text = new JLabel();
JPanel panel = new JPanel();
JPanel buttonpanel = new JPanel();

GridButton[][] board = new GridButton[Rows][collums];

Minesweeper (int Rows, int collums) {
    this.Rows = Rows;
    this.collums = collums;
    
    frame.setSize(boardWidth , boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    
    text.setFont(new Font("Arial", Font.BOLD,25));
    text.setHorizontalAlignment(JLabel.CENTER);
    text.setText("ooga booga");
    text.setOpaque(true);

    panel.setLayout(new BorderLayout());
    panel.add(text);
    frame.add(panel,BorderLayout.NORTH);

    buttonpanel.setLayout(new GridLayout(Rows,collums));
    frame.add(buttonpanel);



    for (int r = 0; r < Rows; r++) {
        for (int c = 0; c < collums; c++) {
            GridButton tile = new GridButton(r, c);
            board[r][c] = tile;

            tile.setFocusable(false);
            tile.setMargin(new Insets(0, 0, 0, 0));
            tile.setText("1");
            buttonpanel.add(tile);
        }
    }

    frame.setVisible(true);
}
}