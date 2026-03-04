package demo;

import appender.AsyncAppender;
import appender.ConsoleAppender;
import appender.FileAppender;
import core.LogLevel;
import core.Logger;
import core.LoggerManager;
import formatter.DefaultFormatter;
import formatter.Formatter;

public class AsyncAppenderDemo {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerManager.getInstance().getLogger("AsyncDemo");
        logger.setLogLevel(LogLevel.INFO);

        Formatter formatter = new DefaultFormatter();

        FileAppender fileAppender = new FileAppender("async-demo.log", formatter);
        ConsoleAppender consoleAppender = new ConsoleAppender(formatter);

        AsyncAppender asyncAppender = new AsyncAppender(fileAppender, 10000);

        logger.addAppender(consoleAppender);
        logger.addAppender(asyncAppender);

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            for(int i=0;i<50;i++) {
                logger.info("Message : " + i + " from " + threadName);
            }
        };

        Thread t1 = new Thread(task, "Thread1");
        Thread t2 = new Thread(task, "Thread2");
        Thread t3 = new Thread(task, "Thread3");
        long start = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();


        t1.join();
        t2.join();
        t3.join();

        long end = System.currentTimeMillis();
        System.out.println("Completed:" + (end - start) + "ms");
        Thread.sleep(2000);
        System.out.println("FINISHED");
    }
}
