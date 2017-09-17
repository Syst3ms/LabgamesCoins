package org.coins.database.coins;


public enum CoinType {

   LABCOINS("&9Labcoins"),
   QUARKS("&bQuarks");

   private final String displayName;

   private CoinType(String displayName) {
      this.displayName = displayName;
   }

   public String getDisplayName() {
      return displayName;
   }
}
