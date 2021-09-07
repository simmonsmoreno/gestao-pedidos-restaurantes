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
public class FXMLPedidosCozinhaController implements Initializable {

    @FXML private Button btnProcessarPedidos;
    @FXML private Button btnHistoricoPedidos;
    @FXML private BorderPane borderpane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTela("ProcessarPedidos");
        btnProcessarPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnHistoricoPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }    

    @FXML
    private void handleProcessarPedidos(MouseEvent event) {
        carregarTela("ProcessarPedidos");
        btnProcessarPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnHistoricoPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML
    private void handleHistoricoPedidos(MouseEvent event) {
        carregarTela("HistoricoPedidos");
        btnHistoricoPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnProcessarPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }
    
    private void carregarTela(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gestaorestaurantesfx/view/FXMLPedidosCozinha"+tela+".fxml"));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLBorderPaneMainGarcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
