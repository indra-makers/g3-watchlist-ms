package com.co.indra.coinmarketcap.watchlist.externalsAPI.users.repositoryRest;

import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.externalsAPI.users.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.co.indra.coinmarketcap.watchlist.exceptions.BusinessExceptions;


@Component
public class UserRest {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.users.url}")
    private String apiUrl;

    public UserModel getUserById(int id) {
        String url = apiUrl+id;
        ResponseEntity <UserModel> response = restTemplate.getForEntity(url, UserModel.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundException(ErrorCodes.USER_DOES_NOT_EXIST.getMessage());
        }
        return response.getBody();
    }
}
