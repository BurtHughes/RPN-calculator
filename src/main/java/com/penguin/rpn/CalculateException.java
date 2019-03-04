package com.penguin.rpn;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/2/28 23:03
 * @Description: calculate exception
 */
public class CalculateException extends RuntimeException {
    public CalculateException(String message) {
        super(message);
    }
}
