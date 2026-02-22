package service;

import model.ChannelType;
import model.UserPreference;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static model.ChannelType.EMAIL;

public class UserPreferenceService {
    private final Map<String , UserPreference> preferences = new ConcurrentHashMap<>();

    public void savePreference(UserPreference userPreference) {
        preferences.put(userPreference.getUserId(), userPreference);
    }

    public UserPreference getUserPreference(String userId) {
        return preferences.getOrDefault(userId,
                new UserPreference(userId, Set.of(ChannelType.EMAIL)));
    }
}
