package gestaorestaurantesfx.modelo.dao;

import gestaorestaurantesfx.Notificacao;
import gestaorestaurantesfx.modelo.basedados.ConnectionFactory;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionariosDAO {

    private final Notificacao notific = new Notificacao();

    public void adicionar(Funcionarios f){

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO funcionarios (nome,senha,cargo,dataNasc,sexo,morada,"
                    + "email,telefone,dataEnt) VALUES (?,?,?,?,?,?,?,?,?)");
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

            notific.showNotificacao("Adicionado", "Funcionario adicionado com sucesso");

        } catch (SQLException ex) {

            notific.showErro("Erro ao adicionar funcionario: \n"+ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public void atualizar(Funcionarios f){

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE funcionarios SET nome = ? ,senha = ? ,cargo = ?, sexo = ?,"
                    + "morada = ?, email = ?, telefone = ? WHERE idFuncionario = ?");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getSenha());
            stmt.setString(3, f.getCargo());
            stmt.setString(4, f.getSexo());
            stmt.setString(5, f.getMorada());
            stmt.setString(6, f.getEmail());
            stmt.setInt(7, f.getTelefone());
            stmt.setInt(8, f.getIdFuncionarios());

            stmt.executeUpdate();

            notific.showNotificacao("Atualizado", "Funcionario atualizado com sucesso");

        } catch (SQLException ex) {

            notific.showErro("Erro ao atualizar funcionario: \n"+ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public void remover(Funcionarios f){

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM funcionarios WHERE idFuncionario = ?");
            stmt.setInt(1, f.getIdFuncionarios());

            stmt.executeUpdate();

            notific.showNotificacao("Removido", "Funcionario removido com sucesso");

        } catch (SQLException ex) {

            notific.showErro("Erro ao remover funcionario: \n"+ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public ArrayList<Funcionarios> listar(){

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionarios> funcionarios = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionarios");
            rs = stmt.executeQuery();

            while(rs.next()){
                Funcionarios f = new Funcionarios();
                f.setIdFuncionarios(rs.getInt("idFuncionario"));
                f.setNome(rs.getString("nome"));
                f.setSenha(rs.getString("senha"));
                f.setCargo(rs.getString("cargo"));
                f.setDataNasc(rs.getString("dataNasc"));
                f.setSexo(rs.getString("sexo"));
                f.setMorada(rs.getString("morada"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getInt("telefone"));
                f.setDataEntrada(rs.getString("dataEnt"));

                funcionarios.add(f);
            }
        } catch (SQLException ex) {

            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return funcionarios;
    }

    public ArrayList<Funcionarios> pesquisar(String palavraChave){

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
                f.setIdFuncionarios(rs.getInt("idFuncionario"));
                f.setNome(rs.getString("nome"));
                f.setSenha(rs.getString("senha"));
                f.setCargo(rs.getString("cargo"));
                f.setDataNasc(rs.getString("dataNasc"));
                f.setSexo(rs.getString("sexo"));
                f.setMorada(rs.getString("morada"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getInt("telefone"));
                f.setDataEntrada(rs.getString("dataEnt"));
                funcionarios.add(f);
            }
        } catch (SQLException ex) {

            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return funcionarios;
    }
}
