import api.NotificationService;
import model.ChannelType;
import model.Notification;
import model.UserPreference;
import service.NotificationDispatcher;
import service.UserPreferenceService;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserPreferenceService preferenceService =  new UserPreferenceService();
        preferenceService.savePreference( new UserPreference("user12345", Set.of(ChannelType.EMAIL, ChannelType.SMS)));

        NotificationDispatcher dispatcher = new NotificationDispatcher(preferenceService);

        NotificationService service = new NotificationService(dispatcher);
        Notification notification = new Notification(
                "user12345",
                "Hello World"
        );
        service.sendNotification(notification);
    }
}