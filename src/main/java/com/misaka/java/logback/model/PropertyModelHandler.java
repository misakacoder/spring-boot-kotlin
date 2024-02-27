package com.misaka.java.logback.model;

import ch.qos.logback.core.Context;

public class PropertyModelHandler extends KeyValueModelHandlerBase {

    public PropertyModelHandler(Context context) {
        super(context);
    }

    @Override
    public String getValue(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }
}
