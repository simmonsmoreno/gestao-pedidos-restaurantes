package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.dao.FuncionariosDAO;
import gestaorestaurantesfx.modelo.dao.PedidosDAO;
import gestaorestaurantesfx.modelo.dao.PratosDAO;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import gestaorestaurantesfx.modelo.domain.Pedidos;
import gestaorestaurantesfx.modelo.domain.Pratos;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLPedidosGarcomEntregarPedidosController implements Initializable {

    @FXML private TableView<Pedidos> tableViewPedidos;
    @FXML private TableColumn<Pedidos, Integer> tableViewPedidosNumeroPedido;
    @FXML private TableColumn<Pedidos, Integer> tableViewPedidosMesa;
    @FXML private TableColumn<Pedidos, String> tableViewPedidosData;
    @FXML private TableColumn<Pedidos, Integer> tableViewPedidosPreço;
    
    @FXML private TableView<Pratos> tableViewPratosInPedidos;
    @FXML private TableColumn<Pratos, String> tableViewPratosInPedidosPratos;
    @FXML private TableColumn<Pratos, Integer> tableViewPratosInPedidosQtdade;
    
    @FXML private Button btnEntregar;
    @FXML private Label lblNumeroPedido;
    @FXML private Label lblNumeroMesa;
    @FXML private Label lblCozinheiro;
    @FXML private Label lblEntregue;
    @FXML private Label lblData;
    @FXML private Label lblPreçoTotal;
    @FXML private Label lblPronto;
    
    private ArrayList<Pedidos> listPedidos;
    private ObservableList<Pedidos> obsListPedidos;
    private ArrayList<Pratos> listPratos;
    private ObservableList<Pratos> obsListPratos;
    @FXML
    private GridPane gridPanel;
    @FXML
    private Label lblCaabecalho;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOcarregarTableViewFuncionarios();
        
        gridPanel.setVisible(false);
        tableViewPratosInPedidos.setVisible(false);
        lblCaabecalho.setText("Selecione um pedido na tabela!");
        btnEntregar.setVisible(false);
        
        carregarTableViewPedidos();
        
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewPedidos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPedidos(newValue));
        
    }    
    
    public void carregarTableViewPedidos(){
        
        tableViewPedidosNumeroPedido.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        tableViewPedidosMesa.setCellValueFactory(new PropertyValueFactory<>("numeroMesa"));
        tableViewPedidosData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableViewPedidosPreço.setCellValueFactory(new PropertyValueFactory<>("preçoTotal"));
        
        listPedidos = new PedidosDAO().listarPedidosPendentes(new FXMLHomeController().getfOnline());
        
        obsListPedidos = FXCollections.observableArrayList(listPedidos);
        tableViewPedidos.setItems(obsListPedidos);
    }

    private void selecionarItemTableViewPedidos(Pedidos p) {
        
        if(p!=null){
            
            btnEntregar.setVisible(true);
        
            carregarTableViewPratosInPedidos();
            
            gridPanel.setVisible(true);
            tableViewPratosInPedidos.setVisible(true);
            lblCaabecalho.setText("Detalhes do Pedido:");

            lblNumeroPedido.setText(Integer.toString(p.getNumeroPedido()));
            lblNumeroMesa.setText(Integer.toString(p.getNumeroMesa()));
            lblPreçoTotal.setText(Integer.toString(p.getPreçoTotal()));
            lblData.setText(p.getData());
            if(p.isPronto()) lblPronto.setText("Sim");
            else lblPronto.setText("Não");
            if(p.isEntregue()) lblEntregue.setText("Sim");
            else lblEntregue.setText("Não");
            for(Funcionarios f: new FuncionariosDAO().listar())
                if(f.getIdFuncionarios() == p.getIdCozinha())
                    lblCozinheiro.setText(f.getNome());
        }else{
            gridPanel.setVisible(false);
            tableViewPratosInPedidos.setVisible(false);
            lblCaabecalho.setText("Selecione um pedido na tabela!");
        }
        
        
    }
    
    public void carregarTableViewPratosInPedidos(){
        
        tableViewPratosInPedidosPratos.setCellValueFactory(new PropertyValueFactory<>("descrição"));
        tableViewPratosInPedidosQtdade.setCellValueFactory(new PropertyValueFactory<>("qtdade"));
                
        Pedidos p = tableViewPedidos.getSelectionModel().getSelectedItem();
        
        listPratos = new PratosDAO().listarProdutos(p);
                
        obsListPratos = FXCollections.observableArrayList(listPratos);
        tableViewPratosInPedidos.setItems(obsListPratos);
        
    }
    
    @FXML private void entregarPedidos(MouseEvent event) {
        Pedidos p = tableViewPedidos.getSelectionModel().getSelectedItem();
        new PedidosDAO().mudarEstadoPedido(new FXMLHomeController().getfOnline(), p);
        carregarTableViewPedidos();
    }
}
