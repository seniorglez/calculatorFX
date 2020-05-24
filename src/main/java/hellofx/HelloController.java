package hellofx;

import hellofx.calculator.service.CalculatorService;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Label label;

    @FXML
    private ResourceBundle resources;

    private String expression = "";
    private final String OPERATION_SIMBOLS = "/*+^-";
    private CalculatorService calculatorService;

    public void initialize() {
        calculatorService = new CalculatorService();
        calculatorService.start();
        calculatorService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                String result = (String) workerStateEvent.getSource().getValue();
                expression = result;
                label.setText(result);
            }
        });
        label.setText(resources.getString("label.text"));
    }

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        String txt = btn.getText();
        System.out.println( txt+ " pressed");
        switch (txt) {
            case "DEL":
                expression = removeLastChar(expression);
                label.setText(expression);
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "*":
            case "-":
            case "/":
            case "^":
                addOperation(txt);
                label.setText(expression);
                break;
            case "%":
            case "√":
                System.out.println("Not implemented yet");
                break;
            default:
                expression+=txt;
                label.setText(expression);
        }
    }

    private void calculate() {
        calculatorService.setMath_expression(expression);
        if ((calculatorService.stateProperty().getValue().equals("SCHEDULED"))) {
            calculatorService.start();
        } else {
            calculatorService.restart();
        }
    }

    private void addOperation(String operation) {
        if(!endWithOperator() && !expression.isEmpty()) expression += operation;
    }

    private static String removeLastChar(String expression) {
        return expression.substring(0, expression.length() - 1);
    }

    private boolean endWithOperator() {
        char[] operations = OPERATION_SIMBOLS.toCharArray();
        for(int i=0; i<operations.length; i++){
            if(expression.endsWith(operations[i]+"")){
                return true;
            }
        }
        return false;
    }
}
