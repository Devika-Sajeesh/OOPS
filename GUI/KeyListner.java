package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListner extends JFrame implements KeyListener {
    private JLabel statusLabel;

    public KeyListner() {
        setTitle("Key Listener Example");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Press any key", JLabel.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // Text area to capture keyboard input, must be focusable
        JTextArea textArea = new JTextArea();
        textArea.setFocusable(true);
        textArea.addKeyListener(this);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // show the typed character
        statusLabel.setText("Key Typed: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        statusLabel.setText("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        statusLabel.setText("Key Released: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeyListner frame = new KeyListner();
            frame.setVisible(true);
        });
    }
}
