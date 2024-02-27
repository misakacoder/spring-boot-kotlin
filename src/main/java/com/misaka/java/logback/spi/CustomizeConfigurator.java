package com.misaka.java.logback.spi;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.OptionHelper;
import com.misaka.java.logback.core.CustomizeJoranConfigurator;
import com.misaka.java.logback.pattern.DefaultConsoleAppender;

import java.net.URL;

public class CustomizeConfigurator extends ContextAwareBase implements Configurator {

    final public static String AUTOCONFIG_FILE = "logback-misaka.xml";
    final public static String CONFIG_FILE_PROPERTY = "logback.misaka.configurationFile";

    @Override
    public ExecutionStatus configure(LoggerContext loggerContext) {
        loggerContext.setMDCAdapter(new LogbackMDCAdapter());
        doConfigure(loggerContext);
        clearStatusManager(loggerContext);
        return ExecutionStatus.DO_NOT_INVOKE_NEXT_IF_ANY;
    }

    private void doConfigure(LoggerContext loggerContext) {
        CustomizeJoranConfigurator configurator = new CustomizeJoranConfigurator();
        configurator.setContext(loggerContext);
        String filepath = System.getProperty(CONFIG_FILE_PROPERTY);
        try {
            if (!OptionHelper.isNullOrEmpty(filepath)) {
                configurator.doConfigure(filepath);
                return;
            } else {
                URL url = ClassLoader.getSystemResource(AUTOCONFIG_FILE);
                if (url != null) {
                    configurator.doConfigure(url);
                    return;
                }
            }
        } catch (Exception e) {
            this.addError(e.getMessage());
        }
        loggerContext.getLogger("ROOT").addAppender(new DefaultConsoleAppender(loggerContext));
        loggerContext.getLogger(this.getClass()).warn("Use default configuration.");
    }

    private void clearStatusManager(LoggerContext loggerContext) {
        StatusManager statusManager = loggerContext.getStatusManager();
        for (Status status : statusManager.getCopyOfStatusList()) {
            int level = status.getLevel();
            Class<?> cls = status.getOrigin().getClass();
            String message = status.getMessage();
            Logger logger = loggerContext.getLogger(cls);
            switch (level) {
                case Status.INFO -> logger.info(message);
                case Status.WARN -> logger.warn(message);
                case Status.ERROR -> logger.error(message);
            }
        }
        statusManager.clear();
    }
}
