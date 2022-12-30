package com.cczu.examples.ratelimiter.rule.parser;


import com.cczu.examples.ratelimiter.rule.RuleConfig;

import java.io.InputStream;

/**
 * @author jianzhen.yin
 * @date 2020/9/22
 */
public abstract class RuleConfigParser {
    public abstract RuleConfig parse(InputStream in);
}
