package gestaopedidosrestaurantes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class Cardapio {
    
    public static void cardapio(ArrayList pratos, ArrayList id, ArrayList categ, ArrayList preco, ArrayList pedidos, File txtPedidos) throws IOException {
        
        ArrayList <String> option = new ArrayList<>();
        String []lista = new String[categ.size()];
        for(int i=0; i<categ.size();i++){
            String value = (String) categ.get(i);
            if(!(option.contains(value))) option.add(value);
        }
        
        for(int i=0; i<option.size();i++) lista[i] = option.get(i);
        
        String msg = (String) JOptionPane.showInputDialog(null , "Escolher a Categoria do Prato:", "APRESENTAR CARDAPIO", JOptionPane.QUESTION_MESSAGE, null, lista, "");
        
        for(int i=0; i<categ.size(); i++){
            if(msg.equals(categ.get(i))){
                lista[i] = (String) 
                        "\nNome: " + pratos.get(i) +
                        "\nPreço: " + preco.get(i) + "$00"+
                        "\n===========================================";
            }
        }
        
        JOptionPane.showMessageDialog(null, "Categoria: "+msg+ ":\n\n" + Arrays.toString(lista), "APRESENTAR CARDAPIO", JOptionPane.INFORMATION_MESSAGE);
    }
}
