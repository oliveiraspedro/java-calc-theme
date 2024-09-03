import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CalculatorFunctions extends JFrame implements ActionListener {

    // Create the buttons
    public JButton createButtons(String text, Integer column, Integer row, int BUTTON_WIDTH, int BUTTON_HEIGHT, int COLOR_RGB){
        JButton btn = new JButton(text);
        btn.setBounds(column, row, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 20));
        btn.setBackground(new Color(COLOR_RGB, COLOR_RGB, COLOR_RGB));
        btn.addActionListener(this);
        btn.setFocusable(false);

        System.out.println(COLOR_RGB);
        return btn;
    }

    // Allow only numbers to be digit
    public void onlyNumbers(KeyEvent event){
        char c = event.getKeyChar();

        if (!Character.isDigit(c)){
            event.consume();
        }
    }


    // Clear the text field
    public void clear(JTextField textField){
        textField.setText("");
    }


    // Mathematics operations
    public double operation(char operator, double num1, double num2){
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> 0.0;
        };
    }

    public String showResult(double num){
        return String.valueOf(num);
    }

    // Muda o tema da calculadora
    public int changeTheme(String theme, JPanel headerPanel, JPanel bottomPannel, JTextField textField){
        if (theme == "Black") headerPanel.setBackground(Color.BLACK); bottomPannel.setBackground(Color.BLACK); return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
