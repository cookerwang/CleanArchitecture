package com.kutec.data.user;

import com.kutec.data.ApplicationCaseTest;
import com.kutec.data.common.repository.net.RestApi;
import com.kutec.data.user.cache.UserCache;
import com.kutec.data.user.datasource.CloudUserDataStore;
import com.kutec.data.user.entity.UserEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class CloudUserDataStoreTest extends ApplicationCaseTest {
    private static final int FAKE_USER_ID = 765;
    private CloudUserDataStore cloudUserDataStore;
    @Mock
    private RestApi mockRestApi;
    @Mock
    private UserCache mockUserCache;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cloudUserDataStore = new CloudUserDataStore(mockRestApi, mockUserCache);
    }

    @Test
    public void testUserEntityList() {
        cloudUserDataStore.userEntityList();
        Mockito.verify(mockRestApi).userEntityList();
    }

    @Test
    public void testUserEntityDetailsById() {
        UserEntity fakeUserEntity = new UserEntity();
        Observable<UserEntity> fakeObservable = Observable.just(fakeUserEntity);
        Mockito.when(mockRestApi.userEntityById(FAKE_USER_ID)).thenReturn(fakeObservable);
        //BDDMockito.given(mockRestApi.userEntityById(FAKE_USER_ID)).willReturn(fakeObservable);
        cloudUserDataStore.userEntityDetailsById(FAKE_USER_ID);
        Mockito.verify(mockRestApi).userEntityById(FAKE_USER_ID);
    }
}
