package com.kutec.data.common.repository.net;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class ApiConnection implements Callable<String> {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "Application/json; charset=utf-8";

    private URL url;
    private String response;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    @Override
    public String call() throws Exception {
        return requestSyncCall();
    }

    public String requestSyncCall() {
        connectToApi();
        return response;
    }

    private void connectToApi() {
        OkHttpClient okHttpClient = createOkHttpClient();
        Request request = new Request.Builder().get().url(url).addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON).build();
        try {
            response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        return okHttpClient;
    }
}
