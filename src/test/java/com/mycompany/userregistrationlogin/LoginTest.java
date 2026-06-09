package com.mycompany.userregistrationlogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // ================= USERNAME TESTS =================
    @Test
    public void testValidUsername() {
        Login login = new Login();
        assertTrue(login.checkUsername("kyl_1"));
    }

    @Test
    public void testInvalidUsername() {
        Login login = new Login();
        assertFalse(login.checkUsername("kyle!!!!!"));
    }

    // ================= PASSWORD TESTS =================
    @Test
    public void testValidPassword() {
        Login login = new Login();
        assertTrue(login.checkPassword("Ch&&sec@ke99!"));
    }

    @Test
    public void testInvalidPassword() {
        Login login = new Login();
        assertFalse(login.checkPassword("password"));
    }

    // ================= PHONE TESTS =================
    @Test
    public void testValidPhone() {
        Login login = new Login();
        assertTrue(login.checkPhone("+27838968976"));
    }

    @Test
    public void testInvalidPhone() {
        Login login = new Login();
        assertFalse(login.checkPhone("08966553"));
    }

    // ================= REGISTER TESTS =================
    @Test
    public void testSuccessfulRegistration() {
        Login login = new Login();

        boolean result = login.register(
                "John",
                "Doe",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(result);
    }

    @Test
    public void testFailedRegistration() {
        Login login = new Login();

        boolean result = login.register(
                "",
                "",
                "invalid",
                "weak",
                "123"
        );

        assertFalse(result);
    }

    // ================= LOGIN TESTS =================
    @Test
    public void testSuccessfulLogin() {
        Login login = new Login();

        login.register(
                "John",
                "Doe",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testFailedLogin() {
        Login login = new Login();

        login.register(
                "John",
                "Doe",
                "ab_cd",
                "Password1!",
                "+27821234567"
        );

        assertFalse(login.loginUser("kyle!!!!!", "Password"));
    }
}