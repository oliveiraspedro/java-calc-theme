
import javax.swing.*;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class CalculatorView extends CalculatorFunctions {

    //JFrame variables
    JFrame frame;
    JPanel headerPanel;
    JPanel bottomPanel;
    JTextField textField;
    JComboBox selectTheme;

    // Button variables
    Color btnColor;

    double num1;
    double num2;
    double result;
    double numPercent;
    char operator;
    int BACKGROUND_COLOR_RGB = 230;
    int BTN_COLOR_RGB = 255;

    // Const variables
    static final int MARGIN_X = 30;
    static final int MARGIN_Y = 20;
    static final int BUTTON_WIDTH = 70;
    static final int BUTTON_HEIGHT = 50;
    static final int WIDTH = 410;
    static final int HEIGHT= 600;

    // Arrays
    JButton[] buttons = new JButton[19];
    String[] themes = {"White", "Dark"};

    // Array with Y position of the buttons
    Integer[] rows = {MARGIN_Y, MARGIN_Y + BUTTON_HEIGHT + 30, MARGIN_Y + BUTTON_HEIGHT * 2 + 30*2, MARGIN_Y + BUTTON_HEIGHT * 3  + 30*3, MARGIN_Y + BUTTON_HEIGHT * 4 + 30*4};
    // Array with X position of the buttons
    Integer[] columns = {MARGIN_X, MARGIN_X + 90, MARGIN_X + 90 * 2, MARGIN_X + 90 * 3};


    public CalculatorView(){
        frame = new JFrame();
        headerPanel = new JPanel();
        bottomPanel = new JPanel();
        textField = new JTextField();
        selectTheme = new JComboBox(themes);
        btnColor = new Color(BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB);

        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        //Header Panel
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 410, 150);
        headerPanel.setBackground(new Color(BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB, 255));
        this.add(headerPanel);

        //Bottom panel
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(0, 150, 410, 450);
        bottomPanel.setBackground(new Color(BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB, BACKGROUND_COLOR_RGB, 255));
        this.add(bottomPanel);

        //textField
        textField.setBounds(10, 60, 300, 60);
        textField.setFont(new Font("Helvetica", Font.BOLD, 35));
        PlainDocument document = (PlainDocument) textField.getDocument();
        document.setDocumentFilter(new FilterTextField());
        headerPanel.add(textField);

        // Inicialização
        initThemeSelect();
        initButtons(columns, rows, BTN_COLOR_RGB);

        this.setVisible(true);
    }

    public void initThemeSelect(){
        selectTheme.setBounds(210, 20, 100, 25);
        selectTheme.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED)
                    return;

                String theme = Objects.requireNonNull(selectTheme.getSelectedItem()).toString();
                changeTheme(theme, headerPanel, bottomPanel, textField, buttons);
            }
        });
        headerPanel.add(selectTheme);

    }

    public void initButtons(Integer[] columns, Integer[] rows, int BTN_COLOR_RGB){
        buttons[0] = createButtons("C", columns[0], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear(textField);
            }
        });
        bottomPanel.add(buttons[0]);

        buttons[1] = createButtons("<-", columns[1], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder(textField.getText());

                try {
                    sb.deleteCharAt(textField.getText().length() - 1);

                    textField.setText(sb.toString());
                } catch (Exception exception){
                    System.out.println("TextField is empty");
                }
            }
        });
        bottomPanel.add(buttons[1]);

        buttons[2] = createButtons("%", columns[2], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numPercent = Double.parseDouble(textField.getText()) / 100;

                textField.setText(showResult(numPercent));
            }
        });
        bottomPanel.add(buttons[2]);

        buttons[3] = createButtons("/", columns[3], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se existe outra operação a ser feita primeira.
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '/';
                    textField.setText("");

                    return;
                }

                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    num1 = 0.0;
                    operator = '/';
                    textField.setText("");

                    return;
                }

                // Se não, está é a primeira operação
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");

            }
        });
        bottomPanel.add(buttons[3]);

        buttons[4] = createButtons("7", columns[0], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "7");
            }
        });
        bottomPanel.add(buttons[4]);

        buttons[5] = createButtons("8", columns[1], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "8");
            }
        });
        bottomPanel.add(buttons[5]);

        buttons[6] = createButtons("9", columns[2], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "9");
            }
        });
        bottomPanel.add(buttons[6]);

        buttons[7] = createButtons("*", columns[3], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se existe outra operação a ser feita primeira.
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '*';
                    textField.setText("");

                    return;
                }

                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    num1 = 0.0;
                    operator = '*';
                    textField.setText("");

                    return;
                }

                // Se não, está é a primeira operação
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");

            }
        });
        bottomPanel.add(buttons[7]);

        buttons[8] = createButtons("4", columns[0], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "4");
            }
        });
        bottomPanel.add(buttons[8]);

        buttons[9] = createButtons("5", columns[1], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "5");
            }
        });
        bottomPanel.add(buttons[9]);

        buttons[10] = createButtons("6", columns[2], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "6");
            }
        });
        bottomPanel.add(buttons[10]);

        buttons[11] = createButtons("-", columns[3], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se existe outra operação a ser feita primeira.
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '-';
                    textField.setText("");

                    return;
                }

                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    operator = '-';
                    num1 = 0.0;
                    textField.setText("");

                    return;
                }

                // Se não, está é a primeira operação
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");

            }
        });
        bottomPanel.add(buttons[11]);

        buttons[12] = createButtons("1", columns[0], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "1");
            }
        });
        bottomPanel.add(buttons[12]);

        buttons[13] = createButtons("2", columns[1], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "2");
            }
        });
        bottomPanel.add(buttons[13]);

        buttons[14] = createButtons("3", columns[2], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "3");
            }
        });
        bottomPanel.add(buttons[14]);

        buttons[15] = createButtons("+", columns[3], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[15].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se existe outra operação a ser feita primeira.
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '+';
                    textField.setText("");

                    return;
                }

                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    operator = '+';
                    textField.setText("");
                    num1 = 0.0;

                    return;
                }

                // Se não, essa é a primeira operação.
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");

            }
        });
        bottomPanel.add(buttons[15]);

        buttons[16] = createButtons(".", columns[0], rows[4], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[16].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + ".");
            }
        });
        bottomPanel.add(buttons[16]);

        buttons[17] = createButtons("0", columns[1], rows[4], BUTTON_WIDTH, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[17].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "0");
            }
        });
        bottomPanel.add(buttons[17]);

        buttons[18] = createButtons("=", columns[2], rows[4], BUTTON_WIDTH*2 + MARGIN_X - 10, BUTTON_HEIGHT, BTN_COLOR_RGB);
        buttons[18].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(textField.getText());

                // Se result for == 0, quer dizer que só tem uma operação a ser feita.
                if (result == 0.0){
                    textField.setText(showResult(operation(operator, num1, num2)));
                    num1 = 0;
                    num2 =0;
                    result = 0;
                    return;
                }

                //Se não, é porque o usuário fez mais de uma operação em sequência
                textField.setText(showResult(operation(operator, result, num2)));
                num1 = 0;
                num2 =0;
                result = 0;

            }
        });
        buttons[18].setBackground(new Color(66, 143, 192));
        bottomPanel.add(buttons[18]);
    }
}
