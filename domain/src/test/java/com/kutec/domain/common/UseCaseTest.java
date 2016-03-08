package com.kutec.domain.common;

import com.kutec.domain.common.excutor.PostExecutionThread;
import com.kutec.domain.common.excutor.ThreadExecutor;
import com.kutec.domain.common.interactor.UseCase;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public class UseCaseTest {
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ThreadExecutor mockThreadExecutor;

    private UseCase useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new UseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testBuildUseCaseObservableReturnCorrectResult() {
        TestScheduler testScheduler = new TestScheduler();
        BDDMockito.given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        useCase.execute(testSubscriber);

        Assert.assertThat(testSubscriber.getOnNextEvents().size(), Is.is(0));
    }

    @Test
    public void testSubscriptionWhenExecutingUseCase() {
        TestSubscriber testSubscriber = new TestSubscriber();

        useCase.execute(testSubscriber);
        useCase.unsubscribe();

        Assert.assertThat(testSubscriber.isUnsubscribed(), Is.is(true));
    }

    private static class UseCaseTestClass extends UseCase {

        public UseCaseTestClass(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override
        protected Observable buildUseCaseObservable() {
            return Observable.empty();
        }

        @Override
        public void execute(Subscriber useCaseSubscriber) {
            super.execute(useCaseSubscriber);
        }
    }
}
