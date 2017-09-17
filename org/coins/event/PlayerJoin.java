package org.coins.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.coins.database.coins.CoinManager;

public class PlayerJoin implements Listener {

   @EventHandler
   public static void onJoin(PlayerJoinEvent event) {
      Player player = event.getPlayer();
      CoinManager.createAccount(player);
   }
}
