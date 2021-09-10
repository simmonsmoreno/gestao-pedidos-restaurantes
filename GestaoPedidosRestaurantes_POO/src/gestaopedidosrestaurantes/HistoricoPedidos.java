package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.Adicionar.achou;
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
            achou = false;
            limparTela();//limpar tela
            System.out.println("========================Historico de Pedidos============================");
            if(pedidos.isEmpty()) System.out.println("Nenhum pedido feito!");
            else{
                if(funcOnline.getCargo().equals("administrador")){
                    pedidos.forEach((pedido) -> {
                        if(pedido.isEntregue()){
                            System.out.println(pedido.showPedido(funcionarios, pratos));
                            achou = true;
                        }
                    });
                }else{
                    pedidos.forEach((pedido) -> {
                        if((pedido.getIdGarcom()==(funcOnline.getId()) || pedido.getIdCozinheiro()==(funcOnline.getId())) && pedido.isEntregue()){
                            if(pedido.isEntregue()){
                                System.out.println(pedido.showPedido(funcionarios, pratos));
                                achou = true;
                            }
                        }
                    });
                }
                if(!achou) System.out.println("Nenhum pedido entregue!");
            }
            System.out.println("=========================================================================");
            System.out.print("Press (1) para continuar >> ");scan.readLine();
        } catch (IOException ex) {
            System.out.println("Erro na leitura: "+ex.getMessage());
            Logger.getLogger(HistoricoPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}