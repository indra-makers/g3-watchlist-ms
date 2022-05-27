package com.co.indra.coinmarketcap.watchlist.exceptions;

import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;

public class BusinessExceptions extends RuntimeException {

   private ErrorCodes bodyCode;

   public BusinessExceptions(String message) {
      super(message);
   }

   public BusinessExceptions(ErrorCodes bodycode) {
      super(bodycode.getMessage());
      this.bodyCode = bodycode;
   }

   public String getCode() {
      return bodyCode.getCode();
   }
}
