package gestaorestaurantesfx.modelo.dao;


import gestaorestaurantesfx.Notificacao;
import gestaorestaurantesfx.modelo.basedados.ConnectionFactory;
import gestaorestaurantesfx.modelo.domain.Pedidos;
import gestaorestaurantesfx.modelo.domain.Pratos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PratosDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Pratos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO pratos (descrição,categoria,preço) VALUES (?,?,?)");
            stmt.setString(1, p.getDescrição());
            stmt.setString(2, p.getCategoria());
            stmt.setInt(3, p.getPreço());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Prato adicionado com sucesso");
            
        } catch (SQLException ex) {
            notific.showErro("Erro ao adicionar prato: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Pratos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE pratos SET descrição = ? ,categoria = ? ,preço = ? WHERE idPrato = ?");
            stmt.setString(1, p.getDescrição());
            stmt.setString(2, p.getCategoria());
            stmt.setInt(3, p.getPreço());
            stmt.setInt(4, p.getIdPratos());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Prato atualizado com sucesso");
            
        } catch (SQLException ex) {
            notific.showErro("Erro ao atualizar prato: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Pratos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE FROM pratos WHERE idPrato = ?");
            stmt.setInt(1, p.getIdPratos());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Prato removido com sucesso");
            
        } catch (SQLException ex) {
            notific.showErro("Erro ao remover prato: \n"+ex.getMessage());
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
                p.setIdPratos(rs.getInt("idPrato"));
                p.setDescrição(rs.getString("descrição"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreço(rs.getInt("preço"));
                pratos.add(p);
            }
        } catch (SQLException ex) {
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pratos;
    }
    
    public ArrayList<Pratos> listarProdutos(Pedidos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pratos> pratos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT Pratos.*, Produtos.qtdade\n "
                    + "FROM Pratos INNER JOIN (Pedidos INNER JOIN Produtos "
                    + "ON Pedidos.numeroPedido = Produtos.numeroPedido) ON Pratos.idPrato = Produtos.idPrato "
                    + "WHERE ( ((Pratos.idPrato)=Produtos.idPrato) AND ((Produtos.numeroPedido)=Pedidos.numeroPedido) "
                    + "AND ((Pedidos.numeroPedido)= ?))");
            stmt.setInt(1, p.getNumeroPedido());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pratos prato = new Pratos();
                prato.setIdPratos(rs.getInt("idPrato"));
                prato.setDescrição(rs.getString("descrição"));
                prato.setCategoria(rs.getString("categoria"));
                prato.setPreço(rs.getInt("preço"));
                prato.setQtdade(rs.getInt("qtdade"));
                pratos.add(prato);
            }
        } catch (SQLException ex) {
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pratos;
    }
    
    public ArrayList<Pratos> listarCategoria(String categoria){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pratos> pratos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pratos WHERE categoria = ?");
            stmt.setString(1, categoria);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pratos p = new Pratos();
                p.setIdPratos(rs.getInt("idPrato"));
                p.setDescrição(rs.getString("descrição"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreço(rs.getInt("preço"));
                pratos.add(p);
            }
        } catch (SQLException ex) {
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
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
                p.setIdPratos(rs.getInt("idPrato"));
                p.setDescrição(rs.getString("descrição"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreço(rs.getInt("preço"));
                pratos.add(p);
            }
        } catch (SQLException ex) {
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pratos;
    }
    
    public ArrayList<Pratos> pesquisarId(int idPrato){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pratos> pratos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pratos WHERE produtos.idPrato LIKE ?");
            stmt.setInt(1, idPrato);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pratos p = new Pratos();
                p.setIdPratos(rs.getInt("idPrato"));
                p.setDescrição(rs.getString("descrição"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreço(rs.getInt("preço"));
                pratos.add(p);
            }
        } catch (SQLException ex) {
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pratos;
    }
    
}
