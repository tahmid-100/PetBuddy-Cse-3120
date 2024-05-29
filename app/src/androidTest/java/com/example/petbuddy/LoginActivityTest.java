package com.example.petbuddy;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<Login> activityRule =
            new ActivityScenarioRule<>(Login.class);

    @Test
    public void testGoButton() {
        Espresso.onView(ViewMatchers.withId(R.id.go_btn))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
    }

    @Test
    public void testSignupButton() {
        Espresso.onView(ViewMatchers.withId(R.id.signup))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
    }

    @Test
    public void testEmailEditText() {
        Espresso.onView(ViewMatchers.withId(R.id.emailEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("test@example.com"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testPasswordEditText() {
        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("password123"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testPhoneEditText() {
        Espresso.onView(ViewMatchers.withId(R.id.phoneEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("1234567890"), ViewActions.closeSoftKeyboard());
    }
}
