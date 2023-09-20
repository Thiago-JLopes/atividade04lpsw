/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.semana04lpsw.model;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
/**
 *
 * @author Ciro
 */

// Esse Factory retorna uma nova conexão a cada chamada
public class ConFactory {
    public static final int MYSQL = 0; 
    public static final int JavaDB = 1;  
  
   public static Connection conexao(String nome, String senha,  
         int banco) throws ClassNotFoundException, SQLException {  
      switch (banco) {        
      case MYSQL:           
         Class.forName("org.gjt.mm.mysql.Driver"); 
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/lpsw?zeroDateTimeBehavior=convertToNull", nome, senha);
      case JavaDB:
          Class.forName("org.apache.derby.jdbc.ClientDriver"); 
          return DriverManager.getConnection("jdbc:derby://localhost:1527/teste", nome, senha);
      }  
      return null;
   }  
}
