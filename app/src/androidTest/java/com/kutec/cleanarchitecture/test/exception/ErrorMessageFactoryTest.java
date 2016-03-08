package com.kutec.cleanarchitecture.test.exception;

import android.test.AndroidTestCase;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.exception.ErrorMessageFactory;
import com.kutec.data.common.repository.net.NetworkConnectionException;
import com.kutec.data.user.UserNotFoundException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/8.
 */
public class ErrorMessageFactoryTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testNetworkConnectionErrorMessage() {
        String expectedMessage = getContext().getString(R.string.exception_message_no_connection);
        String actualMessage = ErrorMessageFactory.create(getContext(), new NetworkConnectionException());
        Assert.assertThat(actualMessage, CoreMatchers.equalTo(expectedMessage));
    }

    public void testUserNotFoundErrorMessage() {
        String expectedMessage = getContext().getString(R.string.exception_message_user_not_found);
        String actualMessage = ErrorMessageFactory.create(getContext(), new UserNotFoundException());
        Assert.assertThat(actualMessage, CoreMatchers.equalTo(expectedMessage));
    }
}
