package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.GravarDados.gravarDados;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class Alterar {
    
    //alterar dados garcon
    public static void alterarGarcon(ArrayList garcon,  ArrayList password, ArrayList morada, ArrayList telef, 
                                            File txtGarcon, File txtPassword, File txtMorada, File txtTelef) throws IOException{
        
        String msg, nome;
        int id, i, j;
        
        if(garcon.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Garçons Registado! Por Favor Adicione Garçons Primeiro", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            
            msg = JOptionPane.showInputDialog(null,"Introduza a senha do graçon a alterar:", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE);
            if(password.contains(msg)){
                
                i = password.indexOf(msg);
                msg = (String) "Garçon encontrado: "+ garcon.get(i) + "\nQueres mesmo alterar?";
                j = JOptionPane.showConfirmDialog(null, msg, "ALTERAR PRATO", JOptionPane.YES_NO_OPTION);
                
                if(j==JOptionPane.YES_OPTION){
                    id = password.indexOf(msg);
                    nome = JOptionPane.showInputDialog(null,"Novo Nome:", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE); garcon.set(id, nome);
                    nome = JOptionPane.showInputDialog(null,"Nova Palavra Passe:", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE); password.set(id, nome);
                    msg = JOptionPane.showInputDialog(null,"Novo Morada:", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE); morada.set(id, msg);
                    msg = JOptionPane.showInputDialog(null,"Novo Telefone:", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE); telef.set(id, msg);

                    //gravar dados
                        gravarDados(garcon, txtGarcon);
                        gravarDados(password, txtPassword);
                        gravarDados(morada, txtMorada);
                        gravarDados(telef, txtTelef);

                    JOptionPane.showMessageDialog(null, "Garçon '"+nome.toUpperCase()+"' Alterado Com Sucesso!", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Nenhum Garçon com o id especificado Encontrado!", "ALTERAR GARÇONS", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void alterarPratos(ArrayList prato,  ArrayList id, ArrayList categ, ArrayList preco,
            File txtPrato, File txtIdPrato, File txtCateg,  File txtPreco) throws IOException{
        
        String msg, nome = null;
        String []categoria = {"Entrada", "Prato Principal", "Sobremesa", "Bebidas", "Pequeno-Almoço", "Fast-Foods"};
        int i, j;
        
        if(prato.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Prato Registado! Por Favor Adicione Pratos Primeiro", "LISTA DE PRATOS", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            
            msg = JOptionPane.showInputDialog(null,"Introduza o id do prato a alterar:", "ALTERAR PRATO", JOptionPane.INFORMATION_MESSAGE);
            if(id.contains(msg)){
                
                i = id.indexOf(msg);
                msg = (String) "Prato encontrado: "+ prato.get(i) + "\nQueres mesmo alterar?";
                j = JOptionPane.showConfirmDialog(null, msg, "ALTERAR PRATO", JOptionPane.YES_NO_OPTION);
                
                if(j==JOptionPane.YES_OPTION){
                    nome = JOptionPane.showInputDialog(null,"Novo Nome:", "ALTERAR PRATO", JOptionPane.INFORMATION_MESSAGE); prato.set(i, nome);
                    msg = (String) JOptionPane.showInputDialog(null , "Nova Categoria do Prato:", "CADASTRAR NOVO PRATO", JOptionPane.QUESTION_MESSAGE, null, categoria, categoria[1]);categ.set(i, msg);
                    msg = JOptionPane.showInputDialog(null,"Novo Preço do Prato:", "ALTERAR PRATO", JOptionPane.INFORMATION_MESSAGE); preco.set(i, msg);
                    
                    //gravar dados
                        gravarDados(prato, txtPrato);
                        gravarDados(id, txtIdPrato);
                        gravarDados(categ, txtCateg);
                        gravarDados(preco, txtPreco);
                    
                    JOptionPane.showMessageDialog(null, "Prato '"+nome+"' Alterado Com Sucesso!", "ALTERAR PRATO", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Nenhum Prato com o id especificado Encontrado!", "ALTERAR PRATO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
