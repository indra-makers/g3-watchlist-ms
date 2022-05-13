package com.co.indra.coinmarketcap.watchlist.repositories;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;



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
}
