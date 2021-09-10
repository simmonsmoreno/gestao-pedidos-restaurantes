package gestaopedidosrestaurantes;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @Simão Moreno
 */
public class Alterar {
    
    //alterar dados garcon
    public static void alterarGarcon(ArrayList garcon, ArrayList password, ArrayList morada, ArrayList telef) throws IOException{
        
        String msg;
        int i, j;
        
        if(garcon.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Garçons Registado! Por Favor Adicione Garçons Primeiro", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            
            msg = JOptionPane.showInputDialog(null,"Introduza a senha do graçon a alterar:", "ALTERAR GARÇONS", JOptionPane.INFORMATION_MESSAGE);
            if(password.contains(msg)){
                
                i = password.indexOf(msg);
                msg = (String) "Garçon encontrado: "+ garcon.get(i) + "\nQueres mesmo alterar?";
                j = JOptionPane.showConfirmDialog(null, msg, "ALTERAR PRATO", JOptionPane.YES_NO_OPTION);
                
                if(j==JOptionPane.YES_OPTION){
                    String []sex = {"Masculino", "Feminino"};
                    JLabel jUserName = new JLabel("Nome do Garçon");
                    JTextField nome = new JTextField();
                    JLabel jSenha = new JLabel("Password");
                    JTextField senha = new JPasswordField();
                    JLabel jIdade = new JLabel("Data Nascimento (DD/MM/AA)");
                    JTextField Idade = new JTextField();
                    JLabel jSex = new JLabel("Sex");
                    String[] item = new String[]{"Masculino", "Feminino"};
                    JComboBox boxSex = new JComboBox(item);
                    JLabel jUserTelef = new JLabel("Telefone");
                    JTextField telefone = new JTextField();
                    JLabel jUserMorada = new JLabel("Morada");
                    JTextField userMorada = new JTextField();

                    Object[] ob = {jUserName, nome, jSenha, senha, jSex, boxSex, jIdade, Idade, 
                        jUserTelef, telefone, jUserMorada, userMorada};
                    int result = JOptionPane.showConfirmDialog(null, ob, "Adicionar Garçon", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        garcon.add(nome.getText());
                        password.add(senha.getText());
                        morada.add(userMorada.getText());
                        telef.add(telefone.getText());

                        JOptionPane.showMessageDialog(null, "Garçon '"+nome.getText()+"' Adicionado Com Sucesso", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Nenhum Garçon com o id especificado Encontrado!", "ALTERAR GARÇONS", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void alterarPratos(ArrayList prato,  ArrayList id, ArrayList categ, ArrayList preco) throws IOException{
        
        if(prato.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum Prato Registado! Por Favor Adicione Pratos Primeiro", "LISTA DE PRATOS", JOptionPane.INFORMATION_MESSAGE);
        }else{
            
            String msg = JOptionPane.showInputDialog(null,"Introduza o id do prato a alterar:", "ALTERAR PRATO", JOptionPane.INFORMATION_MESSAGE);
            
            if(id.contains(msg)){

                int i = id.indexOf(msg);
                msg = (String) "Prato encontrado: "+ prato.get(i) + "\nQueres mesmo alterar?";
                int j = JOptionPane.showConfirmDialog(null, msg, "ALTERAR PRATO", JOptionPane.YES_NO_OPTION);

                if(j==JOptionPane.YES_OPTION){
                    JLabel jNomePrato = new JLabel("Nome do Prato");
                    JTextField nomePrato = new JTextField();
                    JLabel jIdPrato = new JLabel("Id Prato");
                    JTextField idPrato = new JTextField();
                    JLabel jCateg = new JLabel("Categoria");
                    String []categoria = {"Entrada", "Prato Principal", "Sobremesa", "Bebidas", "Pequeno-Almoço", "Fast-Foods"};
                    JComboBox boxCateg = new JComboBox(categoria);
                    JLabel jPreço = new JLabel("Preço");
                    JTextField preçoPrato = new JTextField();

                    Object[] ob = {jNomePrato, nomePrato, jIdPrato, idPrato, jCateg, boxCateg, jPreço, preçoPrato};
                    int result = JOptionPane.showConfirmDialog(null, ob, "ALTERAR PRATO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        prato.set(i, nomePrato.getText());
                        categ.set(i, boxCateg.getSelectedItem().toString());
                        preco.set(i, preçoPrato.getText());

                        JOptionPane.showMessageDialog(null, "Prato '"+nomePrato.getText()+"' Alterado Com Sucesso", "ALTERAR PRATO", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Nenhum Prato com o id especificado Encontrado!", "ALTERAR PRATO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
