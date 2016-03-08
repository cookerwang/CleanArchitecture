package com.kutec.data.user.datasource;

import com.kutec.data.user.cache.UserCache;
import com.kutec.data.user.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class DiskUserDataStore implements UserDataStore {

    private final UserCache userCache;

    public DiskUserDataStore(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<UserEntity> userEntityDetailsById(int userId) {
        return userCache.get(userId);
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
}
