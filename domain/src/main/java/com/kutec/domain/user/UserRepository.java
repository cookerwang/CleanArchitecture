package com.kutec.domain.user;

import java.util.List;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public interface UserRepository {
    Observable<List<User>> users();

    Observable<User> user(final int userId);
}
