package com.kutec.cleanarchitecture.feature.user;

import com.kutec.cleanarchitecture.feature.di.PerActivity;
import com.kutec.domain.common.excutor.PostExecutionThread;
import com.kutec.domain.common.excutor.ThreadExecutor;
import com.kutec.domain.common.interactor.UseCase;
import com.kutec.domain.user.GetUserDetails;
import com.kutec.domain.user.GetUserList;
import com.kutec.domain.user.UserRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */

@Module
public class UserModule {

    private int userId = -1;

    public UserModule() {
    }

    public UserModule(int userId) {
        this.userId = userId;
    }

    @Provides @PerActivity @Named("userList")
    public UseCase provideGetUserList(GetUserList getUserList) {
        return getUserList;
    }

    @Provides @PerActivity @Named("userDetails")
    public UseCase provideGetUserDetails(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetUserDetails(userId, userRepository, threadExecutor, postExecutionThread);
    }
}
