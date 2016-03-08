package com.kutec.data.user;

import com.kutec.data.user.entity.UserEntity;
import com.kutec.data.user.entity.mapper.UserEntityDataMapper;
import com.kutec.domain.user.User;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class UserEntityDataMapperTest {
    private static final int FAKE_USER_ID = 123;
    private static final String FAKE_FULLNAME = "Tony Stark";

    private UserEntityDataMapper userEntityDataMapper;

    @Before
    public void setUp() {
        userEntityDataMapper = new UserEntityDataMapper();
    }

    @Test
    public void testTransformUserEntityHappyCase() {
        UserEntity userEntity = createFakeUserEnty();
        User user = userEntityDataMapper.transform(userEntity);

        Assert.assertThat(user, CoreMatchers.is(CoreMatchers.instanceOf(User.class)));
        Assert.assertThat(FAKE_USER_ID, CoreMatchers.equalTo(user.getUserId()));
        Assert.assertThat(FAKE_FULLNAME, CoreMatchers.equalTo(user.getFullName()));
    }

    @Test
    public void testTransformUserEntityListHappyCase() {
        List<UserEntity> userEntityList = new ArrayList<>(6);
        userEntityList.add(Mockito.mock(UserEntity.class));
        userEntityList.add(Mockito.mock(UserEntity.class));

        List<User> userList = userEntityDataMapper.transform(userEntityList);

        Assert.assertThat(userList.get(0), CoreMatchers.instanceOf(User.class));
        Assert.assertThat(userList.get(1), CoreMatchers.instanceOf(User.class));
        Assert.assertEquals(2, userList.size());
    }

    private UserEntity createFakeUserEnty() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(FAKE_USER_ID);
        userEntity.setFullname(FAKE_FULLNAME);
        return userEntity;
    }
}
