package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.dao.FuncionariosDAO;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLFuncionariosController implements Initializable {

    @FXML private Label lblSelecionarFuncionarios;
    @FXML private GridPane gridPanel;
    @FXML private Label lblNome;
    @FXML private Label lblId;
    @FXML private Label lblCargo;
    @FXML private Label lblDataNasc;
    @FXML private Label lblSexo;
    @FXML private Label lblMorada;
    @FXML private Label lblEmail;
    @FXML private Label lblDataEnt;
    @FXML private Label lblTelefone;
    @FXML private TableView<Funcionarios> tableViewFuncionarios;
    @FXML private TableColumn<Funcionarios, String> tableColumnFuncionariosNome;
    @FXML private TableColumn<Funcionarios, String> tableColumnFuncionariosCargo;
    @FXML private Button btnRemover;
    @FXML private TextField txtPesquisar;

    private ArrayList<Funcionarios> listFuncionarios = new ArrayList<>();
    private ObservableList<Funcionarios> obsListFuncionarios;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblSelecionarFuncionarios.setText("Selecione um Funcionário");
        gridPanel.setVisible(false);
        btnRemover.setVisible(false);
            
        carregarTableViewFuncionarios();
        
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewFuncionarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFuncionarios(newValue));
        
    }    
    
    public void carregarTableViewFuncionarios(){
        
        tableColumnFuncionariosNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableColumnFuncionariosCargo.setCellValueFactory(new PropertyValueFactory<>("Cargo"));
        
        listFuncionarios = new FuncionariosDAO().listar();
        
        obsListFuncionarios = FXCollections.observableArrayList(listFuncionarios);
        tableViewFuncionarios.setItems(obsListFuncionarios);
    }

    private void selecionarItemTableViewFuncionarios(Funcionarios funcionario) {
        if (funcionario != null) {
            lblSelecionarFuncionarios.setText("Detalhes do Funcionário");
            lblId.setText(String.valueOf(funcionario.getIdFuncionarios()));
            lblNome.setText(funcionario.getNome());
            lblCargo.setText(funcionario.getCargo());
            lblDataNasc.setText(funcionario.getDataNasc());
            lblSexo.setText(funcionario.getSexo());
            lblMorada.setText(funcionario.getMorada());
            lblEmail.setText(funcionario.getEmail());
            lblTelefone.setText(String.valueOf(funcionario.getTelefone()));
            lblDataEnt.setText(funcionario.getDataEntrada());
            gridPanel.setVisible(true);
            btnRemover.setVisible(true);
        } else {
            lblSelecionarFuncionarios.setText("Selecione um Funcionário");
            gridPanel.setVisible(false);
            btnRemover.setVisible(false);
        }
    }

    @FXML private void adicionar(MouseEvent event) {
        
        Funcionarios funcionario = new Funcionarios();
        boolean buttonConfirmarClicked = false;
        try {
            buttonConfirmarClicked = showFXMLAnchorPaneFuncionariosDialog(funcionario);
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (buttonConfirmarClicked) {
            new FuncionariosDAO().adicionar(funcionario);
            carregarTableViewFuncionarios();
        }
    }

    @FXML
    private void remover(MouseEvent event) {
        
        Funcionarios funcionario = tableViewFuncionarios.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Eliminar os dados do funcionário "+funcionario.getNome()+ " dos registros?");
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                new FuncionariosDAO().remover(funcionario);
                carregarTableViewFuncionarios();
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Funcionario na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    void handlePesquisar(ActionEvent event) {
        
        tableColumnFuncionariosNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableColumnFuncionariosCargo.setCellValueFactory(new PropertyValueFactory<>("Cargo"));
        
        listFuncionarios = new FuncionariosDAO().pesquisar(txtPesquisar.getText());
        
        obsListFuncionarios = FXCollections.observableArrayList(listFuncionarios);
        tableViewFuncionarios.setItems(obsListFuncionarios);

    }
    
    public boolean showFXMLAnchorPaneFuncionariosDialog(Funcionarios funcionario) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLFuncionariosDialogController.class.getResource("/gestaorestaurantesfx/view/FXMLFuncionariosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Adicionar Novo Funcionário");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        

        // Setando o funcionario no Controller da classe FXMLFuncionariosDialogController.
        FXMLFuncionariosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
    
}
