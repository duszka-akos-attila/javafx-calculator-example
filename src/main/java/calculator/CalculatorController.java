package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           display.setText(String.format("%.0f", result));
           operator = "";
        }
        else if(operatorPressed.equals("AC")){
            clearPressed();
        }
        else if(operatorPressed.equals(",")){
            dotPressed();
        }
        else if(operatorPressed.equals("\u00B1")){
            plusorMinusPressed();
        }
        else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    private void clearPressed() {
        number1 = 0;
        display.setText("");
        startNumber = true;
        operator = "";
    }

    private void dotPressed() {
        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void plusorMinusPressed() {
        if (display.getText().isEmpty() || Character.isDigit(display.getText().charAt(0))) {
            display.setText("-" + display.getText());
            startNumber = false;
        } else {
            display.setText(display.getText(1, display.getText().length()));
        }
    }

}
