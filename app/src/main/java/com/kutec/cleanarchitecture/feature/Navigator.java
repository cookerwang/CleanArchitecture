package com.kutec.cleanarchitecture.feature;

import android.content.Context;
import android.content.Intent;

import com.kutec.cleanarchitecture.feature.user.detail.UserDetailsActivity;
import com.kutec.cleanarchitecture.feature.user.list.UserListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@Singleton
public class Navigator {
    @Inject
    public Navigator() {
    }

    public void navigateToUserList(Context context) {
        if( context != null ) {
            Intent intentToLaunch = UserListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToUserDetails(Context context, final int userId) {
        if( context != null ) {
            Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId);
            context.startActivity(intentToLaunch);
        }
    }
}
