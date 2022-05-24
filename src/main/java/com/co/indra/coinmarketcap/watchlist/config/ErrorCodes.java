package com.co.indra.coinmarketcap.watchlist.config;

public enum ErrorCodes {
   WATCHLIST_LIMIT_REACHED("THE WATCHLIST LIMIT HAS BEEN REACHED", "001"),
   SYMBOL_EXISTS_IN_WATCHLIST("THE COIN SYMBOL IS ALREADY REGISTERED ON THE WATCHLIST", "002"),
   MISSING_PARAMETERS("MISSING SOME PARAMETERS TO ADD WATCHLIST", "003"),
   MISSING_COIN_PARAMETERS("MISSING SOME PARAMETERS TO ADD COIN", "004"),
   USER_DOES_NOT_EXIST("THE USER DOES NOT EXIST", "005"),
   WATCHlLIST_NOT_EXIST("THE WATCHLIST NOT EXIST","006"),
   COIN_IN_WATCHlLIST_NOT_EXIST("THE COIN IN THE WATCHLIST NOT EXIST","007"),
   ONLY_ONE_GOAL_PER_COIN("THERE SHOULD ONE GOAL PER COIN", "008");

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
