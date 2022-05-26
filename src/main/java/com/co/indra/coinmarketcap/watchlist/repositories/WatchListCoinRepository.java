package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class WatchListCoinRowMapper implements RowMapper<WatchListCoin> {
   @Override
   public WatchListCoin mapRow(ResultSet rs, int rowNum) throws SQLException {
      WatchListCoin watchlistcoin = new WatchListCoin();
      watchlistcoin.setIdWatchListCoin(rs.getLong("id_watchlist_coin"));
      watchlistcoin.setIdWatchList(rs.getLong("id_watchlist"));
      watchlistcoin.setSymbol(rs.getString("symbol"));
      return watchlistcoin;
   }
}

@Repository
public class WatchListCoinRepository {
   @Autowired
   private JdbcTemplate template;

   public void addCoinToWatchList(WatchListCoin watchListCoin, Long idWatchList) {
      template.update("INSERT INTO tbl_watchlists_coins(id_watchlist, symbol) values(?,?)", idWatchList,
            watchListCoin.getSymbol());
   }

   public List<WatchListCoin> findWatchListCoinBySymbol(String symbol) {
      return template.query("SELECT id_watchlist_coin, id_watchlist, symbol FROM tbl_watchlists_coins WHERE symbol=?",
            new WatchListCoinRowMapper(), symbol);
   }

   public List<WatchListCoin> findWatchListCoinByWatchlist(Long id_watchlist) {
      return template.query(
            "SELECT id_watchlist_coin, id_watchlist, symbol FROM tbl_watchlists_coins WHERE id_watchlist=?",
            new WatchListCoinRowMapper(), id_watchlist);
   }

   public List<WatchListCoin> findWatchListCoinByCoin(Long idWatchListCoin) {
      return template.query(
            "SELECT id_watchlist_coin, id_watchlist, symbol FROM tbl_watchlists_coins WHERE id_watchlist_coin =?",
            new WatchListCoinRowMapper(), idWatchListCoin);
   }

   // Eliminar moneda a watchlist
   public void deleteCoinToWatchList(Long idWatchList, Long idWatchListCoin) {

      template.update("DELETE FROM tbl_watchlists_coins WHERE id_watchlist = ? AND id_watchlist_coin = ?", idWatchList,
            idWatchListCoin);

   }

}