package gestaopedidosrestaurantes;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class Listar {
    
    //listar todos os garcon
    public static void listarGarcon(ArrayList garcon, ArrayList idade, ArrayList morada, ArrayList sexo, ArrayList telef){
        
        if(garcon.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Garçon Registado! Por Favor Adicione Garçons Primeiro", "LISTA DE GARÇONS", JOptionPane.INFORMATION_MESSAGE);
        }else{
            
            JFrame frame = new JFrame();
            String bigList[] = new String[garcon.size()];
            String lista;

            for (int i = 0; i < garcon.size(); i++)   bigList[i] = (String) garcon.get(i);

            String result = (String) JOptionPane.showInputDialog(frame, "Lista de Garçons", "Input", JOptionPane.QUESTION_MESSAGE, null, bigList, "Titan");
            int i = garcon.indexOf(result);
            
            lista = (String)
                "Nome: " + garcon.get(i) +
                "\nIdade: " + idade.get(i) +
                "\nMorada: " + morada.get(i) +
                "\nSexo: " + sexo.get(i) +
                "\nTelefone: " + telef.get(i);
            
            JOptionPane.showMessageDialog(null, lista);
        }
        
        
    }
    
    public static void listarPratos(ArrayList prato,  ArrayList id, ArrayList categ, ArrayList preco){
        
        if(prato.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Prato Registado! Por Favor Adicione Prato Primeiro", "LISTA DE PRATOS", JOptionPane.INFORMATION_MESSAGE);
        }else{
            /*String []lista = new String[prato.size()];
            
            for(int i=0; i<prato.size(); i++){
            lista[i] = (String)
            "Nome: " + prato.get(i) +
            "\nId: " + id.get(i) +
            "\nCategoria: " + categ.get(i) +
            "\nPreço: " + preco.get(i) + "$00"+
            "\n===========================================";
            }
            JOptionPane.showMessageDialog(null, lista, "LISTA DE PRATOS", JOptionPane.INFORMATION_MESSAGE);*/
            
            JFrame frame = new JFrame();
            String bigList[] = new String[prato.size()];
            String lista;

            for (int i = 0; i < prato.size(); i++)   bigList[i] = (String) prato.get(i);

            String result = (String) JOptionPane.showInputDialog(frame, "Lista de Garçons", "Input", JOptionPane.QUESTION_MESSAGE, null, bigList, "Titan");
            int i = prato.indexOf(result);
            
            lista = (String)
                "Nome: " + prato.get(i) +
                "\nId: " + id.get(i) +
                "\nCategoria: " + categ.get(i) +
                "\nPreço: " + preco.get(i);
            
            JOptionPane.showMessageDialog(null, lista);
        }
    }
}
