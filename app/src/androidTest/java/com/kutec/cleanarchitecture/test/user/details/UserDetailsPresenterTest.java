package com.kutec.cleanarchitecture.test.user.details;

import com.kutec.cleanarchitecture.feature.user.UserModelDataMapper;
import com.kutec.cleanarchitecture.feature.user.detail.UserDetailsPresenter;
import com.kutec.cleanarchitecture.feature.user.detail.UserDetailsView;
import com.kutec.domain.user.GetUserDetails;

import junit.framework.TestCase;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/8.
 */
public class UserDetailsPresenterTest extends TestCase {

    private UserDetailsPresenter userDetailsPresenter;

    @Mock
    GetUserDetails mockGetUserDetailsCase;
    @Mock
    UserModelDataMapper mockUserModelDataMapper;
    @Mock
    UserDetailsView mockUserDetailsView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        userDetailsPresenter = new UserDetailsPresenter(mockGetUserDetailsCase, mockUserModelDataMapper);
        userDetailsPresenter.setView(mockUserDetailsView);
    }

    public void testUserDetailsPresenterInitialize() {
        userDetailsPresenter.initialize();

        Mockito.verify(mockGetUserDetailsCase).execute(Mockito.any(Subscriber.class));
        Mockito.verify(mockUserDetailsView).hideRetry();
        Mockito.verify(mockUserDetailsView).showLoading();
    }
}
