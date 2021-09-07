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
public class FXMLPedidosGarcomController implements Initializable {

    @FXML private Button btnFazerPedidos;
    @FXML private Button btnEntregarPedidos;
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
        carregarTela("FazerPedidos");
        btnFazerPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnEntregarPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnHistoricoPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }    

    @FXML
    private void handleFazerPedidos(MouseEvent event) {
        carregarTela("FazerPedidos");
        btnFazerPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnEntregarPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnHistoricoPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML
    private void handleEntregarPedidos(MouseEvent event) {
        carregarTela("EntregarPedidos");
        btnEntregarPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnFazerPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnHistoricoPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }

    @FXML
    private void handleHistoricoPedidos(MouseEvent event) {
        carregarTela("HistoricoPedidos");
        btnHistoricoPedidos.setStyle("-fx-background-color: #214c92; -fx-text-fill: #ffffff;");
        btnEntregarPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
        btnFazerPedidos.setStyle("-fx-background-color: transparent; -fx-text-fill: #f0f0f0;");
    }
    
    public void carregarTela(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gestaorestaurantesfx/view/FXMLPedidosGarcom"+tela+".fxml"));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLBorderPaneMainGarcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
