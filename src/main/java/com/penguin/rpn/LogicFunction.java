package com.penguin.rpn;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 21:40
 * @Description: rpn calculator's logic function modules
 */
public class LogicFunction {

    private Memory memory;

    public LogicFunction(Memory memory) {
        this.memory = memory;
    }

    /**
     * undo previous operation.
     */
    public void undo() {
        if (memory.operandStackSize() > 0) {
            memory.operandStackPop();
        }
        if (null != memory.getOperand(1)) {
            memory.operandStackPush(memory.getOperand(1));
        }
    }

    /**
     * clear the previous operation.
     */
    public void clear() {
        memory.clearOperandStack();
        memory.clearBuffer();
    }

    /**
     * calculate expression
     */
    public void calculate(String operator, int operatorIndex) {
        if (memory.operandStackSize() < 2) {
            if (memory.operandStackSize() == 1) {
                memory.addBuffer(memory.operandStackPop());
            }
            throw new CalculateException("operator " + operator + " (position: " + operatorIndex + "): insufficient parameters");
        }
        memory.setOperand(memory.operandStackPop(), 2);
        memory.setOperand(memory.operandStackPop(), 1);
        Double firstOperandNum = memory.getOperand(1);
        Double secondOperandNum = memory.getOperand(2);
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
        memory.operandStackPush(result);
    }
}
