package com.cczu.designpattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class Admin {
    private int cursorIdx = 0;
    private List<ConfigMemento> mementoList = new ArrayList<>();
    private Map<String, ConfigMemento> mementoMap = new ConcurrentHashMap<>();

    public void append(ConfigMemento memento) {
        mementoList.add(memento);
        mementoMap.put(memento.getConfigFile().getVersionNo(), memento);
        cursorIdx++;
    }

    public ConfigMemento undo() {
        if (mementoList.size() == 0) {
            throw new RuntimeException("没有可回退的备忘录");
        }
        if (--cursorIdx <= 0) {
            return mementoList.get(0);
        }
        return mementoList.get(cursorIdx);
    }

    public ConfigMemento redo() {
        if (mementoList.size() == 0) {
            throw new RuntimeException("没有可重做的备忘录");
        }
        if (++cursorIdx > mementoList.size()) {
            return mementoList.get(mementoList.size() - 1);
        }
        return mementoList.get(cursorIdx);
    }

    public ConfigMemento get(String versionNo) {
        return mementoMap.get(versionNo);
    }
}
