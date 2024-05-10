import javax.swing.*;

//klass för tilsen på spelplan 
public class Gridsquare extends JButton{
    int row;
    int collum;

    public Gridsquare(int row, int collum){
        this.row = row;
        this.collum = collum;

    }
}
