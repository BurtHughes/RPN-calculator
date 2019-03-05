package com.penguin.rpn;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 22:30
 * @Description: test Memory class
 */
public class MemoryTest {
    
    /**
     * test Memory.getBufferStr() method.
     */
    @Test
    public void getBufferStrTest() {
        Memory memory = new Memory();
        memory.addBuffer(1.2);
        memory.addBuffer(-1.5);
        memory.addBuffer(0.01);
        Assert.assertEquals("1.2 -1.5 0.01", memory.getBufferStr());
    }
}
