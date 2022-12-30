package com.cczu.examples.ratelimiter.alg;



/**
 * @author jianzhen.yin
 * @date 2020/9/22
 */
public interface RateLimitAlg {
    boolean tryAcquire();
}
