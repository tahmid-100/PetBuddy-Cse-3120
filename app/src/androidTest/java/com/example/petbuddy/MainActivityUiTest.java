package com.example.petbuddy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityUiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void splashScreenElementsDisplayed() {
        // Check if the ImageView is displayed
        onView(withId(R.id.img)).check(matches(isDisplayed()));
        // Check if the TextView is displayed and has the correct text
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
        onView(withId(R.id.textView3)).check(matches(withText("Friends Forever")));
    }

    @Test
    public void navigateToLoginScreen() {
        // Wait for the splash screen duration
        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Check if the Login activity is displayed
        onView(withId(R.id.login_layout)).check(matches(isDisplayed()));
    }
}
