package org.coins.manager;

import org.coins.Coins;
import org.coins.commands.AddQuarks;
import org.coins.commands.AddLabCoins;
import org.coins.commands.RemoveQuarks;
import org.coins.commands.RemoveLabCoins;

public class CommandsManager {

   public static void register(Coins coins) {
      coins.getCommand("aquarks").setExecutor(new AddQuarks());
      coins.getCommand("alabcoins").setExecutor(new AddLabCoins());
      coins.getCommand("rquarks").setExecutor(new RemoveQuarks());
      coins.getCommand("rlabcoins").setExecutor(new RemoveLabCoins());
   }
}
