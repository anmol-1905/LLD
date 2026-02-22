package channel;

import model.Notification;

public class SMSNotificationChannel implements NotificationChannel {
    @Override
    public void sendNotification(Notification notification) {
        System.out.println(
                "Send SMS notification to " + notification.getUserId()  + " messaage: " + notification.getMessage()
        );
    }
}
