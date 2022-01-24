package com.example.task.ecommerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.task.ecommerce.utils.Validator;

import org.junit.Before;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ProductListTestCase {
    Validator validator

    @Before
    public void setUp() {
        validator = new Validator();
    }

    @Test
    public void checkValidProductAddedToCart() {

        assertTrue(validator.isValidateItemAddToCart("10457"));
    }

    @Test
    public void checkInValidProductAddedToCart() throws Exception {

        assertFalse(validator.isValidateItemAddToCart("0000"));
    }

    @Test
    public void checkEqualProductAddedToCart() throws Exception {

        assertEquals("10457", validator.isEqualItemAddToCart("10457") );
    }
}