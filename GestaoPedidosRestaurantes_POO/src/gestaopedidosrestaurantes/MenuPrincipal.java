package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.Adicionar.*;
import static gestaopedidosrestaurantes.Alterar.*;
import static gestaopedidosrestaurantes.HistoricoPedidos.historico;
import static gestaopedidosrestaurantes.Estatísticas.estatisticas;
import static gestaopedidosrestaurantes.Cardapio.cardapio;
import static gestaopedidosrestaurantes.LimparTela.limparTela;
import static gestaopedidosrestaurantes.Listar.*;
import static gestaopedidosrestaurantes.Pedidos.*;
import static gestaopedidosrestaurantes.Remover.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class MenuPrincipal{
    
    //variaveis globais
    public static SimpleDateFormat relogio = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
    public static Calendar data = new GregorianCalendar();
    public static Scanner scan = new Scanner(System.in);
    
    public static void MenuAdministrador(ArrayList<Prato> pratos, ArrayList<Funcionario> funcionarios, ArrayList<Pedido> pedidos, Funcionario funcionarioOnline) throws IOException{
        
        String subOpcao;
        
        do{
            limparTela();//limpar tela
            System.out.println("========================Gestão de Pedidos=============================");
            System.out.println("                                            Login como Administrador  ");
            System.out.println(" (A) Gerir Funcionarios                                               ");
            System.out.println(" (B) Gerir Pratos                                                     ");
            System.out.println(" (C) Histórico de Pedidos                                             ");
            System.out.println(" (D) Estatísticas do Restaurante                                      ");
            System.out.println(" (E) Meu Perfil                                                       ");
            System.out.println(" (S) Sair                                                             ");
            System.out.println("                                                     " + relogio.format(data.getTime()));
            System.out.println("======================================================================");
            System.out.print  (">> ");  subOpcao = scan.next();

            switch (subOpcao.toLowerCase()) {
                case "a": 
                    String OutroOpcao;
                    do {
                        limparTela();//limpar tela
                        System.out.println("========================Gerir Funcionarios============================");
                        System.out.println(" (A) Adicionar Funcionarios                                           ");
                        System.out.println(" (B) Listar Funcionarios                                              ");
                        System.out.println(" (C) Remover Funcionarios                                             ");
                        System.out.println(" (S) Voltar ao Menu Principal                                         ");
                        System.out.println("======================================================================");
                        System.out.print  (">> ");  OutroOpcao = scan.next();
                        
                        switch (OutroOpcao.toLowerCase()) {
                            case "a": addFuncionarios(funcionarios); break;
                            case "b": listarFuncionarios(funcionarios);break;
                            case "c": removerFuncionarios(funcionarios, funcionarioOnline);break;
                            case "s": break;
                            default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
                        }
                    }while (!OutroOpcao.equalsIgnoreCase("s"));
                    break;
                case "b": 
                    String OutroOpcao1;
                    do {
                        limparTela();//limpar tela
                        System.out.println("============================Gerir Pratos==============================");
                        System.out.println(" (A) Adicionar Pratos                                                 ");
                        System.out.println(" (B) Listar Pratos                                                    ");
                        System.out.println(" (C) Alterar Pratos                                                   ");
                        System.out.println(" (D) Remover Pratos                                                   ");
                        System.out.println(" (S) Voltar ao Menu Principal                                         ");
                        System.out.println("======================================================================");
                        System.out.print  (">> "); OutroOpcao1 = scan.next();

                        switch (OutroOpcao1.toLowerCase()) {
                            case "a": addPratos(pratos);break;
                            case "b": listarPratos(pratos);break;
                            case "c": alterarPratos(pratos);break;
                            case "d": removerPratos(pratos);break;
                            case "s": break;
                            default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
                        }
                    }while (!OutroOpcao1.equalsIgnoreCase("s"));
                    break;
                case "c": historico(pedidos, pratos, funcionarios, funcionarioOnline); break;
                case "d": estatisticas(pratos, pedidos, funcionarios); break;
                case "e": editarPerfil(funcionarios, funcionarioOnline); break;
                case "s": break;
                default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
                
            }
        }while (!subOpcao.equalsIgnoreCase("s"));
    }
    
    public static void MenuGarcom(ArrayList<Prato> pratos, ArrayList<Funcionario> funcionarios, ArrayList<Pedido> pedidos, Funcionario funcionarioOnline) throws InterruptedException{
        
        String subOpcao1;
        
        do{
            limparTela();//limpar tela
            System.out.println("========================Gestão de Pedidos=============================");
            System.out.println("                                                   Login como Garçom  ");
            System.out.println(" (A) Cardápio                                                         ");
            System.out.println(" (B) Pedidos                                                          ");
            System.out.println(" (C) Histórico de Pedidos                                             ");
            System.out.println(" (D) Meu Perfil                                                       ");
            System.out.println(" (S) Sair                                                             ");
            System.out.println("                                                     " + relogio.format(data.getTime()));
            System.out.println("======================================================================");
            System.out.print  (">> ");  subOpcao1 = scan.next();

            switch (subOpcao1.toLowerCase()) {
                case "a": 
                    cardapio(pratos); 
                    break;
                case "b": 
                    String OutroOpcao3;
                    do{
                        limparTela();//limpar tela
                        System.out.println("==============================Pedidos=================================");
                        System.out.println(" (A) Fazer Pedidos                                                    ");
                        System.out.println(" (B) Entregar Pedidos                                                 ");
                        System.out.println(" (S) Voltar ao menu anterior                                          ");
                        System.out.println("======================================================================");
                        System.out.print  (">> ");  OutroOpcao3 = scan.next();
                        
                        switch (OutroOpcao3.toLowerCase()) {
                            case "a": fazerPedidos(pratos, pedidos, funcionarioOnline);break;
                            case "b": entregarPedidos(pedidos, pratos, funcionarios);break;
                            case "s": break;
                            default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
                        }
                    }while (!OutroOpcao3.equalsIgnoreCase("s"));
                break;
                case "c": historico(pedidos, pratos, funcionarios, funcionarioOnline); break;
                case "d": editarPerfil(funcionarios, funcionarioOnline); break;
                case "s": break;
                default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
            }
        }while (!subOpcao1.equalsIgnoreCase("s"));
    }
    
    public static void MenuCozinheiro(ArrayList<Prato> pratos, ArrayList<Funcionario> funcionarios, ArrayList<Pedido> pedidos, Funcionario funcionarioOnline) throws IOException, InterruptedException{
        
        String subOpcao2;
        
        do{
            limparTela();//limpar tela
            System.out.println("========================Gestão de Pedidos=============================");
            System.out.println("                                            Login como Cozinheiro(a)  ");
            System.out.println(" (A) Gerir Pratos                                                     ");
            System.out.println(" (B) Pedidos                                                          ");
            System.out.println(" (C) Histórico de Pedidos                                             ");
            System.out.println(" (D) Meu Perfil                                                       ");
            System.out.println(" (S) Sair                                                             ");
            System.out.println("                                                     " + relogio.format(data.getTime()));
            System.out.println("======================================================================");
            System.out.print  (">> ");  subOpcao2 = scan.next();
            
            switch (subOpcao2.toLowerCase()) {
                case "a": 
                    String OutroOpcao4;
                    do {
                        limparTela();//limpar tela
                        System.out.println("============================Gerir Pratos==============================");
                        System.out.println(" (A) Adicionar Pratos                                                 ");
                        System.out.println(" (B) Listar Pratos                                                    ");
                        System.out.println(" (C) Alterar Pratos                                                   ");
                        System.out.println(" (D) Remover Pratos                                                   ");
                        System.out.println(" (S) Voltar ao Menu Principal                                         ");
                        System.out.println("======================================================================");
                        System.out.print  (">> ");  OutroOpcao4 = scan.next();

                        switch (OutroOpcao4.toLowerCase()) {
                            case "a": addPratos(pratos);break;
                            case "b": listarPratos(pratos);break;
                            case "c": alterarPratos(pratos);break;
                            case "d": removerPratos(pratos);break;
                            case "s": break;
                            default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
                        }
                    }while (!OutroOpcao4.equalsIgnoreCase("s"));
                    break;
                case "b": processarPedidos(pedidos, pratos, funcionarios, funcionarioOnline);break;
                case "c": historico(pedidos, pratos, funcionarios, funcionarioOnline);break;
                case "d": editarPerfil(funcionarios, funcionarioOnline); break;
                case "s": break;
                default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
            }
        }while (!subOpcao2.equalsIgnoreCase("s"));
    }
}