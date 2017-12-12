package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button zero;
    @FXML
    private Button minus;
    @FXML
    private Button plus;
    @FXML
    private Button backslash;
    @FXML
    private Button star;
    @FXML
    private Button back;
    @FXML
    private Button ans;
    @FXML
    private Button dot;
    @FXML
    private Button clear;
    @FXML
    private TextArea inputArea;
    @FXML
    private TextField answerArea;

    private boolean shouldClearTextOnNextInput;
    private boolean shouldPassAnswer;

    public void initialize() {
        answerArea.setText("0");
        inputArea.setText("");
        shouldClearTextOnNextInput = true;
        disableButtons();
        shouldPassAnswer = false;

    }

    public void disableButtons() {
        minus.setDisable(true);
        plus.setDisable(true);
        star.setDisable(true);
        backslash.setDisable(true);
        dot.setDisable(true);
    }

    public void enableButtons() {
        minus.setDisable(false);
        plus.setDisable(false);
        star.setDisable(false);
        backslash.setDisable(false);
        dot.setDisable(false);
    }

    public String calculate(TextArea textArea) {
        String text = textArea.getText();

        String[] numbers = text.split("[x+/-]");
        Double[] doubleNumbers = new Double[numbers.length];
        for (int i = 0; i < doubleNumbers.length; i++) {
            doubleNumbers[i] = Double.parseDouble(numbers[i]);
        }

        String separators = text.replaceAll("[0-9.]", "");

        double result = doubleNumbers[0];
        for (int i = 1; i < doubleNumbers.length; i++) {
            if (separators.charAt(i - 1) == 'x') {
                result *= doubleNumbers[i];

            } else if (separators.charAt(i - 1) == '+') {
                result += doubleNumbers[i];
            } else if (separators.charAt(i - 1) == '-') {
                result -= doubleNumbers[i];
            } else if (separators.charAt(i - 1) == '/') {
                if (doubleNumbers[i] != 0) {
                    result /= doubleNumbers[i];
                } else {
                    return "0";

                }
            }
        }
        return Double.toString(result);

    }

    @FXML
    public void handleButtonPressed(ActionEvent event) {

        if (shouldClearTextOnNextInput) {
            inputArea.clear();
            answerArea.clear();
            shouldClearTextOnNextInput = false;
        }


        if (event.getSource().equals(one)) {
            inputArea.appendText("1");
            enableButtons();
            answerArea.setText(calculate(inputArea));

        } else if (event.getSource().equals(two)) {
            inputArea.appendText("2");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(three)) {
            inputArea.appendText("3");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(four)) {
            inputArea.appendText("4");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(five)) {
            inputArea.appendText("5");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(six)) {
            inputArea.appendText("6");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(seven)) {
            inputArea.appendText("7");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(eight)) {
            inputArea.appendText("8");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(nine)) {
            inputArea.appendText("9");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(zero)) {
            inputArea.appendText("0");
            enableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(dot)) {
            inputArea.appendText(".");
            disableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(plus)) {
            if (shouldPassAnswer) {
                String text = answerArea.getText();
                inputArea.setText(text);
                shouldPassAnswer = false;
            }
            inputArea.appendText("+");
            disableButtons();
            answerArea.setText(calculate(inputArea));

        } else if (event.getSource().equals(minus)) {
            if (shouldPassAnswer) {
                String text = answerArea.getText();
                inputArea.setText(text);
                shouldPassAnswer = false;
            }
            inputArea.appendText("-");
            disableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(star)) {
            if (shouldPassAnswer) {
                String text = answerArea.getText();
                inputArea.setText(text);
                shouldPassAnswer = false;
            }
            inputArea.appendText("x");
            disableButtons();
            answerArea.setText(calculate(inputArea));
        } else if (event.getSource().equals(backslash)) {
            if (shouldPassAnswer) {
                String text = answerArea.getText();
                inputArea.setText(text);
                shouldPassAnswer = false;
            }
            inputArea.appendText("/");
            disableButtons();
            answerArea.setText(calculate(inputArea));
        }


        if (event.getSource().equals(clear)) {

            inputArea.clear();
            inputArea.setText("");
            answerArea.clear();
            answerArea.setText("0");
            shouldClearTextOnNextInput = true;
            disableButtons();
        } else if (event.getSource().equals(back)) {
            String isEmpty = inputArea.getText().trim();
            if (!isEmpty.isEmpty()) {
                String last = inputArea.getText(inputArea.getLength() - 1, inputArea.getLength());
                if (last != null) {
                    if (last.equals("/") || last.equals("x") || last.equals("+") || last.equals("-")) {
                        enableButtons();
                    }
                    inputArea.deleteText(inputArea.getLength() - 1, inputArea.getLength());
                    if (isEmpty.length() > 1) {
                        answerArea.setText(calculate(inputArea));
                    } else {
                        answerArea.setText("0");
                    }
                }
            }

        } else if (event.getSource().equals(ans)) {
            String isEmpty = inputArea.getText();
            dot.setDisable(true);
            if (!isEmpty.isEmpty()) {
                answerArea.setText(calculate(inputArea));
                inputArea.clear();
            }
            shouldPassAnswer = true;


        }
    }
}


