package hellofx;

import com.gluonhq.attach.vibration.VibrationService;
import com.gluonhq.charm.down.Services;
import hellofx.calculator.CalculatorTask;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private ResourceBundle resources;

    private String expression = "";
    private final String OPERATION_SIMBOLS = "/*+^-";
    private CalculatorTask calculatorTask;

    public void initialize() {
        label.setText(resources.getString("label.text"));
    }

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        Services.get(VibrationService.class).ifPresent(service -> {
            service.vibrate();
        });

        Button btn = (Button) event.getSource();
        String txt = btn.getText();
        System.out.println( txt+ " pressed");
        switch (txt) {
            case "DEL":
                break;
            case "=":
                //expression = CalculatorMachine.calculate(expression) + "";
                calculate();
                break;
            case "+":
            case "*":
            case "-":
            case "/":
            case "^":
                addOperation(txt);
                break;
            case "%":
            case "√":
                System.out.println("Not implemented yet");
                break;
            default:
                expression+=txt;
        }
        label.setText(expression);
    }

    private void calculate() {
        calculatorTask = new CalculatorTask();
        calculatorTask.setMath_Expression(expression);
        calculatorTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                expression = calculatorTask.getMessage();
                label.setText(expression);
            }
        });
        new Thread(calculatorTask).start();
    }

    private void addOperation(String operation) {
        if(!endWithOperator() && !expression.isEmpty()) expression += operation;
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
