package com.kutec.cleanarchitecture.feature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.kutec.cleanarchitecture.app.AndroidApplication;
import com.kutec.cleanarchitecture.app.ApplicationComponent;
import com.kutec.cleanarchitecture.feature.di.ActivityModule;

import javax.inject.Inject;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Navigator navigator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

    }

    protected void addFragment(int containerId, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(containerId, fragment, fragment.getClass().getName())
                .commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
