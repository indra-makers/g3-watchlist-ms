package com.co.indra.coinmarketcap.watchlist.repositories;


import com.co.indra.coinmarketcap.watchlist.model.entities.CoinPriceAlert;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class CoinPriceAlertRowMapper implements RowMapper<CoinPriceAlert> {
    @Override
    public CoinPriceAlert mapRow(ResultSet rs, int rowNum) throws SQLException {
        CoinPriceAlert coinPriceAlert = new CoinPriceAlert();
        coinPriceAlert.setIdCoinPriceAlert(rs.getInt("id_coin_price_alert"));
        coinPriceAlert.setGoalPrice(rs.getDouble("goal_price"));
        coinPriceAlert.setSymbol(rs.getString("symbol"));
        coinPriceAlert.setNotified(rs.getBoolean("isNotified"));
        return coinPriceAlert;
    }
}

@Repository
public class CoinPriceAlertRepository {

    @Autowired
    private JdbcTemplate template;


    public List<CoinPriceAlert> findCoinPriceAlertBySymbolAndIdWatchListCoin(String symbol, int idWatchlistCoin){
        return template.query(
                "SELECT id_coin_price_alert, goal_price, symbol, id_watchlist_coin, isNotified FROM tbl_coin_price_alerts WHERE id_watchlist_coin =? AND symbol =?",
                new CoinPriceAlertRowMapper(), idWatchlistCoin, symbol);
    }

    public List<CoinPriceAlert> findCoinsPriceAlertBySymbol(String symbol){
        return template.query(
                "SELECT id_coin_price_alert, goal_price, symbol, id_watchlist_coin, isNotified FROM tbl_coin_price_alerts WHERE isNotified = false AND symbol =?",
                new CoinPriceAlertRowMapper(),symbol);
    }

    public void addCoinAlertToWatchlist(Long idWatchlist, CoinPriceAlert coinPriceAlert){
        template.update("INSERT INTO tbl_coin_price_alerts(goal_price, symbol, id_watchlist_coin) values(?,?,?)",
                coinPriceAlert.getGoalPrice(), coinPriceAlert.getSymbol(), idWatchlist);
    }
    public void setIsNotifiedTrue(CoinPriceAlert coinPriceAlert){
        template.update("UPDATE tbl_coin_price_alerts SET isNotified=? WHERE id_coin_price_alert = ?",
                true, coinPriceAlert.getIdCoinPriceAlert());
    }

}
