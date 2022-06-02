package com.co.indra.coinmarketcap.watchlist.externalsAPI.users.model;

public class UserModel {
    private int userId;
    private String name;
    private String mail;
    private Long idMembership;

    public UserModel() {
    }

    public UserModel(int userId, String name, String mail, Long idMembership, String code, String message) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.idMembership = idMembership;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(Long idMembership) {
        this.idMembership = idMembership;
    }
}
