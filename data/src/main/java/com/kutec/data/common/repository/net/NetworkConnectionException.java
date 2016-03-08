package com.kutec.data.common.repository.net;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
public class NetworkConnectionException extends Exception {

    public NetworkConnectionException() {
    }

    public NetworkConnectionException(String detailMessage) {
        super(detailMessage);
    }

    public NetworkConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NetworkConnectionException(Throwable throwable) {
        super(throwable);
    }
}
