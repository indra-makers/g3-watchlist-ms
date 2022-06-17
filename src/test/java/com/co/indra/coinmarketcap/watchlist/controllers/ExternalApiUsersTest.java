package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.config.Routes;
import com.co.indra.coinmarketcap.watchlist.externalsAPI.users.model.UserModel;
import com.co.indra.coinmarketcap.watchlist.model.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ExternalApiUsersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestTemplate restTemplate;


    @Test
    public void findUserById() throws Exception {
        UserModel mockedBody = new UserModel(5, "juli",
                "asd@gmail.com", 2l);
        ResponseEntity<UserModel> entity = new ResponseEntity(mockedBody, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<UserModel>>any()
        )).thenReturn(entity);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(Routes.WATCHLIST_RESOURCE).
                content("{\n" +
                        "    \"idUser\": 5,\n" +
                        "    \"watchListName\": \"asasd\",\n" +
                        "    \"watchListDescription\": \"descripcion aki\",\n" +
                        "    \"isPrivate\": \"false\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void findNoUserById() throws Exception {
        ResponseEntity<UserModel> entity = new ResponseEntity(HttpStatus.NOT_FOUND);

        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<UserModel>>any()
        )).thenReturn(entity);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(Routes.WATCHLIST_RESOURCE).
                content("{\n" +
                        "    \"idUser\": 202,\n" +
                        "    \"watchListName\": \"asasd\",\n" +
                        "    \"watchListDescription\": \"descripcion aki\",\n" +
                        "    \"isPrivate\": \"false\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());
        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

        Assertions.assertEquals("NOT_FOUND", error.getCode());
        Assertions.assertEquals(ErrorCodes.USER_DOES_NOT_EXIST.getMessage(), error.getMessage());
    }

}
