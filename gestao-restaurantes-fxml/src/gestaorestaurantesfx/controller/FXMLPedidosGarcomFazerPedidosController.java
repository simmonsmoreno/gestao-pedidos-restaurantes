package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.dao.PedidosDAO;
import gestaorestaurantesfx.modelo.domain.Pedidos;
import gestaorestaurantesfx.modelo.domain.Produtos;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLPedidosGarcomFazerPedidosController implements Initializable {

    @FXML private TableView<Produtos> tableViewProdutos;
    @FXML private TableColumn<Produtos, Integer> tableColumnIdProdutos;
    @FXML private TableColumn<Produtos, String> tableColumnPratosProdutos;
    @FXML private TableColumn<Produtos, Integer> tableColumnQtdadeProdutos;
    @FXML private TableColumn<Produtos, Integer> tableColumnPrecoProdutos;
    @FXML private ComboBox<Integer> cmbMesa;
    @FXML private Button btnIncremento;
    @FXML private Button btnDecremento;
    @FXML private Label lblPreçoTotal;
    @FXML private Label lblData;
    
    private ObservableList<Produtos> obsListPratos;
    private static ArrayList<Produtos> listProdutos = new ArrayList<>();
    
    ArrayList<Integer> noMesas = new ArrayList<>();
    ObservableList<Integer> obsNoMesas;
    private int preço = 0;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        carregartableViewProdutos();
            
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewProdutos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewProdutos(newValue));
    }    

    
    @FXML
    private void fazerPedido(MouseEvent event) {
        if(validarEntradaDeDados()){
            
            Pedidos pedido = new Pedidos();
            
            SimpleDateFormat relogio = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            Calendar data = new GregorianCalendar();

            pedido.setEntregue(false);
            pedido.setPronto(false);
            pedido.setNumeroMesa(cmbMesa.getValue());
            pedido.setData(relogio.format(data.getTime()));
            pedido.setPreçoTotal(preço);
            pedido.setIdGarcom(new FXMLHomeController().getfOnline().getIdFuncionarios());
            
            new PedidosDAO().fazerPedido(pedido);
            
            for(Produtos p:listProdutos){
                p.setNumeroPedido(new PedidosDAO().obterNumeroPedido(pedido));
                new PedidosDAO().adicionarCarrinho(p);
            }
            
            listProdutos.clear();
            carregartableViewProdutos();
            
        }
    }

    @FXML
    private void cancelarPedido(MouseEvent event) {
        listProdutos.clear();
        carregartableViewProdutos();
    }
    
    @FXML
    private void incrementar(MouseEvent event) {
        
        Produtos p = tableViewProdutos.getSelectionModel().getSelectedItem();
        
        if (p != null) {
            p.setQtdade(p.getQtdade()+1);
            listProdutos.set(listProdutos.indexOf(p), p);
            new FXMLBorderPaneMainGarcomController().carregarTela("PedidosGarcom");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um item na Tabela!");
            alert.showAndWait();
        }
    }

    @FXML
    private void descrementar(MouseEvent event) {
        
        Produtos p = tableViewProdutos.getSelectionModel().getSelectedItem();
        
        if (p != null) {
            if(p.getQtdade()>1){
                p.setQtdade(p.getQtdade()-1);
                listProdutos.set(listProdutos.indexOf(p), p);
                carregartableViewProdutos();
            }else if(p.getQtdade()==1){
                listProdutos.remove(listProdutos.indexOf(p));
                carregartableViewProdutos();
            }
            for(Produtos pr: listProdutos) System.out.println(p.toString());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um item na Tabela!");
            alert.showAndWait();
        }  
        carregartableViewProdutos();
    }
    
    public void carregartableViewProdutos(){
        
        tableColumnIdProdutos.setCellValueFactory(new PropertyValueFactory<>("idPrato"));
        tableColumnPratosProdutos.setCellValueFactory(new PropertyValueFactory<>("descrição"));
        tableColumnQtdadeProdutos.setCellValueFactory(new PropertyValueFactory<>("qtdade"));
        tableColumnPrecoProdutos.setCellValueFactory(new PropertyValueFactory<>("preço"));
        
        obsListPratos = FXCollections.observableArrayList(listProdutos);
        tableViewProdutos.setItems(obsListPratos);
        
        preço = 0;
        
        for(Produtos p: listProdutos){
            preço += p.getQtdade()*p.getPreço();
        }
        lblPreçoTotal.setText("Preço Total: "+preço+"$00");
        
        for(int i=0; i<20;i++){
            noMesas.add(i);
        }
        obsNoMesas = FXCollections.observableArrayList(noMesas);
        cmbMesa.setItems(obsNoMesas);
        
        SimpleDateFormat relogio = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
        Calendar data = new GregorianCalendar();
        lblData.setText("Data e hora: "+relogio.format(data.getTime()));
        
        btnIncremento.setVisible(false);
        btnDecremento.setVisible(false);
    }
    
    private void selecionarItemTableViewProdutos(Produtos produtos) {
        btnIncremento.setVisible(true);
        btnDecremento.setVisible(true);
    }

    public void carregarListPratos(ArrayList<Produtos> array){
        for(Produtos p: array)
            listProdutos.add(p);
    }
    
    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {

        String errorMessage = "";

        if (cmbMesa.getValue()==null)  errorMessage += "Por favor, selecione a mesa!\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao fazer pedido");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
