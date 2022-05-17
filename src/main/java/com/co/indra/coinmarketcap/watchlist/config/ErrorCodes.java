package com.co.indra.coinmarketcap.watchlist.config;

public enum ErrorCodes {

    WATCHLIST_LIMIT_REACHED("The watchlist limit has been reached", "001"),
    SYMBOL_EXISTS_IN_WATCHLIST("The coin symbol is already registered on the watchlist", "002"),
    MISSING_PARAMETERS("Missing some parameters to add watchlist", "003"),
    MISSING_COIN_PARAMETERS("Missing some parameters to add coin", "004"),
    USER_DOES_NOT_EXIST("The user does not exist", "005");
    String message;
    String code;
    ErrorCodes(String message, String code) {
        this.message = message;
        this.code = code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
