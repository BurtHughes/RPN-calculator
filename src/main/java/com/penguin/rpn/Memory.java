package com.penguin.rpn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 20:02
 * @Description: calculator's memory to save calculation data.
 */
public class Memory {
    private List<Double> buffer = new ArrayList<>();//calculator cache
    private List<String> input = new ArrayList<>();//input data
    private Stack<Double> operandStack = new Stack<>();
    private Double[] operand = new Double[]{null, null};
    private String invalidElement = "";
    private DecimalFormat format = new DecimalFormat("#.##########");

    public void setInvalidElement(String element) {
        invalidElement = element;
    }

    public String getInvalidElement() {
        return invalidElement;
    }

    public void setOperand(Double operand, int index) {
        this.operand[index - 1] = operand;
    }

    public Double getOperand(int index) {
        return operand[index - 1];
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public List<String> getInput() {
        return input;
    }

    public List<Double> getBuffer() {
        return buffer;
    }

    public void clearInput() {
        input.clear();
    }

    public void clearBuffer() {
        buffer.clear();
    }

    public void clearOperandStack() {
        operandStack.clear();
    }

    public void operandStackPush(Double element) {
        operandStack.push(element);
    }

    public Double operandStackPop() {
        return operandStack.pop();
    }

    public int operandStackSize() {
        return operandStack.size();
    }

    public void addBuffer(Double inputData) {
        buffer.add(inputData);
    }

    public void addInput(String inputData) {
        input.add(inputData);
    }

    /**
     * get buffer data.
     * @return buffer data
     */
    public String getBufferStr() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Double element : buffer) {
            stringBuilder.append(format.format(element)).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
