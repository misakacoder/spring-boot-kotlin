package com.misaka.java.logback.model;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.action.ActionUtil;
import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.model.ModelUtil;
import ch.qos.logback.core.model.processor.ModelHandlerBase;
import ch.qos.logback.core.model.processor.ModelHandlerException;
import ch.qos.logback.core.model.processor.ModelInterpretationContext;
import ch.qos.logback.core.util.OptionHelper;

public abstract class KeyValueModelHandlerBase extends ModelHandlerBase {

    public KeyValueModelHandlerBase(Context context) {
        super(context);
    }

    @Override
    public void handle(ModelInterpretationContext modelInterpretationContext, Model model) throws ModelHandlerException {
        KeyValueModel keyValueModel = (KeyValueModel) model;
        String name = keyValueModel.getName();
        String key = keyValueModel.getKey();
        ActionUtil.Scope scope = ActionUtil.stringToScope(keyValueModel.getScope());
        String defaultValue = keyValueModel.getDefaultValue();
        if (OptionHelper.isNullOrEmpty(name) || OptionHelper.isNullOrEmpty(key)) {
            addError("The \"name\" and \"key\" attributes of %s must be set".formatted(keyValueModel.getTag()));
        } else {
            ModelUtil.setProperty(modelInterpretationContext, name, getValue(key, defaultValue), scope);
        }
    }

    public abstract String getValue(String key, String defaultValue);
}
