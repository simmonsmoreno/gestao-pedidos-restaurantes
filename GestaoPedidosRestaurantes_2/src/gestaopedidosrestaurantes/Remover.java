package gestaopedidosrestaurantes;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class Remover {
    
    //remover garcon
    public static void removerGarcon(ArrayList garcon,  ArrayList password, ArrayList idade, ArrayList morada, ArrayList sexo, ArrayList telef)throws IOException{
        
        String senha, nome, msg;
        int i, j;
        
        if(garcon.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Garçon Registado! Por Favor Adicione Pratos Primeiro", "LISTA DE PRATOS", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            
            senha = JOptionPane.showInputDialog(null,"Introduza o senha do Garçon a remover:", "REMOVER PRATO", JOptionPane.INFORMATION_MESSAGE);
            
            if(password.contains(senha)){
                
                i = password.indexOf(senha);
                nome = (String) garcon.get(i);
                msg = (String) "Prato encontrado: "+ garcon.get(i) + "\nQueres mesmo remover?";
                j = JOptionPane.showConfirmDialog(null, nome, "REMOVER PRATO", JOptionPane.YES_NO_OPTION);
                
                if(j==JOptionPane.YES_OPTION){
                
                    garcon.remove(i);
                    password.remove(i);
                    idade.remove(i);
                    morada.remove(i);
                    sexo.remove(i);
                    telef.remove(i);

                    JOptionPane.showMessageDialog(null, "Garçon '"+ nome +"' Removido Com Sucesso!", "REMOVER PRATO", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Nenhum Garçon com a senha especificado Encontrado!", "REMOVER PRATO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void removerPrato(ArrayList prato,  ArrayList id, ArrayList categ, ArrayList preco) throws IOException{
        
        String nome, s, msg;
        int i, j;
        
        if(prato.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Prato Registado! Por Favor Adicione Pratos Primeiro", "LISTA DE PRATOS", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            
            s = JOptionPane.showInputDialog(null,"Introduza o id do prato a remover:", "REMOVER PRATO", JOptionPane.INFORMATION_MESSAGE);
            
            if(id.contains(s)){
                
                i = id.indexOf(s); System.out.println(i);
                nome =(String) prato.get(i); System.out.println(nome);
                
                msg = (String) "Prato encontrado: "+ prato.get(i) + "\nQueres mesmo remover?";
                j = JOptionPane.showConfirmDialog(null, msg, "REMOVER PRATO", JOptionPane.YES_NO_OPTION);
                
                if(j==JOptionPane.YES_OPTION){
                    prato.remove(i);
                    id.remove(i);
                    categ.remove(i);
                    preco.remove(i);

                    JOptionPane.showMessageDialog(null, "Prato '"+nome+"' Removido Com Sucesso!", "REMOVER PRATO", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null,"Nenhum Prato com id especificado Encontrado!", "REMOVER PRATO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
