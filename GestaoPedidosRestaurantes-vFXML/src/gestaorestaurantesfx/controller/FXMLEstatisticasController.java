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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLEstatisticasController implements Initializable {

    @FXML private BorderPane borderpane;
    @FXML private Button btnHoje;
    @FXML private Button btnSempre;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTela("Hoje");
        btnHoje.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnSempre.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }   
    
    @FXML
    private void handleHoje(MouseEvent event) {
        carregarTela("Hoje");
        btnHoje.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnSempre.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML
    private void handleSempre(MouseEvent event) {
        carregarTela("Sempre");
        btnSempre.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnHoje.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }
    
    private void carregarTela(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gestaorestaurantesfx/view/FXMLEstatisticas"+tela+".fxml"));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLBorderPaneMainGarcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
