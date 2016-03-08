package com.kutec.data.user;

import com.kutec.data.user.datasource.UserDataStoreFactory;
import com.kutec.data.user.entity.mapper.UserEntityDataMapper;
import com.kutec.domain.user.User;
import com.kutec.domain.user.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    @Inject
    public UserRepositoryImpl(UserDataStoreFactory userDataStoreFactory, UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = userDataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }

    @Override
    public Observable<List<User>> users() {
        return userDataStoreFactory
                .createCloudDataStore()
                .userEntityList()
                .map(userEntities -> {
                   return userEntityDataMapper.transform(userEntities);
                });
    }

    @Override
    public Observable<User> user(int userId) {
        return userDataStoreFactory
                .create(userId)
                .userEntityDetailsById(userId)
                .map(userEntity -> {
                    return userEntityDataMapper.transform(userEntity);
                });
    }
}
