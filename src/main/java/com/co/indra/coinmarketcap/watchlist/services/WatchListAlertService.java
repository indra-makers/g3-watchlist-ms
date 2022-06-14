package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.messagingQueue.AlertProducer;
import com.co.indra.coinmarketcap.watchlist.messagingQueue.model.Notification;
import com.co.indra.coinmarketcap.watchlist.messagingQueue.model.NotificationQueueBody;
import com.co.indra.coinmarketcap.watchlist.model.entities.CoinPriceAlert;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinPriceAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListAlertService {
    @Autowired
    private CoinPriceAlertRepository coinPriceAlertRepository;
    @Autowired
    private AlertProducer alertProducer;

    public void sendPriceAlert(String symbol, Double price) {
        List<CoinPriceAlert> coinPriceAlerts = coinPriceAlertRepository.findCoinsPriceAlertBySymbol(symbol);
        for (CoinPriceAlert e : coinPriceAlerts) {
            if (price >= e.getGoalPrice() - (e.getGoalPrice() * 0.02) && price <= e.getGoalPrice() + (e.getGoalPrice() * 0.02)){
                alertProducer.sendAlert(new NotificationQueueBody("SMS", e.getIdWatchlistCoin(), new Notification("Se ha actualizado el precio de la moneda", "ALERTA")));
                coinPriceAlertRepository.setIsNotifiedTrue(e);
            }
        }
    }

    public void createAlert(Long idWatchlist, CoinPriceAlert coinPriceAlert){
        coinPriceAlertRepository.addCoinAlertToWatchlist(idWatchlist, coinPriceAlert);
    }
}
