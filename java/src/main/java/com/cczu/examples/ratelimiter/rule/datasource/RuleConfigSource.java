package com.cczu.examples.ratelimiter.rule.datasource;


import com.cczu.examples.ratelimiter.rule.RuleConfig;

/**
 * @author jianzhen.yin
 * @date 2020/9/22
 */
public interface RuleConfigSource {
    RuleConfig load();
}
