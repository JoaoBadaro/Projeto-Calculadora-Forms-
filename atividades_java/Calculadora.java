import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Alunos: João Pedro e Lucas Rafael
//Prontuário: CB3042901 e CB3036871

public class Calculadora extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculadora() {
        setTitle("Calcul...");
        setSize(250, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 2, 2));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "", "", ""
        };

        for (String text : buttons) {
            if (!text.isEmpty()) {
                JButton button = new JButton(text);
                button.addActionListener(this);
                panel.add(button);
            } else {
                panel.add(new JLabel(""));
            }
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                if (display.getText().equals("0")) {
                    display.setText(command);
                } else {
                    display.setText(display.getText() + command);
                }
            } else if (command.equals("C")) {
                display.setText("0");
                num1 = num2 = result = 0;
                operator = '\0';
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': 
                        if (num2 == 0) throw new ArithmeticException("Divisão por zero");
                        result = num1 / num2; 
                        break;
                }
                display.setText(String.valueOf(result));
                operator = '\0';
            } else {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText("0");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato numérico: " + ex.getMessage());
            display.setText("0");
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, "Erro matemático: " + ex.getMessage());
            display.setText("0");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
            display.setText("0");
        } finally {
            System.out.println("Operação processada: " + command);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora().setVisible(true);
        });
    }
}