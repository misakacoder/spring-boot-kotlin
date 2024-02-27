package com.misaka.java.logback.pattern;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.misaka.java.logback.core.PidPropertyDefiner;

import java.util.HashMap;
import java.util.Map;

public class DefaultConsoleAppender extends ConsoleAppender<ILoggingEvent> {

    private static final String PATTERN_RULE_REGISTRY_KEY = "PATTERN_RULE_REGISTRY";
    private static final String CONSOLE_LOG_PATTERN = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(" + new PidPropertyDefiner().getPropertyValue() + "){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wex";

    public DefaultConsoleAppender(Context context) {
        Map<String, String> ruleRegistry = (Map<String, String>) context.getObject(PATTERN_RULE_REGISTRY_KEY);
        if (ruleRegistry == null) {
            ruleRegistry = new HashMap<>();
            context.putObject(PATTERN_RULE_REGISTRY_KEY, ruleRegistry);
        }
        ruleRegistry.put("clr", ColorConverter.class.getName());
        ruleRegistry.put("wex", WhitespaceThrowableProxyConverter.class.getName());
        this.setContext(context);
        this.setName("CONSOLE");
        LayoutWrappingEncoder<ILoggingEvent> layoutWrappingEncoder = new LayoutWrappingEncoder<>();
        layoutWrappingEncoder.setContext(context);
        PatternLayout layout = new PatternLayout();
        layout.setContext(context);
        layout.setPattern(CONSOLE_LOG_PATTERN);
        layout.start();
        layoutWrappingEncoder.setLayout(layout);
        this.setEncoder(layoutWrappingEncoder);
        this.start();
    }
}
