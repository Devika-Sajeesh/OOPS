package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class FactorialCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTextField resultField;
    private JButton calculateButton;

    public FactorialCalculator() {
        // Frame setup
        setTitle("Factorial Calculator");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        // UI Components
        JLabel inputLabel = new JLabel("  Enter a number:");
        inputField = new JTextField();
        
        JLabel resultLabel = new JLabel("  Factorial:");
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setBackground(Color.WHITE);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        // Add to frame
        add(inputLabel);
        add(inputField);
        add(resultLabel);
        add(resultField);
        add(new JLabel("")); // Spacer
        add(calculateButton);

        // Center on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                String inputStr = inputField.getText().trim();
                if (inputStr.isEmpty()) {
                    resultField.setText("Empty input");
                    return;
                }

                int n = Integer.parseInt(inputStr);
                
                if (n < 0) {
                    resultField.setText("Error: Negative");
                } else {
                    BigInteger fact = calculateFactorial(n);
                    resultField.setText(fact.toString());
                }
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid input");
            } catch (Exception ex) {
                resultField.setText("Error");
            }
        }
    }

    private BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FactorialCalculator());
    }
}
