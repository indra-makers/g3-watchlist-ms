package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchListCoinService {

   @Autowired
   private WatchListCoinRepository watchListCoinRepository;

   public void removeCoinToWatchList(Long idWatchList, Long idWatchListCoin) {

      if (watchListCoinRepository.findWatchListCoinByWatchlist(idWatchList).isEmpty()
            || watchListCoinRepository.findWatchListCoinByCoin(idWatchListCoin).isEmpty()) {
         throw new RuntimeException("WatchList or Coin not exist");
      }

      watchListCoinRepository.deleteCoinToWatchList(idWatchList, idWatchListCoin);

   }

}
