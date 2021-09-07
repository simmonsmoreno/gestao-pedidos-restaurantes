package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.dao.FuncionariosDAO;
import gestaorestaurantesfx.modelo.dao.PratosDAO;
import gestaorestaurantesfx.modelo.domain.Pratos;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FXMLPratosController implements Initializable {

    @FXML private TextField txtDescricao;
    @FXML private TextField txtPreco;
    @FXML private ComboBox<String> cmbCategoria;
    @FXML private Label lblAdicionarAtualizar;
    @FXML private TextField txtPesquisar;
    
    @FXML private TableView<Pratos> tableViewPratos;
    @FXML private TableColumn<Pratos, String> tableColumnDescricaoPratos;
    @FXML private TableColumn<Pratos, String> tableColumnCategoriaPratos;
    @FXML private TableColumn<Pratos, Integer> tableColumnPrecoPratos;
    
    @FXML private Button btnAtualizar;
    @FXML private Button btnRemover;

    private ArrayList<Pratos> listPratos = new ArrayList<>();
    private ObservableList<Pratos> obsListPratos;
    private final PratosDAO pratosDAO = new PratosDAO();
    private final List<String> categorias = new ArrayList<>();
    private ObservableList obsCategorias;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtDescricao.setStyle("-fx-border-color: #6c6c6c; -fx-background-color: transparent; -fx-text-fill: #ffffff;");
        txtPreco.setStyle("-fx-border-color: #6c6c6c; -fx-background-color: transparent; -fx-text-fill: #ffffff;");
        carregarTableViewPratos();
        
        carregarCategorias();
        
        //Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewPratos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPratos(newValue));
    }   
    
    public void carregarCategorias(){

        categorias.add("Entrada");
        categorias.add("Prato-Principal");
        categorias.add("Carnes");
        categorias.add("Acompanhantes");
        categorias.add("Bebidas");
        categorias.add("Sobremesa");
        categorias.add("Fast-Food");
        categorias.add("Pequeno-Almoço");
        
        obsCategorias = FXCollections.observableArrayList(categorias);
        cmbCategoria.setItems(obsCategorias);
        
        cmbCategoria.setStyle("-fx-text-fill: #ffffff;");

    }
    
    public void carregarTableViewPratos(){
        
        tableColumnDescricaoPratos.setCellValueFactory(new PropertyValueFactory<>("Descrição"));
        tableColumnCategoriaPratos.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tableColumnPrecoPratos.setCellValueFactory(new PropertyValueFactory<>("Preço"));
        
        listPratos = pratosDAO.listar();
        
        obsListPratos = FXCollections.observableArrayList(listPratos);
        tableViewPratos.setItems(obsListPratos);
    }

    private void selecionarItemTableViewPratos(Pratos prato) {
        if (prato != null) {
            btnRemover.setVisible(true);
            btnAtualizar.setVisible(true);
            lblAdicionarAtualizar.setText("Atualizar Prato");
            txtDescricao.setText(prato.getDescrição());
            cmbCategoria.setValue(prato.getCategoria());
            txtPreco.setText(String.valueOf(prato.getPreço()));
        } else {
            btnRemover.setVisible(false);
            btnAtualizar.setVisible(false);
            lblAdicionarAtualizar.setText("Adicionar Novo Prato");
            txtDescricao.setText("");
            txtPreco.setText("");
        }
    }

    @FXML
    private void adicionar(MouseEvent event) {
        
        if (validarEntradaDeDados()) {
            Pratos p = new Pratos();
            p.setDescrição(txtDescricao.getText());
            p.setCategoria(cmbCategoria.getValue());
            p.setPreço(Integer.parseInt(txtPreco.getText()));
            pratosDAO.adicionar(p);
            carregarTableViewPratos();
        } 
            
    }

    @FXML
    private void atualizar(MouseEvent event) {
        
        Pratos p = tableViewPratos.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.setDescrição(txtDescricao.getText());
            p.setCategoria(cmbCategoria.getValue());
            p.setPreço(Integer.parseInt(txtPreco.getText()));
            pratosDAO.atualizar(p);
            carregarTableViewPratos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um prato na Tabela!");
            alert.show();
        }
    }

    @FXML
    private void remover(MouseEvent event) {
        
        Pratos p = tableViewPratos.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.setDescrição(txtDescricao.getText());
            p.setCategoria(cmbCategoria.getValue());
            p.setPreço(Integer.parseInt(txtPreco.getText()));
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Eliminar o prato "+p.getDescrição()+ " do cardapio?");
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                pratosDAO.remover(p);
                carregarTableViewPratos();
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um prato na Tabela!");
            alert.show();
        }
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {

        String errorMessage = "";

        if (txtDescricao.getText() == null || txtDescricao.getText().length() == 0)  errorMessage += "Por favor, Introduza a Descrição do Prato!\n";
        if (cmbCategoria.getValue().equals(""))  errorMessage += "Por favor, Selecione uma categoria!\n";
        if (txtPreco.getText() == null || txtPreco.getText().length()==0)  errorMessage += "Por favor, Introduza o preço do prato!\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao adicionar prato");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    @FXML
    private void pesquisar(MouseEvent event) {
        
        tableColumnDescricaoPratos.setCellValueFactory(new PropertyValueFactory<>("Descrição"));
        tableColumnCategoriaPratos.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tableColumnPrecoPratos.setCellValueFactory(new PropertyValueFactory<>("Preço"));
        
        listPratos = pratosDAO.pesquisar(txtPesquisar.getText());
        
        obsListPratos = FXCollections.observableArrayList(listPratos);
        tableViewPratos.setItems(obsListPratos);
    }

}
