import javax.swing.*;
import java.awt.event.*;

public class timer implements ActionListener {
    private JLabel timeLabel = new JLabel();
    private Timer timer;
    private int elapsedTime = 0; // mängden tid som gått 

    public timer() {
    
        updateTimeLabel();

        // Skapar en timer med en delay på 1000 milisekunder 
        timer = new Timer(1000, this);
        timer.start();
    }

    //metoder som används i minesweeperprogrammet 
    public void stopTimer() {
        timer.stop();
    }

    public int getElapsedTime(){
        return elapsedTime;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }
    public void resetTimer(){
        elapsedTime = 0;
    }
    //metod som upptaterar labeln 
    private void updateTimeLabel() {
        int hours = (elapsedTime / 3600);   
        int minutes = (elapsedTime / 60) % 60;
        int seconds = elapsedTime % 60;
        // Formaterar tiden som timmar:minuter:sekunder och uppdaterar etiketten
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime++; 
        updateTimeLabel();
    }

}