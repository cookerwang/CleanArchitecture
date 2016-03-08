package com.kutec.data.user.datasource;

import com.kutec.data.user.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public interface UserDataStore {

    Observable<UserEntity> userEntityDetailsById(final int userId);

    Observable<List<UserEntity>> userEntityList();
}
