package com.co.indra.coinmarketcap.watchlist.model.requests;

public class PriceAlert {
    private Double price;

    public PriceAlert() {
    }

    public PriceAlert(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}