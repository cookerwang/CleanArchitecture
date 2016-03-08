package com.kutec.data.user;

import com.google.gson.JsonSyntaxException;
import com.kutec.data.user.entity.UserEntity;
import com.kutec.data.user.entity.mapper.UserEntityJsonMapper;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
public class UserEntityJsonMapperTest {
    private static final String JSON_RESPONSE_USER_DETAILS = "{\n"
            + "    \"id\": 1,\n"
            + "    \"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n"
            + "    \"full_name\": \"Simon Hill\",\n"
            + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
            + "    \"followers\": 7484,\n"
            + "    \"email\": \"jcooper@babbleset.edu\"\n"
            + "}";

    private static final String JSON_RESPONSE_USER_COLLECTION = "[{\n"
            + "    \"id\": 1,\n"
            + "    \"full_name\": \"Simon Hill\",\n"
            + "    \"followers\": 7484\n"
            + "}, {\n"
            + "    \"id\": 12,\n"
            + "    \"full_name\": \"Pedro Garcia\",\n"
            + "    \"followers\": 1381\n"
            + "}]";

    private UserEntityJsonMapper userEntityJsonMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        userEntityJsonMapper = new UserEntityJsonMapper();
    }

    @Test
    public void testTransformUserEntityHappyCase() {
        UserEntity userEntity = userEntityJsonMapper.transformUserEntity(JSON_RESPONSE_USER_DETAILS);

        Assert.assertEquals(1, userEntity.getUserId());
        Assert.assertThat(userEntity.getFullname(), CoreMatchers.equalTo("Simon Hill"));
        Assert.assertThat(userEntity.getFollowers(), CoreMatchers.is(7484));
        Assert.assertThat(userEntity.getEmail(), CoreMatchers.is(CoreMatchers.equalTo("jcooper@babbleset.edu")));
    }

    @Test
    public void testTransformUserEntityCollectionHappyCase() {
        Collection<UserEntity> userEntityList = userEntityJsonMapper.transformUserEntityCollection(JSON_RESPONSE_USER_COLLECTION);

        Assert.assertEquals(2, userEntityList.size());
        Assert.assertEquals(1, ((UserEntity)userEntityList.toArray()[0]).getUserId());
        Assert.assertEquals(12, ((UserEntity)userEntityList.toArray()[1]).getUserId());
    }

    @Test
    public void testTransformUserEntityNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        userEntityJsonMapper.transformUserEntity("badUserString");
    }

    @Test
    public void testTransformUserEntityCollectionNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        userEntityJsonMapper.transformUserEntityCollection("bad user string");
    }

}
