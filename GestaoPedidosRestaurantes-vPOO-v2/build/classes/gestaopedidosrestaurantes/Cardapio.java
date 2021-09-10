package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cardapio {
    
    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    public static String opcao;
        
    public static void cardapio(ArrayList <Prato> pratos){
        
        try {
            limparTela();//limpar tela
            System.out.println("==============================Cardapio================================");
            if(pratos.isEmpty()){
                System.out.println("Nenhum prato cadastrado");
            }else{
                showCategoria(pratos);
            }
            System.out.print("Press (1) to continue >> "); scan.readLine();
        } catch (IOException ex) {
            System.out.println("Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Cardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void showCategoria(ArrayList <Prato> pratos) {
        
        do{
            try {
                System.out.println("Escolha a categoria:");
                System.out.println(" (A) Entrada");
                System.out.println(" (B) Prato Principal");
                System.out.println(" (C) Sobremesa");
                System.out.println(" (D) Bebidas");
                System.out.println(" (E) Pequeno-Almoço");
                System.out.println(" (F) Fast-Foods");
                System.out.println("----------------------------------------------------------------------");
                System.out.print(">> ");opcao = scan.readLine();
                switch (opcao.toLowerCase()) {
                    case "a": listar(pratos, "Entrada"); break;
                    case "b": listar(pratos, "Prato Principal"); break;
                    case "c": listar(pratos, "Sobremesa"); break;
                    case "d": listar(pratos, "Bebidas"); break;
                    case "e": listar(pratos, "Pequeno-Almoço"); break;
                    case "f": listar(pratos, "Fast-Foods"); break;
                    default: opcao="s"; limparTela();//limpar tela
                }
            } catch (IOException ex) {
                System.out.println("Erro na leitura: "+ex.getMessage());
                Logger.getLogger(Cardapio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while (opcao.equals("s"));
    }
    
    public static void listar(ArrayList <Prato> pratos, String categoria){
        
        boolean encontrou = false;
        
        System.out.println("===================Pratos na Categoria "+ categoria+ "=====================");
        for (Prato prato:pratos){
            if(prato.getCategoria().equals(categoria)){
                encontrou = true;
                System.out.println(prato.showPrato());
                System.out.println("----------------------------------------------------------------------");
            }
        }
        if(!encontrou) System.out.println("Nenhum prato cadastrado na categoria selecionada");
    }
}