import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Minesweeper {
int row ;
int collum ;
int width ;
int height ;
int squareSize = 80;
boolean gameOver = false;

//skapar grundläggande frames labels och panels till programet 
JFrame frame = new JFrame ("Minesweeper");
JLabel title = new JLabel();
JLabel time = new JLabel();
JPanel panel = new JPanel();
JPanel buttonpanel = new JPanel();
//skapar timer class och sätter den till en label 
timer timer = new timer();
JLabel timerLabel = timer.getTimeLabel();

//2d array av klassen gridbuttons vilket utgör spelplannen
Gridsquare[][] board = new Gridsquare[row][collum];
ArrayList<Gridsquare> mines;


Minesweeper(int row) {
    //constructor som tar varibler från main vilket bestämmer svårhetsgrad(antal rader och kollumer)
    this.row = row; 
    this.collum = row;
    this.width = collum * squareSize;
    this.height = row * squareSize;

    //storlek 
    this.board = new Gridsquare[row][collum];


    MinesweeperGame();
}

public void MinesweeperGame () {
    //skapar jframen och bestämmer villkor
    frame.setSize(width , height);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    //bestämmer utsendet av title labeln
    title.setFont(new Font("Arial", Font.BOLD, 25));
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setText("Oogabooga:");
    title.setOpaque(true);

    //bestämmer utsendet av timer labeln
    timerLabel.setFont(new Font("Arial", Font.BOLD, 25));
    timerLabel.setHorizontalAlignment(JLabel.LEFT);
    timerLabel.setOpaque(true);

    //bestämmer plats och layout av panelen samt lägger till den till frame
    panel.setLayout(new FlowLayout()); 
    frame.add(panel, BorderLayout.NORTH);
    panel.add(title);
    panel.add(timerLabel);

    //lägger till en panel med gridlayout som är mängden row*collum 
    buttonpanel.setLayout(new GridLayout(row, collum));
    frame.add(buttonpanel);



    for (int r = 0; r < row; r++) {
        //går igenom alla värden för rows
        for (int c = 0; c < collum; c++) {
        //går igenom alla värden för collum
            //skapar en GridButton för alla värden i griden och lägger in det i 2d arrayen board
            Gridsquare tile = new Gridsquare(r, c);
            board[r][c] = tile;

            tile.setFocusable(false);
            tile.setMargin(new Insets(0, 0, 0, 0));
            tile.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                    if (gameOver) {
                        return;
                        
                    }
                    Gridsquare tile = (Gridsquare) e.getSource();
                    if(e.getButton() == MouseEvent.BUTTON1){
                        if (tile.getText() == ""){
                            if(mines.contains(tile)){
                                revealMines();
                            }
                            else{
                                checkMine(tile.row, tile.collum);
                            }
                        }
                    }
                    else if (e.getButton()== MouseEvent.BUTTON3){
                        if(tile.getText() == "" && tile.isEnabled()){
                            tile.setText("f");
                        }
                        else if (tile.getText() == "f"){
                            tile.setText("");
                        }
                        
                    }
                }
            });
            buttonpanel.add(tile);
        }
    }

    frame.setVisible(true);

    setmines(); 
    }
    void setmines(){
        mines = new ArrayList<Gridsquare>();

        mines.add(board[2][2]);
        mines.add(board[3][2]);
        mines.add(board[4][2]);
        mines.add(board[5][2]);
        mines.add(board[5][1]);
    }
    void revealMines(){
        for (int i = 0; i < mines.size();i++){
            Gridsquare tile = mines.get(i);
            tile.setText("1");
        }
        timer.stopTimer();
        title.setText("du torsk");
        gameOver = true;
    }
    void checkMine(int r,int c){
        if (r<0 || r >= row || c<0 || c>= collum){
            return;
        }
        Gridsquare tile = board[r][c];
        if(!tile.isEnabled()){
            return;
        }
        tile.setEnabled(false);

        int minesFound = 0;

        minesFound += countMine(r+1,c-1);
        minesFound += countMine(r+1,c);
        minesFound += countMine(r+1,c+1);
        minesFound += countMine(r-1,c-1);
        minesFound += countMine(r-1,c);
        minesFound += countMine(r-1,c+1);
        minesFound += countMine(r,c-1);
        minesFound += countMine(r,c+1);

        

        if (minesFound > 0){
            tile.setText(Integer.toString(minesFound));
        }
        else{
            tile.setText("");
            checkMine(r+1,c-1);
            checkMine(r+1,c);
            checkMine(r+1,c+1);
            checkMine(r-1,c-1);
            checkMine(r-1,c);
            checkMine(r-1,c+1);
            checkMine(r,c-1);
            checkMine(r,c+1);

            
        }
    }

    int countMine(int r, int c){
        if (r<0 || r >= row || c<0 || c>= collum){
            return 0;
        }
        if (mines.contains(board[r][c])){
            return 1;
        }
        return 0;
    }
}