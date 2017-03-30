package com.stepango.rxjava2tips;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

public class TimeControlTest {

    @Rule
    public TestSchedulersRule testSchedulerRule = new TestSchedulersRule();
    private TestScheduler testScheduler = testSchedulerRule.testScheduler;

    @Test
    public void subscriberTest() throws InterruptedException {
        Observable<Integer> externalObservable = Observable.timer(10, TimeUnit.SECONDS)
                .flatMap(ignore -> Observable.just(1, 2));

        TestObserver<Integer> testObserver = externalObservable.test();

        testObserver.assertNoValues();

        testScheduler.advanceTimeBy(10, TimeUnit.SECONDS);

        testObserver.assertNoErrors()
                .assertComplete()
                .assertResult(1, 2)
                .assertNever(3);
    }
}