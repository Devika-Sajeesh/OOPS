//The program should display the current mouse event (Clicked, Entered, Exited, Pressed, Released) along with the coordinates (X, Y) on a Label or in the console.

package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class mouseListner extends JFrame {
    private JLabel statusLabel;

    public mouseListner() {
        // Frame setup
        setTitle("Mouse Event Listener");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Status label
        statusLabel = new JLabel("Mouse Event: ", JLabel.CENTER);
        statusLabel.setBounds(50, 50, 300, 30);
        add(statusLabel);

        // Add mouse listener to the frame
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateStatus("Clicked", e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                updateStatus("Entered", e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateStatus("Exited", e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                updateStatus("Pressed", e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateStatus("Released", e);
            }
        });
    }

    private void updateStatus(String eventType, MouseEvent e) {
        String status = String.format("Mouse Event: %s at (%d, %d)", eventType, e.getX(), e.getY());
        statusLabel.setText(status);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new mouseListner().setVisible(true);
        });
    }
}