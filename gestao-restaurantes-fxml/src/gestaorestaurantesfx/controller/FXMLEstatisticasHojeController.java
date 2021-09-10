package gestaorestaurantesfx.controller;

import gestaorestaurantesfx.modelo.dao.EstatisticasDAO;
import gestaorestaurantesfx.modelo.domain.Estatisticas;
import gestaorestaurantesfx.modelo.domain.Funcionarios;
import gestaorestaurantesfx.modelo.domain.Pratos;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLEstatisticasHojeController implements Initializable {

    @FXML private Label lblLucroTotal;
    @FXML private Label lblTotalPedido;
    @FXML private Label lblData;
    
    @FXML private TableView<Pratos> tableViewEstatisticasPratos;
    @FXML private TableColumn<Pratos, String> tableViewEstatisticasPratosColumnPratos;
    @FXML private TableColumn<Pratos, Integer> tableViewEstatisticasPratosColumnQtdade;
    
    @FXML private TableView<Funcionarios> tableViewEstatisticasGarçom;
    @FXML private TableColumn<Funcionarios, String> tableViewEstatisticasGarçomColumnGarçom;
    @FXML private TableColumn<Funcionarios, Integer> tableViewEstatisticasGarçomColumnNoPedidos;
    
    @FXML private TableView<Funcionarios> tableViewEstatisticasCozinheiro;
    @FXML private TableColumn<Funcionarios, String> tableViewEstatisticasCozinheiroColumnCozinheiro;
    @FXML private TableColumn<Funcionarios, Integer> tableViewEstatisticasCozinheiroColumnNoPedidos;

    private ObservableList<Funcionarios> obsListGarçom;
    private ObservableList<Funcionarios> obsListCozinheiro;
    private ObservableList<Pratos> obsListPratos;
    
    private Estatisticas estatisticas = new Estatisticas();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        SimpleDateFormat relogio = new SimpleDateFormat("dd/MM/yyyy");
        Calendar data = new GregorianCalendar();
        
        lblData.setText(relogio.format(data.getTime()));
        lblLucroTotal.setText(Integer.toString(new EstatisticasDAO().estatisticasHoje().getLucroTotal()));
        lblTotalPedido.setText(Integer.toString(new EstatisticasDAO().estatisticasHoje().getTotalPedido()));
        
        carregartableViewEstatisticasPratos();
        carregartableViewEstatisticasGarçom();
        carregartableViewEstatisticasCozinheiro();
    }    
    
    public void carregartableViewEstatisticasPratos(){
        
        tableViewEstatisticasPratosColumnPratos.setCellValueFactory(new PropertyValueFactory<>("descrição"));
        tableViewEstatisticasPratosColumnQtdade.setCellValueFactory(new PropertyValueFactory<>("qtdade"));
        
        estatisticas = new EstatisticasDAO().estatisticasHoje();
        
        obsListPratos = FXCollections.observableArrayList(estatisticas.getPratos());
        tableViewEstatisticasPratos.setItems(obsListPratos);
    }
    
    public void carregartableViewEstatisticasGarçom(){
        
        tableViewEstatisticasGarçomColumnGarçom.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableViewEstatisticasGarçomColumnNoPedidos.setCellValueFactory(new PropertyValueFactory<>("noPedido"));
        
        estatisticas = new EstatisticasDAO().estatisticasHoje();
        
        obsListGarçom = FXCollections.observableArrayList(estatisticas.getGarcomMaisPedidos());
        tableViewEstatisticasGarçom.setItems(obsListGarçom);
    }
    
    public void carregartableViewEstatisticasCozinheiro(){
        
        tableViewEstatisticasCozinheiroColumnCozinheiro.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableViewEstatisticasCozinheiroColumnNoPedidos.setCellValueFactory(new PropertyValueFactory<>("noPedido"));
        
        estatisticas = new EstatisticasDAO().estatisticasHoje();
        
        obsListCozinheiro = FXCollections.observableArrayList(estatisticas.getCozinheiroMaisPedido());
        tableViewEstatisticasCozinheiro.setItems(obsListCozinheiro);
    }
}
