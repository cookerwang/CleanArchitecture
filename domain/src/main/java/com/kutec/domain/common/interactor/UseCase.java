package com.kutec.domain.common.interactor;

import com.kutec.domain.common.excutor.PostExecutionThread;
import com.kutec.domain.common.excutor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public abstract class UseCase {

    private Subscription subscription = Subscriptions.empty();

    private final PostExecutionThread postExecutionThread;
    private final ThreadExecutor threadExecutor;

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    protected abstract Observable buildUseCaseObservable();

    public void execute(Subscriber useCaseSubscriber) {
        subscription = buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if( !subscription.isUnsubscribed() ) {
            subscription.unsubscribe();
        }
    }
}
