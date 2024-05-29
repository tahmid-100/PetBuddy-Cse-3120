package com.example.petbuddy;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SignUpInformationTest {
    private SignUpInformation signUpInformation;

    @Before
    public void setUp() {
        signUpInformation = new SignUpInformation("John Doe", "johndoe", "john.doe@example.com", "1234567890", "password123");
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", signUpInformation.getName());
    }

    @Test
    public void testSetName() {
        signUpInformation.setName("Jane Doe");
        assertEquals("Jane Doe", signUpInformation.getName());
    }

    @Test
    public void testGetUsername() {
        assertEquals("johndoe", signUpInformation.getUsername());
    }

    @Test
    public void testSetUsername() {
        signUpInformation.setUsername("janedoe");
        assertEquals("janedoe", signUpInformation.getUsername());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", signUpInformation.getEmail());
    }

    @Test
    public void testSetEmail() {
        signUpInformation.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", signUpInformation.getEmail());
    }

    @Test
    public void testGetPhone() {
        assertEquals("1234567890", signUpInformation.getPhone());
    }

    @Test
    public void testSetPhone() {
        signUpInformation.setPhone("0987654321");
        assertEquals("0987654321", signUpInformation.getPhone());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", signUpInformation.getPassword());
    }

    @Test
    public void testSetPassword() {
        signUpInformation.setPassword("newpassword123");
        assertEquals("newpassword123", signUpInformation.getPassword());
    }
}
