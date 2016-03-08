package com.kutec.data.user.cache;

import com.kutec.data.user.entity.UserEntity;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public interface UserCache {

    Observable<UserEntity> get(final int userId);

    void put(UserEntity userEntity);

    boolean isCached(final int userid);

    boolean isExpired();

    void evictAll();
}
