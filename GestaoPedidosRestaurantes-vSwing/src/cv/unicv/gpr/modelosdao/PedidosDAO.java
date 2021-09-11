package cv.unicv.gpr.modelosdao;

import cv.unicv.gpr.conexao.ConnectionFactory;
import cv.unicv.gpr.modelos.Pedidos;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PedidosDAO {
    
    public void fazerPedido(Pedidos p, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO pedidos (numeroPedido,numeroMesa,preçoTotal,dataPedido,pronto,entregue,idGarcom,idCozinheiro)"
                    + " VALUES (?,?,?,?,?,?,?,?)");
            stmt.setInt(1, p.getNumeroPedido());
            stmt.setInt(2, p.getNumeroMesa());
            stmt.setInt(3, p.getPreçoTotal());
            stmt.setString(4, p.getData());
            stmt.setBoolean(5, p.isPronto());
            stmt.setBoolean(6, p.isEntregue());
            stmt.setInt(7, p.getIdGarcom());
            stmt.setInt(8, p.getIdCozinha());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(este, "Pedido feito com sucesso", "Pedido Feito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao fazer pedido: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void processarPedido(Pedidos p, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE pedidos SET pronto = ? WHERE numeroPedido = ?");
            stmt.setBoolean(1, p.isPronto());
            stmt.setInt(2, p.getNumeroPedido());
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement("UPDATE pedidos SET idCozinheiro = ? WHERE numeroPedido = ?");
            stmt.setInt(1, p.getIdCozinha());
            stmt.setInt(3, p.getNumeroPedido());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(este, "Pedido processado com sucesso", "Pedido Processado", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao processar pedido: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void entregarPedido(Pedidos p, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE pedidos SET entregue = ? WHERE numeroPedido = ?");
            stmt.setBoolean(1, p.isEntregue());
            stmt.setInt(2, p.getNumeroPedido());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(este, "Pedido entregue com sucesso", "Pedido Processado", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao entregue pedido: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
}
