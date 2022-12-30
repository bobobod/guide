package com.cczu.designpattern.structure.compose.service.engine;



import com.cczu.designpattern.structure.compose.modal.aggregates.TreeRich;
import com.cczu.designpattern.structure.compose.modal.vo.EngineResult;

import java.util.Map;

/**
 * 决策引擎接口
 */
public interface IEngine {
    EngineResult process(final Long treeId,
                         final String userId,
                         TreeRich treeRich,
                         final Map<String, String> decisionMatter);
}
