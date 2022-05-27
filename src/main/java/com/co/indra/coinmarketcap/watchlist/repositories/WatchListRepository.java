package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class WatchListRowMapper implements RowMapper<WatchList> {
   @Override
   public WatchList mapRow(ResultSet rs, int rowNum) throws SQLException {
      WatchList watchlist = new WatchList();
      watchlist.setIdWatchList(rs.getLong("id_watchlist"));
      watchlist.setIdUser(rs.getLong("id_user"));
      watchlist.setWatchListName(rs.getString("watchlist_name"));
      watchlist.setWatchListDescription(rs.getString("watchlist_description"));
      watchlist.setPrivate(rs.getBoolean("is_private"));
      return watchlist;
   }
}

@Repository
public class WatchListRepository {
   @Autowired
   private JdbcTemplate template;

   public void createWatchlist(WatchList watchList) {
      template.update(
            "INSERT INTO tbl_watchlists(id_user, watchlist_name, watchlist_description, is_private) values(?,?,?,?)",
            watchList.getIdUser(), watchList.getWatchListName(), watchList.getWatchListDescription(),
            watchList.isPrivate());
   }

   public List<WatchList> findWatchListById(Long idWatchList) {
      return template.query(
            "SELECT id_watchlist, id_user, watchlist_name, watchlist_description, is_private FROM tbl_watchlists WHERE id_watchlist=?",
            new WatchListRowMapper(), idWatchList);
   }

   public List<WatchList> findWatchListByName(String watchlist_name) {
      return template.query(
            "SELECT id_watchlist, id_user, watchlist_name, watchlist_description, is_private FROM tbl_watchlists WHERE watchlist_name=?",
            new WatchListRowMapper(), watchlist_name);
   }

   public List<WatchList> findWatchListByUserId(Long id_user) {
      return template.query(
            "SELECT id_watchlist, id_user, watchlist_name, watchlist_description, is_private FROM tbl_watchlists WHERE id_user=?",
            new WatchListRowMapper(), id_user);
   }

   
   
   // Eliminar watchlist
   public void deleteWatchlist(Long idWatchList) {
      template.update("DELETE FROM tbl_watchlists WHERE id_watchlist = ? ", idWatchList);
   }
   
   
   

}