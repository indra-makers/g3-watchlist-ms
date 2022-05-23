package com.co.indra.coinmarketcap.watchlist.config;

public class Routes {
   public static final String WATCHLIST_RESOURCE = "/watchlists";
   public static final String WATCHLIST_PARAM_ID = "/{idWatchlist}";
   public static final String ADD_COIN_TO_WATCHLIST = "/{idWatchlist}/coins";
   public static final String GET_COIN_FROM_WATCHLIST = "/{idWatchlist}/coins";
   public static final String DELETE_COIN_FROM_WATCHLIST = "/{idWatchlist}/coins/{idWatchListCoin}";
   public static final String WATCHLIST_COIN_RESOURCE = "/watchlistcoins";
   public static final String USERS_RESOURCE = "/users";

}
