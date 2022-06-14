package com.co.indra.coinmarketcap.watchlist.messaging.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private String body;
    private String subject;

    public Notification() {
    }

    public Notification(String body, String subject) {
        this.body = body;
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}