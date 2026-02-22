package model;

import java.util.Set;

public class UserPreference {
    private String userId;
    private Set<ChannelType> preferencedChannels;

    public UserPreference(String userId, Set<ChannelType> preferencedChannels) {
        this.userId = userId;
        this.preferencedChannels = preferencedChannels;
    }

    public String getUserId() {
        return userId;
    }

    public Set<ChannelType> getPreferencedChannels() {
        return preferencedChannels;
    }
}
