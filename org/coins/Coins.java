package org.coins;

import org.bukkit.plugin.java.JavaPlugin;
import org.coins.database.Database;
import org.coins.manager.CommandsManager;
import org.coins.manager.EventsManager;

public class Coins extends JavaPlugin {
   public static final String PREFIX = "[&e&lCoins&r]";
   private static Coins instance;

   public void onEnable() {
      instance = this;
      try {
         Class.forName("org.coins.database.CoinManager", true, ClassLoader.getSystemClassLoader());
      } catch (ClassNotFoundException ignored) {
      }
      EventsManager.register(this);
      CommandsManager.register(this);
      Database.setConnection("localhost", "LabGames", 3306, "root", "labgamesmc");
   }

   public void onDisable() {
      Database.removeConnection();
   }

   public static Coins getInstance() {
      return instance;
   }
}
