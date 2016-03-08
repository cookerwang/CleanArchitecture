package com.kutec.domain.common.exception;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public class DefaultErrorBundle implements ErrorBundle {
    private static final String UNKNOWN_ERROR = "Unknown error";
    private final Exception exception;
    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String msg = (exception == null )? UNKNOWN_ERROR : exception.getMessage();
        return msg;
    }
}
