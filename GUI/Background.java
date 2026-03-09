//Create a GUI with three buttons labeled "Red", "Green", and "Blue". When a button is clicked, the background color of the window should change to the corresponding color. Use ‘ActionListener‘ to handle the events.

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Background extends JFrame implements ActionListener {
    private JButton redButton;
    private JButton greenButton;
    private JButton blueButton;

    public Background() {
        // Frame setup
        setTitle("Background Color Changer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create buttons
        redButton = new JButton("Red");
        greenButton = new JButton("Green");
        blueButton = new JButton("Blue");

        // Add action listeners
        redButton.addActionListener(this);
        greenButton.addActionListener(this);
        blueButton.addActionListener(this);

        // Add buttons to frame
        add(redButton);
        add(greenButton);
        add(blueButton);

        // Center on screen
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == redButton) {
            getContentPane().setBackground(Color.RED);
        } else if (e.getSource() == greenButton) {
            getContentPane().setBackground(Color.GREEN);
        } else if (e.getSource() == blueButton) {
            getContentPane().setBackground(Color.BLUE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Background().setVisible(true);
        });
    }
}