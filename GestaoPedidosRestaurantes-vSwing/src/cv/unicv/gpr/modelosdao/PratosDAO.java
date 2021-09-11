package cv.unicv.gpr.modelosdao;

import cv.unicv.gpr.conexao.ConnectionFactory;
import cv.unicv.gpr.modelos.Pratos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class PratosDAO {
    
    
    public void adicionar(Pratos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO pratos (descrição,categoria,preço) VALUES (?,?,?)");
            stmt.setString(1, p.getDescrição());
            stmt.setString(2, p.getCategoria());
            stmt.setInt(3, p.getPreço());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Prato adicionado com sucesso", "Adicionado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Pratos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE pratos SET descrição = ? ,categoria = ? ,preço = ? WHERE idPratos = ?");
            stmt.setString(1, p.getDescrição());
            stmt.setString(2, p.getCategoria());
            stmt.setInt(3, p.getPreço());
            stmt.setInt(4, p.getIdPratos());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Prato atualizado com sucesso", "Adicionado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Pratos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE FROM pratos WHERE idPratos = ?");
            stmt.setInt(1, p.getIdPratos());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Prato removido com sucesso", "Adicionado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Pratos> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pratos> pratos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pratos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pratos p = new Pratos();
                p.setIdPratos(rs.getInt("idPratos"));
                p.setDescrição(rs.getString("descrição"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreço(rs.getInt("preço"));
                pratos.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar dados: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pratos;
    }
    public ArrayList<Pratos> pesquisar( String palavraChave ){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pratos> pratos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pratos WHERE descrição LIKE ?");
            stmt.setString(1, "%"+palavraChave+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pratos p = new Pratos();
                p.setIdPratos(rs.getInt("idPratos"));
                p.setDescrição(rs.getString("descrição"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreço(rs.getInt("preço"));
                pratos.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar dados: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pratos;
    }
    
}
