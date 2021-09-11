/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.unicv.gpr.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/gestaopedidosrestaurantes";
    public static final String USER = "root";
    public static final String PASSWORD = "";
        
    public static Connection getConnection(){
        
        java.sql.Connection conexao = null;
        
        try{
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL,USER, PASSWORD);
            System.out.println("Conexão feita a base de dados com sucesso: \n" + conexao);
            return conexao;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro na conexão com a base de dados: \n" + e.getMessage());
            return null;
        }
    }
    
    public static void closeConnection(Connection conn){
        
        try {
            if(conn!=null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        
        closeConnection(conn);
        
        try {
            if(stmt!=null) stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(conn, stmt);
        
        try {
            if(rs!=null) rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
