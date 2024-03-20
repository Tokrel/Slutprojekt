import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Minesweeper {
int tileSize = 70;
int Rows = 8;
int collums = Rows;
int boardWidth = collums * tileSize;
int boardHeight = Rows * tileSize;
JFrame frame = new JFrame ("Minesweeper");

Minesweeper () {
frame.setVisible(true);
frame.setSize(boardWidth , boardHeight);
frame.setLocationRelativeTo(null);
frame.setResizable(false);
frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());
}
}