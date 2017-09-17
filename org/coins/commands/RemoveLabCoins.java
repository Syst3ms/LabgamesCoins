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

public class RemoveLabCoins implements CommandExecutor {

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if(label.equalsIgnoreCase("rlabcoins") && sender.isOp()) {
         if(args.length != 2) {
            sender.sendMessage(Coins.PREFIX + "&cErreur : Vous devez spécifier un joueur ainsi qu'un nombre de " + CoinType.LABCOINS.getDisplayName() + "&c !");
            return false;
         }

         int labcoins = Integer.parseInt(args[0]);
         if(labcoins < 0) {
            sender.sendMessage(Coins.PREFIX + "&cErreur : Vous ne pouvez pas ajouter un nombre de " + CoinType.LABCOINS.getDisplayName() + "&c inférieur à 0");
            return false;
         }

         Player target = Bukkit.getPlayer(args[1]);
         if(target == null) {
            sender.sendMessage(ChatColor.RED + "Erreur : Joueur non-connecté");
            return false;
         }

         CoinManager.getInstance().removeCoins(target, CoinType.LABCOINS, (long)labcoins);
         sender.sendMessage(Coins.PREFIX + "&aLe joueur s'est vu attribué " + labcoins + " " + CoinType.LABCOINS.getDisplayName());
      }

      return true;
   }
}
