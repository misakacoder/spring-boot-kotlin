package com.misaka.java.logback.model;

import ch.qos.logback.core.model.NamedModel;

public class KeyValueModel extends NamedModel {

    private String scope;

    private String key;

    private String defaultValue;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
