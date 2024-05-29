package com.example.petbuddy;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class LoginHelperTest {
    private FirebaseAuth mAuthMock;
    private DatabaseReference mDatabaseMock;
    private LoginHelper loginHelper;

    @Before
    public void setUp() {
        mAuthMock = mock(FirebaseAuth.class);
        mDatabaseMock = mock(DatabaseReference.class);

        // Mock the behavior of Patterns.EMAIL_ADDRESS
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

        loginHelper = new LoginHelper(mAuthMock, mDatabaseMock, emailPattern);
    }

    @Test
    public void testIsEmailValid_ValidEmail_ReturnsTrue() {
        assertTrue(loginHelper.isEmailValid("test@example.com"));
    }

    @Test
    public void testIsEmailValid_InvalidEmail_ReturnsFalse() {
        assertFalse(loginHelper.isEmailValid("invalid_email"));
    }

    @Test
    public void testIsPasswordValid_ValidPassword_ReturnsTrue() {
        assertTrue(loginHelper.isPasswordValid("password"));
    }

    @Test
    public void testIsPasswordValid_PasswordLengthLessThanSix_ReturnsFalse() {
        assertFalse(loginHelper.isPasswordValid("pass"));
    }

    @Test
    public void testSignIn_Successful() {
        // Mock task result
        Task<AuthResult> mockTask = mock(Task.class);
        when(mockTask.isSuccessful()).thenReturn(true);

        // Mock signInWithEmailAndPassword method
        when(mAuthMock.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mockTask);

        // Perform sign in
        loginHelper.signIn("test@example.com", "password", new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                assertTrue(task.isSuccessful());
            }
        });
    }


}
