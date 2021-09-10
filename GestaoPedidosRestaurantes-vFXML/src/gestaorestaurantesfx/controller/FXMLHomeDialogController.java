package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.domain.Funcionarios;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLHomeDialogController implements Initializable {

    @FXML private static Label lblCabecalho;
    @FXML private TextField txtNome;
    @FXML private TextField txtMorada;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefone;
    @FXML private PasswordField txtSenha;
    @FXML private PasswordField txtSenhaNova;
    @FXML private PasswordField txtSenhaConfirm;
    @FXML private DatePicker dpData;
    @FXML private ComboBox<String> cmbSexo;

    Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Funcionarios funcionario;
    private static String cabecalho;

    private final List<String> sexos = new ArrayList<>();
    private ObservableList obsSexos;
    private String senha;
    
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Funcionarios getFuncionario() {
        return funcionario;
    }
    
    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        FXMLHomeDialogController.cabecalho = cabecalho;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarSexo();
    }   

    public void carregarSexo(){

        sexos.add("Masculino");
        sexos.add("Feminino");
        obsSexos = FXCollections.observableArrayList(sexos);
        cmbSexo.setItems(obsSexos);

    }

    public void setFuncionario(Funcionarios funcionario) {
        
        this.funcionario = funcionario;
        this.txtNome.setText(funcionario.getNome());
        senha = funcionario.getSenha();
        //this.txtSenha.setText(funcionario.getSenha());
        this.txtEmail.setText(funcionario.getEmail());
        this.txtMorada.setText(funcionario.getMorada());
        this.txtTelefone.setText(Integer.toString(funcionario.getTelefone()));
        this.cmbSexo.setItems(obsSexos);
        this.cmbSexo.setValue(funcionario.getSexo());
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {
            
            funcionario.setNome(txtNome.getText());
            funcionario.setSenha(txtSenhaNova.getText());
            funcionario.setDataNasc(dpData.getValue().toString());
            funcionario.setSexo(cmbSexo.getValue());
            funcionario.setMorada(txtMorada.getText());
            funcionario.setEmail(txtEmail.getText());
            funcionario.setTelefone(Integer.parseInt(txtTelefone.getText()));

            buttonConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {

        String errorMessage = "";

        if (txtNome.getText() == null || txtNome.getText().length() == 0)  errorMessage += "Nome inválido!\n";
        if (txtSenha.getText() == null || txtSenha.getText().length() == 0 || !txtSenha.getText().equals(senha))  errorMessage += "Senha inválida!\n";
        if (!txtSenhaNova.getText().equals(txtSenhaConfirm.getText()))  errorMessage += "Senhas não coincidem!\n";
        if (dpData.getValue().equals(""))  errorMessage += "Por favor, selecione uma data!\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao adicionar Funcionario");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
