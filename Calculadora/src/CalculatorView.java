
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculatorView extends CalculatorFunctions {

    //JFrame variables
    JFrame frame;
    JPanel headerPanel;
    JPanel bottomPanel;
    JTextField textField;
    JComboBox selectTheme;

    // Button variables
    JButton btnC;
    JButton btnDelete;
    JButton btnPorcentagem;
    JButton btnDiv;
    JButton btnMulti;
    JButton btnSub;
    JButton btnAdd;
    JButton btnPonto;
    JButton btnIgual;
    JButton btn7;
    JButton btn8;
    JButton btn9;
    JButton btn4;
    JButton btn5;
    JButton btn6;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn0;
    Color btnColor;

    double num1;
    double num2;
    double result;
    double numPercent;
    char operator;
    int COLOR_RGB = 255;

    // Const variables
    static final int MARGIN_X = 30;
    static final int MARGIN_Y = 20;
    static final int BUTTON_WIDTH = 70;
    static final int BUTTON_HEIGHT = 50;
    static final int WIDTH = 410;
    static final int HEIGHT= 600;

    // Arrays
    String[] themes = {"White", "Black"};

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
        btnColor = new Color(COLOR_RGB, COLOR_RGB, COLOR_RGB);

        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        //Header Panel
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 410, 150);
        //headerPanel.setBackground(Color.BLUE);
        this.add(headerPanel);

        //Bottom panel
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(0, 150, 410, 450);
        //bottomPanel.setBackground(Color.red);
        this.add(bottomPanel);

        //textField
        textField.setBounds(10, 60, 300, 60);
        textField.setFont(new Font("Helvetica", Font.BOLD, 35));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        headerPanel.add(textField);

        //Combo Box
        selectTheme.setBounds(210, 20, 100, 25);
        selectTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COLOR_RGB = changeTheme(selectTheme.getSelectedItem().toString(), headerPanel, bottomPanel, textField);
            }
        });
        headerPanel.add(selectTheme);

        initButtons(columns, rows, COLOR_RGB);

        this.setVisible(true);
    }

    public void initButtons(Integer[] columns, Integer[] rows, int COLOR_RGB){
        System.out.println(COLOR_RGB);
        btnC = createButtons("C", columns[0], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear(textField);
            }
        });
        bottomPanel.add(btnC);

        btnDelete = createButtons("<-", columns[1], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnDelete.addActionListener(new ActionListener() {
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
        bottomPanel.add(btnDelete);

        btnPorcentagem = createButtons("%", columns[2], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnPorcentagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numPercent = Double.parseDouble(textField.getText()) / 100;

                textField.setText(showResult(numPercent));
            }
        });
        bottomPanel.add(btnPorcentagem);

        btnDiv = createButtons("/", columns[3], rows[0], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se já existe um número atribuido na variavel num1. Se existir, é porque existe outra operação a ser feita primeiro
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '/';
                    textField.setText("");

                    //System.out.println("Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator + "Result " + result);
                    return;
                }

                // Verifica se já existe um número atribuido na variavel result. Se existir, é porque existe outra operação a ser feita primeiro
                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    num1 = 0.0;
                    operator = '/';
                    textField.setText("");

                    //System.out.println("Caiu no if result");
                    return;
                }

                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");

                //System.out.println("Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator + "Result " + result);
            }
        });
        bottomPanel.add(btnDiv);

        btn7 = createButtons("7", columns[0], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "7");
            }
        });
        bottomPanel.add(btn7);

        btn8 = createButtons("8", columns[1], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "8");
            }
        });
        bottomPanel.add(btn8);

        btn9 = createButtons("9", columns[2], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "9");
            }
        });
        bottomPanel.add(btn9);

        btnMulti = createButtons("*", columns[3], rows[1], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnMulti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se já existe um número atribuido na VARIAVEL NUM1. Se existir, é porque existe outra operação a ser feita primeiro
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '*';
                    textField.setText("");

                    //System.out.println("Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator + "Result " + result);
                    return;
                }

                // Verifica se já existe um número atribuido na VARIAVEL RESULT. Se existir, é porque existe outra operação a ser feita primeiro
                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    num1 = 0.0;
                    operator = '*';
                    textField.setText("");

                    //System.out.println("Caiu no if result " + result);
                    return;
                }

                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");

                //System.out.println("Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator + "Result " + result);
            }
        });
        bottomPanel.add(btnMulti);

        btn4 = createButtons("4", columns[0], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "4");
            }
        });
        bottomPanel.add(btn4);

        btn5 = createButtons("5", columns[1], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "5");
            }
        });
        bottomPanel.add(btn5);

        btn6 = createButtons("6", columns[2], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "6");
            }
        });
        bottomPanel.add(btn6);

        btnSub = createButtons("-", columns[3], rows[2], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se já existe um número atribuido na variavel num1. Se existir, é porque existe outra operação a ser feita primeiro
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '-';
                    textField.setText("");

                    //System.out.println("Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator + "Result " + result);
                    return;
                }

                // Verifica se já existe um número atribuido na variavel result. Se existir, é porque existe outra operação a ser feita primeiro
                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    operator = '-';
                    num1 = 0.0;
                    textField.setText("");

                    //System.out.println("Caiu no if result");
                    return;
                }

                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");

                //System.out.println("Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator);
            }
        });
        bottomPanel.add(btnSub);

        btn1 = createButtons("1", columns[0], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "1");
            }
        });
        bottomPanel.add(btn1);

        btn2 = createButtons("2", columns[1], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "2");
            }
        });
        bottomPanel.add(btn2);

        btn3 = createButtons("3", columns[2], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "3");
            }
        });
        bottomPanel.add(btn3);

        btnAdd = createButtons("+", columns[3], rows[3], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verifica se já existe um número atribuido na variavel num1. Se existir, é porque existe outra operação a ser feita primeiro
                if (num1 != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, num1, num2);
                    num1 = 0.0;
                    operator = '+';
                    textField.setText("");

                    //System.out.println("Caiu no if num1 " + "Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator + "Result " + result);
                    return;
                }

                // Verifica se já existe um número atribuido na variavel result. Se existir, é porque existe outra operação a ser feita primeiro
                if (result != 0.0){
                    num2 = Double.parseDouble(textField.getText());
                    result = operation(operator, result, num2);
                    operator = '+';
                    textField.setText("");
                    num1 = 0.0;

                    //System.out.println("Caiu no if result " + result);
                    return;
                }

                // Se não, essa é a primeira operação.
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");

                //System.out.println("Num1 = " + num1 + " Num2 = " + num2 + " Operator " + operator + "Result " + result);
            }
        });
        bottomPanel.add(btnAdd);

        btnPonto = createButtons(".", columns[0], rows[4], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btnPonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + ".");
            }
        });
        bottomPanel.add(btnPonto);

        btn0 = createButtons("0", columns[1], rows[4], BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RGB);
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "0");
            }
        });
        bottomPanel.add(btn0);

        btnIgual = createButtons("=", columns[2], rows[4], BUTTON_WIDTH*2 + MARGIN_X - 10, BUTTON_HEIGHT, COLOR_RGB);
        btnIgual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(textField.getText());

                // Se o resultado for == 0, quer dizer que só tem uma operação a ser feita.
                if (result == 0.0){
                    textField.setText(showResult(operation(operator, num1, num2)));
                    num1 = 0;
                    num2 =0;
                    result = 0;
                    return;
                }

                //Se não é == 0, é porque o usuário fez mais de uma operação em sequência
                textField.setText(showResult(operation(operator, result, num2)));
                num1 = 0;
                num2 =0;
                result = 0;

                //System.out.println("Operator: " + operator + " Num1 " + num1 + " Num2 " + num2 + " Result " + result);
            }
        });
        bottomPanel.add(btnIgual);
    }
}
