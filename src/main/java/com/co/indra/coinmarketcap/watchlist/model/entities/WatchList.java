package com.co.indra.coinmarketcap.watchlist.model.entities;

import java.io.Serializable;

public class WatchList implements Serializable {

   private Long idWatchList;
   private Long idUser;
   private String watchListName;
   private String watchListDescription;
   private boolean isPrivate;

   public WatchList() {
   }

   public WatchList(Long idWatchList, Long idUser, String watchListName, String watchListDescription,
         boolean isPrivate) {
      this.idWatchList = idWatchList;
      this.idUser = idUser;
      this.watchListName = watchListName;
      this.watchListDescription = watchListDescription;
      this.isPrivate = isPrivate;
   }

   public Long getIdWatchList() {
      return idWatchList;
   }

   public void setIdWatchList(Long idWatchList) {
      this.idWatchList = idWatchList;
   }

   public Long getIdUser() {
      return idUser;
   }

   public void setIdUser(Long idUser) {
      this.idUser = idUser;
   }

   public String getWatchListName() {
      return watchListName;
   }

   public void setWatchListName(String watchListName) {
      this.watchListName = watchListName;
   }

   public String getWatchListDescription() {
      return watchListDescription;
   }

   public void setWatchListDescription(String watchListDescription) {
      this.watchListDescription = watchListDescription;
   }

   public boolean isPrivate() {
      return isPrivate;
   }

   public void setPrivate(boolean aPrivate) {
      isPrivate = aPrivate;
   }
}
