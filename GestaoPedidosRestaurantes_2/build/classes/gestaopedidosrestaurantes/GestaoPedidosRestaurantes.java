package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.Adicionar.addPrato;
import static gestaopedidosrestaurantes.Adicionar.adicionarGarcon;
import static gestaopedidosrestaurantes.Alterar.alterarGarcon;
import static gestaopedidosrestaurantes.Alterar.alterarPratos;
import static gestaopedidosrestaurantes.Cardapio.cardapio;
import static gestaopedidosrestaurantes.CarregarDados.carregarDados;
import static gestaopedidosrestaurantes.GravarDados.gravarDados;
import static gestaopedidosrestaurantes.HistoricoPedidos.historicoPedidos;
import static gestaopedidosrestaurantes.Listar.listarGarcon;
import static gestaopedidosrestaurantes.Listar.listarPratos;
import static gestaopedidosrestaurantes.Pedidos.pedidos;
import static gestaopedidosrestaurantes.Remover.removerGarcon;
import static gestaopedidosrestaurantes.Remover.removerPrato;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Simão Moreno
 */
public class GestaoPedidosRestaurantes {

    public static void main(String[] args) throws IOException {
        
        //configuraões do JOptionPane
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Rockwell", Font.PLAIN, 14)));
            UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
            UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
        
        //definir a localizacao dos ficheiros com as suas variaveis
            File txtGarcon = new File("DadosGarcon/Nomes.txt");
            File txtPassword = new File("DadosGarcon/Passwords.txt");
            File txtIdade = new File("DadosGarcon/Idades.txt");
            File txtMorada= new File("DadosGarcon/Endereços.txt");
            File txtSexo = new File("DadosGarcon/Sexos.txt");
            File txtTelef = new File("DadosGarcon/Telefones.txt");
            File txtPratos = new File("DadosPratos/Nomes.txt");
            File txtIdPratos = new File("DadosPratos/Ids.txt");
            File txtCateg = new File("DadosPratos/Categorias.txt");
            File txtPreco = new File("DadosPratos/Preços.txt");
            File txtPedidos = new File("DadosPratos/Pedidos.txt");
            File txtFatura = new File("DadosPratos/Faturas.txt");
        
        //arraylist de dados dos garcons
            ArrayList <String> garcon = new ArrayList <>();
            ArrayList <String> password = new ArrayList <>();
            ArrayList <String> idade = new ArrayList <>();
            ArrayList <String> morada = new ArrayList <>();
            ArrayList <String> sexo = new ArrayList <>();
            ArrayList <String> telef = new ArrayList <>();
       
            
        //arraylist de dados dos pratos
            ArrayList <String> pratos = new ArrayList <>();
            ArrayList <String> idPratos = new ArrayList <>();
            ArrayList <String> categ = new ArrayList <>();
            ArrayList <String> preco = new ArrayList <>();
            ArrayList <String> pedidos = new ArrayList <>();
            ArrayList <String> fatura = new ArrayList <>();
        
        //declaração variaveis
            int op = 0;
            boolean entrar = false;
        
        //carregar dados dos ficheiros
            carregarDados(garcon, txtGarcon);
            carregarDados(password, txtPassword);
            carregarDados(idade, txtIdade);
            carregarDados(morada, txtMorada);
            carregarDados(sexo, txtSexo);
            carregarDados(telef, txtTelef);
            carregarDados(pratos, txtPratos);
            carregarDados(idPratos, txtIdPratos);
            carregarDados(categ, txtCateg);
            carregarDados(preco, txtPreco);
            carregarDados(pedidos, txtPedidos);
        
        //fazer login
        /*do{
        login(garcon, password);
        System.out.println(login(garcon, password));
        }while(!login(garcon, password));*/
        
        
        //MENU PRINCIPAL 
        do {            
            op = Integer.parseInt((String) JOptionPane.showInputDialog(null, ""
                    + "(1) Editar Pratos\n"
                    + "(2) Cardápio\n"
                    + "(3) Pedidos\n"
                    + "(4) Gestão de Garçons\n"
                    + "(5) Histórico de Pedidos\n"
                    + "(0) Sair", "MENU"
                    ,JOptionPane.PLAIN_MESSAGE));
            switch (op) {
                case 1:
                    int op1;
                    
                    //SUB-MENU GESTÃO DE PRATOS
                    do {
                        op1 =(Integer.parseInt(JOptionPane.showInputDialog(null,""
                                + "(1) Adicionar Novo Prato\n"
                                + "(2) Listar Pratos\n"
                                + "(3) Alterar Dados dos Pratos Existentes\n"
                                + "(4) Remover Prato\n"
                                + "(0) Voltar ao Menu Principal", "MENU - GESTÃO DE PRATOS", 
                                JOptionPane.PLAIN_MESSAGE)));

                        switch (op1) {
                            case 1:
                                addPrato(pratos, idPratos, categ, preco);break;
                            case 2:
                                listarPratos(pratos, idPratos, categ, preco);break;
                            case 3:
                                alterarPratos(pratos, idPratos, categ, preco);break;
                            case 4:
                                removerPrato(pratos, idPratos, categ, preco); break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção Inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }
                    }while (op1!=0); 
                    break;
                case 2:
                    cardapio(pratos, idade, categ, preco, pedidos);break;
                case 3:
                    pedidos(pratos, idPratos, categ, preco, garcon, pedidos, fatura); break;
                case 4:
                    int op2;
                    
                    //SUB-MENU GESTÃO DE GARÇONS
                    do {
                        op2 =(Integer.parseInt(JOptionPane.showInputDialog(null, ""
                                + "(1) Adicionar Novo Garçon\n"
                                + "(2) Listar Garçons\n"
                                + "(3) Alterar Dados dos Garçons Existentes\n"
                                + "(4) Remover Garçons\n"
                                + "(0) Voltar ao Menu Principal", "MENU - GESTÃO DE GARÇONS", 
                                JOptionPane.PLAIN_MESSAGE)));

                        switch (op2) {
                            case 1:
                                adicionarGarcon(garcon, password, idade, morada, sexo, telef); break;
                            case 2:
                                listarGarcon(garcon, idade, morada, sexo, telef);break;
                            case 3:
                                alterarGarcon(garcon,  password, morada, telef) ;break;
                            case 4:
                                removerGarcon(garcon,  password, idade, morada, sexo, telef);break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção Inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }
                    }while (op2!=0); 
                    break;
                case 5:
                    historicoPedidos(pedidos); break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Introduza uma opção correta!", "Opção Errada", JOptionPane.ERROR_MESSAGE);
            }
        }while (op!=0);
        
        //gravar dados nos ficheiros
            gravarDados(garcon, txtGarcon);
            gravarDados(password, txtPassword);
            gravarDados(idade, txtIdade);
            gravarDados(morada, txtMorada);
            gravarDados(sexo, txtSexo);
            gravarDados(telef, txtTelef);
            gravarDados(pratos, txtPratos);
            gravarDados(idPratos, txtIdPratos);
            gravarDados(categ, txtCateg);
            gravarDados(preco, txtPreco);
            gravarDados(pedidos, txtPedidos);
    }
}
