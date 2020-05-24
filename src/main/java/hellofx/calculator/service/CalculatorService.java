package hellofx.calculator.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CalculatorService extends Service<String> {

    private String math_expression;

    @Override
    protected Task<String> createTask() {
        CalculatorTask calculatorTask = new CalculatorTask();
        calculatorTask.setMath_Expression(math_expression);
        return calculatorTask;
    }

    public void setMath_expression(String math_expression) {
        this.math_expression = math_expression;
    }
}
