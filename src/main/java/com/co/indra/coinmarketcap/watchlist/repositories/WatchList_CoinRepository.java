package com.co.indra.coinmarketcap.watchlist.repositories;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList_Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;



class WatchList_CoinRowMapper implements RowMapper<WatchList_Coin> {
    @Override
    public WatchList_Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
        WatchList_Coin watchlist_coin = new WatchList_Coin();
        watchlist_coin.setWatchlist_coin_id(rs.getLong("watchlist_coin_id"));
        watchlist_coin.setWatchlist_id(rs.getLong("watchlist_id"));
        watchlist_coin.setSymbol(rs.getString("symbol"));
        return watchlist_coin;
    }
}

@Repository
public class WatchList_CoinRepository {
    @Autowired
    private JdbcTemplate template;
}
