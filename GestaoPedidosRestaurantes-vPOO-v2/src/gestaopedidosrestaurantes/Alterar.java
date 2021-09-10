package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Alterar {
    
    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    public static boolean encontrou = false;
    public static String opcao;
        
    static void alterarPratos(ArrayList<Prato> pratos) {
    
        try {
            limparTela();//limpar tela
            System.out.println("===========================Alterar Pratos=============================");
            for(Prato prato: pratos){
                System.out.println("Prato: "+ prato.getNome() + "\nId: " + prato.getId());
                System.out.println("----------------------------------------------------------------------");
            }
            System.out.print("Introduza o id do prato a alterar\n>> "); String id = scan.readLine();
            
            for (Prato prato : pratos){
                if(prato.getId().equals(id)){
                    encontrou = true;
                    do {
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" (A) Alterar Nome                                                     ");
                        System.out.println(" (B) Alterar Categoria                                                ");
                        System.out.println(" (C) Alterar Preço                                                    ");
                        System.out.println(" (S) Voltar ao Menu Principal                                         ");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.print(">> "); opcao = scan.readLine();
                        switch (opcao.toLowerCase()) {
                            case "a":
                                System.out.print("Novo Nome: "); prato.setNome(scan.readLine());
                                System.out.println("Nome alterado com sucesso!");
                                break;
                            case "b":
                                System.out.println("Escolha a categoria:");
                                System.out.println(" (A) Entrada");
                                System.out.println(" (B) Prato Principal");
                                System.out.println(" (C) Sobremesa");
                                System.out.println(" (D) Bebidas");
                                System.out.println(" (E) Pequeno-Almoço");
                                System.out.println(" (F) Fast-Foods");
                                System.out.print(">> "); opcao = scan.readLine();
                                switch (opcao.toLowerCase()) {
                                    case "a": prato.setCategoria("Entrada"); break;
                                    case "b": prato.setCategoria("Prato Principal"); break;
                                    case "c": prato.setCategoria("Sobremesa"); break;
                                    case "d": prato.setCategoria("Bebidas"); break;
                                    case "e": prato.setCategoria("Pequeno-Almoço"); break;
                                    case "f": prato.setCategoria("Fast-Foods"); break;
                                    default: System.out.println("Escolha uma categoria!");
                                }
                                System.out.println("Categoria alterado com sucesso!");
                                break;
                            case "c":
                                System.out.print("Novo Preço >> "); prato.setPreço(scan.read());
                                System.out.println("Preço alterado com sucesso!");
                                break;
                            case "s":
                                break;
                            default: System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.readLine();
                        }
                        pratos.set(pratos.indexOf(prato), prato);
                        break;
                    } while (!opcao.toLowerCase().equals("s"));
                }
            }
            if(!encontrou) System.out.println("Id não encontrada");
            
            System.out.println("======================================================================");
            System.out.print("Press (1) to continue >> "); scan.readLine();
        } catch (IOException ex) {
            System.out.println("Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Alterar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void editarPerfil(ArrayList<Funcionario> funcionarios, Funcionario funcionarioOnline) {
    
        do { 
            try {
                limparTela();//limpar tela
                System.out.println("=============================Meu Perfil===============================");
                System.out.println("Informações Gerais:");
                System.out.println("----------------------------------------------------------------------");
                System.out.println(funcionarioOnline.showFuncionario());
                System.out.println("----------------------------------------------------------------------");
                System.out.println("                                                     Alterar Nome (A) ");
                System.out.println("                                                 Alterar Password (B) ");
                System.out.println("                                       Alterar Data de Nascimento (C) ");
                System.out.println("                                                     Alterar Sexo (D) ");
                System.out.println("                                                   Alterar Morada (E) ");
                System.out.println("                                                 Alterar Telefone (F) ");
                System.out.println("                                         Voltar ao Menu Principal (S) ");
                System.out.println("======================================================================");
                System.out.print(">> "); opcao = scan.readLine();
                switch (opcao.toLowerCase()) {
                    case "a":
                        System.out.print("Novo Nome: "); funcionarioOnline.setNome(scan.readLine());
                        System.out.println("Nome alterado com sucesso!");
                        System.out.print("Press (1) to continue >> "); scan.readLine();
                        break;
                    case "b":
                        System.out.print("Password Antigo: "); String pass=scan.readLine();
                        if(funcionarioOnline.getPassword().equals(pass)){
                            System.out.print("Novo Password: ");funcionarioOnline.setPassword(scan.readLine());
                            System.out.println("Password alterado com sucesso!");
                            System.out.print("Press (1) to continue >> "); scan.readLine();
                        }else{
                            System.out.println("Password incorrecta!");
                            System.out.print("Press (1) to continue >> "); scan.readLine();
                        }
                        break;
                    case "c":
                        System.out.print("Novo Data Nascimento (dd/mm/aa): "); funcionarioOnline.setDataNasc(scan.readLine());
                        System.out.println("Data de Nascimento alterado com sucesso!");
                        System.out.print("Press (1) to continue >> "); scan.readLine();
                        break;
                    case "d":
                        System.out.print("Novo Sexo >> "); funcionarioOnline.setSexo(scan.readLine());
                        System.out.println("Sexo alterado com sucesso!");
                        System.out.print("Press (1) to continue >> "); scan.readLine();
                        break;
                    case "e":
                        System.out.print("Novo Morada >> "); funcionarioOnline.setMorada(scan.readLine());
                        System.out.println("Morada alterado com sucesso!");
                        System.out.print("Press (1) to continue >> "); scan.readLine();
                        break;
                    case "f":
                        System.out.print("Novo Telefone >> "); funcionarioOnline.setTelefone(scan.readLine());
                        System.out.println("Telefone alterado com sucesso!");
                        System.out.print("Press (1) to continue >> "); scan.readLine();
                        break;
                    case "s":
                        break;
                    default:
                        System.out.print("Opção Inválida!\nPress (1) para continuar >> "); scan.readLine();
                }
                funcionarios.set(funcionarios.indexOf(funcionarioOnline), funcionarioOnline);
            } catch (IOException ex) {
                Logger.getLogger(Alterar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!opcao.toLowerCase().equals("s"));
    }
}