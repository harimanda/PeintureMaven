/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author itu
 */
public class ConnectBase {
     Connection c = null;
  public Connection connect() {
     
       
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/peinture",
            "postgres", "itu");
         c.setAutoCommit(true);
     
          

      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      return c;
   }
}
    



