import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Minesweeper {
//skapar variabler
int row ;
int collum ;
int width ;
int height ;
int squareSize = 80;
boolean gameOver = false;
Random random = new Random();
int mineAmount ;
int clickedSqueres=0;
ArrayList<Gridsquare> mines;

//skapar grundläggande frames,labels och panels till programet 
JFrame frame = new JFrame ("Minesweeper");
JLabel title = new JLabel();
JLabel time = new JLabel();
JPanel panel = new JPanel();
JPanel leaderboardpanel = new JPanel();
JLabel Leadtitle = new JLabel();
timer timer = new timer();
JLabel timerLabel = timer.getTimeLabel();
JPanel buttonpanel = new JPanel();
JPanel winpanel = new JPanel();
JFrame leaderboardFrame = new JFrame("Leaderboard");
JPanel leaderboardPanel = new JPanel();
JLabel LeaderboardtitleLabel = new JLabel("Leaderboard");




//2d array av klassen gridbuttons vilket utgör spelplannen
Gridsquare[][] board = new Gridsquare[row][collum];



Minesweeper(int row , int mineAmount) {
    //constructor som tar varibler från main vilket bestämmer svårhetsgrad(antal rader och kollumer)
    this.row = row; 
    this.collum = row;
    this.width = collum * squareSize;
    this.height = row * squareSize;
    this.mineAmount = mineAmount;

    //storlek 
    this.board = new Gridsquare[row][collum];

    //Startar spelet
    MinesweeperGame();
}

public void MinesweeperGame () {
    //sätter properties för framen
    frame.setSize(width , height);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    //sätter properties för title labeln
    title.setFont(new Font("Arial", Font.BOLD, 25));
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setText("Minesweeper:");
    title.setOpaque(true);
 
   

    //sätter properties för timer labeln
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


            //sätter properties för tile och lägger till en MouseListener
            tile.setFocusable(false);
            tile.setMargin(new Insets(0, 0, 0, 0));
            tile.setFont(new Font("Arial Unicode MS",Font.PLAIN,40));
            tile.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                    if (gameOver) {
                        //kollar ifall spelet är över 
                        return;
                        
                    }
                    Gridsquare tile = (Gridsquare) e.getSource();
                    //logik för ifall man klickar vänsterklick på en tile
                    if(e.getButton() == MouseEvent.BUTTON1){
                        //kollar ifall tilen har blivit klickad 
                        if (tile.getText() == ""){
                            if(mines.contains(tile)){
                                //kallar revealMines metoden ifall man klickar på en mina 
                                revealMines();
                            }
                            else{
                                //kallar checkMine metoden ifall tilen inte har en mina i sig
                                checkMine(tile.row, tile.collum);
                            }
                        }
                    }
                    //logik för ifall man klickar högerklick på en tile
                    else if (e.getButton()== MouseEvent.BUTTON3){
                        if(tile.getText() == "" && tile.isEnabled()){
                            //kollar ifall tilen är ledig och i så fall sätter dit en flagga
                            tile.setText("🚩");
                        }
                        else if (tile.getText() == "🚩"){
                            //kollar ifall tilen har en flagga i sig och i så fall tar bort den 
                            tile.setText("");
                        }
                        
                    }
                }
            });
            //Lägger till tilen i panelen som visas 
            buttonpanel.add(tile);
        }
    }
    // Gör allt synligt 
    frame.setVisible(true);
    //lägger till minor 
    setmines(); 
    }
    void setmines(){
        //skapar en ArrayList som innehåller Gridsquares med kordinaterna med minorna 
        mines = new ArrayList<Gridsquare>();
        //variabel med mängden minor som ska skaapas 
        int mineLeft = mineAmount ;
        //loop som för alla värden i minemaount sätter en random gridsquare som en mina 
        while (mineLeft > 0) {
            int r = random.nextInt(row); 
            int c = random.nextInt(collum);

            Gridsquare tile = board[r][c]; 
            if (!mines.contains(tile)) {
                mines.add(tile);
                mineLeft -= 1;
            }
        }
    }

    void revealMines(){
        //loop som går igenom arraylisten mines och sätter dom tilesen som bomb
        for (int i = 0; i < mines.size();i++){
            Gridsquare tile = mines.get(i);
            tile.setText("💣");
        }
        //logik för att förlora 
        timer.stopTimer();
        title.setText("Du förlorade");
        gameOver = true;
        loseSeq();
    }
    void checkMine(int r,int c){
        if (r<0 || r >= row || c<0 || c>= collum){
            return;
            // om den valda tilen ligger utanför borden avslutas metoden 
        }

        //skapar tile för klickade squaren 
        Gridsquare tile = board[r][c];
        if(!tile.isEnabled()){
            return;
        }
        //stänger av den klickade tilen 
        tile.setEnabled(false);

        clickedSqueres+=1;

        //kod som kollar hur många minor är kring den klickade tilen 
        int minesFound = 0;
        minesFound += countMine(r+1,c-1);
        minesFound += countMine(r+1,c);
        minesFound += countMine(r+1,c+1);
        minesFound += countMine(r-1,c-1);
        minesFound += countMine(r-1,c);
        minesFound += countMine(r-1,c+1);
        minesFound += countMine(r,c-1);
        minesFound += countMine(r,c+1);

        
        //kod osm kollar ifall det finns några minor kring den klickade tilen 
        if (minesFound > 0){
            //sätter den klickade tilens text till mängden minor kring 
            tile.setText(Integer.toString(minesFound));
        }
        else{
            //Rekursiv kod som kollar tilsen brevid för minor ifall den klickade tilen inte hade några mines i sig och iså fall revelar dom 
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

        if(clickedSqueres == row*collum-mineAmount){
            //logik för att vinna 
            gameOver = true;
            timer.stopTimer();
            title.setText("Du vann");
            int elapesedPLayerTime = timer.getElapsedTime();
            winSeq(elapesedPLayerTime);
        }
    }

    int countMine(int r, int c){
        // kod som kollar den valda tilen för mines 
        if (r<0 || r >= row || c<0 || c>= collum){
            //ifall tilen ligger utanför borden ger retrurnar den 0 
            return 0;
        }
        if (mines.contains(board[r][c])){
            //om den valda tilen har en mina i sig returnar den 0 
            return 1;
        }
        // om den inte har en i sig returnar den 0
        return 0;
    }

    void loseSeq(){
        //  skapar panels och buttons till metoden 
        JPanel registerPanel = new JPanel();
        JButton leaderboardButton = new JButton("Leaderboard");
        JButton restarButton = new JButton("Restart");

        //kod som bestämmer vad som sker när knapparna blir klickade 
        leaderboardButton.addActionListener(e -> {
            showLeaderboard();
        });

        restarButton.addActionListener(e -> {
            frame.remove(registerPanel);
            resetGame();
        });
    
        //lägger till allt
        registerPanel.add(leaderboardButton);
        registerPanel.add(restarButton);
    
        frame.getContentPane().add(registerPanel, BorderLayout.SOUTH);
        frame.revalidate();

    }

    void winSeq(int elapesedPLayerTime){
        //  skapar panels,labels,textfields och buttons till metoden 
        JPanel registerPanel = new JPanel();
        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField nameField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton leaderboardButton = new JButton("Lead board");
        JButton restarButton = new JButton("restart");

        //kod som bestämmer vad som sker när knapparna blir klickade 
        submitButton.addActionListener(e -> {
            //Tar namnet från fieldet och tiden till vinst och registrer det 
            String playerName = nameField.getText();
            RegisterPlayer register = new RegisterPlayer(playerName,elapesedPLayerTime);
            register.execute();
        });

        leaderboardButton.addActionListener(e -> {
            //visar leaderboarden 
            showLeaderboard();
        });

        restarButton.addActionListener(e -> {
            //startar om spelet 
            frame.remove(registerPanel);
            resetGame();
        });
    

        //lägger till allt 
        registerPanel.add(nameLabel);
        registerPanel.add(nameField);
        registerPanel.add(submitButton);
        registerPanel.add(leaderboardButton);
        registerPanel.add(restarButton);
    
        frame.getContentPane().add(registerPanel, BorderLayout.SOUTH);
        frame.revalidate();
    }


    //metod för att starta om spelet 
    void resetGame() {
        frame.dispose();
        Minesweeper minesweeper = new Minesweeper(row,mineAmount);
    }

    //metod för att öppna leaderboarden 
    void showLeaderboard() {
        //sätter utsende på panels,labels och frames 
        leaderboardFrame.setSize(width, height);
        leaderboardFrame.setLocationRelativeTo(null);
        leaderboardFrame.setResizable(false);
        leaderboardFrame.setLayout(new BorderLayout());


        
        LeaderboardtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        LeaderboardtitleLabel.setHorizontalAlignment(JLabel.CENTER);
    

        
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
    

        //skapar database objekt 
        PlayerDatabase dataBase = new PlayerDatabase();
        //går igenom den sorterade listan av spelare och lägger till dom till leaderboarden 
        for(Player registerdUser : dataBase.sortByElapsedPlayerTime()) {
            String name = registerdUser.getPlayerName();
            int time = registerdUser.getElapesedPLayerTime();
            addLeaderboardEntry(leaderboardPanel, name, time);
        }

        
        //lägger till allt 
        leaderboardFrame.add(leaderboardPanel, BorderLayout.CENTER);
        leaderboardFrame.add(LeaderboardtitleLabel, BorderLayout.NORTH);
    
        leaderboardFrame.setVisible(true);
        
    }

    //metod för att lägga till spelare till leaderboard panelen 
    void addLeaderboardEntry(JPanel leaderboardPanel, String name, int time) {
        JLabel playerLabel = new JLabel(name + ": " + time + " seconds");
        leaderboardPanel.add(playerLabel);
    }


}