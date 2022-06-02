package com.co.indra.coinmarketcap.watchlist.externalsAPI.users.repositoryRest;

import com.co.indra.coinmarketcap.watchlist.externalsAPI.users.model.UserModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class UserRest {

    private final RestTemplate restTemplate;


    public UserRest(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public UserModel getUserById(int id) {
        String url = "https://g3-users-ms.herokuapp.com/api/user-ms/users/"+id;
        return this.restTemplate.getForObject(url, UserModel.class);
    }
}
