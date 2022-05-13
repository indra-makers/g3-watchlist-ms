package com.co.indra.coinmarketcap.watchlist.config;

public class Routes {
    public static final String WATCHLIST_PARAM_ID = "/{watchlist_id}";
    public static final String ADD_COIN_TO_WATCHLIST = "/{watchlist_id}/coins";
    public static final String GET_COIN_FROM_WATCHLIST = "/{watchlist_id}/coins";
    public static final String DELETE_COIN_FROM_WATCHLIST = "/{watchlist_id}/coins/{symbol}";
}
