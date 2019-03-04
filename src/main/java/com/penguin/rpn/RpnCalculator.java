package com.penguin.rpn;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/2/27 22:20
 * @Description: RPN Calculator entrance.
 */
public class RpnCalculator {

    private List<Double> buffer = new ArrayList<>();//calculator cache
    private List<String> line = new ArrayList<>();//input line
    private HashSet<String> validOperators = new HashSet<>();
    private HashSet<String> validElements = new HashSet<>();//valid input elements set
    private Stack<Double> operandStack = new Stack<>();
    private String invalidElement = "";
    private Double firstOperand = null;
    private Double secondOperand = 0.0;
    private DecimalFormat format = new DecimalFormat("#.##########");

    public RpnCalculator() {
        /* initialize valid input elements */
        validOperators.add("+");
        validOperators.add("-");
        validOperators.add("*");
        validOperators.add("/");
        validElements.add("undo");
        validElements.add("clear");
        validElements.addAll(validOperators);
    }

    /**
     * set input line.
     *
     * @param inputLine input line.
     */
    public void setLine(List<String> inputLine) {
        line = inputLine;
    }

    /**
     * get buffer data.
     * @return buffer data
     */
    public String getBuffer() {
        return getBufferString();
    }

    /**
     * main method of RPN Calculator
     *
     * @param args run parameters
     */
    public static void main(String[] args) {
        RpnCalculator calculator = new RpnCalculator();
        System.out.println("****** Welcome to use RPN Calculator! ******");
        while (true) {
            System.out.println("please input RPN expression: ");
            /* get user input */
            calculator.getInput();
            /* execute calculation */
            calculator.dropWhiteSpace();
            calculator.execute();
            /* reset index and array */
            calculator.clearLine();
        }
    }

    /**
     * clear line.
     */
    private void clearLine() {
        line.clear();
    }

    /**
     * execute calculate.
     * @return execute result: 0-success, 1-invalid input, 2-calculate exception
     */
    public int execute() {
        int result = 0;
        /* check input elements */
        if (checkInput()) {
            try {
                executeCalculation();
            } catch (CalculateException e) {
                System.out.println(e.getMessage());
                result = 2;
            }
            System.out.println("buffer: " + getBufferString());
        } else {
            System.out.println("invalid element: " + invalidElement);
            result = 1;
        }
        return result;
    }

    /**
     * drop all white space.
     */
    private void dropWhiteSpace() {
        StringBuilder temp = new StringBuilder();
        List<String> newLine = new ArrayList<>();
        for (int i = 0; i < line.size(); i++) {
            String character = line.get(i);
            if (character.equals(" ") && !temp.toString().isEmpty()) {
                newLine.add(temp.toString());
                temp = new StringBuilder();
            } else {
                temp.append(character);
                if (i == (line.size() - 1)) {
                    newLine.add(temp.toString());
                }
            }
        }
        line.clear();
        line.addAll(newLine);
    }

    /**
     * get buffer in String
     *
     * @return buffer content
     */
    public String getBufferString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Double element : buffer) {
            stringBuilder.append(getFormattedStr(element)).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * get formatted number string.
     * @param number target number
     * @return formatted number string
     */
    private String getFormattedStr(Double number) {
        return format.format(number);
    }

    /**
     * get user input line.
     */
    private void getInput() {
        int inputCharacter;
        try {
            while ((inputCharacter = System.in.read()) != -1) {
                if (inputCharacter == 10) {
                    break;
                }
                line.add(String.valueOf((char) inputCharacter));
            }
        } catch (Exception e) {
            System.out.println("Oops, an calculator error has occurred.");
        }
    }

    /**
     * check input character
     */
    private boolean checkInput() {
        boolean checkOk = true;
        for (String element : line) {
            if (!validElements.contains(element) && !isNumber(element)) {
                checkOk = false;
                invalidElement = element;
            }
        }
        return checkOk;
    }

    /**
     * check if the element is a number.
     *
     * @param element the element to check.
     * @return return true if the element is a number, otherwise return false.
     */
    private boolean isNumber(String element) {
        boolean isNumber;
        try {
            Double.parseDouble(element);
            isNumber = true;
        } catch (NumberFormatException e) {
            isNumber = false;
        }
        return isNumber;
    }

    /**
     * get element's type
     *
     * @param element element
     * @return element's type
     */
    private String getElementType(String element) {
        String type;
        if (isNumber(element)) {
            type = "operand";
        } else if (validOperators.contains(element)) {
            type = "operator";
        } else if ("undo".equals(element)) {
            type = "undo";
        } else if ("clear".equals(element)) {
            type = "clear";
        } else {
            type = "";
        }
        return type;
    }

    /**
     * execute calculation
     */
    private void executeCalculation() {
        // empty operand stack.
        operandStack.clear();
        // push buffer to stack.
        for (Double bufferElement : buffer) {
            operandStack.push(bufferElement);
        }
        // scan input line.
        for (int i = 0; i < line.size(); i++) {
            String element = line.get(i);
            String elementType = getElementType(element);
            switch (elementType) {
                case "operand":
                    operandStack.push(Double.parseDouble(element));
                    break;
                case "operator":
                    calculate(element, i);
                    break;
                case "undo":
                    undo();
                    break;
                case "clear":
                    clear();
                    break;
                default:
                    ;//nothing to do.
            }
        }
        //firstOperand = "";
        //secondOperand = "";
        // move calculation result to buffer.
        buffer.clear();
        List<Double> tempArr = new ArrayList<>();
        if (operandStack.size() > 0) {
            while (operandStack.size() > 0) {
                tempArr.add(operandStack.pop());
            }
            for (int i = tempArr.size() - 1; i >= 0; i--) {
                buffer.add(tempArr.get(i));
            }
        }
    }

    /**
     * undo previous operation.
     */
    private void undo() {
        if (!operandStack.isEmpty()) {
            operandStack.pop();
        }
        if (null != firstOperand) {
            operandStack.push(firstOperand);
        }
    }

    /**
     * clear the previous operation.
     */
    private void clear() {
        operandStack.clear();
        buffer.clear();
    }

    /**
     * calculate expression
     */
    private void calculate(String operator, int operatorIndex) {
        if (operandStack.size() < 2) {
            if (operandStack.size() == 1) {
                buffer.add(operandStack.pop());
            }
            throw new CalculateException("operator " + operator + " (position: " + operatorIndex + "): insufficient parameters");
        }
        secondOperand = operandStack.pop();
        firstOperand = operandStack.pop();
        Double secondOperandNum = secondOperand;
        Double firstOperandNum = firstOperand;
        Double result = 0.0;
        switch (operator) {
            case "+":
                result = firstOperandNum + secondOperandNum;
                break;
            case "-":
                result = firstOperandNum - secondOperandNum;
                break;
            case "*":
                result = firstOperandNum * secondOperandNum;
                break;
            case "/":
                result = firstOperandNum / secondOperandNum;
                break;
            default:
                ;//nothing to do.
        }
        operandStack.push(result);
    }
}
