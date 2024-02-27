package com.misaka.java.logback.model;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.util.OptionHelper;

public class EnvironmentModelHandler extends KeyValueModelHandlerBase {

    public EnvironmentModelHandler(Context context) {
        super(context);
    }

    @Override
    public String getValue(String key, String defaultValue) {
        String value = System.getenv(key);
        return !OptionHelper.isNullOrEmpty(value) ? value : defaultValue;
    }
}
