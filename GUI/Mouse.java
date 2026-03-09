//Implement the ‘MouseMotionListener‘ interface to create a simple freehand drawing tool. When the user drags the mouse, draw lines following the mouse path on the window.

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

// simple freehand drawing tool using MouseMotionListener
public class Mouse extends JFrame {
    private JLabel statusLabel;
    private DrawingPanel drawPanel;

    public Mouse() {
        // Frame setup
        setTitle("Mouse Motion Listener");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Drawing panel handles mouse motion and painting
        drawPanel = new DrawingPanel();
        add(drawPanel, BorderLayout.CENTER);

        // Status label
        statusLabel = new JLabel("Mouse Event: ", JLabel.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    // Update status label with mouse event and coordinates
    private void updateStatus(String eventType, MouseEvent e) {
        String status = String.format("Mouse Event: %s at (%d, %d)", eventType, e.getX(), e.getY());
        statusLabel.setText(status);
    }

    private class DrawingPanel extends JPanel implements MouseMotionListener {
        private final List<Point> points = new ArrayList<>();
        private Point prevPoint = null;

        public DrawingPanel() {
            setBackground(Color.WHITE);
            addMouseMotionListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            for (int i = 1; i < points.size(); i++) {
                Point p1 = points.get(i - 1);
                Point p2 = points.get(i);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point current = e.getPoint();
            points.add(current);
            prevPoint = current;
            updateStatus("Dragged", e);
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            updateStatus("Moved", e);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Mouse().setVisible(true);
        });
    }
}