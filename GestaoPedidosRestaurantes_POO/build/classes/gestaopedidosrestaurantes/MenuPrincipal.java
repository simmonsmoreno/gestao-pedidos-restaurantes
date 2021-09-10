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
import java.util.*;

public class MenuPrincipal{
    
    public static Scanner scan = new Scanner(System.in);
    public static int opcao;
    
    public static void MenuAdministrador(ArrayList<Prato> pratos, ArrayList<Funcionario> funcionarios, ArrayList<Pedido> pedidos, Funcionario funcionarioOnline) throws IOException{
        
        do{
            limparTela();//limpar tela
            System.out.println("========================Gestão de Pedidos=============================");
            System.out.println("                                            Login como Administrador  ");
            System.out.println(" (1) Gerir Funcionarios                                            ");
            System.out.println(" (2) Gerir Pratos                                                  ");
            System.out.println(" (3) Histórico de Pedidos                                             ");
            System.out.println(" (4) Estatísticas do Restaurante                                      ");
            System.out.println(" (5) Meu Perfil                                                       ");
            System.out.println(" (0) Sair                                                             ");
            System.out.println("======================================================================");
            System.out.print  (">> "); opcao = scan.nextInt();

            switch (opcao) {
                case 1: 
                    do {
                        limparTela();//limpar tela
                        System.out.println("========================Gerir Funcionarios============================");
                        System.out.println(" (1) Adicionar Funcionarios                                           ");
                        System.out.println(" (2) Listar Funcionarios                                              ");
                        System.out.println(" (3) Remover Funcionarios                                             ");
                        System.out.println(" (0) Voltar ao Menu Principal                                         ");
                        System.out.println("======================================================================");
                        System.out.print  (">> "); opcao = scan.nextInt();

                        switch (opcao) {
                            case 1: addFuncionarios(funcionarios); break;
                            case 2: listarFuncionarios(funcionarios);break;
                            case 3: removerFuncionarios(funcionarios, funcionarioOnline);break;
                            case 0: break;
                            default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.next();
                        }
                    }while (opcao!=0);
                    opcao=1;
                    break;
                case 2: 
                    do {
                        limparTela();//limpar tela
                        System.out.println("============================Gerir Pratos==============================");
                        System.out.println(" (1) Adicionar Pratos                                                 ");
                        System.out.println(" (2) Listar Pratos                                                    ");
                        System.out.println(" (3) Alterar Pratos                                                   ");
                        System.out.println(" (4) Remover Pratos                                                   ");
                        System.out.println(" (0) Voltar ao Menu Principal                                         ");
                        System.out.println("======================================================================");
                        System.out.print  (">> "); opcao = scan.nextInt();

                        switch (opcao) {
                            case 1: addPratos(pratos);break;
                            case 2: listarPratos(pratos);break;
                            case 3: alterarPratos(pratos);break;
                            case 4: removerPratos(pratos);break;
                            case 0: break;
                            default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.next();
                        }
                    }while (opcao!=0);
                    opcao=2;
                    break;
                case 3: historico(pedidos, funcionarios, funcionarioOnline); break;
                case 4: estatisticas(pratos, pedidos, funcionarios); break;
                case 5: editarPerfil(funcionarios, funcionarioOnline); break;
                case 0: break;
                default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.next();
            }
        }while (opcao!=0);
    }
    
    public static void MenuGarcom(ArrayList<Prato> pratos, ArrayList<Funcionario> funcionarios, ArrayList<Pedido> pedidos, Funcionario funcionarioOnline) throws InterruptedException{
        
        do{
            limparTela();//limpar tela
            System.out.println("========================Gestão de Pedidos=============================");
            System.out.println("                                                   Login como Garçom  ");
            System.out.println(" (1) Cardápio                                                         ");
            System.out.println(" (2) Pedidos                                                          ");
            System.out.println(" (3) Histórico de Pedidos                                             ");
            System.out.println(" (4) Meu Perfil                                                       ");
            System.out.println(" (0) Sair                                                             ");
            System.out.println("======================================================================");
            System.out.print  (">> "); opcao = scan.nextInt();

            switch (opcao) {
                case 1: 
                    cardapio(pratos); 
                    break;
                case 2: 
                    do{
                        limparTela();//limpar tela
                        System.out.println("==============================Pedidos=================================");
                        System.out.println(" (1) Fazer Pedidos                                                    ");
                        System.out.println(" (2) Entregar Pedidos                                                 ");
                        System.out.println(" (0) Voltar ao menu anterior                                          ");
                        System.out.println("======================================================================");
                        System.out.print  (">> "); opcao = scan.nextInt();
                        switch (opcao) {
                            case 1: fazerPedidos(pratos, pedidos, funcionarioOnline);break;
                            case 2: entregarPedidos(pedidos, funcionarios);break;
                            case 0: break;
                            default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.next();
                        }
                    }while (opcao!=0);
                    opcao=2;
                break;
                case 3: historico(pedidos, funcionarios, funcionarioOnline); break;
                case 4: editarPerfil(funcionarios, funcionarioOnline); break;
                case 0: break;
                default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.next();
            }
        }while (opcao!=0);
    }
    
    public static void MenuCozinheiro(ArrayList<Prato> pratos, ArrayList<Funcionario> funcionarios, ArrayList<Pedido> pedidos, Funcionario funcionarioOnline) throws IOException, InterruptedException{
        
        do{
            limparTela();//limpar tela
            System.out.println("========================Gestão de Pedidos=============================");
            System.out.println("                                            Login como Cozinheiro(a)  ");
            System.out.println(" (1) Gerir Pratos                                                 ");
            System.out.println(" (2) Pedidos                                                          ");
            System.out.println(" (3) Histórico de Pedidos                                             ");
            System.out.println(" (4) Meu Perfil                                                       ");
            System.out.println(" (0) Sair                                                             ");
            System.out.println("======================================================================");
            System.out.print  (">> "); opcao = scan.nextInt();
            
            switch (opcao) {
                case 1: 
                    do {
                        limparTela();//limpar tela
                        System.out.println("============================Gerir Pratos==============================");
                        System.out.println(" (1) Adicionar Pratos                                                 ");
                        System.out.println(" (2) Listar Pratos                                                    ");
                        System.out.println(" (3) Alterar Pratos                                                   ");
                        System.out.println(" (4) Remover Pratos                                                   ");
                        System.out.println(" (0) Voltar ao Menu Principal                                         ");
                        System.out.println("======================================================================");
                        System.out.print  (">> "); opcao = scan.nextInt();

                        switch (opcao) {
                            case 1: addPratos(pratos);break;
                            case 2: listarPratos(pratos);break;
                            case 3: alterarPratos(pratos);break;
                            case 4: removerPratos(pratos);break;
                            case 0: break;
                            default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.next();
                        }
                    }while (opcao!=0);
                    opcao=1;
                    break;
                case 2: processarPedidos(pedidos, funcionarios, funcionarioOnline);break;
                case 3: historico(pedidos, funcionarios, funcionarioOnline);break;
                case 4: editarPerfil(funcionarios, funcionarioOnline); break;
                case 0: break;
                default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.next();
            }
        }while (opcao!=0);
    }
}