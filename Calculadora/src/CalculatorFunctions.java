import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorFunctions extends JFrame implements ActionListener {

    // Create the buttons
    public JButton createButtons(String text, Integer column, Integer row, int BUTTON_WIDTH, int BUTTON_HEIGHT, int COLOR_RGB){
        JButton btn = new JButton(text);
        btn.setBounds(column, row, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 20));
            btn.setBackground(new Color(COLOR_RGB, COLOR_RGB, COLOR_RGB));

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
    public void changeTheme(String theme, JPanel headerPanel, JPanel bottomPannel, JTextField textField, JButton[] buttons){
        int BACKGROUND_COLOR_RGB;
        int TF_BG_COLOR;
        if (Objects.equals(theme, "Dark")){
            BACKGROUND_COLOR_RGB = 0;
            TF_BG_COLOR = 42;
        } else {
            BACKGROUND_COLOR_RGB = 230;
            TF_BG_COLOR = 255;
        }

        Color color = new Color(BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB);

        headerPanel.setBackground(color);
        bottomPannel.setBackground(color);
        textField.setBackground(new Color(TF_BG_COLOR, TF_BG_COLOR, TF_BG_COLOR));
        changeButtonsColor(theme, buttons);
        textField.setForeground(theme.equals("Dark") ? Color.WHITE : Color.BLACK);
    }

    public void changeButtonsColor(String theme, JButton[] buttons){
        for (JButton btn : buttons){
            int BTN_COLOR_RGB;
            if (Objects.equals(theme, "Dark")){
                BTN_COLOR_RGB = 42;
            } else {
                BTN_COLOR_RGB = 255;
            }

            Color color = new Color(BTN_COLOR_RGB, BTN_COLOR_RGB, BTN_COLOR_RGB);

            btn.setBackground(color);
            btn.setForeground(theme.equals("Dark") ? Color.WHITE : Color.BLACK);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
