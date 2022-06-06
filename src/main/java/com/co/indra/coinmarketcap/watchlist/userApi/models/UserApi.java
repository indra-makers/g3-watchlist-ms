package com.co.indra.coinmarketcap.watchlist.userApi.models;

import java.io.Serializable;

public class UserApi implements Serializable {
	private Long userId;
	private String name;
	private String mail;
	private Integer idMembership;

	public UserApi(Long userId, String name, String mail, Integer idMembership) {
		super();
		this.userId = userId;
		this.name = name;
		this.mail = mail;
		this.idMembership = idMembership;
	}

	public UserApi() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public Integer getIdMembership() {
		return idMembership;
	}

	public void setIdMembership(Integer idMembership) {
		this.idMembership = idMembership;
	}

}
