package com.misaka.java.logback.core;

import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.RuleStore;
import ch.qos.logback.core.model.processor.DefaultProcessor;
import com.misaka.java.logback.action.EnvironmentAction;
import com.misaka.java.logback.action.PropertyAction;
import com.misaka.java.logback.model.EnvironmentModel;
import com.misaka.java.logback.model.EnvironmentModelHandler;
import com.misaka.java.logback.model.PropertyModel;
import com.misaka.java.logback.model.PropertyModelHandler;

public class CustomizeJoranConfigurator extends JoranConfigurator {

    @Override
    public void addElementSelectorAndActionAssociations(RuleStore ruleStore) {
        super.addElementSelectorAndActionAssociations(ruleStore);
        ruleStore.addRule(new ElementSelector("configuration/prop"), PropertyAction::new);
        ruleStore.addRule(new ElementSelector("configuration/env"), EnvironmentAction::new);
    }

    @Override
    protected void addModelHandlerAssociations(DefaultProcessor defaultProcessor) {
        defaultProcessor.addHandler(PropertyModel.class, (context, ic) -> new PropertyModelHandler(context));
        defaultProcessor.addHandler(EnvironmentModel.class, (context, ic) -> new EnvironmentModelHandler(context));
        super.addModelHandlerAssociations(defaultProcessor);
    }
}
