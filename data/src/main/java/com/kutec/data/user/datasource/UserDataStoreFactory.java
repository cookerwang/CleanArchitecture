package com.kutec.data.user.datasource;

import android.content.Context;

import com.kutec.data.common.repository.net.RestApi;
import com.kutec.data.common.repository.net.RestApiImpl;
import com.kutec.data.user.cache.UserCache;
import com.kutec.data.user.entity.mapper.UserEntityJsonMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
@Singleton
public class UserDataStoreFactory {
    private final Context context;
    private final UserCache userCache;

    @Inject
    public UserDataStoreFactory(Context context, UserCache userCache) {
        if( context == null || userCache == null ) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }

    public UserDataStore create(final int userId) {
        if( !userCache.isExpired() && userCache.isCached(userId) ) {
            return new DiskUserDataStore(userCache);
        }
        return createCloudDataStore();
    }

    public UserDataStore createCloudDataStore() {
        RestApi restApi = new RestApiImpl(context, new UserEntityJsonMapper());
        return  new CloudUserDataStore(restApi, userCache);
    }
}
