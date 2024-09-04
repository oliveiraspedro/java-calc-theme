import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class CalculatorFunctions extends JFrame implements ActionListener {

    // Create the buttons
    public JButton createButtons(String text, Integer column, Integer row, int BUTTON_WIDTH, int BUTTON_HEIGHT, int COLOR_RGB, JButton[] buttons){
        JButton btn = new JButton(text);
        btn.setBounds(column, row, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 20));

        return btn;
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
    public void changeTheme(String theme, JPanel headerPanel, JPanel bottomPannel, JTextField textField, int COLOR_RGB){
        if (Objects.equals(theme, "Black")){
            COLOR_RGB = 0;
        } else {
            COLOR_RGB = 255;
        }

        Color color = new Color(COLOR_RGB, COLOR_RGB, COLOR_RGB);

        headerPanel.setBackground(color);
        bottomPannel.setBackground(color);
        textField.setBackground(color);
        textField.setForeground(theme.equals("Black") ? Color.WHITE : Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
