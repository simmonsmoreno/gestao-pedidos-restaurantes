package gestaorestaurantesfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLCardapioController implements Initializable {

    @FXML private AnchorPane anchorPane;
    
    private double x;
    private double y;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBtnEntrada(MouseEvent event) {
        
        try {
            new FXMLCardapioDialogController().setCategoria("Entrada");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnPratoPrincipal(MouseEvent event) {

        try {
            new FXMLCardapioDialogController().setCategoria("Prato-Principal");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnCarnes(MouseEvent event) {
        
        try {
            new FXMLCardapioDialogController().setCategoria("Carnes");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnBebidas(MouseEvent event) {
        
        try {
            new FXMLCardapioDialogController().setCategoria("Bebidas");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnAcompanhantes(MouseEvent event) {
        
        try {
            new FXMLCardapioDialogController().setCategoria("Acompanhantes");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnSobremesas(MouseEvent event) {
        
        try {
            new FXMLCardapioDialogController().setCategoria("Sobremesa");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnPequenoAlmoco(MouseEvent event) {
        
        try {
            new FXMLCardapioDialogController().setCategoria("Pequeno-Almoço");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnFastFood(MouseEvent event) {
        
        try {
            new FXMLCardapioDialogController().setCategoria("Fast-Food");
            showFXMLAnchorPaneFuncionariosDialog();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void showFXMLAnchorPaneFuncionariosDialog() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCardapioDialogController.class.getResource("/gestaorestaurantesfx/view/FXMLCardapioDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Editar meu Perfil");
        Scene scene = new Scene(page);
        
        scene.setFill(Color.TRANSPARENT);
        
        page.setOnMousePressed((event1) -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });

        page.setOnMouseDragged((event2) -> {
            dialogStage.setX(event2.getScreenX()-x);
            dialogStage.setY(event2.getScreenY()-y);
        });

        dialogStage.initStyle(StageStyle.TRANSPARENT);
        
        dialogStage.setResizable(false);
        dialogStage.setScene(scene);
        
        FXMLCardapioDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        // Mostrar o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
}
