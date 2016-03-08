package com.kutec.cleanarchitecture.feature.di;

import com.kutec.cleanarchitecture.app.ApplicationComponent;

import dagger.Component;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
}
