package com.penguin.rpn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 20:02
 * @Description: calculator's memory to save calculation data.
 */
public class Memory {
    private List<Double> buffer = new ArrayList<>();//calculator cache
    private List<String> line = new ArrayList<>();//input line
}
