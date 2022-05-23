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
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WatchListCoinControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private WatchListCoinRepository watchListCoinRepository;

   @Test
   @Sql("/testdata/createRegisterOfWatchlist.sql")
   @Sql("/testdata/createCoinToWatchlist.sql")
   public void removeCoinToWatchList() throws Exception {

      MockHttpServletRequestBuilder requestRemoveCoinToWatchList = MockMvcRequestBuilders.delete("/watchlistcoins/187coin/13")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemoveCoinToWatchList = mockMvc.perform(requestRemoveCoinToWatchList).andReturn()
            .getResponse();
      Assertions.assertEquals(200, responseRemoveCoinToWatchList.getStatus());

      List<WatchListCoin> watchListCoinList = watchListCoinRepository.findWatchListCoinByWatchlist((long) 187);
      Assertions.assertEquals(0, watchListCoinList.size());

   }

}
