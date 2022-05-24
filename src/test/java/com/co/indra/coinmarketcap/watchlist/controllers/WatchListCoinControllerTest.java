package com.co.indra.coinmarketcap.watchlist.controllers;

import java.util.List;

import javax.transaction.Transactional;
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

import com.co.indra.coinmarketcap.watchlist.config.Routes;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.model.responses.ErrorResponse;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WatchListCoinControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private WatchListCoinRepository watchListCoinRepository;
   
   @Autowired
   private ObjectMapper objectMapper;

   //Test para eliminar sin errores una moneda relacionada con un Watchlist
   @Test
   @Sql("/testdata/createRegisterOfWatchlist.sql")
   @Sql("/testdata/createCoinToWatchlist.sql")
   public void removeCoinToWatchListHappyPath() throws Exception {

      MockHttpServletRequestBuilder requestRemoveCoinToWatchList = MockMvcRequestBuilders.delete(Routes.WATCHLIST_COIN_RESOURCE+Routes.DELETE_COIN_FROM_WATCHLIST,187,13)
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemoveCoinToWatchList = mockMvc.perform(requestRemoveCoinToWatchList).andReturn()
            .getResponse();
      Assertions.assertEquals(200, responseRemoveCoinToWatchList.getStatus());

      List<WatchListCoin> watchListCoinList = watchListCoinRepository.findWatchListCoinByWatchlist((long) 187);
      Assertions.assertEquals(0, watchListCoinList.size());

   }
   
   
   //Test para eliminar una moneda de una watchlist donde arroja un error cuando
   //no se encuentra un IDWatchlistCoin o una moneda asociada para eliminar
   @Test
   @Sql("/testdata/createRegisterOfWatchlist.sql")
   @Sql("/testdata/createCoinToWatchlist.sql")
   public void removeCoinToWatchListIDWatchListCoinNotExist() throws Exception {

      MockHttpServletRequestBuilder requestRemoveCoinToWatchList = MockMvcRequestBuilders.delete(Routes.WATCHLIST_COIN_RESOURCE+Routes.DELETE_COIN_FROM_WATCHLIST,187,44)
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemoveCoinToWatchList = mockMvc.perform(requestRemoveCoinToWatchList).andReturn()
            .getResponse();
      Assertions.assertEquals(404, responseRemoveCoinToWatchList.getStatus());

      List<WatchListCoin> watchListCoinList = watchListCoinRepository.findWatchListCoinByWatchlist((long) 187);
      Assertions.assertEquals(1, watchListCoinList.size());
      
      String textResponse = responseRemoveCoinToWatchList.getContentAsString();
      
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);
      Assertions.assertEquals("NOT_FOUND", error.getCode());
      Assertions.assertEquals("The Coin in the Watchlist not exist",error.getMessage());
      

   }
   
   
   //Test para eliminar una moneda de una watchlist donde arroja un error cuando
   //no se encuentra un IDWatchlist o una Watchlist asociada para eliminar
   @Test
   @Sql("/testdata/createRegisterOfWatchlist.sql")
   @Sql("/testdata/createCoinToWatchlist.sql")
   public void removeCoinToWatchListIDWatchListNotExist() throws Exception {

      MockHttpServletRequestBuilder requestRemoveCoinToWatchList = MockMvcRequestBuilders.delete(Routes.WATCHLIST_COIN_RESOURCE+Routes.DELETE_COIN_FROM_WATCHLIST,277,13)
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemoveCoinToWatchList = mockMvc.perform(requestRemoveCoinToWatchList).andReturn()
            .getResponse();
      Assertions.assertEquals(404, responseRemoveCoinToWatchList.getStatus());

      List<WatchListCoin> watchListCoinList = watchListCoinRepository.findWatchListCoinByWatchlist((long) 187);
      Assertions.assertEquals(1, watchListCoinList.size());
      
      String textResponse = responseRemoveCoinToWatchList.getContentAsString();
      
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);
      Assertions.assertEquals("NOT_FOUND", error.getCode());
      Assertions.assertEquals("The Watchlist not exist",error.getMessage());
      
      
   }
   
   
   
   
   

}
