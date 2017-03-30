package com.stepango.rxjava2tips;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TrampolineRuleTest {

    @Rule
    public final TrampolineSchedulersRule schedulers = new TrampolineSchedulersRule();

    @Test
    public void observerTest() throws InterruptedException {
        Observable.timer(1, TimeUnit.SECONDS)
                .flatMap(ignore -> Observable.just(1, 2))
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertResult(1, 2)
                .assertNever(3);
    }
}