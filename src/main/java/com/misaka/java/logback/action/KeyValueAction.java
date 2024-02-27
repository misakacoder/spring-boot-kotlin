package com.misaka.java.logback.action;

import ch.qos.logback.core.joran.action.BaseModelAction;
import ch.qos.logback.core.joran.spi.SaxEventInterpretationContext;
import ch.qos.logback.core.model.Model;
import com.misaka.java.logback.model.KeyValueModel;
import org.xml.sax.Attributes;

public abstract class KeyValueAction extends BaseModelAction {

    private static final String DEFAULT_VALUE_ATTRIBUTE = "defaultValue";

    @Override
    protected Model buildCurrentModel(SaxEventInterpretationContext interpretationContext, String name, Attributes attributes) {
        KeyValueModel keyValueModel = getModel();
        keyValueModel.setName(attributes.getValue(NAME_ATTRIBUTE));
        keyValueModel.setKey(attributes.getValue(KEY_ATTRIBUTE));
        keyValueModel.setDefaultValue(attributes.getValue(DEFAULT_VALUE_ATTRIBUTE));
        return keyValueModel;
    }

    public abstract KeyValueModel getModel();
}
