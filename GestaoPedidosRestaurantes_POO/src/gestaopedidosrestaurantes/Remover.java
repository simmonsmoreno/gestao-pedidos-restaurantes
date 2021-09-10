package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Remover {
    
    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    public static boolean encontrou = false;
    public static Scanner ler = new Scanner(System.in);
        
    public static void removerPratos(ArrayList<Prato> pratos) {
    
        int id;
                
        try {
            limparTela();//limpar tela
            System.out.println("===========================Remover Pratos=============================");
            for(Prato prato: pratos) {
                System.out.println("Prato: "+ prato.getNome() + "\nId: " + prato.getId());
                System.out.println("------------------------------------------------------------------------");
            }
            System.out.print("Introduza o id do prato a remover\n>> "); id = ler.nextInt();
            for (Prato prato:pratos){
                if(prato.getId()==(id)){
                    encontrou = true;
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(prato.showPrato());
                    System.out.println("----------------------------------------------------------------------");
                    System.out.print("Remover? (S/N): "); String esc = scan.readLine();
                    if(esc.equalsIgnoreCase("s")){
                        System.out.println("Prato '"+prato.getNome().toUpperCase()+ "' removido com sucesso!");
                        pratos.remove(prato);
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
            JOptionPane.showMessageDialog(null, "Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Remover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void removerFuncionarios(ArrayList<Funcionario> funcionarios, Funcionario funcOnline) {
    
        int id;
                
        try {
            limparTela();//limpar tela
            System.out.println("=========================Remover Funcionarios===========================");
            for(Funcionario funcionario: funcionarios){
                if(!funcOnline.equals(funcionario)) {
                    System.out.println("Nome: "+ funcionario.getNome() + "\nId: " + funcionario.getId());
                    System.out.println("------------------------------------------------------------------------");
                }
            }
            System.out.print("Introduza o id do funcionario a remover\n>> "); id = ler.nextInt();
            
            for (Funcionario funcionario:funcionarios){
                if(funcionario.getId()==(id)){
                    encontrou = true;
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(funcionario.showFuncionario());
                    System.out.println("----------------------------------------------------------------------");
                    System.out.print("Remover? (S/N): ");String esc = scan.readLine();
                    if(esc.toLowerCase().equals("s")){
                        System.out.println("Funcionario '"+funcionario.getNome().toUpperCase()+ "' removido com sucesso!");
                        funcionarios.remove(funcionario);
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
            JOptionPane.showMessageDialog(null, "Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Remover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}