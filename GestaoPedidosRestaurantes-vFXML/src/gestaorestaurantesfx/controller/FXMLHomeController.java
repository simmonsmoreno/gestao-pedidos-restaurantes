package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.dao.FuncionariosDAO;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLHomeController implements Initializable {

   
    @FXML private Label lblMostrarTipoCargo;
    @FXML private Label lblData;
    
    private static Funcionarios fOnline = new Funcionarios();

    public Funcionarios getfOnline() {
        return fOnline;
    }

    public void setfOnline(Funcionarios fOnline) {
        FXMLHomeController.fOnline = fOnline;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(fOnline.toString());
        lblMostrarTipoCargo.setText("Login como "+fOnline.getCargo());
        
        SimpleDateFormat relogio = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
        Calendar data = new GregorianCalendar();
        lblData.setText(relogio.format(data.getTime()));
    }   

    @FXML
    private void editarPerfil(MouseEvent event) {
        
        FuncionariosDAO funcionariodao = new FuncionariosDAO();
        Funcionarios funcionario = new Funcionarios();
        funcionario = fOnline;
        boolean buttonConfirmarClicked = false;
        try {
            buttonConfirmarClicked = showFXMLAnchorPaneFuncionariosDialog(funcionario);
        } catch (IOException ex) {
            Logger.getLogger(FXMLFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (buttonConfirmarClicked) {
            funcionariodao.atualizar(funcionario);
        }
        
    }
    
    public boolean showFXMLAnchorPaneFuncionariosDialog(Funcionarios funcionario) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLHomeDialogController.class.getResource("/gestaorestaurantesfx/view/FXMLHomeDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Editar meu Perfil");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        

        // Setando o funcionario no Controller da classe FXMLFuncionariosDialogController.
        FXMLHomeDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

}
