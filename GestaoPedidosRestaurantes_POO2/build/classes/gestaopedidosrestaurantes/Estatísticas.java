package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estatísticas {
    
    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    
    public static void estatisticas(ArrayList <Prato> pratos, ArrayList<Pedido> pedidos, ArrayList<Funcionario> funcionarios){
        
        boolean naoEncontrou = false;
        limparTela();
        System.out.println("====================Estatísticas de Pedidos=========================");
        for(Pedido pedido: pedidos){
            if(pedido.isPronto()==false){
                naoEncontrou=true;
            }
        }
        
        if(naoEncontrou){
            try {
                System.out.println("Nenhum pedido feito!");
                System.out.print  ("Press (1) para voltar >> "); scan.readLine();
            } catch (IOException ex) {
                System.out.println("Erro na leitura: "+ex.getMessage());
                Logger.getLogger(Estatísticas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                int np=0, lucro=0, maiorGarcom=0, maiorCozinheiro=0, maiorPrato=0, i=0;
                String g = null, c = null;
                Prato p = new Prato();
                for(Pedido pedido:pedidos){
                    if(pedido.isEntregue()){
                        np++;
                        lucro += pedido.getPreçoTotal();
                    }
                }   
                
                for(Funcionario funcionario:funcionarios){
                    for(Pedido pedido:pedidos){
                        if(funcionario.getId().equals(pedido.getIdGarcom())) i++;
                    }
                    if(i>maiorGarcom){
                        maiorGarcom=i;
                        g=funcionario.getNome();
                    }
                }   
                
                for(Funcionario funcionario:funcionarios){
                    for(Pedido pedido:pedidos){
                        if(funcionario.getId().equals(pedido.getIdCozinheiro())) i++;
                    }
                    if(i>maiorCozinheiro){
                        maiorCozinheiro=i;
                        c=funcionario.getNome();
                    }
                }   
                
                for(Prato prato:pratos){
                    if(prato.getVendas()>maiorPrato) {
                        maiorPrato=prato.getVendas();
                        p=prato;
                    }
                }   
                
                System.out.println("Numero total de pedidos feitos: "+ np);
                System.out.println("Lucro total arrecadado: "+ lucro+"$00");
                System.out.println("Garçom que mais fez pedidos: " + g + "\nTotal de pedidos feitos: "+ maiorGarcom);
                System.out.println("Cozinheiro(a) que mais processou pedidos: " + c + "\nTotal de pedidos processados: "+ maiorCozinheiro);
                System.out.println("Prato mais vendido: " + p.getNome() + "\nNumero de vendas: "+ maiorPrato);
                System.out.println("-----------------------------------------------------------------------");
                System.out.print  ("Press (1) para voltar >> ");
                scan.readLine();
            } catch (IOException ex) {
                System.out.println("Erro na leitura: "+ ex.getMessage());
                Logger.getLogger(Estatísticas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}