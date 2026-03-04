package demo;

import appender.ConsoleAppender;
import core.LogLevel;
import core.Logger;
import core.LoggerManager;
import formatter.DefaultFormatter;

public class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = LoggerManager.getInstance().getLogger(LoggerDemo.class.getName());
        logger.addAppender(new ConsoleAppender(new DefaultFormatter()));
        logger.setLogLevel(LogLevel.FATAL);
        logger.fatal("this is a error message");


    }
}
