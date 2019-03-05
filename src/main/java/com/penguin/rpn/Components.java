package com.penguin.rpn;

import java.util.HashSet;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 20:39
 * @Description: rpn calculator's components
 */
public class Components {
    private HashSet<String> validOperators = new HashSet<>();
    private HashSet<String> validComponents = new HashSet<>();

    public Components() {
        // initialize validOperators and validComponents
        validOperators.add("+");
        validOperators.add("-");
        validOperators.add("*");
        validOperators.add("/");
        validComponents.add("undo");
        validComponents.add("clear");
        validComponents.addAll(validOperators);
    }
    
    /**
     * check if the parameter is valid component.
     * @param element target component
     * @return check result
     */
    public boolean isValidComponent(String element) {
        return validComponents.contains(element);
    }
    
    /**
     * check if the parameter is valid operator.
     * @param element target operator
     * @return check result
     */
    public boolean isValidOperator(String element) {
        return validOperators.contains(element);
    }
}
