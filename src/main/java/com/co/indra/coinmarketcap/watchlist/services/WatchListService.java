package com.co.indra.coinmarketcap.watchlist.services;
import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;
    @Autowired
    private WatchListCoinRepository watchListCoinRepository;

    public void createWatchList(WatchList watchList){
        if(watchList.getIdUser() == null){
            throw new BusinessExceptions(ErrorCodes.MISSING_PARAMETERS);
        }
        watchListRepository.createWatchlist(watchList);
    }
    public void addCoinToWatchList(WatchListCoin watchListCoin, Long idWatchList){
        if (!watchListCoinRepository.findWatchListCoinBySymbol(watchListCoin.getSymbol()).isEmpty()){
            throw new BusinessExceptions(ErrorCodes.SYMBOL_EXISTS_IN_WATCHLIST);
        }
        else if(watchListCoin.getSymbol() == null){
            throw new BusinessExceptions(ErrorCodes.MISSING_COIN_PARAMETERS);
        }
        watchListCoinRepository.addCoinToWatchList(watchListCoin, idWatchList);
    }
}
