package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoPedidos {
    
    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    
    public static void historico(ArrayList <Pedido> pedidos, ArrayList <Prato> pratos, ArrayList <Funcionario> funcionarios, Funcionario funcOnline){
        
        try {
            limparTela();//limpar tela
            System.out.println("========================Historico de Pedidos============================");
            if(pedidos.isEmpty()) System.out.println("Nenhum pedido feito!");
            else{
                if(funcOnline.getCargo().equals("administrador")){
                    for(Pedido pedido:pedidos){
                        if(pedido.isEntregue()) System.out.println(pedido.showPedido(funcionarios, pratos));
                        else{
                            System.out.println("Nenhum pedido entregue!");
                        }
                    }
                }else{
                    for(Pedido pedido:pedidos){
                        if((pedido.getIdGarcom().equals(funcOnline.getId()) || pedido.getIdCozinheiro().equals(funcOnline.getId())) && pedido.isEntregue()){
                            if(pedido.isEntregue()) System.out.println(pedido.showPedido(funcionarios, pratos));
                        }else{
                            System.out.println("Nenhum pedido entregue!");
                        }
                    }
                }
            }
            System.out.print("Press (1) para continuar >> ");scan.readLine();
        } catch (IOException ex) {
            System.out.println("Erro na leitura: "+ex.getMessage());
            Logger.getLogger(HistoricoPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}