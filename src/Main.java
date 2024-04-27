import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame implements ActionListener {
    JLabel label;
    JPanel panel = new JPanel(new GridLayout(4, 3));
    StringBuilder currentNumber = new StringBuilder();
    String currentOperator = "";
    int previousNumber = 0;

    public static void main(String[] args) {
        Main app = new Main();

        app.mapButtonsToScreen(9);
        app.setUpFrame();
    }

    void addComponent(Component component) {
        panel.add(component);
    }

    void setUpFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 800);
        setTitle("Calculator");

        label = new JLabel("0", SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, 30));

        Border border = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        label.setBorder(border);

        add(label, BorderLayout.NORTH);
        add(panel);

        setVisible(true);
    }

    void createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        addComponent(button);
    }

    void mapButtonsToScreen(int length) {
        for (int i = 1; i <= length; i++) {
            createButton(String.valueOf(i));
        }

        ArrayList<String> buttons = new ArrayList<>(Arrays.asList("0", "+", "-", "*", "/", "C", "="));
        for (String button : buttons) {
            createButton(button);
        }
    }

    void changeLabelOnButtonClick(String text) {
        label.setText(text);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("+")) {
            previousNumber = Integer.parseInt(currentNumber.toString());
            currentNumber = new StringBuilder();
            currentOperator = "+";
        } else if (e.getActionCommand().equals("-")) {
            previousNumber = Integer.parseInt(currentNumber.toString());
            currentNumber = new StringBuilder();
            currentOperator = "-";
        } else if (e.getActionCommand().equals("*")) {
            previousNumber = Integer.parseInt(currentNumber.toString());
            currentNumber = new StringBuilder();
            currentOperator = "*";
        } else if (e.getActionCommand().equals("/")) {
            previousNumber = Integer.parseInt(currentNumber.toString());
            currentNumber = new StringBuilder();
            currentOperator = "/";
        } else if (e.getActionCommand().equals("C")) {
            previousNumber = 0;
            currentNumber = new StringBuilder();
            changeLabelOnButtonClick("0");
            currentOperator = "";
        } else if (e.getActionCommand().equals("=")) {
            int result = getResult();

            changeLabelOnButtonClick(String.valueOf(result));
            currentNumber = new StringBuilder();
        } else {
            currentNumber.append(e.getActionCommand());
            changeLabelOnButtonClick(currentNumber.toString());
        }
    }

    private int getResult() {
        int currentNumberInt = Integer.parseInt(currentNumber.toString());
        int result = 0;

        if (currentOperator.equals("+")) {
            result = previousNumber + currentNumberInt;
        } else if (currentOperator.equals("-")) {
            result = previousNumber - currentNumberInt;
        } else if (currentOperator.equals("*")) {
            result = previousNumber * currentNumberInt;
        } else if (currentOperator.equals("/")) {
            result = previousNumber / currentNumberInt;
        }
        return result;
    }
}
