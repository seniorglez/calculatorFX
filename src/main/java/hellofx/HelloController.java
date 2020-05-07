/**
 * Copyright (c) 2019 Gluon
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of Gluon, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL GLUON BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package hellofx;

import hellofx.calculator.CalculatorMachine;
import javafx.event.ActionEvent;
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

    public void initialize() {
        label.setText(expression);
    }

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        String txt = btn.getText();
        System.out.println( txt+ " pressed");
        switch (txt) {
            case "DEL":
                break;
            case "=":
                expression = CalculatorMachine.calculate(expression) + "";
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
