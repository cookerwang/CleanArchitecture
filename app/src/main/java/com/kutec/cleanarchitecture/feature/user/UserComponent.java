package com.kutec.cleanarchitecture.feature.user;

import com.kutec.cleanarchitecture.app.ApplicationComponent;
import com.kutec.cleanarchitecture.feature.di.ActivityModule;
import com.kutec.cleanarchitecture.feature.di.PerActivity;
import com.kutec.cleanarchitecture.feature.user.detail.UserDetailsFragment;
import com.kutec.cleanarchitecture.feature.user.list.UserListFragment;

import dagger.Component;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@PerActivity
@Component(modules = {ActivityModule.class, UserModule.class}, dependencies = ApplicationComponent.class)
public interface UserComponent {
    void inject(UserListFragment userListFragment);
    void inject(UserDetailsFragment userDetailsFragment);
}
