package com.cczu.examples.ratelimiter.rule.parser;

import com.cczu.examples.ratelimiter.rule.RuleConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author jianzhen.yin
 * @date 2020/9/22
 */
public class YamlRuleConfigParser extends RuleConfigParser {
    @Override
    public RuleConfig parse(InputStream in) {
        Yaml yaml = new Yaml();
        RuleConfig ruleConfig = null;
        try {
            ruleConfig = yaml.loadAs(in, RuleConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleConfig;
    }
}
