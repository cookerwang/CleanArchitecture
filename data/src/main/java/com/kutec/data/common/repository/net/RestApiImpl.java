package com.kutec.data.common.repository.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.kutec.data.user.entity.UserEntity;
import com.kutec.data.user.entity.mapper.UserEntityJsonMapper;

import java.net.MalformedURLException;
import java.util.List;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class RestApiImpl implements RestApi {

    private final Context context;
    private final UserEntityJsonMapper userEntityJsonMapper;

    public RestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        if( context == null || userEntityJsonMapper == null ) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return Observable.create(subscriber -> {
            if( isThereInternetConnection() ) {
                try {
                    String userEntityListFromApi = getUserEntityListFromAapi();
                    if( !subscriber.isUnsubscribed() ) {
                        if (userEntityListFromApi != null) {
                            subscriber.onNext(userEntityJsonMapper.transformUserEntityCollection(userEntityListFromApi));
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    }
                } catch (MalformedURLException e) {
                    if( !subscriber.isUnsubscribed() ) {
                        subscriber.onError(new NetworkConnectionException(e.getMessage()));
                    }
                }
            } else {
                if( !subscriber.isUnsubscribed() ) {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    @Override
    public Observable<UserEntity> userEntityById(final int userId) {
        return Observable.create( subscriber -> {
            if( isThereInternetConnection() ) {
                try {
                    String userDetailsFromApi = getUserDetailsFromApi(userId);
                    if( !subscriber.isUnsubscribed() ) {
                        if( userDetailsFromApi != null ) {
                            subscriber.onNext(userEntityJsonMapper.transformUserEntity(userDetailsFromApi));
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    }
                } catch (MalformedURLException e) {
                    if( !subscriber.isUnsubscribed() ) {
                        subscriber.onError(new NetworkConnectionException(e.getMessage()));
                    }
                }
            } else {
                if( !subscriber.isUnsubscribed() ) {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    private String getUserDetailsFromApi(int userId) throws MalformedURLException {
        String url = RestApi.API_URL_GET_USER_DETAILS + userId + ".json";
        return ApiConnection.createGET(url).requestSyncCall();
    }

    private String getUserEntityListFromAapi() throws MalformedURLException {
        String url = RestApi.API_URL_GET_USER_LIST;
        return ApiConnection.createGET(url).requestSyncCall();
    }

    private boolean isThereInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return (ni != null ) && ni.isConnectedOrConnecting();
    }
}
