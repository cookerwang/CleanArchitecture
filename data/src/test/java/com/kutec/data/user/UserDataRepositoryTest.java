package com.kutec.data.user;

import com.kutec.data.ApplicationCaseTest;
import com.kutec.data.user.datasource.CloudUserDataStore;
import com.kutec.data.user.datasource.UserDataStore;
import com.kutec.data.user.datasource.UserDataStoreFactory;
import com.kutec.data.user.entity.UserEntity;
import com.kutec.data.user.entity.mapper.UserEntityDataMapper;
import com.kutec.domain.user.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class UserDataRepositoryTest extends ApplicationCaseTest {

    private static final int FAKE_USER_ID = 123;

    private UserRepository userRepository;
    @Mock private UserDataStoreFactory mockUserDataStoreFactory;
    @Mock private UserEntityDataMapper mockUserEntityDataMapper;
    @Mock private UserDataStore mockUserDataStore;
    @Mock private CloudUserDataStore mockCloudDataStore;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userRepository = new UserRepositoryImpl(mockUserDataStoreFactory, mockUserEntityDataMapper);
        Mockito.when(mockUserDataStoreFactory.create(FAKE_USER_ID)).thenReturn(mockUserDataStore);
        Mockito.when(mockUserDataStoreFactory.createCloudDataStore()).thenReturn(mockCloudDataStore);
    }

    @Test
    public void testUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity());
        userEntities.add(new UserEntity());
        Mockito.when(mockCloudDataStore.userEntityList()).thenReturn(Observable.just(userEntities));
        userRepository.users();

        Mockito.verify(mockUserDataStoreFactory).createCloudDataStore();
        Mockito.verify(mockCloudDataStore).userEntityList();
    }

    @Test
    public void testUserById() {
        UserEntity fakeUserEntity = new UserEntity();
        Mockito.when(mockUserDataStore.userEntityDetailsById(FAKE_USER_ID)).thenReturn(Observable.just(fakeUserEntity));
        userRepository.user(FAKE_USER_ID);
        Mockito.verify(mockUserDataStoreFactory).create(FAKE_USER_ID);
        Mockito.verify(mockUserDataStore).userEntityDetailsById(FAKE_USER_ID);

    }
}
