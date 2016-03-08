package com.kutec.data.common.cache.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */

public class JsonSerializer<T> {
    private Gson gson = new Gson();

    @Inject
    public JsonSerializer() {
    }

    public String serialize(T entity) {
       return gson.toJson(entity, new TypeToken<T>(){}.getType());
    }

    public T deSerialize(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, cls);
    }

}
