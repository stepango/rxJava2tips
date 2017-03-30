package com.stepango.rxjava2tips;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

public class TestSchedulersRule implements TestRule {

    TestScheduler testScheduler = new TestScheduler();

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override public void evaluate() throws Throwable {
                RxJavaPlugins.setIoSchedulerHandler(
                        scheduler -> testScheduler);
                RxJavaPlugins.setComputationSchedulerHandler(
                        scheduler -> testScheduler);
                RxJavaPlugins.setNewThreadSchedulerHandler(
                        scheduler -> testScheduler);
                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                }
            }
        };
    }
}
