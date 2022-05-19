package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.config.Routes;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // por cada test hace un rollback
public class UserControllerTest {
   @Autowired
   WatchListRepository watchListRepository;

   @Autowired
   WatchListCoinRepository watchListCoinRepository;

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Test
   @Sql("/testdata/createWatchlist.sql")
   public void getWatchlistUser() throws Exception {
      // ----la ejecucion de la prueba misma--------------
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(Routes.USERS_RESOURCE + "?idUser=300")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      // ------------ las verificaciones--------------------
      Assertions.assertEquals(200, response.getStatus());

      WatchList[] watchLists = objectMapper.readValue(response.getContentAsString(), WatchList[].class);
      Assertions.assertEquals(4, watchLists.length);

   }

   @Test
   public void getWatchlistUserDoesNotExist() throws Exception {
      // ----la ejecucion de la prueba misma--------------
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(Routes.USERS_RESOURCE + "?idUser=17")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      // ------------ las verificaciones--------------------
      Assertions.assertEquals(404, response.getStatus());
   }
}
