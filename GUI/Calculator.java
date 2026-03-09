package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstOperand = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setPreferredSize(new Dimension(400, 60));
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusable(false);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        
        remove(panel);
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]") || command.equals(".")) {
            if (isOperatorPressed) {
                display.setText(command);
                isOperatorPressed = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("C")) {
            display.setText("");
            firstOperand = 0;
            operator = "";
        } else if (command.equals("=")) {
            calculate();
            operator = "";
        } else if (command.equals("%")) {
            if (!display.getText().isEmpty()) {
                double val = Double.parseDouble(display.getText());
                display.setText(String.valueOf(val / 100));
            }
        } else {
            if (!display.getText().isEmpty()) {
                firstOperand = Double.parseDouble(display.getText());
                operator = command;
                isOperatorPressed = true;
            }
        }
    }

    private void calculate() {
        if (operator.isEmpty() || display.getText().isEmpty()) return;

        double secondOperand = Double.parseDouble(display.getText());
        double result = 0;

        try {
            switch (operator) {
                case "+": result = firstOperand + secondOperand; break;
                case "-": result = firstOperand - secondOperand; break;
                case "*": result = firstOperand * secondOperand; break;
                case "/":
                    if (secondOperand == 0) throw new ArithmeticException("Divide by zero");
                    result = firstOperand / secondOperand;
                    break;
            }
            // Format result to remove .0 if it's an integer
            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
            } else {
                display.setText(String.valueOf(result));
            }
        } catch (ArithmeticException ex) {
            display.setText("Error: " + ex.getMessage());
            operator = "";
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
