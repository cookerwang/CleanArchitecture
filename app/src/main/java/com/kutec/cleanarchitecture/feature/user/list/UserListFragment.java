package com.kutec.cleanarchitecture.feature.user.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.feature.BaseFragment;
import com.kutec.cleanarchitecture.feature.user.UserComponent;
import com.kutec.cleanarchitecture.feature.user.UserModel;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public class UserListFragment extends BaseFragment implements UserListView {
    @Inject
    UserListPresenter userListPresenter;
    @Inject
    UserListAdapter userListAdapter;

    @Bind(R.id.rv_users)
    RecyclerView rvUsers;
    @Bind(R.id.bt_retry)
    Button btRetry;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;

    private UserListAdapter.OnItemClickListener onItemClickListener = new UserListAdapter.OnItemClickListener() {
        @Override
        public void onUserItemClicked(UserModel userModel) {
            if (UserListFragment.this.userListPresenter != null && userModel != null) {
                UserListFragment.this.userListPresenter.onUserClicked(userModel);
            }
        }
    };

    /**
     * Interface for listening user list events.
     */
    public interface UserListListener {
        void onUserClicked(final UserModel userModel);
    }

    private UserListListener userListListener;

    public UserListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserListListener) {
            userListListener = (UserListListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userListPresenter.setView(this);
        if (savedInstanceState == null) {
            loadUserList();
        }
    }

    private void loadUserList() {
        userListPresenter.initialize();
    }

    private void setupRecyclerView() {
        userListAdapter.setOnItemClickListener(onItemClickListener);
        rvUsers.setHasFixedSize(true);
        rvUsers.setLayoutManager(new LinearLayoutManager(context()));
        rvUsers.setAdapter(userListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        userListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        userListPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        userListPresenter = null;
    }

    @Override
    public void renderUserList(Collection<UserModel> userModelCollection) {
        userListAdapter.setUsersList(userModelCollection);
    }

    @Override
    public void viewUser(UserModel userModel) {
        if( userListListener != null ) {
            userListListener.onUserClicked(userModel);
        }
    }

    @OnClick(R.id.bt_retry)
    public void onRetry() {
        loadUserList();
    }

    @Override
    public void showLoading() {
        rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rlProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        rlRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        rlRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
}
