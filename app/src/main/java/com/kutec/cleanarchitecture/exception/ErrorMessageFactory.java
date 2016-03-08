package com.kutec.cleanarchitecture.exception;

import android.content.Context;

import com.kutec.cleanarchitecture.R;
import com.kutec.data.common.repository.net.NetworkConnectionException;
import com.kutec.data.user.UserNotFoundException;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/7.
 */
public class ErrorMessageFactory {
    public ErrorMessageFactory() {
    }

    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof UserNotFoundException) {
            message = context.getString(R.string.exception_message_user_not_found);
        }

        return message;
    }

}
