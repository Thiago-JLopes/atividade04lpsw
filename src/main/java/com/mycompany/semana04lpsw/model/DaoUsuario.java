/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.semana04lpsw.model;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.ArrayList;
import java.util.List;
 
//import javax.swing.JOptionPane;  
  

/**
 *
 * @author Ciro
 */
public class DaoUsuario {
    // Configura essas variáveis de acordo com o seu banco  
   private int BANCO;
   private String NOME = "bancoD01";
   private String SENHA = "1234";  
  
   private Connection con;  
   private Statement comando;  
   
   public DaoUsuario (int _banco, String _nome, String _pass){
       BANCO = _banco;
       NOME = _nome;
       SENHA = _pass;
   }
  
   public void apagar(String id) {  
      conectar();  
      try {  
         comando  
               .executeUpdate("DELETE FROM usuario WHERE matricula = '" + id  
                     + "';");  
      } catch (SQLException e) {  
         imprimeErro("Erro ao apagar USUARIO", e.getMessage());  
      } finally {  
         fechar();  
      }  
   }  
  
   public List<Usuario> buscarTodos() {  
      conectar();  
      List<Usuario> resultados = new ArrayList<>();  
      ResultSet rs;  
      try {  
         rs = comando.executeQuery("SELECT * FROM usuario");  
         while (rs.next()) {  
            Usuario temp = new Usuario();  
            // pega todos os atributos da pessoa  
            temp.setId(rs.getInt("matricula"));  
            temp.setNome(rs.getString("nome"));  
            temp.setSenha(rs.getString("senha"));  
            resultados.add(temp);  
         }  
         return resultados;  
      } catch (SQLException e) {  
         imprimeErro("Erro ao buscar USUARIOS", e.getMessage());  
         return null;  
      }  finally {  
         fechar();  
      }  
   }  
  
   public void atualizar(Usuario aluno) {  
      conectar();  
      String com = "UPDATE usuario SET nome = '" + aluno.getNome()  
            + "', senha ='" + aluno.getSenha()
            + "' WHERE  matricula = '" + aluno.getId()+ "';";  
      try {  
         comando.executeUpdate(com);  
      } catch (SQLException e) {  
         imprimeErro("Erro ao atualizar USUARIO", e.getMessage());  
      } finally {  
         fechar();  
      }  
   }  
  
   // Busca pela chave matrícula
   public Usuario buscar(String id) {  
      conectar();  
      ResultSet rs;  
      Usuario temp = null;
      if (id== null) return null;
      try {  
          String cmd = "SELECT * FROM usuario WHERE nome = '"  
               + id +"'";
         rs = comando.executeQuery(cmd);  
         if (rs.next()) {  
            temp = new Usuario();  
            // pega todos os atributos da pessoa  
            temp.setId(rs.getInt("id"));  
            temp.setNome(rs.getString("nome"));  
            temp.setSenha(rs.getString("senha"));    
         }  
         return temp;  
      } catch (SQLException e) {  
         imprimeErro("Erro ao buscar USUARIO", e.getMessage());  
         return null;  
      }  finally {  
         fechar();  
      }  
  
   }  
  
   public void insere(Usuario aluno){  
      conectar();  
      try {  
         comando.executeUpdate("INSERT INTO usuario VALUES("  
               + aluno.getId() + ", '" 
               + aluno.getNome() + "','"  
                 + aluno.getSenha() + "')");   
      } catch (SQLException e) {  
         imprimeErro("Erro ao inserir USUARIO", e.getMessage());  
      } finally {  
         fechar();  
      }  
   }  
  
   private void conectar() {  
      try {  
         con = ConFactory.conexao(NOME, SENHA, BANCO);  
         comando = con.createStatement();   
      } catch (ClassNotFoundException e) {  
         imprimeErro("Erro ao carregar o driver", e.getMessage());  
      } catch (SQLException e) {  
         imprimeErro("Erro ao conectar", e.getMessage());  
      }  
   }  
  
   private void fechar() {  
      try {  
         comando.close();  
         con.close();   
      } catch (SQLException e) {  
         imprimeErro("Erro ao fechar conexão", e.getMessage());  
      }  
   }  
  
   private void imprimeErro(String msg, String msgErro) {  
      //JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);  
      System.err.println(msg);  
      System.out.println(msgErro);  
      System.exit(0);  
   }  
}
