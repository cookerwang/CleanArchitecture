package com.kutec.cleanarchitecture.app;

import android.content.Context;

import com.kutec.data.common.cache.serializer.JsonSerializer;
import com.kutec.data.common.excutor.JobExecutor;
import com.kutec.data.user.UserRepositoryImpl;
import com.kutec.data.user.cache.UserCache;
import com.kutec.data.user.cache.UserCacheImpl;
import com.kutec.data.user.entity.UserEntity;
import com.kutec.domain.common.excutor.PostExecutionThread;
import com.kutec.domain.common.excutor.ThreadExecutor;
import com.kutec.domain.user.UserRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides @Singleton
    public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    public PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Named("userEntity")
    public JsonSerializer<UserEntity> provideJsonSerializer() {
        return new JsonSerializer<UserEntity>();
    }

    @Provides @Singleton
    public UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides @Singleton
    public UserRepository provideUserRepository(UserRepositoryImpl userRepository) {
        return userRepository;
    }
}
