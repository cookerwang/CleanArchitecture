package com.kutec.data.user.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.kutec.data.user.entity.UserEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
@Singleton
public class UserEntityJsonMapper {
    private final Gson gson;

    @Inject
    public UserEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link UserEntity}.
     *
     * @param userJsonResponse A json representing a user profile.
     * @return {@link UserEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public UserEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
        try {
            Type userEntityType = new TypeToken<UserEntity>() {}.getType();
            UserEntity userEntity = this.gson.fromJson(userJsonResponse, userEntityType);

            return userEntity;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    /**
     * Transform from valid json string to List of {@link UserEntity}.
     *
     * @param userListJsonResponse A json representing a collection of users.
     * @return List of {@link UserEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<UserEntity> transformUserEntityCollection(String userListJsonResponse)
            throws JsonSyntaxException {

        List<UserEntity> userEntityCollection;
        try {
            Type listOfUserEntityType = new TypeToken<List<UserEntity>>() {}.getType();
            userEntityCollection = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);

            return userEntityCollection;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
