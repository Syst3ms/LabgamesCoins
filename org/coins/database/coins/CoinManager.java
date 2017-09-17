package org.coins.database.coins;

import org.bukkit.entity.Player;
import org.coins.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinManager {
    private static CoinManager instance;

    static {
        instance = new CoinManager();
    }

    private CoinManager() {}

    public static CoinManager getInstance() {
        return instance;
    }

   public static void createAccount(Player player) {
      try {
         PreparedStatement e = Database.getConnection().prepareStatement("SELECT quarks AND labcoins FROM coins WHERE uuid=?");
         e.setString(1, player.getUniqueId().toString());
         ResultSet rs = e.executeQuery();
         if(!rs.next()) {
            e.close();
            e = Database.getConnection().prepareStatement("INSERT INTO coins(uuid, quarks, labcoins) VALUES(?, ?, ?)");
            e.setString(1, player.getUniqueId().toString());
            e.setLong(2, 0L);
            e.setLong(3, 1000L);
            e.executeUpdate();
         }
      } catch (SQLException var8) {
         var8.printStackTrace();
      }

   }

   public long getCoins(Player player, CoinType type) {
      try {
         PreparedStatement e = Database.getConnection().prepareStatement("SELECT " + type.name().toLowerCase() + " FROM coins WHERE uuid=?");
         e.setString(1, player.getUniqueId().toString());
         ResultSet rs = e.executeQuery();
         if(rs.next()) {
            return rs.getLong(type.name().toLowerCase());
         }
      } catch (SQLException var4) {
         var4.printStackTrace();
      }

      return 0L;
   }

   public void addCoins(Player player, CoinType type, long amount) {
      if(amount >= 0L) {
         try {
            PreparedStatement e = Database.getConnection().prepareStatement("SELECT " + type.name().toLowerCase() + " FROM coins WHERE uuid=? ");
            e.setString(1, player.getUniqueId().toString());
            ResultSet rs = e.executeQuery();
            if(rs.next()) {
               long money = rs.getLong(type.name().toLowerCase());
               e.close();
               e = Database.getConnection().prepareStatement("UPDATE coins SET " + type.name().toLowerCase() + "=? WHERE uuid=?");
               e.setLong(1, money + amount);
               e.setString(2, player.getUniqueId().toString());
               e.executeUpdate();
            }
         } catch (SQLException var8) {
            var8.printStackTrace();
         }

      }
   }

   public void removeCoins(Player player, CoinType type, long amount) {
      if(amount >= 0L) {
         try {
            PreparedStatement e = Database.getConnection().prepareStatement("SELECT " + type.name().toLowerCase() + " FROM coins WHERE uuid=?");
            e.setString(1, player.getUniqueId().toString());
            ResultSet rs = e.executeQuery();
            if(rs.next()) {
               long money = rs.getLong(type.name().toLowerCase());
               e.close();
               e = Database.getConnection().prepareStatement("UPDATE coins SET " + type.name().toLowerCase() + "=? WHERE uuid=?");
               e.setLong(1, money - amount);
               e.executeUpdate();
            }
         } catch (SQLException var8) {
            var8.printStackTrace();
         }

      }
   }
}
