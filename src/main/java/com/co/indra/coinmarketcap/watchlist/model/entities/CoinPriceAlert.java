package com.co.indra.coinmarketcap.watchlist.model.entities;

import java.io.Serializable;

public class CoinPriceAlert implements Serializable {

    private int idCoinPriceAlert;
    private String symbol;
    private double goalPrice;
    private int idWatchlistCoin;

    public CoinPriceAlert(int idCoinPriceAlert, String symbol, double goalPrice, int idWatchlist) {
        this.idCoinPriceAlert = idCoinPriceAlert;
        this.symbol = symbol;
        this.goalPrice = goalPrice;
        this.idWatchlistCoin = idWatchlist;
    }

    public CoinPriceAlert() {
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

    public int getIdWatchlist() {
        return idWatchlistCoin;
    }

    public void setIdWatchlist(int idWatchlist) {
        this.idWatchlistCoin = idWatchlist;
    }
}
