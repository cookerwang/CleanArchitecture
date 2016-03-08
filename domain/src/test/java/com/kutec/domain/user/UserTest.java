package com.kutec.domain.user;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public class UserTest {
    private final int FAKE_USER_ID = 8;
    private User user;
    @Before
    public void setUp() {
        user = new User(FAKE_USER_ID);
    }
    @Test
    public void testUserConstructHappyCase() {
        Assert.assertThat(user.getUserId(), Is.is(FAKE_USER_ID));
    }
}
