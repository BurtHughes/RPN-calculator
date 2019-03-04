package com.penguin.rpn;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        RpnCalculator calculator = new RpnCalculator();
        List<String> line = new ArrayList<>(Arrays.asList(lineStr.split(" ")));
        calculator.setLine(line);
        int result = calculator.execute();
        Assert.assertTrue(result == expectedResult);
        Assert.assertTrue(calculator.getBufferString().equals(expectedStr));
    }

    /**
     * test calculation not only once
     *
     * @param calculator     calculator instance
     * @param lineStr        input line
     * @param expectedStr    expected buffer
     * @param expectedResult expected execution result
     */
    private void testMore(RpnCalculator calculator, String lineStr, String expectedStr, int expectedResult) {
        List<String> line = new ArrayList<>(Arrays.asList(lineStr.split(" ")));
        calculator.setLine(line);
        int result = calculator.execute();
        Assert.assertTrue(result == expectedResult);
        Assert.assertTrue(calculator.getBufferString().equals(expectedStr));
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
        RpnCalculator calculator = new RpnCalculator();
        testMore(calculator, "6 5 4 3", "6 5 4 3", 0);
        testMore(calculator, "undo undo *", "30", 0);
        testMore(calculator, "10 *", "300", 0);
        testMore(calculator, "undo", "30", 0);
    }
}
