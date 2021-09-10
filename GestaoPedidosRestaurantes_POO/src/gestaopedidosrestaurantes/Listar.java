package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.util.ArrayList;
import java.util.Scanner;

public class Listar {
    
    public static Scanner scan = new Scanner (System.in);
    
    public static void listarPratos(ArrayList<Prato> pratos) {
        
        limparTela();//limpar tela
        System.out.println("==========================Listar Pratos==============================");
        if(pratos.isEmpty()){
            System.out.println("Nenhum prato cadastrado");
        }else{
            for (Prato prato:pratos){
                System.out.println(prato.showPrato());
                System.out.println("----------------------------------------------------------------------");
            }
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) para continuar >> "); scan.next();
    }
    
    public static void listarFuncionarios(ArrayList<Funcionario> funcionarios) {
        
        limparTela();//limpar tela
        System.out.println("=========================Listar Funcionarios==========================");
        if(funcionarios.isEmpty()){
            System.out.println("Nenhum funcionario cadastrado");
        }else{
            for (int i=0; i<funcionarios.size(); i++){
                System.out.println(funcionarios.get(i).showFuncionario());
                System.out.println("----------------------------------------------------------------------");
            }
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) para continuar >> "); scan.next();
    }
}