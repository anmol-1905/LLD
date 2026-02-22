package channel;

import model.Notification;

public class EmailNotificationChannel implements NotificationChannel {
    @Override
    public void sendNotification(Notification notification) {
        System.out.println(
                "Send email notification to " + notification.getUserId() + " on " + notification.getType()  + " messaage: " + notification.getMessage()
        );
    }
}
