import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] operatorButtons = new JButton[4];
    JButton equalButton, clrButton;
    JPanel panel;

    List<Double> operands = new ArrayList<>();
    List<Character> operators = new ArrayList<>();

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(new Font("Arial", Font.PLAIN, 24));
        textfield.setEditable(false);

        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");

        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
        }

        equalButton = new JButton("=");
        clrButton = new JButton("Clr");

        equalButton.addActionListener(this);
        clrButton.addActionListener(this);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        for (int i = 1; i <= 9; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(operatorButtons[0]);
        panel.add(numberButtons[0]);
        panel.add(operatorButtons[1]);
        panel.add(operatorButtons[2]);
        panel.add(operatorButtons[3]);
        panel.add(clrButton);
        panel.add(equalButton);

        frame.add(panel);
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
            operands.clear();
            operators.clear();
        }
        if (e.getSource() == operatorButtons[0]) {
            operands.add(Double.parseDouble(textfield.getText()));
            operators.add('+');
            textfield.setText("");
        }
        if (e.getSource() == operatorButtons[1]) {
            operands.add(Double.parseDouble(textfield.getText()));
            operators.add('-');
            textfield.setText("");
        }
        if (e.getSource() == operatorButtons[2]) {
            operands.add(Double.parseDouble(textfield.getText()));
            operators.add('*');
            textfield.setText("");
        }
        if (e.getSource() == operatorButtons[3]) {
            operands.add(Double.parseDouble(textfield.getText()));
            operators.add('/');
            textfield.setText("");
        }
        if (e.getSource() == equalButton) {
            if (!textfield.getText().isEmpty()) {
                operands.add(Double.parseDouble(textfield.getText()));
            }

            double result = operands.get(0);
            for (int i = 0; i < operators.size(); i++) {
                char operator = operators.get(i);
                double nextOperand = operands.get(i + 1);

                switch (operator) {
                    case '+':
                        result += nextOperand;
                        break;
                    case '-':
                        result -= nextOperand;
                        break;
                    case '*':
                        result *= nextOperand;
                        break;
                    case '/':
                        if (nextOperand != 0) {
                            result /= nextOperand;
                        } else {
                            JOptionPane.showMessageDialog(frame, "Division by zero is not allowed!");
                            textfield.setText("");
                            operands.clear();
                            operators.clear();
                            return;
                        }
                        break;
                }
            }

            textfield.setText(String.valueOf(result));
            operands.clear();
            operators.clear();
        }
    }
}
