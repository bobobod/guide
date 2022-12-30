package com.cczu.examples.ratelimiter.rule.datasource;


import com.cczu.examples.ratelimiter.rule.RuleConfig;
import com.cczu.examples.ratelimiter.rule.parser.JsonRuleConfigParser;
import com.cczu.examples.ratelimiter.rule.parser.RuleConfigParser;
import com.cczu.examples.ratelimiter.rule.parser.YamlRuleConfigParser;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author jianzhen.yin
 * @date 2020/9/22
 */
public class FileRuleConfigSource implements RuleConfigSource{
    private static final Logger log = LoggerFactory.getLogger(FileRuleConfigSource.class);

    public static final String API_LIMIT_CONFIG_NAME = "ratelimiter-rule";
    public static final String YAML_EXTENSION = "yaml";
    public static final String YML_EXTENSION = "yml";
    public static final String JSON_EXTENSION = "json";

    public static final String[] SUPPORT_EXTENSION =
            new String[]{YAML_EXTENSION,YML_EXTENSION,JSON_EXTENSION};
    private static final Map<String, RuleConfigParser> PARSER_MAP = Maps.newHashMap();
    static {
        PARSER_MAP.put(YAML_EXTENSION,new YamlRuleConfigParser());
        PARSER_MAP.put(YML_EXTENSION,new YamlRuleConfigParser());
        PARSER_MAP.put(JSON_EXTENSION,new JsonRuleConfigParser());
    }

    @Override
    public RuleConfig load() {
        for (String extension : SUPPORT_EXTENSION) {
            InputStream in = null;
            try{
                in = this.getClass().getResourceAsStream("/" + getFileNameByExt(extension));
                if (in != null){
                    RuleConfigParser parser = PARSER_MAP.get(extension);
                    return parser.parse(in);
                }
            }finally {
                if (in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        log.error("close file error" + e);
                    }
                }
            }
        }
        return  null;
    }

    private String getFileNameByExt(String extension){
        return API_LIMIT_CONFIG_NAME + '.' + extension;
    }
}
