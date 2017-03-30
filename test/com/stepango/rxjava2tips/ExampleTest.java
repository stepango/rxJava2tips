package com.stepango.rxjava2tips;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ExampleTest {

    @Test
    public void intTest() {
        Observable<Integer> observable = Observable.just(1, 2);
        int first = observable.blockingFirst();
        assertEquals(first, 1);

        int last = observable.blockingLast();
        assertEquals(last, 2);

        Iterable<Integer> integers = observable.blockingIterable();
        int sum = 0;
        for (Integer integer : integers) sum += integer;
        assertEquals(sum, 3);
    }

    @Test
    public void subscriberTest() throws InterruptedException {
        Observable.timer(1, TimeUnit.SECONDS)
                .flatMap(ignore -> Observable.just(1, 2))
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .assertResult(1, 2)
                .assertNever(3);
    }

    @Test
    public void awaitTerminalEventTest() {
        boolean hasTerminalEvent = Observable.timer(1, TimeUnit.SECONDS)
                .flatMap(ignore -> Observable.just(1, 2))
                .test()
                .awaitTerminalEvent(500, TimeUnit.MILLISECONDS);

        assertFalse(hasTerminalEvent);
    }
}