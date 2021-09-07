package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.Launch;
import gestaorestaurantesfx.Notificacao;
import gestaorestaurantesfx.modelo.dao.FuncionariosDAO;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLBorderPaneLoginController implements Initializable {

    @FXML private Pane parent;
    @FXML private TextField txtUser;
    @FXML private PasswordField txtSenha;
    @FXML private Label lblErro;
    @FXML private Label btnMinus;
    @FXML private Label btnClose;

    private double x=0,y=0;
    boolean achou = false;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblErro.setVisible(false);
    }    

    @FXML
    private void pararErro(MouseEvent event) {
        txtUser.setStyle("-fx-background-color: #363B41; -fx-text-fill: #FFFFFF;");
        txtSenha.setStyle("-fx-background-color: #363B41; -fx-text-fill: #FFFFFF;");
        lblErro.setVisible(false);
    }
    
    private void mostrarErro() {
        //mudar a cor da borda dos text fields para vermelho
        txtUser.setStyle("-fx-background-color: #363B41; -fx-text-fill: #ff000a; -fx-border-color: #ff000a;");
        txtSenha.setStyle("-fx-background-color: #363B41; -fx-border-color: #ff000a; -fx-text-fill: #ff000a;");
        lblErro.setStyle("-fx-text-fill: #ff000a;");
        lblErro.setVisible(true);
    }
    
    @FXML
    private void handleEntrar(ActionEvent event) {
        
        for(Funcionarios f: new FuncionariosDAO().listar()){
            
            //testar se existe funcionario com o nome ou email e a senha introduzidos
            if((f.getNome().equalsIgnoreCase(txtUser.getText()) || (f.getEmail().equalsIgnoreCase(txtUser.getText()))) && f.getSenha().equals(txtSenha.getText())){
                
                new FXMLHomeController().setfOnline(f);
                achou = true;
                
                switch(f.getCargo()){
                    case "Administrador": abrirTela("Admin"); break;
                    case "Garcom": abrirTela("Garcom"); break;
                    case "Cozinheiro(a)": abrirTela("Cozinha"); break;
                }
                
                //mostrar notificação com o nome do funcionario e o cargo
                new Notificacao().showNotificacao("Bem-Vindo ", new FXMLHomeController().getfOnline().getNome());
                
                //não verificar mais nenhum funcionario
                break;
            } 
        }
        
        //se não encontrou nenhum funcionario mostrar erro
        if(!achou) mostrarErro();
    }
    
    @FXML private void hoverBtnMinusEntered(MouseEvent event) {
        btnMinus.setStyle("-fx-background-color: #888888;");
    }
    
    @FXML private void hoverBtnMinusExited(MouseEvent event) {
        btnMinus.setStyle("-fx-background-color: transparent;");
    }

    @FXML private void hoverBtnCloseEntered(MouseEvent event) {
        btnClose.setStyle("-fx-background-color: #ff0000;");
    }
    
    @FXML private void hoverBtnCloseExited(MouseEvent event) {
         btnClose.setStyle("-fx-background-color: transparent;");
    }

    @FXML private void handleMinus(MouseEvent event) {
        Launch.stage.setIconified(true);
    }

    @FXML private void handleClose(MouseEvent event) {
        System.exit(0);
    }
    
    public void abrirTela(String tela){
        
        //criar um estágio para a tela principal
        Stage stageTelaPrincipal = new Stage();
        
        //pegar o estágio da tela de login (atual) e fechar 
        Stage stageLogin = (Stage) parent.getScene().getWindow();
        stageLogin.close();

        //criar um parente root que é a tela que vamos apresentar
        Parent root = null;

        try {
            //carregar o diretorio da tela que vamos aprsentar na variavel root do tipo parent
            root = FXMLLoader.load(getClass().getResource("/gestaorestaurantesfx/view/FXMLBorderPaneMain"+tela+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLBorderPaneLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //setar o estagio da tela principal com a cena apartir da variavel root
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed((event1) -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });

        root.setOnMouseDragged((event2) -> {
            stageTelaPrincipal.setX(event2.getScreenX()-x);
            stageTelaPrincipal.setY(event2.getScreenY()-y);
        });

        stageTelaPrincipal.setScene(scene);
        stageTelaPrincipal.initStyle(StageStyle.TRANSPARENT);
        stageTelaPrincipal.setResizable(false);
        stageTelaPrincipal.show();
    }
    
}
