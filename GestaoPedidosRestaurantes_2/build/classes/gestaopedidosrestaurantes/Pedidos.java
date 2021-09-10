package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.GravarDados.gravarDados;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class Pedidos {
    
    static void pedidos(ArrayList prato,  ArrayList id, ArrayList categ, ArrayList preco, ArrayList garcon, ArrayList pedidos,
                            ArrayList faturas) throws IOException {
        
        String msg, nome, lista, qtdd;
        int i, j, k=0, precoTotal=0;
        
        do {            
            msg = JOptionPane.showInputDialog(null, "Introduza o nome do prato ou o id:", "FAZER PEDIDOS", JOptionPane.INFORMATION_MESSAGE);
            if (prato.contains(msg)) {
                
                i = prato.indexOf(msg);
                lista = (String) "Nome: " + prato.get(i) + "\nId: " + id.get(i) + "\nCategoria: " + categ.get(i) + "\nPreço: " + preco.get(i) + "$00";
                
                msg = (String) "Prato encontrado: \n\n" + lista + "\nAdicionar aos pedidos?";
                j = JOptionPane.showConfirmDialog(null, msg, "FAZER PEDIDOS", JOptionPane.YES_NO_OPTION);
                
                if (j == JOptionPane.YES_OPTION) {
                    pedidos.add("Prato: "+prato.get(i));
                    pedidos.add("Id: "+id.get(i));
                    pedidos.add("Categoria: "+categ.get(i));
                    pedidos.add("Preço unitário: "+preco.get(i));
                    qtdd = JOptionPane.showInputDialog("Introduza a quantidade: "); pedidos.add("Quantidade: "+qtdd);
                    precoTotal += (Integer.parseInt(qtdd))*(Integer.parseInt((String) preco.get(i)));
                    k = JOptionPane.showConfirmDialog(null, "Fazer mais pedidos?", "FAZER PEDIDOS", JOptionPane.YES_NO_OPTION);
                    if (k == JOptionPane.NO_OPTION){
                        
                        pedidos.add("===============================================================================");
                        pedidos.add("===============================================================================");
                        nome = JOptionPane.showInputDialog("Nome do cliente: "); pedidos.add("Nome do cliente: "+nome);
                        nome = JOptionPane.showInputDialog("Nome do garçon: "); pedidos.add("Nome do garçon: "+nome);
                        JOptionPane.showMessageDialog(null, "Total a Pegar: "+precoTotal+"$00");pedidos.add("Total Pago: "+precoTotal+"$00");
                        Date data = new Date(); pedidos.add("Data e Hora do Pedido: "+data);
                        pedidos.add("================================================================================");
                        pedidos.add("===============================================================================");
                        pedidos.add("===============================================================================");
                        
                        JOptionPane.showMessageDialog(null, "Pedido feito Com Sucesso!", "FAZER PEDIDOS", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        pedidos.add("===============================================================================");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Nenhum Prato Encontrado!", "FAZER PEDIDOS", JOptionPane.ERROR_MESSAGE);
            }
        } while (k == JOptionPane.YES_OPTION);
    }
}
