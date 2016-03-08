package com.kutec.cleanarchitecture.feature.user.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kutec.cleanarchitecture.R;
import com.kutec.cleanarchitecture.feature.di.PerActivity;
import com.kutec.cleanarchitecture.feature.user.UserModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
@PerActivity
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<UserModel> usersCollection;
    private final LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onUserItemClicked(UserModel userModel);
    }

    @Inject
    public UserListAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        usersCollection = Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.item_user_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserModel userModel = usersCollection.get(position);
        holder.textViewTitle.setText(userModel.getFullName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( onItemClickListener != null ) {
                    onItemClickListener.onUserItemClicked(userModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersCollection == null ? 0 : usersCollection.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView textViewTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setUsersList(Collection<UserModel> users) {
        if( users == null ) {
            throw new IllegalArgumentException("user list can't be null");
        }
        usersCollection = (List<UserModel>) users;
        notifyDataSetChanged();
    }
}
