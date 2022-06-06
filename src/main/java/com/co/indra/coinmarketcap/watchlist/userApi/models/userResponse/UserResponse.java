package com.co.indra.coinmarketcap.watchlist.userApi.models.userResponse;

public class UserResponse {

	private User users;

	public UserResponse(User users) {
		super();
		this.users = users;
	}

	public UserResponse() {

	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

}
