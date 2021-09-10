package gestaopedidosrestaurantes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class HistoricoPedidos {
    static void historicoPedidos(ArrayList pedidos){
        
        if(pedidos.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Pedido Feito!", "LISTA DE PEDIDOS", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String []lista = new String[pedidos.size()];
            for(int i=0; i<pedidos.size(); i++) lista[i] = (String) pedidos.get(i);
            JOptionPane.showMessageDialog(null, lista, "LISTA DE PEDIDOS", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
