package com.kutec.data.user.datasource;

import com.kutec.data.common.repository.net.RestApi;
import com.kutec.data.user.cache.UserCache;
import com.kutec.data.user.entity.UserEntity;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class CloudUserDataStore implements UserDataStore {

    private final RestApi restApi;
    private final UserCache userCache;

    private Action1<UserEntity> saveToCacheAction = userEntity -> {
        if( userEntity != null ) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    public CloudUserDataStore(RestApi restApi, UserCache userCache) {
        this.restApi = restApi;
        this.userCache = userCache;
    }

    @Override
    public Observable<UserEntity> userEntityDetailsById(int userId) {
        return restApi.userEntityById(userId).doOnNext(saveToCacheAction);
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return restApi.userEntityList();
    }
}
