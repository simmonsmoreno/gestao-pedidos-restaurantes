package cv.unicv.gpr.modelosdao;

import cv.unicv.gpr.conexao.ConnectionFactory;
import cv.unicv.gpr.modelos.Funcionarios;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FuncionarioDAO {
    
    public void adicionar(Funcionarios f, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO funcionarios (nome,senha,cargo,data_nasc,sexo,morada,email,telefone,data_ent)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getSenha());
            stmt.setString(3, f.getCargo());
            stmt.setString(4, f.getDataNasc());
            stmt.setString(5, f.getSexo());
            stmt.setString(6, f.getMorada());
            stmt.setString(7, f.getEmail());
            stmt.setInt(8, f.getTelefone());
            stmt.setString(9, f.getDataEntrada());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(este, "Funcionario adicionado com sucesso", "Adicionado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao adicionar funcionario: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Funcionarios f, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE pratos SET nome = ? ,senha = ? ,cargo = ?, data_nasc = ?, sexo = ?,"
                    + "morada = ?, email = ?, telefone = ?, data_ent = ? WHERE id = ?");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getSenha());
            stmt.setString(3, f.getCargo());
            stmt.setString(4, f.getDataNasc());
            stmt.setString(5, f.getSexo());
            stmt.setString(6, f.getMorada());
            stmt.setString(7, f.getEmail());
            stmt.setInt(8, f.getTelefone());
            stmt.setString(9, f.getDataEntrada());
            stmt.setInt(10, f.getIdFuncionarios());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(este, "Funcionario atualizado com sucesso", "Atualizado", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao atualizar funcionario: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Funcionarios f, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE FROM funcionarios WHERE id = ?");
            stmt.setInt(1, f.getIdFuncionarios());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(este, "Funcionario removido com sucesso", "Removido", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao remover funcionario: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Funcionarios> listar(Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionarios");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios f = new Funcionarios();
                f.setIdFuncionarios(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setSenha(rs.getString("senha"));
                f.setCargo(rs.getString("cargo"));
                f.setDataNasc(rs.getString("data_nasc"));
                f.setSexo(rs.getString("sexo"));
                f.setMorada(rs.getString("morada"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getInt("telefone"));
                f.setDataEntrada(rs.getString("data_ent"));
                funcionarios.add(f);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao recuperar dados: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return funcionarios;
    }
    
    public ArrayList<Funcionarios> pesquisar(String palavraChave, Component este){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionarios WHERE nome LIKE ?");
            stmt.setString(1, "%"+palavraChave+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios f = new Funcionarios();
                f.setIdFuncionarios(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setSenha(rs.getString("senha"));
                f.setCargo(rs.getString("cargo"));
                f.setDataNasc(rs.getString("data_nasc"));
                f.setSexo(rs.getString("sexo"));
                f.setMorada(rs.getString("morada"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getInt("telefone"));
                f.setDataEntrada(rs.getString("data_ent"));
                funcionarios.add(f);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(este, "Erro ao pesquisar dados: \n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return funcionarios;
    }
}
