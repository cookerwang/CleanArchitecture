package com.kutec.cleanarchitecture.test.user.details;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.feature.user.detail.UserDetailsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/8.
 */
public class UserDetailsActivityTest extends ActivityInstrumentationTestCase2<UserDetailsActivity> {
    private static final int FAKE_USER_ID = 10;
    private UserDetailsActivity userDetailsActivity;

    public UserDetailsActivityTest() {
        super(UserDetailsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityIntent(createTargetIntent());
        userDetailsActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private Intent createTargetIntent() {
        return UserDetailsActivity.getCallingIntent(getInstrumentation().getTargetContext(), FAKE_USER_ID);
    }

    public void testContainsUserDetailsFragment() {
        Fragment userDetailsFragment =
                userDetailsActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertThat(userDetailsFragment, is(notNullValue()));
    }

    public void testContainsProperTitle() {
        String actualTitle = userDetailsActivity.getTitle().toString().trim();
        assertThat(actualTitle, is("User Details"));
    }

    public void testLoadUserHappyCaseViews() {
        onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

        onView(withId(R.id.tv_fullname)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_email)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    public void testLoadUserHappyCaseData() {
        onView(withId(R.id.tv_fullname)).check(matches(withText("John Sanchez")));
        onView(withId(R.id.tv_email)).check(matches(withText("dmedina@katz.edu")));
        onView(withId(R.id.tv_followers)).check(matches(withText("4523")));
    }

}
