package com.co.indra.coinmarketcap.watchlist.model.entities;

import java.io.Serializable;

public class WatchListCoin implements Serializable {

   private Long idWatchListCoin;
   private Long idWatchList;
   private String symbol;

   public WatchListCoin() {
   }

   public WatchListCoin(Long idWatchListCoin, Long idWatchList, String symbol) {
      this.idWatchListCoin = idWatchListCoin;
      this.idWatchList = idWatchList;
      this.symbol = symbol;
   }

   public Long getIdWatchListCoin() {
      return idWatchListCoin;
   }

   public void setIdWatchListCoin(Long idWatchListCoin) {
      this.idWatchListCoin = idWatchListCoin;
   }

   public Long getIdWatchList() {
      return idWatchList;
   }

   public void setIdWatchList(Long idWatchList) {
      this.idWatchList = idWatchList;
   }

   public String getSymbol() {
      return symbol;
   }

   public void setSymbol(String symbol) {
      this.symbol = symbol;
   }
}
