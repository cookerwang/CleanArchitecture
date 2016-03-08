package com.kutec.cleanarchitecture.feature.user.detail;

import android.support.annotation.NonNull;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.kutec.cleanarchitecture.exception.ErrorMessageFactory;
import com.kutec.cleanarchitecture.feature.di.PerActivity;
import com.kutec.cleanarchitecture.feature.pv.Presenter;
import com.kutec.cleanarchitecture.feature.user.UserModelDataMapper;
import com.kutec.domain.common.exception.DefaultErrorBundle;
import com.kutec.domain.common.interactor.DefaultSubscriber;
import com.kutec.domain.common.interactor.UseCase;
import com.kutec.domain.user.User;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@PerActivity
public class UserDetailsPresenter implements Presenter {
    private UserDetailsView userDetailsView;

    private final UseCase getUserDetailsCase;
    private final UserModelDataMapper userModelDataMapper;
    private Object userDetails;

    @Inject
    public UserDetailsPresenter(@Named("userDetails") UseCase getUserDetailsCase, UserModelDataMapper userModelDataMapper) {
        this.getUserDetailsCase = getUserDetailsCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull UserDetailsView userDetailsView) {
        this.userDetailsView = userDetailsView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getUserDetailsCase.unsubscribe();
        userDetailsView = null;
    }

    public void initialize() {
        loadUserDetails();
    }

    private void loadUserDetails() {
        userDetailsView.showLoading();
        userDetailsView.hideRetry();
        getUserDetails();
    }

    public void getUserDetails() {
        getUserDetailsCase.execute(new GetUserDetailsCaseSubscriber());
    }

    private void showUserDetailsInView(User user) {
        userDetailsView.renderUser(userModelDataMapper.transform(user));
    }

    private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
        userDetailsView.showError(ErrorMessageFactory.create(userDetailsView.context(), defaultErrorBundle.getException()));
    }

    @RxLogSubscriber
    private class GetUserDetailsCaseSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onCompleted() {
            UserDetailsPresenter.this.userDetailsView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserDetailsPresenter.this.userDetailsView.hideLoading();
            UserDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserDetailsPresenter.this.userDetailsView.showRetry();
        }

        @Override
        public void onNext(User user) {
            UserDetailsPresenter.this.showUserDetailsInView(user);
        }
    }



}
