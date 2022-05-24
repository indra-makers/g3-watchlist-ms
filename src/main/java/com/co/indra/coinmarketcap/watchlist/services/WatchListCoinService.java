package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchListCoinService {

   @Autowired
   private WatchListCoinRepository watchListCoinRepository;
   
  
   
   
   public void removeCoinToWatchList(Long idWatchList, Long idWatchListCoin) {
      
      
      if (watchListCoinRepository.findWatchListCoinByWatchlist(idWatchList).isEmpty()) {
         throw new NotFoundException(ErrorCodes.WATCHlLIST_NOT_EXIST);
      }
      
      if(watchListCoinRepository.findWatchListCoinByCoin(idWatchListCoin).isEmpty()) {
         throw new NotFoundException(ErrorCodes.COIN_IN_WATCHlLIST_NOT_EXIST);
      }
      
      
      watchListCoinRepository.deleteCoinToWatchList(idWatchList, idWatchListCoin);

   }
   
   

}
