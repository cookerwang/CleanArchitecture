package com.kutec.cleanarchitecture.feature.user.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.feature.BaseFragment;
import com.kutec.cleanarchitecture.feature.user.UserComponent;
import com.kutec.cleanarchitecture.feature.user.UserModel;
import com.kutec.cleanarchitecture.widget.AutoLoadImageView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public class UserDetailsFragment extends BaseFragment implements UserDetailsView {

    @Inject
    UserDetailsPresenter userDetailsPresenter;
    @Bind(R.id.iv_cover)
    AutoLoadImageView ivCover;
    @Bind(R.id.tv_fullname)
    TextView tvFullname;
    @Bind(R.id.tv_email)
    TextView tvEmail;
    @Bind(R.id.tv_followers)
    TextView tvFollowers;
    @Bind(R.id.tv_description)
    TextView tvDescription;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.bt_retry)
    Button btRetry;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;

    public UserDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            loadUserDetails();
        }
    }

    private void loadUserDetails() {
        if (userDetailsPresenter != null) {
            userDetailsPresenter.initialize();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userDetailsPresenter.resume();
    }


    @Override
    public void onPause() {
        super.onPause();
        userDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userDetailsPresenter.destroy();
        userDetailsPresenter = null;
    }

    @Override
    public void renderUser(UserModel user) {
        if (user != null) {
            ivCover.setImageUrl(user.getCoverUrl());
            tvFullname.setText(user.getFullName());
            tvEmail.setText(user.getEmail());
            tvFollowers.setText(String.valueOf(user.getFollowers()));
            tvDescription.setText(user.getDescription());
        }
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
        Snackbar.make(getView(), message+"", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @OnClick(R.id.bt_retry)
    public void onRetry() {
        if( userDetailsPresenter != null ) {
            userDetailsPresenter.getUserDetails();
        }
    }
}
