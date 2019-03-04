package com.penguin.rpn;

import java.util.HashSet;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 20:39
 * @Description: rpn calculator's components
 */
public class Components {
    private HashSet<String> validOperators = new HashSet<>();
    private HashSet<String> validElements = new HashSet<>();

    public Components() {
        validOperators.add("+");
        validOperators.add("-");
        validOperators.add("*");
        validOperators.add("/");
        validElements.add("undo");
        validElements.add("clear");
        validElements.addAll(validOperators);
    }

    public boolean isValidComponent(String element) {
        return validElements.contains(element);
    }

    public boolean isValidOperator(String element) {
        return validOperators.contains(element);
    }
}
