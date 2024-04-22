import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Main extends Thread {
    private static JFrame jf;
    private static JLabel ct;
    private static JLabel date;
    private Calendar cal;

    Main() {
        start();
    }

    public void run() {
        while (true) {
            // Time
            cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            int am_pm = cal.get(Calendar.AM_PM);
            // Dates
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            String s = (am_pm == Calendar.AM) ? "AM" : "PM";
            SwingUtilities.invokeLater(() -> {
                ct.setText("Current Time: " + hour + ":" + min + ":" + sec + " " + s);
                date.setText(Days[dayofweek - 1] + ", " + day + " " + Months[month] + " " + year);
            });

            try {
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String[] Months = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    private static final String[] Days = {
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };

    public static void main(String[] args) {
        jf = new JFrame("Digital Clock");
        jf.setBounds(600, 100, 500, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        p.setLayout(null);

        ct = new JLabel("Current time");
        ct.setBounds(10, 10, 480, 60);
        ct.setFont(new Font("Helvetica", Font.BOLD, 35));
        ct.setForeground(Color.RED);
        p.add(ct);

        date = new JLabel("");
        date.setBounds(10, 70, 480, 60);
        date.setFont(new Font("Helvetica", Font.BOLD, 30));
        date.setForeground(Color.DARK_GRAY);
        p.add(date);

        jf.getContentPane().add(p);
        jf.setVisible(true);
        new Main();
    }
}
