package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.co.indra.coinmarketcap.watchlist.config.Routes;

import java.util.List;

@RestController
@RequestMapping(Routes.WATCHLIST_RESOURCE)
public class WatchListController {

   @Autowired
   private WatchListService watchListService;

   @PostMapping
   public void createWatchlist(@RequestBody WatchList watchList) {
      watchListService.createWatchList(watchList);
   }

   @PostMapping(Routes.ADD_COIN_TO_WATCHLIST)
   public void addCoinToWatchList(@PathVariable(name = "idWatchlist") Long idWatchList,
         @RequestBody WatchListCoin watchListCoin) {
      watchListService.addCoinToWatchList(watchListCoin, idWatchList);
   }

}