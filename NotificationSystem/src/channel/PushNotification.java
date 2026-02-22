package channel;

import model.Notification;

public class PushNotification implements NotificationChannel {
    @Override
    public void sendNotification(Notification notification) {
        System.out.println(
                "Send push notification to " + notification.getUserId() + " on " + notification.getType() + " messaage: " + notification.getMessage()
        );
    }
}
