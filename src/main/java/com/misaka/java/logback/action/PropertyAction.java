package com.misaka.java.logback.action;

import com.misaka.java.logback.model.KeyValueModel;
import com.misaka.java.logback.model.PropertyModel;

public class PropertyAction extends KeyValueAction {

    @Override
    public KeyValueModel getModel() {
        return new PropertyModel();
    }
}
