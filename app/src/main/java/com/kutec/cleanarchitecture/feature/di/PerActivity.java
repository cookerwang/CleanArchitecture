package com.kutec.cleanarchitecture.feature.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
