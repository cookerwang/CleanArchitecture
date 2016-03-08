package com.kutec.data.user;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public UserNotFoundException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public UserNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
