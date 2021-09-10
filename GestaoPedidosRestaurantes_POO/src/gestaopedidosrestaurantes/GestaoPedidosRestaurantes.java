package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.GravarDados.gravarDados;
import static gestaopedidosrestaurantes.CarregarDados.*;
import static gestaopedidosrestaurantes.LimparTela.limparTela;
import static gestaopedidosrestaurantes.Login.login;
import static gestaopedidosrestaurantes.MenuPrincipal.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Simão Moreno
 * Numero 110406
 * Email: simaomoreno.unicv@gmail.com
 * 9848806
 */
public class GestaoPedidosRestaurantes {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        //criar a classe scanner para criar o objecto scan para leitura do teclado
            Scanner scan = new Scanner(System.in);
            
        //definir a localizacao dos ficheiros com as suas variaveis
            File txtFunc = new File("Funcionarios.txt"); 
            File txtPratos = new File("Pratos.txt"); 
            File txtPedidos = new File("Pedidos.txt"); 
        
        //arraylist dos dados
            ArrayList <Funcionario> funcionarios = new ArrayList <>();
            ArrayList <Prato> pratos = new ArrayList <>();
            ArrayList <Pedido> pedidos = new ArrayList <>();
            
        //objecto funcionario para guardar dados do funcionario que faz login no sistema
            Funcionario funcionarioOnline = new Funcionario();

            String opcao ;
        
        //carregar dados dos ficheiros
            carregarFuncionarios(funcionarios, txtFunc);
            carregarPratos(pratos,txtPratos);
            carregarPedidos(pedidos, txtPedidos);
            
        //Menu Login
            do {
                limparTela();//limpar tela
                SimpleDateFormat relogio = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
                Calendar data = new GregorianCalendar();
                System.out.println("======================================================================");
                System.out.println("                                                     " + relogio.format(data.getTime()));
                System.out.println("======================================================================");
                System.out.println("|                   Bem-Vindo ao gestão de Pedidos                   |");
                System.out.println("|                               do                                   |");
                System.out.println("|                         Restaurante Piteu                          |");
                System.out.println("======================================================================");
                System.out.println("| (A) Fazer login                                                    |");
                System.out.println("| (S) Sair                                                           |");
                System.out.println("|                                                                    |");
                System.out.println("|                                              PowerBy Saiman Moreno |");
                System.out.println("======================================================================");
                System.out.print(">> "); opcao = scan.next();
                
                switch (opcao.toLowerCase()) {
                    case "a":
                        funcionarioOnline = login(funcionarios); //fazer login
                        switch (funcionarioOnline.getCargo().toLowerCase()) {
                            case "administrador": MenuAdministrador(pratos, funcionarios, pedidos, funcionarioOnline); break;
                            case "garçom": MenuGarcom(pratos, funcionarios, pedidos, funcionarioOnline); break;
                            case "cozinha": MenuCozinheiro(pratos, funcionarios, pedidos, funcionarioOnline); break;
                        }
                        
                        //gravar dados nos ficheiros
                            gravarDados(funcionarios, txtFunc);
                            gravarDados(pratos, txtPratos);
                            gravarDados(pedidos, txtPedidos);
                            
                        break;
                    case "s":
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcao inválida");
                }
            } while (!opcao.equalsIgnoreCase("s"));
    }
}