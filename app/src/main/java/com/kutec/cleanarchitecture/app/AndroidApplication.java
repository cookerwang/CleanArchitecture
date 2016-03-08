package com.kutec.cleanarchitecture.app;

import android.app.Application;
import android.util.Log;

import com.kutec.cleanarchitecture.BuildConfig;
import com.squareup.leakcanary.LeakCanary;


/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Log.i("test", "error:" + ex.getMessage());
                for(StackTraceElement es : ex.getStackTrace() ) {
                    Log.i("test", es.toString());
                }
            }
        });
    }

    private void initializeLeakDetection() {
        if( BuildConfig.DEBUG ) {
            LeakCanary.install(this);
        }
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
