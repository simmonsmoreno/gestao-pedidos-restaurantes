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
public class Adicionar {
    
    //adicionar
    public static void adicionarGarcon(ArrayList garcon, ArrayList password, ArrayList idade, ArrayList morada, ArrayList sexo, ArrayList telef) throws IOException{
        
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
            idade.add(Idade.getText());
            morada.add(userMorada.getText());
            sexo.add(boxSex.getSelectedItem().toString());
            telef.add(telefone.getText());
            
            JOptionPane.showMessageDialog(null, "Garçon '"+nome.getText()+"' Adicionado Com Sucesso", "CADASTRAR GARÇON", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void addPrato(ArrayList prato,  ArrayList id, ArrayList categ, ArrayList preco) throws IOException{
        
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
        int result = JOptionPane.showConfirmDialog(null, ob, "Adicionar Prato", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            prato.add(nomePrato.getText());
            id.add(idPrato.getText());
            categ.add(boxCateg.getSelectedItem().toString());
            preco.add(preçoPrato.getText());
            
            JOptionPane.showMessageDialog(null, "Prato '"+nomePrato.getText()+"' Adicionado Com Sucesso", "CADASTRAR NOVO PRATO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
