package api;

import model.Notification;
import service.NotificationDispatcher;

import java.util.concurrent.ExecutorService;

public class AsyncNotificationService {
    private final NotificationDispatcher dispatcher;
    private final ExecutorService executorService;

    public AsyncNotificationService(NotificationDispatcher dispatcher, ExecutorService executorService) {
        this.dispatcher = dispatcher;
        this.executorService = executorService;
    }

    public void sendNotification(Notification notification) {
        executorService.submit(()->dispatcher.dispatch(notification));
    }
}
