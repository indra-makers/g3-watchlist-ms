package com.co.indra.coinmarketcap.watchlist.model.entities;

import java.io.Serializable;

public class WatchList implements Serializable {
    private Long watchlist_id;
    private Long user_id;
    private String watchlist_name;
    private String watchlist_description;
    private boolean watchlist_privacy;

    public WatchList() {
    }

    public WatchList(Long watchlist_id, Long user_id, String watchlist_name, String watchlist_description, boolean watchlist_privacy) {
        this.watchlist_id = watchlist_id;
        this.user_id = user_id;
        this.watchlist_name = watchlist_name;
        this.watchlist_description = watchlist_description;
        this.watchlist_privacy = watchlist_privacy;
    }

    public Long getWatchlist_id() {
        return watchlist_id;
    }

    public void setWatchlist_id(Long watchlist_id) {
        this.watchlist_id = watchlist_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getWatchlist_name() {
        return watchlist_name;
    }

    public void setWatchlist_name(String watchlist_name) {
        this.watchlist_name = watchlist_name;
    }

    public String getWatchlist_description() {
        return watchlist_description;
    }

    public void setWatchlist_description(String watchlist_description) {
        this.watchlist_description = watchlist_description;
    }

    public boolean isWatchlist_privacy() {
        return watchlist_privacy;
    }

    public void setWatchlist_privacy(boolean watchlist_privacy) {
        this.watchlist_privacy = watchlist_privacy;
    }
}
