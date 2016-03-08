package com.kutec.data.common.repository.net;

import com.kutec.data.user.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public interface RestApi {
    String API_BASE_URL = "http://www.android10.org/myapi/";
    /** Api url for getting all users */
    String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";
    /** Api url for getting a user profile: Remember to concatenate id + 'json' */
    String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntityById(final int userId);
}
