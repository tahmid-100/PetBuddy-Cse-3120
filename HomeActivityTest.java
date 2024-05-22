import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule =
            new ActivityScenarioRule<>(HomeActivity.class);

    @Rule
    public IntentsTestRule<HomeActivity> intentsTestRule =
            new IntentsTestRule<>(HomeActivity.class);

    @Test
    public void testCardViewDisplayed() {
        onView(withId(R.id.cardView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testImageSliderDisplayed() {
        onView(withId(R.id.imageSlider))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewDisplayed() {
        onView(withText("Choose Your Catagorey"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testImageButton1Clicked() {
        onView(withId(R.id.image_btn1))
                .perform(click());
        // Optionally add an assertion to verify the next activity is displayed
    }

    @Test
    public void testImageButton2Clicked() {
        onView(withId(R.id.image_btn2))
                .perform(click());
        // Optionally add an assertion to verify the next activity is displayed
    }

    @Test
    public void testImageButton3Clicked() {
        onView(withId(R.id.image_btn3))
                .perform(click());
        Intents.intended(IntentMatchers.hasComponent(Bird.class.getName()));
    }
}
