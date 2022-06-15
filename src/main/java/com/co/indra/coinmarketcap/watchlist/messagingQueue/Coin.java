package com.co.indra.coinmarketcap.watchlist.messagingQueue;

import org.hibernate.dialect.LobMergeStrategy;

public class Coin {
    private String symbol;
    private Double price;

    public Coin() {
    }

    public Coin(String symbol, Double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
