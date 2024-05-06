import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class timer implements ActionListener {
    private JLabel timeLabel = new JLabel();
    private Timer timer;
    private int elapsedTime = 0; // Variable to keep track of elapsed time

    public timer() {
        // Initialize timer label
        updateTimeLabel();

        // Create timer
        timer = new Timer(1000, this);
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    private void updateTimeLabel() {
        int hours = (elapsedTime / 3600);
        int minutes = (elapsedTime / 60) % 60;
        int seconds = elapsedTime % 60;

        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime++; // Increment elapsed time
        updateTimeLabel();
    }

}