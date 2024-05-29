package com.example.petbuddy;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ProfileActivityTest {

    @Rule
    public ActivityScenarioRule<ProfileActivity> activityRule =
            new ActivityScenarioRule<>(ProfileActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testGoHomeButton() {
        Espresso.onView(ViewMatchers.withId(R.id.goHomeBtn))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(HomeActivity.class.getName()));
    }

    @Test
    public void testUpdateButton() {
        Espresso.onView(ViewMatchers.withId(R.id.update_btn))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("Update"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDeleteButton() {
        Espresso.onView(ViewMatchers.withId(R.id.deleteBtn))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(Login.class.getName()));
    }

    @Test
    public void testFullNameTextView() {
        Espresso.onView(ViewMatchers.withId(R.id.fullnameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testUsernameTextView() {
        Espresso.onView(ViewMatchers.withId(R.id.usernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testEmailTextView() {
        Espresso.onView(ViewMatchers.withId(R.id.emailEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testPhoneTextView() {
        Espresso.onView(ViewMatchers.withId(R.id.phonenumEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testPasswordTextView() {
        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
