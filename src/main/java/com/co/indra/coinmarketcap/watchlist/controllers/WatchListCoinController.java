package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.config.Routes;
import com.co.indra.coinmarketcap.watchlist.model.entities.CoinPriceAlert;
import com.co.indra.coinmarketcap.watchlist.model.requests.PriceAlert;
import com.co.indra.coinmarketcap.watchlist.services.WatchListAlertService;
import com.co.indra.coinmarketcap.watchlist.services.WatchListCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(Routes.WATCHLIST_COIN_RESOURCE)
public class WatchListCoinController {

   @Autowired
   private WatchListCoinService watchListCoinService;
   @Autowired
   private WatchListAlertService watchListAlertService;

   // Eliminar los datos de envio por medio del idWatchList FK
   @DeleteMapping(Routes.DELETE_COIN_FROM_WATCHLIST)
   public void removeCoinToWatchList(@PathVariable("idWatchList") Long idWatchList,
         @PathVariable("idWatchListCoin") Long idWatchListCoin) {
      watchListCoinService.removeCoinToWatchList(idWatchList, idWatchListCoin);
   }
   @PostMapping(Routes.SEND_ALERT_NOTIFY_COINS)
   public void sendAlertNotify(@PathVariable("symbol") String symbol, @RequestBody PriceAlert priceAlert){
      watchListAlertService.sendPriceAlert(symbol, priceAlert.getPrice());
   }

   @PostMapping(Routes.CREATE_ALERT)
   public void createAlert(@PathVariable("idWatchlist") Long idWatchlist, @RequestBody CoinPriceAlert coinPriceAlert){
      watchListAlertService.createAlert(idWatchlist, coinPriceAlert);
   }
}
