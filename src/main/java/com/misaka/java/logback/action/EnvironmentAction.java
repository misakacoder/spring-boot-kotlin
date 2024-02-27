package com.misaka.java.logback.action;

import com.misaka.java.logback.model.EnvironmentModel;
import com.misaka.java.logback.model.KeyValueModel;

public class EnvironmentAction extends KeyValueAction {

    @Override
    public KeyValueModel getModel() {
        return new EnvironmentModel();
    }
}
