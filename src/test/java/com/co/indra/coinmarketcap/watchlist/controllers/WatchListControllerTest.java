package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.config.Routes;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.model.responses.ErrorResponse;
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
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // por cada test hace un rollback
public class WatchListControllerTest {

   @Autowired
   WatchListRepository watchListRepository;

   @Autowired
   WatchListCoinRepository watchListCoinRepository;

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Test
   public void createWatchListHappy() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(Routes.WATCHLIST_RESOURCE).content("{\n"
            + "    \"idUser\": 15,\n" + "    \"watchListName\": \"Mi primera watchlist\",\n"
            + "    \"watchListDescription\": \"Lorem ipsum dolor sit amet. Id maiores ratione quo nulla placeat rem molestias architecto quo obcaecati enim beatae eligendi hic sunt harum ea omnis obcaecati.\",\n"
            + "    \"isPrivate\": false\n" + "}").contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      List<WatchList> watchListList = watchListRepository.findWatchListByName("Mi primera watchlist");
      Assertions.assertEquals(1, watchListList.size());

      WatchList watchListToAssert = watchListList.get(0);

      Assertions.assertEquals("Mi primera watchlist", watchListToAssert.getWatchListName());

   }

   @Test
   public void createWatchListNoUser() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(Routes.WATCHLIST_RESOURCE).content("{\n"
            + "    \"watchListName\": \"Mi primera watchlist\",\n"
            + "    \"watchListDescription\": \"Lorem ipsum dolor sit amet. Id maiores ratione quo nulla placeat rem molestias architecto quo obcaecati enim beatae eligendi hic sunt harum ea omnis obcaecati.\",\n"
            + "    \"isPrivate\": false\n" + "}").contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(412, response.getStatus());

      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("003", error.getCode());
      Assertions.assertEquals("Missing some parameters to add watchlist", error.getMessage());

   }

   @Test
   @Sql("/testdata/addCoinToWatchListHappy.sql")
   public void addCoinToWatchListHappy() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.WATCHLIST_RESOURCE + Routes.ADD_COIN_TO_WATCHLIST, "999")
            .content("{\n" + "    \"symbol\": \"ZZZ\"\n" + "}").contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      List<WatchListCoin> watchListCoinList = watchListCoinRepository.findWatchListCoinBySymbol("ZZZ");
      Assertions.assertEquals(1, watchListCoinList.size());

      WatchListCoin watchListCoinToAssert = watchListCoinList.get(0);

      Assertions.assertEquals("ZZZ", watchListCoinToAssert.getSymbol());
   }

   @Test
   public void addCoinToNoWatchList() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.WATCHLIST_RESOURCE + Routes.ADD_COIN_TO_WATCHLIST, "999")
            .content("{\n" + "    \"symbol\": \"ZZZ\"\n" + "}").contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(500, response.getStatus());
   }

   @Test
   @Sql("/testdata/addCoinToWatchListSameCoin.sql")
   public void addCoinToWatchListSameCoin() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.WATCHLIST_RESOURCE + Routes.ADD_COIN_TO_WATCHLIST, "999")
            .content("{\n" + "    \"symbol\": \"ZZZ\"\n" + "}").contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(412, response.getStatus());

      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("002", error.getCode());
      Assertions.assertEquals("The coin symbol is already registered on the watchlist", error.getMessage());
   }

   @Test
   @Sql("/testdata/addCoinToWatchListHappy.sql")
   public void addNoCoinToWatchListSameCoin() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.WATCHLIST_RESOURCE + Routes.ADD_COIN_TO_WATCHLIST, "999").content("{\n" + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(412, response.getStatus());

      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("004", error.getCode());
      Assertions.assertEquals("Missing some parameters to add coin", error.getMessage());
   }

   // Test para eliminar sin errores una WatchList que no tiene monedas
   @Test
   @Sql("/testdata/createRegisterOfWatchlist.sql")
   public void removeWatchListHappyPath() throws Exception {

      MockHttpServletRequestBuilder requestRemoveWatchList = MockMvcRequestBuilders
            .delete(Routes.WATCHLIST_RESOURCE + Routes.DELETE_WATCHLIST_BY_ID, 187)
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemoveWatchList = mockMvc.perform(requestRemoveWatchList).andReturn()
            .getResponse();
      Assertions.assertEquals(200, responseRemoveWatchList.getStatus());

      List<WatchList> ListWatchList = watchListRepository.findWatchListById((long) 187);
      Assertions.assertEquals(0, ListWatchList.size());

   }

   // Test para eliminar una WatchList donde arroja un error que no encuentra
   // una watchlist para eliminar
   @Test
   @Sql("/testdata/createRegisterOfWatchlist.sql")

   public void removeWatchListNotFound() throws Exception {

      MockHttpServletRequestBuilder requestRemoveWatchList = MockMvcRequestBuilders
            .delete(Routes.WATCHLIST_RESOURCE + Routes.DELETE_WATCHLIST_BY_ID, 244)
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemoveWatchList = mockMvc.perform(requestRemoveWatchList).andReturn()
            .getResponse();
      Assertions.assertEquals(404, responseRemoveWatchList.getStatus());

      List<WatchList> ListWatchList = watchListRepository.findWatchListById((long) 187);
      Assertions.assertEquals(1, ListWatchList.size());

      String textResponse = responseRemoveWatchList.getContentAsString();

      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);
      Assertions.assertEquals("NOT_FOUND", error.getCode());
      Assertions.assertEquals("The Watchlist not exist", error.getMessage());

   }

   // Test para eliminar cuando tiene monedas asociadas
   @Test
   @Sql("/testdata/createRegisterOfWatchlist.sql")
   @Sql("/testdata/createCoinToWatchlist.sql")
   public void removeWatchListWithCoin() throws Exception {

      MockHttpServletRequestBuilder requestRemoveWatchList = MockMvcRequestBuilders
            .delete(Routes.WATCHLIST_RESOURCE + Routes.DELETE_WATCHLIST_BY_ID, 187)
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemoveWatchList = mockMvc.perform(requestRemoveWatchList).andReturn()
            .getResponse();
      Assertions.assertEquals(412, responseRemoveWatchList.getStatus());

      List<WatchList> ListWatchList = watchListRepository.findWatchListById((long) 187);
      Assertions.assertEquals(1, ListWatchList.size());

      String textResponse = responseRemoveWatchList.getContentAsString();

      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);
      Assertions.assertEquals("008", error.getCode());
      Assertions.assertEquals("This watchlist contains coins currently", error.getMessage());

   }

}
