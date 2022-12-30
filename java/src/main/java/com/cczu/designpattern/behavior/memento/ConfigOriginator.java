package com.cczu.designpattern.behavior.memento;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class ConfigOriginator {
    private ConfigFile configFile;

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigFile configFile) {
        this.configFile = configFile;
    }

    public ConfigMemento saveMemento() {
        return new ConfigMemento(configFile);
    }

    public void getMemento(ConfigMemento configMemento) {
        this.configFile = configMemento.getConfigFile();
    }
}
