package com.kutec.data.common.serializer;

import com.kutec.data.ApplicationCaseTest;
import com.kutec.data.common.cache.serializer.JsonSerializer;
import com.kutec.data.user.entity.UserEntity;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
public class JsonSerializerTest extends ApplicationCaseTest {

    private static final String JSON_RESPONSE = "{\n"
            + "    \"id\": 1,\n"
            + "    \"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n"
            + "    \"full_name\": \"Simon Hill\",\n"
            + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
            + "    \"followers\": 7484,\n"
            + "    \"email\": \"jcooper@babbleset.edu\"\n"
            + "}";

    private JsonSerializer<UserEntity> jsonSerializer = new JsonSerializer<UserEntity>();;

    @Before
    public void setUp() {
    }

    @Test
    public void testDeserializeHappyCase() {
        UserEntity userEntityOne = jsonSerializer.deSerialize(JSON_RESPONSE, UserEntity.class);

        Assert.assertEquals(1, userEntityOne.getUserId());
        Assert.assertEquals("Simon Hill", userEntityOne.getFullname());
        Assert.assertEquals(7484, userEntityOne.getFollowers());
    }

    @Test
    public void testSerializeHappyCase() {
        UserEntity userEntityOne = jsonSerializer.deSerialize(JSON_RESPONSE, UserEntity.class);
        String jsonString = jsonSerializer.serialize(userEntityOne);
        UserEntity userEntityTwo = jsonSerializer.deSerialize(jsonString, UserEntity.class);

        Assert.assertThat(userEntityOne.getUserId(), CoreMatchers.is(userEntityTwo.getUserId()));
        Assert.assertThat(userEntityOne.getFullname(), CoreMatchers.is(CoreMatchers.equalTo(userEntityTwo.getFullname())));
        Assert.assertThat(userEntityOne.getFollowers(), CoreMatchers.is(userEntityTwo.getFollowers()));
    }

}
