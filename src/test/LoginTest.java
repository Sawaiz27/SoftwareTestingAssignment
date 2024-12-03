package test;

import loginapp.LoginApp;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

class LoginTest {
    private static final String DB_URL = "jdbc:mysql://mysql-1e7edf9b-lhr-b3a4.e.aivencloud.com:25416/softwaretesting";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sawaiz";

    // Instance of LoginApp (assuming it has a working connection to the database)
    private LoginApp loginApp = new LoginApp();

    // Test case for successful login with valid email and password
    @Test
    public void testLoginSuccessWithValidEmailAndPassword() {
        String email = "johndoe@example.com";
        String password = "password123";  // Email and password both are provided, expected to succeed if both are correct
        String userName = loginApp.authenticateUser(email, password);  // Pass password to authenticateUser()

        assertNotNull(userName);  // We expect the login to succeed if the credentials are correct
        assertEquals("John Doe", userName);  // This is the expected username, assuming correct credentials
    }

    // Test case for login with email containing special characters or numbers
    @Test
    public void testLoginWithSpecialCharactersOrNumbersInEmail() {
        String email = "bob.clark+123@example.com";
        String password = "password123";  // Special characters in email; we expect it to work if email is valid
        String userName = loginApp.authenticateUser(email, password);  // Pass password to authenticateUser()

        assertNotNull(userName);  // If the email is valid, it should return a username
        assertEquals("Bob Clark", userName);  // Assuming this email maps to the correct user
    }

    // Test case for login with invalid email format
    @Test
    public void testLoginWithInvalidEmailFormat() {
        String email = "invalidemail.com";  // Invalid email format should cause a failure
        String password = "anyPassword";  // Password shouldn't matter as the email is invalid
        String userName = loginApp.authenticateUser(email, password);  // Pass password to authenticateUser()

        assertNull(userName);  // Invalid email format should return null, assuming proper validation
    }

    // Test case for login failure with empty email field
    @Test
    public void testLoginFailureWithEmptyEmail() {
        String email = "";  // Empty email
        String password = "password123";
        String userName = loginApp.authenticateUser(email, password);  // Pass password to authenticateUser()

        assertNull(userName);  // Empty email should return null
    }

    // Test case for login with valid email but incorrect password
    @Test
    public void testLoginWithValidEmailButIncorrectPassword() {
        String email = "johndoe@example.com";
        String password = "incorrectpassword";  // Providing a correct email but incorrect password
        String userName = loginApp.authenticateUser(email, password);  // Pass password to authenticateUser()

        assertNull(userName);  // The login should fail if the password is incorrect (assuming password validation is in place)
    }
}
