package com.penguin.rpn;

import java.util.*;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 21:06
 * @Description: rpn calculator's core module
 */
public class CalculatorEngine {

    private Memory memory = new Memory();
    private LogicFunction logicFunction = new LogicFunction(memory);
    private Components components = new Components();

    /**
     * start rpn calculator.
     */
    public void start() {
        while (true) {
            System.out.println("please input RPN expression: ");
            /* get user input */
            getInput();
            /* execute calculation */
            dropWhiteSpace();
            execute();
            /* reset index and array */
            memory.clearInput();
        }
    }

    /**
     * test method.
     * @param input input string.
     * @return calculation result.
     */
    public HashMap<String, Object> runForTest(String input) {
        String[] inputSplit = input.split(" ");
        memory.setInput(Arrays.asList(inputSplit));
        int errCode = execute();
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("errorCode", errCode);
        ret.put("buffer", memory.getBufferStr());
        return ret;
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
                memory.addInput(String.valueOf((char) inputCharacter));
            }
        } catch (Exception e) {
            System.out.println("Oops, an calculator error has occurred.");
        }
    }

    /**
     * drop all white space.
     */
    private void dropWhiteSpace() {
        StringBuilder temp = new StringBuilder();
        List<String> processedInput = new ArrayList<>();
        List<String> inputContent = memory.getInput();
        for (int i = 0; i < inputContent.size(); i++) {
            String character = inputContent.get(i);
            if (character.equals(" ") && !temp.toString().isEmpty()) {
                processedInput.add(temp.toString());
                temp = new StringBuilder();
            } else {
                temp.append(character);
                if (i == (inputContent.size() - 1)) {
                    processedInput.add(temp.toString());
                }
            }
        }
        memory.setInput(processedInput);
    }

    /**
     * execute calculate.
     * @return execute result: 0-success, 1-invalid input, 2-calculate exception
     */
    private int execute() {
        int result = 0;
        /* check input elements */
        if (checkInput()) {
            try {
                executeCalculation();
            } catch (CalculateException e) {
                System.out.println(e.getMessage());
                result = 2;
            }
            System.out.println("buffer: " + memory.getBufferStr());
        } else {
            System.out.println("invalid element: " + memory.getInvalidElement());
            result = 1;
        }
        return result;
    }

    /**
     * check input character
     */
    private boolean checkInput() {
        boolean checkOk = true;
        for (String element : memory.getInput()) {
            if (!components.isValidComponent(element) && !isNumber(element)) {
                checkOk = false;
                memory.setInvalidElement(element);
            }
        }
        return checkOk;
    }

    /**
     * execute calculation
     */
    private void executeCalculation() {
        // empty operand stack.
        memory.clearOperandStack();
        // push buffer to stack.
        List<Double> buffer = memory.getBuffer();
        for (Double bufferElement : buffer) {
            memory.operandStackPush(bufferElement);
        }
        // scan input line.
        List<String> input = memory.getInput();
        for (int i = 0; i < input.size(); i++) {
            String element = input.get(i);
            String elementType = getElementType(element);
            switch (elementType) {
                case "operand":
                    memory.operandStackPush(Double.parseDouble(element));
                    break;
                case "operator":
                    logicFunction.calculate(element, i);
                    break;
                case "undo":
                    logicFunction.undo();
                    break;
                case "clear":
                    logicFunction.clear();
                    break;
                default:
                    ;//nothing to do.
            }
        }
        // move calculation result to buffer.
        if (memory.operandStackSize() > 0) {
            List<Double> tempArr = new ArrayList<>();
            while (memory.operandStackSize() > 0) {
                tempArr.add(memory.operandStackPop());
            }
            buffer.clear();
            for (int i = tempArr.size() - 1; i >= 0; i--) {
                buffer.add(tempArr.get(i));
            }
        }
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
        } else if (components.isValidOperator(element)) {
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
}