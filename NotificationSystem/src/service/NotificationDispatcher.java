package service;

import channel.NotificationChannel;
import factory.NotificationChannelFactory;
import model.ChannelType;
import model.Notification;
import model.UserPreference;

import java.util.Set;

public class NotificationDispatcher {
    private final UserPreferenceService userPreferenceService;
    public NotificationDispatcher(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }
    public void dispatch(Notification notification) {
        UserPreference preference = userPreferenceService.getUserPreference(notification.getUserId());
        Set<ChannelType> channels = preference.getPreferencedChannels();
        for(ChannelType channelType: channels) {
            NotificationChannel notificationChannel = NotificationChannelFactory.getChannelType(channelType);
            notificationChannel.sendNotification(notification);
        }
    }
}
