package org.coins.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

   private static Connection connection;


   public static void setConnection(String host, String database, int port, String user, String password) {
      if(!isConnected()) {
         try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
         } catch (SQLException var6) {
            var6.printStackTrace();
         }
      }

   }

   public static void removeConnection() {
      if(isConnected()) {
         try {
            connection.close();
         } catch (SQLException var1) {
            var1.printStackTrace();
         }
      }

   }

   public static Connection getConnection() {
      return connection;
   }

   public static boolean isConnected() {
      try {
         return connection != null && !connection.isClosed() && connection.isValid(5);
      } catch (SQLException var1) {
         var1.printStackTrace();
         return false;
      }
   }
}
