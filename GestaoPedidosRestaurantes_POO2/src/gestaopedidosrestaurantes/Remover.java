package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Remover {
    
    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    public static boolean encontrou = false;
        
    static void removerPratos(ArrayList<Prato> pratos) {
    
        try {
            limparTela();//limpar tela
            System.out.println("===========================Remover Pratos=============================");
            for(Prato prato: pratos) {
                System.out.println("Prato: "+ prato.getNome() + "\nId: " + prato.getId());
                System.out.println("------------------------------------------------------------------------");
            }
            System.out.print("Introduza o id do prato a remover\n>> "); String id = scan.readLine();
            
            for (int i=0; i<pratos.size(); i++){
                if(pratos.get(i).getId().equals(id)){
                    encontrou = true;
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(pratos.get(i).showPrato());
                    System.out.println("----------------------------------------------------------------------");
                    System.out.print("Remover? (S/N): ");String esc = scan.readLine();
                    if(esc.toLowerCase().equals("s")){
                        System.out.println("Prato '"+pratos.get(i).getNome().toUpperCase()+ "' removido com sucesso!");
                        pratos.remove(pratos.get(i));
                    }else{
                        System.out.println("Remover Cancelado!");
                    }
                    break;
                }
            }
            if(!encontrou) System.out.println("Id não encontrada");
            System.out.println("======================================================================");
            System.out.print("Press (1) para continuar >> "); scan.readLine();
        } catch (IOException ex) {
            System.out.println("Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Remover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    static void removerFuncionarios(ArrayList<Funcionario> funcionarios, Funcionario funcOnline) {
    
        try {
            limparTela();//limpar tela
            System.out.println("=========================Remover Funcionarios===========================");
            for(Funcionario funcionario: funcionarios){
                if(!funcOnline.equals(funcionario)) {
                    System.out.println("Nome: "+ funcionario.getNome() + "\t\t\tId: " + funcionario.getId());
                    System.out.println("------------------------------------------------------------------------");
                }
            }
            System.out.print("Introduza o id do funcionario a remover\n>> "); String id = scan.readLine();
            
            for (int i=0; i<funcionarios.size(); i++){
                if(funcionarios.get(i).getId().equals(id)){
                    encontrou = true;
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(funcionarios.get(i).showFuncionario());
                    System.out.println("----------------------------------------------------------------------");
                    System.out.print("Remover? (S/N): ");String esc = scan.readLine();
                    if(esc.toLowerCase().equals("s")){
                        System.out.println("Funcionario '"+funcionarios.get(i).getNome().toUpperCase()+ "' removido com sucesso!");
                        funcionarios.remove(funcionarios.get(i));
                    }else{
                        System.out.println("Remover Cancelado!");
                    }
                    break;
                }
            }
            if(!encontrou) System.out.println("Id não encontrada");
            System.out.println("======================================================================");
            System.out.print("Press (1) para continuar >> "); scan.readLine();
        } catch (IOException ex) {
            System.out.println("Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Remover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}