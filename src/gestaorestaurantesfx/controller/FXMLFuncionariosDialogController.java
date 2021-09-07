package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.domain.Funcionarios;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

public class FXMLFuncionariosDialogController implements Initializable {

    @FXML private Label lblCabecalho;
    @FXML private TextField txtNome;
    @FXML private TextField txtMorada;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefone;
    @FXML private PasswordField txtSenha;
    @FXML private DatePicker dpData;
    @FXML private ComboBox<String> cmbCargo;
    @FXML private ComboBox<String> cmbSexo;

    Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Funcionarios funcionario;
    private static String cabecalho;
    
    private final List<String> cargos = new ArrayList<>();
    private ObservableList obsCargos;

    private final List<String> sexos = new ArrayList<>();
    private ObservableList obsSexos;
    
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
        FXMLFuncionariosDialogController.cabecalho = cabecalho;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblCabecalho.setText("Adicionar Novo Funcionário");
        carregarCargo();
        carregarSexo();
        
    }    

    public void carregarCargo(){

        cargos.add("Administrador");
        cargos.add("Garçom");
        cargos.add("Cozinheiro(a)");
        obsCargos = FXCollections.observableArrayList(cargos);
        cmbCargo.setItems(obsCargos);

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
        this.txtSenha.setText(funcionario.getSenha());
        this.txtEmail.setText(funcionario.getEmail());
        this.txtMorada.setText(funcionario.getMorada());
        this.txtTelefone.setText(Integer.toString(funcionario.getTelefone()));
        this.cmbCargo.setItems(obsCargos);
        this.cmbCargo.setValue(funcionario.getCargo());
        this.cmbSexo.setItems(obsSexos);
        this.cmbSexo.setValue(funcionario.getSexo());
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {
            
            SimpleDateFormat relogio = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
            Calendar data = new GregorianCalendar();

            funcionario.setNome(txtNome.getText());
            funcionario.setSenha(txtSenha.getText());
            funcionario.setCargo(cmbCargo.getValue());
            funcionario.setDataNasc(dpData.getValue().toString());
            funcionario.setSexo(cmbSexo.getValue());
            funcionario.setMorada(txtMorada.getText());
            funcionario.setEmail(txtEmail.getText());
            funcionario.setTelefone(Integer.parseInt(txtTelefone.getText()));
            funcionario.setDataEntrada(relogio.format(data.getTime()));

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
        if (txtSenha.getText() == null || txtSenha.getText().length() == 0)  errorMessage += "Senha inválida!\n";
        if (cmbCargo.getValue().length()==0)  errorMessage += "Por favor, selecione um cargo!\n";
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
