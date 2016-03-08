package com.kutec.cleanarchitecture.feature.user.list;

import android.support.annotation.NonNull;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.kutec.cleanarchitecture.exception.ErrorMessageFactory;
import com.kutec.cleanarchitecture.feature.di.PerActivity;
import com.kutec.cleanarchitecture.feature.pv.Presenter;
import com.kutec.cleanarchitecture.feature.user.UserModel;
import com.kutec.cleanarchitecture.feature.user.UserModelDataMapper;
import com.kutec.domain.common.exception.DefaultErrorBundle;
import com.kutec.domain.common.interactor.DefaultSubscriber;
import com.kutec.domain.common.interactor.UseCase;
import com.kutec.domain.user.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@PerActivity
public class UserListPresenter implements Presenter {

    private UserListView userListView;

    private final UseCase getUserListUseCase;
    private final UserModelDataMapper userModelDataMapper;

    @Inject
    public UserListPresenter(@Named("userList") UseCase getUserListUseCase, UserModelDataMapper userModelDataMapper) {
        this.getUserListUseCase = getUserListUseCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull UserListView userListView) {
        this.userListView = userListView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getUserListUseCase.unsubscribe();
        userListView = null;
    }

    public void initialize() {
        loadUserList();
    }

    private void loadUserList() {
        userListView.hideRetry();
        userListView.showLoading();
        getUserList();
    }

    private void getUserList() {
        getUserListUseCase.execute(new UserListSubscriber());
    }

    public void onUserClicked(UserModel userModel) {
        userListView.viewUser(userModel);
    }

    private void showViewRetry() {
        userListView.showRetry();
    }

    private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
        String errorMessage = ErrorMessageFactory.create(userListView.context(), defaultErrorBundle.getException());
        userListView.showError(errorMessage);
    }

    private void hideViewLoading() {
        userListView.hideLoading();
    }

    private void showUsersCollectionInView(List<User> users) {
        userListView.renderUserList(userModelDataMapper.transform(users));
    }

    @RxLogSubscriber
    private class UserListSubscriber extends DefaultSubscriber<List<User>> {
        @Override
        public void onCompleted() {
            UserListPresenter.this.userListView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<User> users) {
            UserListPresenter.this.showUsersCollectionInView(users);
        }
    }

}
