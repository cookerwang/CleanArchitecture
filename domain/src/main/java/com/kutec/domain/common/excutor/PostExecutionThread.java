package com.kutec.domain.common.excutor;

import rx.Scheduler;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
