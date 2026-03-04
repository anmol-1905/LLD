package core;

import appender.Appender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private final String loggerName;
    private volatile LogLevel logLevel;
    private final List<Appender> appenders;

    public Logger(String loggerName, LogLevel logLevel) {
        this.loggerName = loggerName;
        this.logLevel = logLevel;
        appenders = new CopyOnWriteArrayList<>();
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void addAppender(Appender appender) {
        this.appenders.add(appender);
    }

    public void log(LogLevel logLevel, String message) {
        if(logLevel.getLevel()<this.logLevel.getLevel()) {
            return;
        }
        LogMessage logMessage = new LogMessage(message, logLevel, this.loggerName);
        for(Appender appender: appenders) {
            appender.append(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }


}
