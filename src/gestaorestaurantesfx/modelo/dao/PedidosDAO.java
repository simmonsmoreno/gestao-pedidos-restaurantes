package gestaorestaurantesfx.modelo.dao;

import gestaorestaurantesfx.Notificacao;
import gestaorestaurantesfx.modelo.basedados.ConnectionFactory;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import gestaorestaurantesfx.modelo.domain.Pedidos;
import gestaorestaurantesfx.modelo.domain.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class PedidosDAO {
    
    //fazer pedido
    public void adicionarCarrinho(Produtos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO produtos (numeroPedido,idPrato,qtdade) VALUES (?,?,?)");
            stmt.setInt(1, p.getNumeroPedido());
            stmt.setInt(2, p.getIdPrato());
            stmt.setInt(3, p.getQtdade());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao adicionar ao carrinho: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    public void fazerPedido(Pedidos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO pedidos (numeroMesa,preçoTotal,dataPedido,"
                    + "pronto,entregue,idGarcom) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, p.getNumeroMesa());
            stmt.setInt(2, p.getPreçoTotal());
            stmt.setString(3, p.getData());
            stmt.setBoolean(4, p.isPronto());
            stmt.setBoolean(5, p.isEntregue());
            stmt.setInt(6, p.getIdGarcom()); 
            
            stmt.executeUpdate();
            
            new Notificacao().showNotificacao("Pedido feito com sucesso", "O seu pedido foi enviado a cozinha");
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao fazer pedido: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    //processar e entregar pedido
    public void mudarEstadoPedido(Funcionarios funcionarioOnline, Pedidos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if(funcionarioOnline.getCargo().equals("Garçom")){
                stmt = conn.prepareStatement("UPDATE pedidos SET entregue = 1 WHERE numeroPedido = ?");
                stmt.setInt(1, p.getNumeroPedido());
                new Notificacao().showNotificacao("Pedido entregue com sucesso", "Bom apetite!");
            }
            else if(funcionarioOnline.getCargo().equals("Cozinheiro(a)")){
                stmt = conn.prepareStatement("UPDATE pedidos SET pronto = 1, idCozinheiro = ?  WHERE numeroPedido = ?");
                stmt.setInt(1, funcionarioOnline.getIdFuncionarios());
                stmt.setInt(2, p.getNumeroPedido());
                new Notificacao().showNotificacao("Pedido processado com sucesso", "Aguarde a entrega do pedido!");
            }
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao processar pedido: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    //listar todos os pedidos
    public ArrayList<Pedidos> listarPedidos(){
        ArrayList<Pedidos> orders = new ArrayList<>();
        ArrayList<Pedidos> pedidos = listarpedidos();
        for(Pedidos p:pedidos){
            p.setProdutos(listarProdutos(p));
            orders.add(p);
        }
        return orders;
    }
    public ArrayList<Pedidos> listarpedidos(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pedidos> pedidos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pedidos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pedidos p = new Pedidos();
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setNumeroMesa(rs.getInt("numeroMesa"));
                p.setPreçoTotal(rs.getInt("preçoTotal"));
                p.setData(rs.getString("dataPedido"));
                p.setPronto(rs.getBoolean("pronto"));
                p.setEntregue(rs.getBoolean("entregue"));
                p.setIdGarcom(rs.getInt("idGarcom"));
                p.setIdGarcom(rs.getInt("idCozinheiro"));
                pedidos.add(p);
            }
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao listar pedidos: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pedidos;
    }
    
    //listar pedidos pendentes (por processar ou por entregar)
    public ArrayList<Pedidos> listarPedidosPendentes(Funcionarios funcionarioOnline){
        ArrayList<Pedidos> orders = new ArrayList<>();
        ArrayList<Pedidos> pedidos = listarpedidospendentes(funcionarioOnline);
        for(Pedidos p:pedidos){
            p.setProdutos(listarProdutos(p));
            orders.add(p);
        }
        return orders;
    }
    public ArrayList<Pedidos> listarpedidospendentes(Funcionarios funcionarioOnline){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pedidos> pedidos = new ArrayList<>();
                
        try {
            if(funcionarioOnline.getCargo().equals("Garçom")){
                stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE pronto = 1 AND entregue = 0 AND idGarcom = ?");
                stmt.setInt(1, funcionarioOnline.getIdFuncionarios());
            }
            else if(funcionarioOnline.getCargo().equals("Cozinheiro(a)"))
                stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE pronto = 0 AND entregue = 0 ");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pedidos p = new Pedidos();
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setNumeroMesa(rs.getInt("numeroMesa"));
                p.setPreçoTotal(rs.getInt("preçoTotal"));
                p.setData(rs.getString("dataPedido"));
                p.setPronto(rs.getBoolean("pronto"));
                p.setEntregue(rs.getBoolean("entregue"));
                p.setIdGarcom(rs.getInt("idGarcom"));
                p.setIdGarcom(rs.getInt("idCozinheiro"));
                pedidos.add(p);
            }
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao listar pedidos: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pedidos;
    }
    
    //listar historico de pedidos
    public ArrayList<Pedidos> listarHistoricoPedidos(Funcionarios funcionarioOnline){
        ArrayList<Pedidos> orders = new ArrayList<>();
        ArrayList<Pedidos> pedidos = listarhistoricopedidos(funcionarioOnline);
        for(Pedidos p:pedidos){
            p.setProdutos(listarProdutos(p));
            orders.add(p);
        }
        return orders;
    }
    public ArrayList<Pedidos> listarhistoricopedidos(Funcionarios funcionarioOnline){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pedidos> pedidos = new ArrayList<>();
                
        try {
            if     (funcionarioOnline.getCargo().equalsIgnoreCase("Garçom")){
                stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE entregue = 1 AND idGarcom = ?");
                stmt.setInt(1, funcionarioOnline.getIdFuncionarios());
            }
            else if(funcionarioOnline.getCargo().equalsIgnoreCase("Cozinheiro(a)")){
                stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE entregue = 1 AND idCozinheiro = ?");
                stmt.setInt(1, funcionarioOnline.getIdFuncionarios());
            }
            else if(funcionarioOnline.getCargo().equalsIgnoreCase("Administrador")){
                stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE entregue = 1");
            }
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pedidos p = new Pedidos();
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setNumeroMesa(rs.getInt("numeroMesa"));
                p.setPreçoTotal(rs.getInt("preçoTotal"));
                p.setData(rs.getString("dataPedido"));
                p.setPronto(rs.getBoolean("pronto"));
                p.setEntregue(rs.getBoolean("entregue"));
                p.setIdGarcom(rs.getInt("idGarcom"));
                p.setIdCozinha(rs.getInt("idCozinheiro"));
                pedidos.add(p);
            }
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao listar pedidos: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pedidos;
    }
    
    //listar cada produto num pedido
    public ArrayList<Produtos> listarProdutos(Pedidos pedido){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Produtos> produtos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM produtos WHERE numeroPedido = ?");
            stmt.setInt(1, pedido.getNumeroPedido());
            rs = stmt.executeQuery();

            Produtos produto = new Produtos();

            while(rs.next()){
                produto.setNumeroPedido(rs.getInt("numeroPedido"));
                produto.setIdPrato(rs.getInt("idPrato"));
                produto.setQtdade(rs.getInt("qtdade"));
                produtos.add(produto);
            }
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao listar pedidos: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return produtos;
    }
    
    //pesquisar pedidos
    public ArrayList<Pedidos> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pedidos> pedidos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE dataPedido LIKE ?");
            stmt.setString(1, "%"+palavraChave+"%");
            rs = stmt.executeQuery();
            Pedidos p = new Pedidos();
            
            while(rs.next()){
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setNumeroMesa(rs.getInt("numeroMesa"));
                p.setPreçoTotal(rs.getInt("preçoTotal"));
                p.setData(rs.getString("dataPedido"));
                p.setPronto(rs.getBoolean("pronto"));
                p.setEntregue(rs.getBoolean("entregue"));
                p.setIdGarcom(rs.getInt("idGarcom"));
                p.setIdGarcom(rs.getInt("idCozinheiro"));
            }
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao pesquisar pedidos: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pedidos;
    }
    
    //recuperar o numero de pedido
    public int obterNumeroPedido(Pedidos p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;
        
        try {
            stmt = conn.prepareStatement("SELECT numeroPedido FROM pedidos WHERE dataPedido = ?");
            stmt.setString(1, p.getData());
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno = rs.getInt("numeroPedido");
            }
            
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao listar produtos: \n"+ex.getMessage());
            alert.show();
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return retorno;
    }
    
}