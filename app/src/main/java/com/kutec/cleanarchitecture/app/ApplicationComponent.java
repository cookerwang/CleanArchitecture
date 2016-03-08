package com.kutec.cleanarchitecture.app;

import android.content.Context;

import com.kutec.cleanarchitecture.feature.BaseActivity;
import com.kutec.domain.common.excutor.PostExecutionThread;
import com.kutec.domain.common.excutor.ThreadExecutor;
import com.kutec.domain.user.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    UserRepository userRepository();

}
