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
    /**
     * calculator cache
     */
    private List<Double> buffer = new ArrayList<>();
    
    /**
     * input data
     */
    private List<String> input = new ArrayList<>();
    
    /**
     * operand stack for calculation.
     */
    private Stack<Double> operandStack = new Stack<>();
    
    /**
     * the two operand for calculation
     */
    private Double[] operand = new Double[]{null, null};
    
    /**
     * the invalid element in input data.
     */
    private String invalidElement = "";
    
    /**
     * precision of buffer data when print.
     */
    private DecimalFormat format = new DecimalFormat("#.##########");
    
    /**
     * set the invalid element.
     * @param element the invalid element
     */
    public void setInvalidElement(String element) {
        invalidElement = element;
    }
    
    /**
     * get the invalid element.
     * @return the invalid element
     */
    public String getInvalidElement() {
        return invalidElement;
    }
    
    /**
     * set the operand for calculation at the position which index points.
     * @param operand the operand
     * @param index the calculation position
     */
    public void setOperand(Double operand, int index) {
        this.operand[index - 1] = operand;
    }
    
    /**
     * get the operand at the position which index points.
     * @param index the calculation position
     * @return the operand
     */
    public Double getOperand(int index) {
        return operand[index - 1];
    }
    
    /**
     * set the user input data.
     * @param input input data
     */
    public void setInput(List<String> input) {
        this.input = input;
    }
    
    /**
     * get the user input data.
     * @return
     */
    public List<String> getInput() {
        return input;
    }
    
    /**
     * get the calculation buffer.
     * @return buffer
     */
    public List<Double> getBuffer() {
        return buffer;
    }
    
    /**
     * clear the user input data.
     */
    public void clearInput() {
        input.clear();
    }
    
    /**
     * clear the calculation buffer.
     */
    public void clearBuffer() {
        buffer.clear();
    }
    
    /**
     * clear the operand stack in calculation memory.
     */
    public void clearOperandStack() {
        operandStack.clear();
    }
    
    /**
     * push an operand to the calculation operand stack.
     * @param element the target operand
     */
    public void operandStackPush(Double element) {
        operandStack.push(element);
    }
    
    /**
     * pop an operand from the calculation operand stack.
     * @return the popped operand
     */
    public Double operandStackPop() {
        return operandStack.pop();
    }
    
    /**
     * get the size of calculation operand stack.
     * @return the size of operand stack
     */
    public int operandStackSize() {
        return operandStack.size();
    }
    
    /**
     * add a element to calculation buffer
     * @param inputData the element to add
     */
    public void addBuffer(Double inputData) {
        buffer.add(inputData);
    }
    
    /**
     * add a string to user input data.
     * @param inputData the string to add
     */
    public void addInput(String inputData) {
        input.add(inputData);
    }

    /**
     * get calculation buffer data string.
     * @return string of buffer data
     */
    public String getBufferStr() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Double element : buffer) {
            stringBuilder.append(format.format(element)).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
