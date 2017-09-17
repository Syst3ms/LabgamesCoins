package org.coins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.coins.Coins;
import org.coins.database.coins.CoinManager;
import org.coins.database.coins.CoinType;

public class AddQuarks implements CommandExecutor {

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if(label.equalsIgnoreCase("aquarks") && sender.isOp()) {
         if(args.length != 2) {
            sender.sendMessage(Coins.PREFIX + "&cErreur : Vous devez spécifier un joueur ainsi qu'un nombre de " + CoinType.QUARKS.getDisplayName() + "&c !");
            return false;
         }

         int quarks = Integer.parseInt(args[0]);
         if(quarks < 0) {
            sender.sendMessage(Coins.PREFIX + "&cErreur : Vous ne pouvez pas ajouter un nombre de " + CoinType.QUARKS.getDisplayName() + "&c inférieur à 0");
            return false;
         }

         Player target = Bukkit.getPlayer(args[1]);
         if(target == null) {
            sender.sendMessage(Coins.PREFIX + "&cErreur : Joueur non-connecté");
            return false;
         }

         CoinManager.getInstance().addCoins(target, CoinType.QUARKS, (long)quarks);
         sender.sendMessage(Coins.PREFIX + "&cLe joueur s\'est vu attribué " + quarks + " " + CoinType.QUARKS.getDisplayName());
      }
      return true;
   }
}
