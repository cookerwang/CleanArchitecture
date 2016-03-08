package com.kutec.cleanarchitecture.test.user.list;

import com.kutec.cleanarchitecture.feature.user.UserModelDataMapper;
import com.kutec.cleanarchitecture.feature.user.list.UserListPresenter;
import com.kutec.cleanarchitecture.feature.user.list.UserListView;
import com.kutec.domain.common.interactor.UseCase;

import junit.framework.TestCase;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/8.
 */
public class UserListPresenterTest extends TestCase {

    private UserListPresenter userListPresenter;
    @Mock
    UseCase mockGetUserListUseCase;
    @Mock
    UserModelDataMapper mockUserModelMapper;
    @Mock
    UserListView mockUserListView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        userListPresenter = new UserListPresenter(mockGetUserListUseCase, mockUserModelMapper);
        userListPresenter.setView(mockUserListView);
    }


    public void testUserListPresenterInitialize() {

        userListPresenter.initialize();

        Mockito.verify(mockGetUserListUseCase).execute(Mockito.any(Subscriber.class));
        Mockito.verify(mockUserListView).hideRetry();
        Mockito.verify(mockUserListView).showLoading();

    }

}
