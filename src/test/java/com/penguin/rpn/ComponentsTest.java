package com.penguin.rpn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Auther: Burt Hughes
 * @Date: 2019/3/4 22:39
 * @Description: rpn calculator's components
 */
public class ComponentsTest {
    
    private Components components = null;
    
    @Before
    public void initialize() {
        components = new Components();
    }
    
    @Test
    public void testIsValidComponent() {
        Assert.assertTrue(components.isValidComponent("+"));
        Assert.assertTrue(components.isValidComponent("-"));
        Assert.assertTrue(components.isValidComponent("*"));
        Assert.assertTrue(components.isValidComponent("/"));
        Assert.assertTrue(components.isValidComponent("undo"));
        Assert.assertTrue(components.isValidComponent("clear"));
        
        Assert.assertFalse(components.isValidComponent(""));
        Assert.assertFalse(components.isValidComponent("!"));
        Assert.assertFalse(components.isValidComponent("#"));
        Assert.assertFalse(components.isValidComponent(" "));
        Assert.assertFalse(components.isValidComponent(null));
    }
    
    @Test
    public void testIsValidOperator() {
        Assert.assertTrue(components.isValidOperator("+"));
        Assert.assertTrue(components.isValidOperator("-"));
        Assert.assertTrue(components.isValidOperator("*"));
        Assert.assertTrue(components.isValidOperator("/"));
        
        Assert.assertFalse(components.isValidOperator("undo"));
        Assert.assertFalse(components.isValidOperator("clear"));
        Assert.assertFalse(components.isValidOperator(""));
        Assert.assertFalse(components.isValidOperator("!"));
        Assert.assertFalse(components.isValidOperator("#"));
        Assert.assertFalse(components.isValidOperator(" "));
        Assert.assertFalse(components.isValidOperator(null));
    }
}
