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
        watchlist.setIdWatchList(rs.getLong("id_watchlist"));
        watchlist.setIdUser(rs.getLong("id_user"));
        watchlist.setWatchListName(rs.getString("watchlist_name"));
        watchlist.setWatchListDescription(rs.getString("watchlist_description"));
        watchlist.setWatchListPrivacy(rs.getBoolean("watchlist_privacy"));
        return watchlist;
    }
}

@Repository
public class WatchListRepository {
    @Autowired
    private JdbcTemplate template;
}
