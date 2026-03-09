package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Traffic extends JFrame implements ActionListener {
    private JRadioButton redButton;
    private JRadioButton yellowButton;
    private JRadioButton greenButton;
    private ButtonGroup buttonGroup;
    private JLabel statusLabel;
    private JPanel displayPanel;

    public Traffic() {
        // Frame setup
        setTitle("Traffic Light Simulator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for radio buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Select Light"));

        // UI Components
        redButton = new JRadioButton("Red");
        yellowButton = new JRadioButton("Yellow");
        greenButton = new JRadioButton("Green");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(redButton);
        buttonGroup.add(yellowButton);
        buttonGroup.add(greenButton);

        // Add action listeners
        redButton.addActionListener(this);
        yellowButton.addActionListener(this);
        greenButton.addActionListener(this);

        // Add buttons to panel
        buttonPanel.add(redButton);
        buttonPanel.add(yellowButton);
        buttonPanel.add(greenButton);

        // Display panel for color visualization
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.GRAY);

        // Status label
        statusLabel = new JLabel("Select a light", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(statusLabel, BorderLayout.CENTER);

        // Add components to frame
        add(buttonPanel, BorderLayout.WEST);
        add(displayPanel, BorderLayout.CENTER);

        // Center on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (redButton.isSelected()) {
            displayPanel.setBackground(Color.RED);
            statusLabel.setText("STOP");
            statusLabel.setForeground(Color.WHITE);
        } else if (yellowButton.isSelected()) {
            displayPanel.setBackground(Color.YELLOW);
            statusLabel.setText("READY");
            statusLabel.setForeground(Color.BLACK);
        } else if (greenButton.isSelected()) {
            displayPanel.setBackground(Color.GREEN);
            statusLabel.setText("GO");
            statusLabel.setForeground(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Traffic());
    }

}