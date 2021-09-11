/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.unicv.gpr.modelosdao;

import cv.unicv.gpr.conexao.ConnectionFactory;
import cv.unicv.gpr.modelos.Pedidos;
import cv.unicv.gpr.modelos.Pratos;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Pedidos_has_pratosDAO {
    
    public void fazerPedido(Pedidos p, Pratos pr, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO pedidos_tem_pratos (numeroPedido, idPrato) VALUES (?,?)");
            stmt.setInt(1, p.getNumeroPedido());
            stmt.setInt(2, pr.getIdPratos());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro relacionar prato e pedido: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
   
}
