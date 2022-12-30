package com.cczu.designpattern.structure.compose.service.engine;



import com.cczu.designpattern.structure.compose.service.logic.LogicFilter;
import com.cczu.designpattern.structure.compose.service.logic.impl.UserAgeFilter;
import com.cczu.designpattern.structure.compose.service.logic.impl.UserGenderFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 决策节点配置,将可提供服务的配置放到map中
 *
 * @author yjz
 * @date 2021/11/12
 */
public class EngineConfig {
    static Map<String, LogicFilter> logicFilterMap;

    static {
        logicFilterMap = new ConcurrentHashMap<>();
        logicFilterMap.put("userAge", new UserAgeFilter());
        logicFilterMap.put("userGender", new UserGenderFilter());
    }

    public static Map<String, LogicFilter> getLogicFilterMap() {
        return logicFilterMap;
    }

    public static void setLogicFilterMap(Map<String, LogicFilter> logicFilterMap) {
        EngineConfig.logicFilterMap = logicFilterMap;
    }
}
