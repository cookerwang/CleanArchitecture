package com.kutec.data.common.repository;

import com.kutec.domain.common.exception.DefaultErrorBundle;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
public class RepositoryErrorBundle extends DefaultErrorBundle {

    public RepositoryErrorBundle(Exception exception) {
        super(exception);
    }
}
