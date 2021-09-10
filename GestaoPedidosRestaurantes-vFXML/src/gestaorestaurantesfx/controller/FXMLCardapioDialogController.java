package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.Notificacao;
import gestaorestaurantesfx.modelo.dao.PratosDAO;
import gestaorestaurantesfx.modelo.domain.Pratos;
import gestaorestaurantesfx.modelo.domain.Produtos;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLCardapioDialogController implements Initializable {

    @FXML private AnchorPane anchorPane;
    @FXML private TableView<Pratos> tableViewPratos;
    @FXML private TableColumn<Pratos, String> tableColumnDescricaoPratos;
    @FXML private TableColumn<Pratos, Integer> tableColumnPrecoPratos;
    @FXML private TableColumn<Pratos, Integer> tableColumnIdPratos;
    @FXML private Button btnAdd;
    @FXML private Label lblCategoria;

    private ArrayList<Pratos> listPratos = new ArrayList<>();
    private ArrayList<Produtos> produtos = new ArrayList<>();
    private ObservableList<Pratos> obsListPratos;
    private Stage dialogStage;
    private static String categoria;
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        FXMLCardapioDialogController.categoria = categoria;
    }

    public ArrayList<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produtos> produtos) {
        this.produtos = produtos;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblCategoria.setText(categoria);
        btnAdd.setVisible(false);
        
        carregarTableViewPratos();
        
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewPratos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPratos(newValue));
    }    

    public void carregarTableViewPratos(){
        
        tableColumnIdPratos.setCellValueFactory(new PropertyValueFactory<>("idPratos"));
        tableColumnDescricaoPratos.setCellValueFactory(new PropertyValueFactory<>("Descrição"));
        tableColumnPrecoPratos.setCellValueFactory(new PropertyValueFactory<>("Preço"));
        
        listPratos = new PratosDAO().listarCategoria(categoria);
        
        obsListPratos = FXCollections.observableArrayList(listPratos);
        tableViewPratos.setItems(obsListPratos);
    }

    @FXML
    private void addCarrinho(MouseEvent event) {
        
        Pratos p = tableViewPratos.getSelectionModel().getSelectedItem();
        if (p != null) {
            boolean existe = false;
            for(Produtos prod: produtos)
                if(prod.getIdPrato()==p.getIdPratos()){
                    existe = true;
                    break;
                }
            if(!existe){
                Produtos produto = new Produtos();
                produto.setIdPrato(p.getIdPratos());
                produto.setDescrição(p.getDescrição());
                produto.setQtdade(1);
                produto.setPreço(p.getPreço());
                produtos.add(produto);
                new Notificacao().showNotificacao("Prato adicionado ao carrinho",p.getDescrição());
            }else{
                // Mostrando a mensagem de erro
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Item já adicionado ao carrinho");
                alert.show();
            }
        }
    }

    @FXML
    private void voltar(MouseEvent event) {
        new FXMLPedidosGarcomFazerPedidosController().carregarListPratos(produtos);
        Stage stageLogin = (Stage) anchorPane.getScene().getWindow();
        stageLogin.close();
    }
    
    private void selecionarItemTableViewPratos(Pratos prato) {
        btnAdd.setVisible(true);
    }
}
