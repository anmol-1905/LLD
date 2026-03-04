package appender;

import core.LogMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class AsyncAppender implements Appender {
    private final BlockingQueue<LogMessage> queue;
    private final Appender delegate;
    private final Thread workerThread;
    private volatile boolean running = true;

    private static final int BATCH_SIZE = 25;

    public AsyncAppender(Appender delegate, int capacity) {
        this.delegate = delegate;
        this.queue = new LinkedBlockingDeque<>(capacity);
        this.workerThread = new Thread(this::processLogs, "AsyncLogger-worker");
        this.workerThread.setDaemon(true);
        this.workerThread.start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
    }

    private void processLogs() {
        while (running || !queue.isEmpty()) {
            try {
                List<LogMessage> batch = new ArrayList<>();
                queue.drainTo(batch, BATCH_SIZE);
                if(batch.isEmpty()) {
                    batch.add(queue.take());
                }
                for(LogMessage logMessage: batch) {
                    delegate.append(logMessage);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void shutDown() {
        running = false;
        workerThread.interrupt();
    }

    @Override
    public void append(LogMessage logMessage) {
        try {
            queue.put(logMessage);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
