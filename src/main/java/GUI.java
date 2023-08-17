import javax.swing.*;
import java.awt.*;

public class GUI {

    WeatherInterface weather;
    JFrame jframe = new JFrame("Weather Project");
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Enter City");
    JLabel cityLabel = new JLabel();
    JLabel tempLabel = new JLabel();
    JLabel windLabel = new JLabel();
    JLabel descLabel = new JLabel();
    JTextField tf = new JTextField(20);
    JButton send = new JButton("Send");
    JButton reset = new JButton("Reset");

    public GUI() throws Exception {
        // sets up the popup window
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(480, 150);
        jframe.setResizable(true);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        cityLabel.setFont(new Font("Verdana", Font.PLAIN, 26));
        tempLabel.setFont(new Font("Verdana", Font.PLAIN, 26));
        windLabel.setFont(new Font("Verdana", Font.PLAIN, 26));
        descLabel.setFont(new Font("Verdana", Font.PLAIN, 26));
        panel.add(label);
        panel.add(tf);
        panel.add(send);
        panel.add(reset);
        jframe.add(panel);
        jframe.setVisible(true);

        send.addActionListener(e -> {
            try {
                sendButtonPressed();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        reset.addActionListener(e -> resetButtonPressed());

    }

    private void sendButtonPressed() throws Exception {
        String loc = tf.getText();
        weather = new WeatherInterface(loc);
        updateInterface();
    }

    private void resetButtonPressed() {
        tf.setText("");
        jframe.setSize(480, 150);
    }

    private void updateInterface() {
        cityLabel.setText("City: " + weather.getCity());
        tempLabel.setText("Temperature: " + weather.getTemperature());
        windLabel.setText("Wind Speed: " + weather.getWind());
        descLabel.setText(weather.getDescription());

        panel.add(cityLabel);
        panel.add(tempLabel);
        panel.add(windLabel);
        panel.add(descLabel);

        jframe.setSize(480, 300);

    }

}
