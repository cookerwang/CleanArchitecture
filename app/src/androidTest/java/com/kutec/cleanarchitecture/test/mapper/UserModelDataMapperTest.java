package com.kutec.cleanarchitecture.test.mapper;

import com.kutec.cleanarchitecture.feature.user.UserModel;
import com.kutec.cleanarchitecture.feature.user.UserModelDataMapper;
import com.kutec.domain.user.User;

import junit.framework.TestCase;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/8.
 */
public class UserModelDataMapperTest extends TestCase {

    private static final int FAKE_USER_ID = 123;
    private static final String FAKE_FULLNAME = "Tony Stark";

    private UserModelDataMapper userModelDataMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userModelDataMapper = new UserModelDataMapper();
    }


    public void testTransformUser() {
        User fakeUser = createFakeUser();
        UserModel userModel = userModelDataMapper.transform(fakeUser);

        Assert.assertThat(userModel, CoreMatchers.instanceOf(UserModel.class));
        Assert.assertEquals(FAKE_USER_ID, userModel.getUserId());
        Assert.assertEquals(FAKE_FULLNAME, userModel.getFullName());
    }

    public void testTransformUserCollection() {
        List<User> fakeUsers = new ArrayList<>(6);
        fakeUsers.add(Mockito.mock(User.class));
        fakeUsers.add(Mockito.mock(User.class));

        Collection<UserModel> userModelList = userModelDataMapper.transform(fakeUsers);

        Assert.assertThat(userModelList.size(), CoreMatchers.is(2));
        Assert.assertThat(userModelList.toArray()[0], CoreMatchers.instanceOf(UserModel.class));
        Assert.assertThat(userModelList.toArray()[1], CoreMatchers.instanceOf(UserModel.class));

    }

    private User createFakeUser() {
        User user = new User(FAKE_USER_ID);
        user.setFullName(FAKE_FULLNAME);
        return user;
    }


}
