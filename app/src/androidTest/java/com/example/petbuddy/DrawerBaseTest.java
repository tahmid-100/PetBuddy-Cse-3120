package com.example.petbuddy;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DrawerBaseTest {

    @Rule
    public ActivityScenarioRule<DrawerBase> activityScenarioRule =
            new ActivityScenarioRule<>(DrawerBase.class);

    @Test
    public void testDrawerIsDisplayed() {
        // Check if DrawerLayout is displayed
        Espresso.onView(withId(R.id.drawer_layout))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testNavigationViewIsDisplayed() {
        // Open navigation drawer
        Espresso.onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open());

        // Check if NavigationView is displayed
        Espresso.onView(withId(R.id.nav_view))
                .check(matches(isDisplayed()));
    }
    @Test
    public void testHomeItemClicked() {
        // Open navigation drawer
        Espresso.onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open());

        // Click on the "Home" item
        Espresso.onView(ViewMatchers.withText("Home"))
                .perform(click());


    }
    @Test
    public void testProfileItemClicked() {
        // Open navigation drawer
        Espresso.onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open());

        // Click on the "Profile" item
        Espresso.onView(ViewMatchers.withText("Profile"))
                .perform(click());


    }

    // You can add more tests for specific UI interactions as needed
}
