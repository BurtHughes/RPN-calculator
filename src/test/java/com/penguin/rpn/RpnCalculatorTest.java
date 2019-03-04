package com.penguin.rpn;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/1 21:26
 * @Description: test RpnCalculator's function.
 */
public class RpnCalculatorTest {

    /**
     * test template
     *
     * @param lineStr        input line
     * @param expectedStr    expected buffer
     * @param expectedResult expected execution result
     */
    private void test(String lineStr, String expectedStr, int expectedResult) {
        CalculatorEngine engine = new CalculatorEngine();
        HashMap<String, Object> result = engine.runForTest(lineStr);
        Assert.assertEquals((int) result.get("errorCode"), expectedResult);
        Assert.assertEquals(result.get("buffer"), expectedStr);
    }

    /**
     * test calculation not only once
     *
     * @param engine         calculatorEngine instance
     * @param lineStr        input line
     * @param expectedStr    expected buffer
     * @param expectedResult expected execution result
     */
    private void testMore(CalculatorEngine engine, String lineStr, String expectedStr, int expectedResult) {
        HashMap<String, Object> result = engine.runForTest(lineStr);
        Assert.assertEquals((int) result.get("errorCode"), expectedResult);
        Assert.assertEquals(result.get("buffer"), expectedStr);
    }

    @Test
    public void test01() {
        test("1 2", "1 2", 0);
    }

    @Test
    public void test02() {
        test("4 2 3 5 4 + - * /", "-0.3333333333", 0);
    }

    @Test
    public void test03() {
        test("3 -", "3", 2);
    }

    @Test
    public void test04() {
        test("3 4 2 * 3 + 1 -", "3 10", 0);
    }

    @Test
    public void test05() {
        test("6 3 1 + 5 * undo +", "10", 0);
    }

    @Test
    public void test06() {
        test("3 4 + clear 1", "1", 0);
    }

    @Test
    public void test07() {
        test("2 3 undo", "2", 0);
    }

    @Test
    public void test08() {
        CalculatorEngine engine = new CalculatorEngine();
        testMore(engine, "6 5 4 3", "6 5 4 3", 0);
        testMore(engine, "undo undo *", "30", 0);
        testMore(engine, "10 *", "300", 0);
        testMore(engine, "undo", "30", 0);
    }
}
