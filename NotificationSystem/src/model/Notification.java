package model;

public class Notification {
    private final String userId;
    private final String message;

    public Notification(String userId, String message) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }



}
