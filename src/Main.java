
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        final JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setSize(200, 100);
        frame.add(label);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                String string = new SimpleDateFormat("HH:mm:ss").format(new Date());
                label.setText(string);
            }
        }, 0, 1000);
    }
}