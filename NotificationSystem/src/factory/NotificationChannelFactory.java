package factory;

import channel.EmailNotificationChannel;
import channel.NotificationChannel;
import channel.PushNotification;
import channel.SMSNotificationChannel;
import model.ChannelType;

public class NotificationChannelFactory {
    public static NotificationChannel getChannelType(ChannelType channelType) {
        return switch (channelType) {
            case EMAIL -> new EmailNotificationChannel();
            case SMS -> new SMSNotificationChannel();
            case PUSH -> new PushNotification();
        };
    }
}
