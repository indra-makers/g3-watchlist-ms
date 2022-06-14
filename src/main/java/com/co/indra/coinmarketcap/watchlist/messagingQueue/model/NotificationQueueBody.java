package com.co.indra.coinmarketcap.watchlist.messagingQueue.model;

import java.io.Serializable;

public class NotificationQueueBody implements Serializable {
    private String type;
    private int userId;
    private Notification notification;

    public NotificationQueueBody() {
    }

    public NotificationQueueBody(String type, int userId, Notification notification) {
        this.type = type;
        this.userId = userId;
        this.notification = notification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
