package com.co.indra.coinmarketcap.watchlist.messagingQueue.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private String body;
    private String subject;
    private int idUser;

    public Notification() {
    }

    public Notification(String body, String subject, int idUser) {
        this.body = body;
        this.subject = subject;
        this.idUser = idUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
