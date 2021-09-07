package gestaorestaurantesfx.modelo.dao;

import gestaorestaurantesfx.modelo.basedados.ConnectionFactory;
import gestaorestaurantesfx.modelo.domain.Estatisticas;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import gestaorestaurantesfx.modelo.domain.Pratos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EstatisticasDAO {
    
    public Estatisticas estatisticasHoje(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat relogio = new SimpleDateFormat("dd/MMM/yyyy");
        Calendar data = new GregorianCalendar();
        
        Estatisticas estatisticas = new Estatisticas();
                
        try {
            //lucro total
            stmt = conn.prepareStatement("SELECT SUM(preçoTotal) AS lucroTotal FROM pedidos WHERE pedidos.dataPedido LIKE ? LIMIT 5");
            stmt.setString(1, relogio.format(data.getTime())+"%");
            rs = stmt.executeQuery();
            while(rs.next())estatisticas.setLucroTotal(rs.getInt("lucroTotal"));
            stmt = null; rs = null;
            System.out.println("lucro total: "+estatisticas.getLucroTotal());
            
            //numero pedidos
            stmt = conn.prepareStatement("SELECT COUNT(numeroPedido) AS numeroPedido FROM pedidos WHERE dataPedido LIKE ? LIMIT 5");
            stmt.setString(1, relogio.format(data.getTime())+"%");
            rs = stmt.executeQuery();
            while(rs.next())estatisticas.setTotalPedido(rs.getInt("numeroPedido"));
            stmt = null; rs = null;
            System.out.println("numero pedidos: "+estatisticas.getTotalPedido());
            
            //prato mais consumido
            stmt = conn.prepareStatement("SELECT pratos.descrição AS prato, SUM(produtos.qtdade) AS qtdadeTotal "
                    + "FROM pratos, produtos, pedidos "
                    + "WHERE pratos.idPrato = produtos.idPrato AND pedidos.numeroPedido = produtos.numeroPedido AND pedidos.dataPedido LIKE ? "
                    + "GROUP BY produtos.idPrato LIMIT 5");
            stmt.setString(1, relogio.format(data.getTime())+"%");
            rs = stmt.executeQuery();
            while(rs.next()){
                Pratos p = new Pratos();
                p.setDescrição(rs.getString("prato"));
                p.setQtdade(rs.getInt("qtdadeTotal"));
                System.out.println("pratos: "+p.toString());
                estatisticas.getPratos().add(p);
            }
            stmt = null; rs = null;
            
            //melhor garçom
            stmt = conn.prepareStatement("SELECT funcionarios.nome AS garçom, COUNT(pedidos.idGarcom) AS numeroPedidos "
                    + "FROM pedidos, funcionarios "
                    + "WHERE pedidos.idGarcom = funcionarios.idFuncionario AND pedidos.dataPedido LIKE ? "
                    + "GROUP BY pedidos.idGarcom LIMIT 5");
            stmt.setString(1, relogio.format(data.getTime())+"%");
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionarios f = new Funcionarios();
                f.setNome(rs.getString("garçom"));
                f.setNoPedido(rs.getInt("numeroPedidos"));
                System.out.println("garçom: "+f.toString());
                estatisticas.getGarcomMaisPedidos().add(f);
            }
            stmt = null; rs = null;
            
            //melhor garçom
            stmt = conn.prepareStatement("SELECT funcionarios.nome AS cozinheiro, COUNT(pedidos.idCozinheiro) AS numeroPedidos "
                    + "FROM pedidos, funcionarios "
                    + "WHERE pedidos.idCozinheiro = funcionarios.idFuncionario AND pedidos.dataPedido LIKE ? "
                    + "GROUP BY pedidos.idCozinheiro LIMIT 5");
            stmt.setString(1, relogio.format(data.getTime())+"%");
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionarios f = new Funcionarios();
                f.setNome(rs.getString("cozinheiro"));
                f.setNoPedido(rs.getInt("numeroPedidos"));
                System.out.println("cozinheiro: "+f.toString());
                estatisticas.getCozinheiroMaisPedido().add(f);
            }
            stmt = null; rs = null;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar dados: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return estatisticas;
    }
    
    public Estatisticas estatisticasSempre(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Estatisticas estatisticas = new Estatisticas();
                
        try {
            //lucro total
            stmt = conn.prepareStatement("SELECT SUM(preçoTotal) AS lucroTotal FROM pedidos LIMIT 5");
            rs = stmt.executeQuery();
            while(rs.next())estatisticas.setLucroTotal(rs.getInt("lucroTotal"));
            stmt = null; rs = null;
            System.out.println("lucro total: "+estatisticas.getLucroTotal());
            
            //numero pedidos
            stmt = conn.prepareStatement("SELECT COUNT(numeroPedido) AS numeroPedido FROM pedidos LIMIT 5");
            rs = stmt.executeQuery();
            while(rs.next())estatisticas.setTotalPedido(rs.getInt("numeroPedido"));
            stmt = null; rs = null;
            System.out.println("numero pedidos: "+estatisticas.getTotalPedido());
            
            //prato mais consumido
            stmt = conn.prepareStatement("SELECT pratos.descrição AS prato, SUM(produtos.qtdade) AS qtdadeTotal "
                    + "FROM pratos, produtos, pedidos "
                    + "WHERE pratos.idPrato = produtos.idPrato AND pedidos.numeroPedido = produtos.numeroPedido "
                    + "GROUP BY produtos.idPrato LIMIT 5");
            rs = stmt.executeQuery();
            while(rs.next()){
                Pratos p = new Pratos();
                p.setDescrição(rs.getString("prato"));
                p.setQtdade(rs.getInt("qtdadeTotal"));
                System.out.println("pratos: "+p.toString());
                estatisticas.getPratos().add(p);
            }
            stmt = null; rs = null;
            
            //melhor garçom
            stmt = conn.prepareStatement("SELECT funcionarios.nome AS garçom, COUNT(pedidos.idGarcom) AS numeroPedidos "
                    + "FROM pedidos, funcionarios "
                    + "WHERE pedidos.idGarcom = funcionarios.idFuncionario "
                    + "GROUP BY pedidos.idGarcom LIMIT 5");
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionarios f = new Funcionarios();
                f.setNome(rs.getString("garçom"));
                f.setNoPedido(rs.getInt("numeroPedidos"));
                System.out.println("garçom: "+f.toString());
                estatisticas.getGarcomMaisPedidos().add(f);
            }
            stmt = null; rs = null;
            
            //melhor garçom
            stmt = conn.prepareStatement("SELECT funcionarios.nome AS cozinheiro, COUNT(pedidos.idCozinheiro) AS numeroPedidos "
                    + "FROM pedidos, funcionarios "
                    + "WHERE pedidos.idCozinheiro = funcionarios.idFuncionario "
                    + "GROUP BY pedidos.idCozinheiro LIMIT 5");
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionarios f = new Funcionarios();
                f.setNome(rs.getString("cozinheiro"));
                f.setNoPedido(rs.getInt("numeroPedidos"));
                System.out.println("cozinheiro: "+f.toString());
                estatisticas.getCozinheiroMaisPedido().add(f);
            }
            stmt = null; rs = null;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar dados: \n"+ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return estatisticas;
    }
}
