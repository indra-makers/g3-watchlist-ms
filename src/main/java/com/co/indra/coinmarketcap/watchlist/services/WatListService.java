package com.co.indra.coinmarketcap.watchlist.services;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WatListService {

    @Autowired
    WatchListRepository watchListRepository;
    @Autowired
    WatchListCoinRepository watchListCoinRepository;
}
