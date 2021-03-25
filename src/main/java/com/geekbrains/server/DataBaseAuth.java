package com.geekbrains.server;
import java.sql.*;


public class DataBaseAuth{
 private static DataBaseAuth express;
 private static Connection connection;
 private static PreparedStatement logPass;

 private DataBaseAuth(){}

 public static DataBaseAuth getExpress() throws SQLException, ClassNotFoundException {
  if (express == null) {
   loadConnect();
   prepStat();
   express = new DataBaseAuth();
  }
  return express;
  }

  private static void loadConnect() throws ClassNotFoundException, SQLException {
    Class.forName("org.sqlite.JDBC");
    connection = DriverManager.getConnection("jdbc:sqlite:WorkDB.db");
  }
  private static void prepStat()  {
     try {
         logPass = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
     }
     catch (SQLException e) {
         System.out.println("Ошибка коннекта");
         e.printStackTrace();
     }
  }
  public String logPass(String login, String password) throws SQLException {
       ResultSet resultSet = null;
       try {
           logPass.setString(1, login);
           logPass.setString(2, password);
           resultSet = logPass.executeQuery();
           if (resultSet.next()) {
               return resultSet.getString("nickname");
           }
       }
       catch (SQLException e) {
           e.printStackTrace();
       }
       finally {
           resultClose(resultSet);
       }
       return null;
 }

 private void resultClose(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
        resultSet.close();
     }
}

    public void close() throws SQLException {
        logPass.close();
        connection.close();

    }
}



