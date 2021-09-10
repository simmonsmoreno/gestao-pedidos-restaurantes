package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.Adicionar.addFuncionarios;
import static gestaopedidosrestaurantes.LimparTela.limparTela;
import static java.lang.System.exit;
import java.util.*;
import java.io.*;

public class Login {
    
    public static Funcionario login(ArrayList<Funcionario> funcionarios) throws IOException {
        
        Scanner ler = new Scanner(System.in);
        Funcionario funcOnline = new Funcionario();
        String nome, password;
        boolean encontrou = false;
        int oport = 0;
        
        funcOnline.setCargo("");
        
        while(!encontrou) {
            limparTela();//limpar tela
            System.out.println("================================Login=================================");

            if(funcionarios.isEmpty()){
                System.out.print("Nenhum funcionario cadastrado!\nPress (1) para adicionar funcionarios\n>> ");ler.next();
                addFuncionarios(funcionarios);
            }
            oport++;
            System.out.print("Nome >> "); nome = ler.next();
            System.out.print("Senha>> "); password = ler.next();
            System.out.println("======================================================================");
            for(Funcionario funcionario: funcionarios){
                if ((nome.toLowerCase().equalsIgnoreCase(funcionario.getNome())) && (password.toLowerCase().equalsIgnoreCase(funcionario.getPassword()))) {
                    encontrou=true;
                    System.out.print("Bem Vindo ao Sistema "+ nome.toUpperCase() +"! Digite (1) para entrar!\n>> "); ler.next();
                    funcOnline = funcionario;
                    break;
                }
            }
            if(!encontrou){
                if(oport==3){
                    System.out.print("Numero de Tentativas Excedidos! Digite (1) para sair do programa!\n>> ");ler.next();
                    exit(1);
                }
                System.out.print("Username ou Password incorreta! Digite (1) para tentar novamente!\n>> "); ler.next();
            }
        }
    return funcOnline;
    }
}