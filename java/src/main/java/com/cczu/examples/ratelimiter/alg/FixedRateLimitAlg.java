package com.cczu.examples.ratelimiter.alg;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jianzhen.yin
 * @date 2020/9/22
 */
public class FixedRateLimitAlg implements RateLimitAlg {
    private static final Logger log = LoggerFactory.getLogger(FixedRateLimitAlg.class);

    private static final long TRY_LOCK_TIMEOUT = 200L;
    private final int limit;
    private Stopwatch stopwatch;
    private AtomicInteger currentCount = new AtomicInteger(0);
    private Lock lock = new ReentrantLock();

    public FixedRateLimitAlg(int limit) {
        this(limit, StopWatch.createStarted());
    }


    public FixedRateLimitAlg(int limit, StopWatch stopWatch) {
        this.limit = limit;
        this.stopwatch = stopwatch;
    }

    @Override
    public boolean tryAcquire() {
        int updateCount = currentCount.incrementAndGet();
        if (updateCount <= limit) {
            return true;
        }
        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if (stopwatch.elapsed(TimeUnit.MICROSECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                        currentCount.set(0);
                        stopwatch.reset();
                    }
                    updateCount = currentCount.incrementAndGet();
                    return updateCount <= limit;
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("tryAcquire() time out  ..");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("tryAcquire() is interrupted ..");
        }

    }
}
