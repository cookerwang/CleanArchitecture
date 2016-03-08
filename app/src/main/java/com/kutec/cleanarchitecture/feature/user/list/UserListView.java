package com.kutec.cleanarchitecture.feature.user.list;

import com.kutec.cleanarchitecture.feature.pv.LoadDataView;
import com.kutec.cleanarchitecture.feature.user.UserModel;

import java.util.Collection;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public interface UserListView extends LoadDataView {
    /**
     * Render a user list in the UI.
     *
     * @param userModelCollection The collection of {@link UserModel} that will be shown.
     */
    void renderUserList(Collection<UserModel> userModelCollection);

    /**
     * View a {@link UserModel} profile/details.
     *
     * @param userModel The user that will be shown.
     */
    void viewUser(UserModel userModel);
}
