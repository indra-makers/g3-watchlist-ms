package com.co.indra.coinmarketcap.watchlist.userApi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.co.indra.coinmarketcap.watchlist.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.watchlist.userApi.models.UserApi;

@Component
public class UserMsClient {
	@Autowired
	private RestTemplate resTemplate;
	public UserApi getUser(Long idUser) {
		String url = "https://g3-users-ms.herokuapp.com/api/user-ms/users/" + idUser;
		ResponseEntity<UserApi> response = resTemplate.getForEntity(url, UserApi.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new BusinessExceptions();
		}
		UserApi u = response.getBody();
		return new UserApi(u.getUserId(), u.getName(), u.getMail(), u.getIdMembership());
	}
}
