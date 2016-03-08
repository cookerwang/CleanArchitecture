package com.kutec.cleanarchitecture.feature.user.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.feature.BaseActivity;
import com.kutec.cleanarchitecture.feature.di.HasComponent;
import com.kutec.cleanarchitecture.feature.user.DaggerUserComponent;
import com.kutec.cleanarchitecture.feature.user.UserComponent;
import com.kutec.cleanarchitecture.feature.user.UserModel;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public class UserListActivity extends BaseActivity implements HasComponent<UserComponent>, UserListFragment.UserListListener {

    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserListActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        initializeInjector();
        if( savedInstanceState == null ) {
            addFragment(R.id.fragmentContainer, new UserListFragment());
        }
    }

    private void initializeInjector() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    @Override
    public void onUserClicked(UserModel userModel) {
        navigator.navigateToUserDetails(this, userModel.getUserId());
    }
}
