package com.kutec.cleanarchitecture.test.user.list;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.feature.user.list.UserListActivity;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/8.
 */
public class UserListActivityTest extends ActivityInstrumentationTestCase2<UserListActivity> {

    private UserListActivity userListActivity;

    public UserListActivityTest() {
        super(UserListActivity.class);
    }

    public UserListActivityTest(Class<UserListActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityIntent(createTargetIntent());
        userListActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testContainsUserListFragment() {
        Fragment userListFragment = userListActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        MatcherAssert.assertThat(userListFragment, CoreMatchers.<Fragment>notNullValue());
    }

    @Test
    public void testContainsProperTitle() {
        String actualTitle = userListActivity.getTitle().toString().trim();
        MatcherAssert.assertThat(actualTitle, CoreMatchers.is("Users List"));
    }

    private Intent createTargetIntent() {
        return UserListActivity.getCallingIntent(getInstrumentation().getTargetContext());
    }


}
