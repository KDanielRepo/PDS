package POASaveObject;

import java.util.*;
import java.sql.*;

import POASaveObject.dbtest.*;

public class DBServant extends dbFacePOA {
  private String driver = "com.mysql.jdbc.Driver";
  private String dbUrl = "jdbc:mysql://localhost:3306/booksdb";
  private String lastQuery = "";

  private Connection connect = null;
  private Statement stmt = null;

  public void setDriver(String driver) {
   this.driver = driver;
  }

  public void setUrl(String dbUrl) {
   this.dbUrl = dbUrl;
  }

  public void setLastQuery(String query) {
   this.lastQuery = query;
  }
  
  public String getDriver() {
   return this.driver;
  }

  public String getUrl() {
   return this.dbUrl;
  }

  public String getLastQuery() {
   return this.lastQuery;
  }

  public void connect() {
  System.out.println(" Servant: connect()");
  try 
    {
     Class.forName(driver); // zaladowanie sterownika
    } 
    catch(java.lang.ClassNotFoundException e) {
     System.err.println(e.getMessage());
    }
    try {
       connect = DriverManager.getConnection(dbUrl); // polaczenie z baza
       stmt = connect.createStatement();        // zainicjowanie Statement 
    } catch (Exception e) {
        System.err.println("Problem z polaczeniem: "+dbUrl+ ": "+e.getMessage());
     } // catch    
   }

  public void disconnect() {
    System.out.println(" Servant: disconnect()");
    try {
       if (stmt != null) stmt.close();
       if (connect != null) connect.close();
   } catch (Exception e) {
      System.err.println("Problem z zakonczeniem polaczenia: "+dbUrl+ ": "+e.getMessage());
     } // catch
  }

  public String getName(int primary_key) {
   String data = "";
     try {
       String query = "select NAME from USERS where ID=" + primary_key;
       System.out.println(" Servant: getName(): query: " + query);
       ResultSet rs = stmt.executeQuery(query);
       rs.next();
       data = rs.getString("name");
       lastQuery = query;
     } catch (Exception e) {
       System.err.println("Problem z poleceniem select SQL do "+dbUrl+ ": "+e.getMessage());
       data = "Blad polaczenia z baza danych :(";
      }
    return data;
  }
}
