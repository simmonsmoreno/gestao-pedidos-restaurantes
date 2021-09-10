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
public class Adicionar {
    
    //adicionar
    public static void adicionarGarcon(ArrayList garcon,  ArrayList password, ArrayList idade, ArrayList morada, 
                                            ArrayList sexo, ArrayList telef, File txtGarcon, File txtPassword, 
                                                File txtIdade,  File txtMorada, File txtSexo,  File txtTelef) throws IOException{
        
        String msg, nome;
        String []sex = {"Masculino", "Feminino"};
        
        nome = JOptionPane.showInputDialog(null, "Nome:", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE); garcon.add(nome);
        msg = JOptionPane.showInputDialog(null, "Palavra Passe:", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE); password.add(msg);
        msg = JOptionPane.showInputDialog(null, "Data Nascimento (DD/MM/AA):", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE); idade.add(msg);
        msg = JOptionPane.showInputDialog(null, "Morada:", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE); morada.add(msg);
        msg = (String) JOptionPane.showInputDialog(null , "Sexo:", "CADASTRAR NOVO GARÇON", JOptionPane.INFORMATION_MESSAGE, null, sex,"");sexo.add(msg);
        msg = JOptionPane.showInputDialog(null, "Telefone:", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE); telef.add(msg);
   
        //gravar dados
            gravarDados(garcon, txtGarcon);
            gravarDados(password, txtPassword);
            gravarDados(idade, txtIdade);
            gravarDados(morada, txtMorada);
            gravarDados(sexo, txtSexo);
            gravarDados(telef, txtTelef);
            
        JOptionPane.showMessageDialog(null, "Garçon '"+nome.toUpperCase()+"' Adicionado Com Sucesso", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void addPrato(ArrayList prato,  ArrayList id, ArrayList categ, ArrayList preco,
            File txtPrato, File txtIdPrato, File txtCateg,  File txtPreco) throws IOException{
        
        String msg, nome;
        String []categoria = {"Entrada", "Prato Principal", "Sobremesa", "Bebidas", "Pequeno-Almoço", "Fast-Foods"};
        
        nome = JOptionPane.showInputDialog(null,"Nome do Prato:", "CADASTRAR NOVO PRATO", JOptionPane.INFORMATION_MESSAGE); prato.add(nome);
        msg = JOptionPane.showInputDialog(null,"Id do Prato:", "CADASTRAR NOVO PRATO", JOptionPane.INFORMATION_MESSAGE); id.add(msg);
        msg = (String) JOptionPane.showInputDialog(null , "Categoria do Prato:", "CADASTRAR NOVO PRATO", JOptionPane.QUESTION_MESSAGE, null, categoria, categoria[1]);categ.add(msg);
        msg = JOptionPane.showInputDialog(null,"Preço do Prato:", "CADASTRAR NOVO PRATO", JOptionPane.INFORMATION_MESSAGE); preco.add(msg);
        
        //gravar dados
            gravarDados(prato, txtPrato);
            gravarDados(id, txtIdPrato);
            gravarDados(categ, txtCateg);
            gravarDados(preco, txtPreco);
            
        JOptionPane.showMessageDialog(null, "Prato '"+nome+"' Adicionado Com Sucesso", "CADASTRAR NOVO PRATO", JOptionPane.INFORMATION_MESSAGE);
    }
}
