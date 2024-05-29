package com.example.petbuddy;


import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationHelperTest {

    @Test
    public void testValidEmail() {
        assertTrue(ValidationHelper.isValidEmail("test@example.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(ValidationHelper.isValidEmail("invalid.email"));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(ValidationHelper.isValidEmail(""));
    }

    @Test
    public void testNullEmail() {
        assertFalse(ValidationHelper.isValidEmail(null));
    }
}

