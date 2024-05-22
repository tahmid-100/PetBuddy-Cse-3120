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
