package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.config.Routes;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.USERS_RESOURCE)
public class UserController {
   @Autowired
   private WatchListService watchListService;

   @GetMapping()
   public List<WatchList> getWatchlistByUserId(@RequestParam(name = "idUser") Long idUser) {
      return watchListService.getWatchlist(idUser);
   }
}
