package com.example.petbuddy;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class SignUpActivityTest {
    @Rule
    public ActivityScenarioRule<SignUp> activityRule = new ActivityScenarioRule<>(SignUp.class);



    @Test
    public void testSignupButton() {
        Espresso.onView(ViewMatchers.withId(R.id.signup_btn))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
    }

    @Test
    public void testSigninButton() {
        Espresso.onView(ViewMatchers.withId(R.id.signin_btn))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(Login.class.getName()));
    }

    @Test
    public void testPhonenumInput() {
        Espresso.onView(ViewMatchers.withId(R.id.phonenum))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("1234567890"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testUsernameInput() {
        Espresso.onView(ViewMatchers.withId(R.id.username))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("testuser"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testFullnameInput() {
        Espresso.onView(ViewMatchers.withId(R.id.fullname))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("Test User"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testEmailInput() {
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("test@example.com"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testPasswordInput() {
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("password123"), ViewActions.closeSoftKeyboard());
    }
}
