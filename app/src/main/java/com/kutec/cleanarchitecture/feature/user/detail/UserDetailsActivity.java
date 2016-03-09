package com.kutec.cleanarchitecture.feature.user.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.feature.BaseActivity;
import com.kutec.cleanarchitecture.feature.di.HasComponent;
import com.kutec.cleanarchitecture.feature.user.DaggerUserComponent;
import com.kutec.cleanarchitecture.feature.user.UserComponent;
import com.kutec.cleanarchitecture.feature.user.UserModule;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public class UserDetailsActivity extends BaseActivity implements HasComponent<UserComponent>{
    private static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";

    private UserComponent userComponent;
    private int userId = -1;

    public static Intent getCallingIntent(Context context, int userId) {
        Intent callingIntent = new Intent(context, UserDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
        return callingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        initializeActivity(savedInstanceState);
        initializeInjector();
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if( savedInstanceState == null ) {
            userId =getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
            addFragment(R.id.fragmentContainer, new UserDetailsFragment());
        } else {
            userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(INSTANCE_STATE_PARAM_USER_ID, this.userId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initializeInjector() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule(userId))
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
