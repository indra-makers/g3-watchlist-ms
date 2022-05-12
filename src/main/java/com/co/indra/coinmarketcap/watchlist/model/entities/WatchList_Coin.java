package com.co.indra.coinmarketcap.watchlist.model.entities;

import java.io.Serializable;

public class WatchList_Coin implements Serializable {
    private Long watchlist_coin_id;
    private Long watchlist_id;
    private String Symbol;

    public WatchList_Coin() {
    }

    public WatchList_Coin(Long watchlist_coin_id, Long watchlist_id, String symbol) {
        this.watchlist_coin_id = watchlist_coin_id;
        this.watchlist_id = watchlist_id;
        Symbol = symbol;
    }

    public Long getWatchlist_coin_id() {
        return watchlist_coin_id;
    }

    public void setWatchlist_coin_id(Long watchlist_coin_id) {
        this.watchlist_coin_id = watchlist_coin_id;
    }

    public Long getWatchlist_id() {
        return watchlist_id;
    }

    public void setWatchlist_id(Long watchlist_id) {
        this.watchlist_id = watchlist_id;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }
}
