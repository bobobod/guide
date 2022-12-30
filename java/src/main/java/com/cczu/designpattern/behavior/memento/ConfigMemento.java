package com.cczu.designpattern.behavior.memento;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class ConfigMemento {
    private ConfigFile configFile;

    public ConfigMemento(ConfigFile configFile) {
        this.configFile = configFile;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigFile configFile) {
        this.configFile = configFile;
    }
}
