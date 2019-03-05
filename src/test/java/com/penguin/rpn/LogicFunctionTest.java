package com.penguin.rpn;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 22:31
 * @Description: rpn calculator's logic function modules test
 */
public class LogicFunctionTest {
    
    /**
     * calculation precision
     */
    private Double precision = 0.000_000_000_000_001;
    
    /**
     * test LogicFunction.undo() when no calculation
     */
    @Test
    public void undoTest1() {
        CalculatorEngine engine = new CalculatorEngine();
        engine.getLogicFunction().undo();
        Memory memory = engine.getMemory();
        Assert.assertEquals(0, memory.operandStackSize());
    }
    
    /**
     * test LogicFunction.undo() when operand stack exist only one operand,
     * but no calculation operand in memory.
     */
    @Test
    public void undoTest2() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(1.5);
        engine.getLogicFunction().undo();
        Assert.assertEquals(0, memory.operandStackSize());
    }
    
    /**
     * test LogicFunction.undo() when operand stack exist only one operand,
     * and first operand is saved in operand memory.
     */
    @Test
    public void undoTest3() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(1.5);
        memory.setOperand(2.5, 1);
        engine.getLogicFunction().undo();
        Assert.assertEquals((Double)2.5, memory.operandStackPop());
    }
    
    /**
     * test LogicFunction.clear() when operand stack is empty and buffer is
     * empty, too.
     */
    @Test
    public void clearTest1() {
        CalculatorEngine engine = new CalculatorEngine();
        engine.getLogicFunction().clear();
        Memory memory = engine.getMemory();
        Assert.assertEquals(0, memory.operandStackSize());
        Assert.assertEquals(0, memory.getBuffer().size());
    }
    
    /**
     * test LogicFunction.clear() when operand stack is not empty, buffer is
     * not empty, either.
     */
    @Test
    public void clearTest2() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(3.4);
        memory.operandStackPush(3.7);
        memory.operandStackPush(0.7);
        memory.getBuffer().add(2.6);
        memory.getBuffer().add(8.6);
        engine.getLogicFunction().clear();
        Assert.assertEquals(0, memory.operandStackSize());
        Assert.assertEquals(0, memory.getBuffer().size());
    }
    
    /**
     * test LogicFunction.calculate() when operator is +.
     */
    @Test
    public void testCalculate1() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(1.2);
        memory.operandStackPush(3.2);
        engine.getLogicFunction().calculate("+", 2);
        Double calculationResult = memory.operandStackPop();
        Assert.assertEquals(4.4, calculationResult, precision);
    }
    
    /**
     * test LogicFunction.calculate() when operator is -.
     */
    @Test
    public void testCalculate2() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(1.2);
        memory.operandStackPush(3.2);
        engine.getLogicFunction().calculate("-", 2);
        Double calculationResult = memory.operandStackPop();
        Assert.assertEquals(-2.0, calculationResult, precision);
    }
    
    /**
     * test LogicFunction.calculate() when operator is *.
     */
    @Test
    public void testCalculate3() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(1.2);
        memory.operandStackPush(3.2);
        engine.getLogicFunction().calculate("*", 2);
        Double calculationResult = memory.operandStackPop();
        Assert.assertEquals(3.84, calculationResult, precision);
    }
    
    /**
     * test LogicFunction.calculate() when operator is /.
     */
    @Test
    public void testCalculate4() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(1.2);
        memory.operandStackPush(3.2);
        engine.getLogicFunction().calculate("/", 2);
        Double calculationResult = memory.operandStackPop();
        Assert.assertEquals(0.375, calculationResult, precision);
    }
    
    /**
     * test LogicFunction.calculate() when number of operand is not two.
     */
    @Test
    public void testCalculate5() {
        CalculatorEngine engine = new CalculatorEngine();
        Memory memory = engine.getMemory();
        memory.operandStackPush(1.2);
        boolean actualCalculationFlag = true;
        try {
            engine.getLogicFunction().calculate("/", 2);
        } catch (CalculateException e) {
            actualCalculationFlag = false;
        }
        Assert.assertFalse(actualCalculationFlag);
        Assert.assertEquals(1.2, memory.getBuffer().get(0), precision);
    }
}
