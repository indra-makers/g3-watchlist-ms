package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.model.entities.CoinPriceAlert;
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

   // Eliminar watchlist por medio del ID
   @DeleteMapping(Routes.DELETE_WATCHLIST_BY_ID)
   public void removeWatchlist(@PathVariable("idWatchlist") Long idWatchlist) {
      watchListService.removeWatchlist(idWatchlist);

   }

   @PostMapping(Routes.ADD_COIN_ALERT_TO_WATCHLIST)
   public void addCoinAlertToWatchList(@PathVariable("idWatchlistCoin") Long idWatchlist, @RequestBody CoinPriceAlert coinPriceAlert){
      watchListService.addCoinAlertToWatchlist(idWatchlist, coinPriceAlert);
   }

}