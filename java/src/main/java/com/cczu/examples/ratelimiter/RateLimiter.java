package com.cczu.examples.ratelimiter;



import com.cczu.examples.ratelimiter.alg.FixedRateLimitAlg;
import com.cczu.examples.ratelimiter.alg.RateLimitAlg;
import com.cczu.examples.ratelimiter.rule.ApiLimit;
import com.cczu.examples.ratelimiter.rule.RateLimiterRule;
import com.cczu.examples.ratelimiter.rule.RuleConfig;
import com.cczu.examples.ratelimiter.rule.datasource.FileRuleConfigSource;
import com.cczu.examples.ratelimiter.rule.datasource.RuleConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jianzhen.yin
 * @date 2020/9/22
 */
public class RateLimiter {
    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);
    private Map<String, RateLimitAlg> counters = new ConcurrentHashMap<>();
    private RateLimiterRule rule;
    public RateLimiter(){
        InputStream in = null;
        RuleConfigSource source = new FileRuleConfigSource();
        RuleConfig ruleConfig = source.load();
        // 将限流规则构建成支持快速查找的数据结构RateLimitRule
        this.rule = new RateLimiterRule(ruleConfig);
    }

    public boolean limit(String appId,String url){
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if (apiLimit == null){
            return true;
        }
        String countKey = appId + ":" + apiLimit.getApi();
        RateLimitAlg rateLimitAlg = counters.get(countKey);
        if (rateLimitAlg == null){
            RateLimitAlg newRateLimitAlg = new FixedRateLimitAlg(apiLimit.getLimit());
            rateLimitAlg = counters.putIfAbsent(countKey,newRateLimitAlg);
            if (rateLimitAlg == null){
                rateLimitAlg = newRateLimitAlg;
            }
        }
        return rateLimitAlg.tryAcquire();
    }

}
