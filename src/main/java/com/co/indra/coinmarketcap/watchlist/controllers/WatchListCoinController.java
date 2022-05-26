package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.config.Routes;
import com.co.indra.coinmarketcap.watchlist.services.WatchListCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.WATCHLIST_COIN_RESOURCE)
public class WatchListCoinController {

   @Autowired
   private WatchListCoinService watchListCoinService;

   // Eliminar los datos de envio por medio del idWatchList FK
   @DeleteMapping(Routes.DELETE_COIN_FROM_WATCHLIST)
   public void removeCoinToWatchList(@PathVariable("idWatchList") Long idWatchList,
         @PathVariable("idWatchListCoin") Long idWatchListCoin) {
      watchListCoinService.removeCoinToWatchList(idWatchList, idWatchListCoin);

   }

}
