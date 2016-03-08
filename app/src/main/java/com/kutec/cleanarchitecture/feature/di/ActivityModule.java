package com.kutec.cleanarchitecture.feature.di;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@Module
public class ActivityModule {
    private AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides @PerActivity
    public AppCompatActivity provideCompatActivity() {
        return appCompatActivity;
    }
}
