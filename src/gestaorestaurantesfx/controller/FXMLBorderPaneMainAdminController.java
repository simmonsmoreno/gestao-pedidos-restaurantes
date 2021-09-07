package gestaorestaurantesfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLBorderPaneMainAdminController implements Initializable {

    @FXML private BorderPane borderpane;
    @FXML private Button btnHome;
    @FXML private Button btnPratos;
    @FXML private Button btnFuncionarios;
    @FXML private Button btnPedidos;
    @FXML private Button btnEstatisticas;
    @FXML private Label btnMinus;
    @FXML private Label btnClose;
    
    private double x,y; 

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTela("Home");
        btnHome.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnPratos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnEstatisticas.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }    

    @FXML
    private void home(MouseEvent event) {
        carregarTela("Home");
        btnHome.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnPratos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnEstatisticas.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML
    private void pratos(MouseEvent event) {
        carregarTela("Pratos");
        btnPratos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnEstatisticas.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML
    private void funcionarios(MouseEvent event) {
        carregarTela("Funcionarios");
        btnFuncionarios.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnPratos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnEstatisticas.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML
    private void pedidos(MouseEvent event) {
        carregarTela("PedidosAdmin");
        btnPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnPratos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnEstatisticas.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML private void estatisticas(MouseEvent event) {
        carregarTela("Estatisticas");
        btnEstatisticas.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnPratos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }
    
    @FXML void poweroff(MouseEvent event) {
        //criar um estágio para a tela principal
        Stage stageTelaPrincipal = new Stage();
        //pegar o estágio da tela de login para podermos fechar mais logo
        Stage stageLogin = (Stage) borderpane.getScene().getWindow();
        stageLogin.close();
        
        //criar um parente root que é a tela que vamos apresentar
        Parent root = null;

        try {
            //carregar o diretorio da tela que vamos aprsentar na variavel root do tipo parent
            root = FXMLLoader.load(getClass().getResource("/gestaorestaurantesfx/view/FXMLBorderPaneLogin.fxml"));
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

        //fechar a tela atual(login) e mostrar a tela principal
        stageTelaPrincipal.show();
        
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
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML private void handleClose(MouseEvent event) {
        System.exit(0);
    }
    
    private void carregarTela(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gestaorestaurantesfx/view/FXML"+tela+".fxml"));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLBorderPaneMainAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}