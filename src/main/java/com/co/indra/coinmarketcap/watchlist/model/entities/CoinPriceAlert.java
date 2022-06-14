package com.co.indra.coinmarketcap.watchlist.model.entities;

import java.io.Serializable;

public class CoinPriceAlert implements Serializable {

    private int idCoinPriceAlert;
    private String symbol;
    private double goalPrice;
    private int idWatchlistCoin;
    private boolean isNotified;

    public CoinPriceAlert() {
    }

    public CoinPriceAlert(int idCoinPriceAlert, String symbol, double goalPrice, int idWatchlistCoin, boolean isNotified) {
        this.idCoinPriceAlert = idCoinPriceAlert;
        this.symbol = symbol;
        this.goalPrice = goalPrice;
        this.idWatchlistCoin = idWatchlistCoin;
        this.isNotified = isNotified;
    }

    public int getIdCoinPriceAlert() {
        return idCoinPriceAlert;
    }

    public void setIdCoinPriceAlert(int idCoinPriceAlert) {
        this.idCoinPriceAlert = idCoinPriceAlert;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getGoalPrice() {
        return goalPrice;
    }

    public void setGoalPrice(double goalPrice) {
        this.goalPrice = goalPrice;
    }

    public int getIdWatchlistCoin() {
        return idWatchlistCoin;
    }

    public void setIdWatchlistCoin(int idWatchlistCoin) {
        this.idWatchlistCoin = idWatchlistCoin;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }
}