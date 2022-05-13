package com.co.indra.coinmarketcap.watchlist.repositories;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


class WatchListRowMapper implements RowMapper<WatchList> {
    @Override
    public WatchList mapRow(ResultSet rs, int rowNum) throws SQLException {
        WatchList watchlist = new WatchList();
        watchlist.setWatchlist_id(rs.getLong("watchlist_id"));
        watchlist.setUser_id(rs.getLong("user_id"));
        watchlist.setWatchlist_name(rs.getString("watchlist_name"));
        watchlist.setWatchlist_description(rs.getString("watchlist_description"));
        watchlist.setWatchlist_privacy(rs.getBoolean("watchlist_privacy"));
        return watchlist;
    }
}

@Repository
public class WatchListRepository {
    @Autowired
    private JdbcTemplate template;
}
