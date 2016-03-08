package com.kutec.cleanarchitecture.feature.user.detail;

import com.kutec.cleanarchitecture.feature.pv.LoadDataView;
import com.kutec.cleanarchitecture.feature.user.UserModel;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public interface UserDetailsView extends LoadDataView {
    void renderUser(UserModel user);
}

