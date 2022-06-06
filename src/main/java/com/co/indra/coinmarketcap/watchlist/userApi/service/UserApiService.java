package com.co.indra.coinmarketcap.watchlist.userApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.userApi.client.UserMsClient;
import com.co.indra.coinmarketcap.watchlist.userApi.models.UserApi;
import com.co.indra.coinmarketcap.watchlist.userApi.models.userResponse.User;

@Service
public class UserApiService {

	@Autowired
	private UserMsClient users;

	public UserApi extractUser(Long idUser) {
		return users.getUser(idUser);
		}
	}

