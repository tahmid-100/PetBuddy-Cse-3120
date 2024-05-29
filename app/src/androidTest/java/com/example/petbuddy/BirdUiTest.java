package com.example.petbuddy;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.regex.Pattern.matches;

@RunWith(AndroidJUnit4.class)
public class BirdUiTest {

    @Rule
    public ActivityScenarioRule<Bird> activityScenarioRule =
            new ActivityScenarioRule<>(Bird.class);

    @Test
    public void testScrollViewIsDisplayed() {
        // Check if the ScrollView is displayed
        onView(withId(android.R.id.content))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testBirdsTextDisplayed() {
        // Check if the "Birds" text is displayed in the breedContainer LinearLayout
        onView(ViewMatchers.withId(R.id.breedContainer))
                .check(ViewAssertions.matches(isDisplayed()));

        onView(withText("Birds"))
                .check(ViewAssertions.matches(isDisplayed()));
    }

}

