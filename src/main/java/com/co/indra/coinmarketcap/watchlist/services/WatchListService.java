package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.watchlist.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

@Service
public class WatchListService {

   @Autowired
   private WatchListRepository watchListRepository;
   @Autowired
   private WatchListCoinRepository watchListCoinRepository;

   public void createWatchList(WatchList watchList) {
      if (watchList.getIdUser() == null) {
         throw new BusinessExceptions(ErrorCodes.MISSING_PARAMETERS);
      }
      watchListRepository.createWatchlist(watchList);
   }

   public void addCoinToWatchList(WatchListCoin watchListCoin, Long idWatchList) {
      if (!watchListCoinRepository.findWatchListCoinBySymbol(watchListCoin.getSymbol()).isEmpty()) {
         throw new BusinessExceptions(ErrorCodes.SYMBOL_EXISTS_IN_WATCHLIST);
      } else if (watchListCoin.getSymbol() == null) {
         throw new BusinessExceptions(ErrorCodes.MISSING_COIN_PARAMETERS);
      }
      watchListCoinRepository.addCoinToWatchList(watchListCoin, idWatchList);
   }

   public List<WatchList> getWatchlist(Long idUser) {
      if (watchListRepository.findWatchListByUserId(idUser).isEmpty()) {
         throw new NotFoundException(ErrorCodes.USER_DOES_NOT_EXIST);
      }
      return watchListRepository.findWatchListByUserId(idUser);
   }

   // Eliminar Watchlist
   public void removeWatchlist(String watchListName) {

      if (watchListRepository.findWatchListByName(watchListName).isEmpty()) {
         throw new NotFoundException(ErrorCodes.WATCHlLIST_NOT_EXIST);
      }

      List<WatchList> ListWatchlist = watchListRepository.findWatchListByName(watchListName);

      if (!ListWatchlist.isEmpty()) {
         WatchList ResultWatchList = ListWatchlist.get(0);
         List<WatchListCoin> listWatchlistCoins = watchListCoinRepository
               .findWatchListCoinByWatchlist(ResultWatchList.getIdWatchList());

         if (!listWatchlistCoins.isEmpty()) {
            WatchListCoin ResultWatchListCoin = listWatchlistCoins.get(0);
            boolean b = ResultWatchList.getIdWatchList() == ResultWatchListCoin.getIdWatchList();

            if (b) {
               throw new BusinessExceptions(ErrorCodes.WATCHLIST_RELATED_TO_A_CURRENCY);
            }
         } else {
            watchListRepository.deleteWatchlist(watchListName);
         }

      }

   }

}
