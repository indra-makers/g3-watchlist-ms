package com.co.indra.coinmarketcap.watchlist.services;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchListCoinService {

    @Autowired
    private WatchListCoinRepository watchListCoinRepository;
    
    public void removeCoinToWatchList(Long idWatchList){
       watchListCoinRepository.deleteCoinToWatchList(idWatchList);
       
   }

}
