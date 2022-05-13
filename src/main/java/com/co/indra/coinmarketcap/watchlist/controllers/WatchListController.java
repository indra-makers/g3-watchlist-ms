package com.co.indra.coinmarketcap.watchlist.controllers;
import com.co.indra.coinmarketcap.watchlist.services.WatchListService;
import com.co.indra.coinmarketcap.watchlist.services.WatchListCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WatchListController {

    @Autowired
    WatchListCoinService watchListCoinService;
    @Autowired
    WatchListService watchListService;
}
