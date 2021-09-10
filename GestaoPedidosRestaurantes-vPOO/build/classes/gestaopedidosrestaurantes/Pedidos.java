package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import static gestaopedidosrestaurantes.LoadingBar.load;
import java.util.*;

public class Pedidos {
    
    public static Scanner scan = new Scanner (System.in);
    public static boolean encontrou = false;
    
    public static void fazerPedidos(ArrayList <Prato> pratos, ArrayList <Pedido> pedidos, Funcionario funcOnline) throws InterruptedException{
        
        int qtdade, preçoTotal = 0, vendas;
        Pedido ped = new Pedido();
        String id, msg = "n";
        boolean encontrou=false;
        
        limparTela();//limpar tela
        System.out.println("===========================Fazer Pedidos==============================");
        if(pratos.isEmpty())System.out.println("Nenhum prato cadastrado");
        else{
            do {
                Cardapio.showCategoria(pratos);
                System.out.print("Introduza o id do prato desejado >> "); id = scan.next();
                for (Prato prato: pratos) {
                    if (prato.getId().equals(id)) {
                        encontrou=true;
                        System.out.println("Prato escolhido: " + prato.getNome());
                        System.out.print("Introduza a quantidade >> "); qtdade = scan.nextInt();
                        vendas=prato.getVendas();
                        for (int j = 0; j < qtdade; j++) ped.getPratos().add(prato);
                        vendas+=qtdade;
                        prato.setVendas(qtdade);
                        preçoTotal += Integer.parseInt(prato.getPreço())*qtdade;
                        System.out.print("Continuar a escolher pratos? (S/N) >> "); msg = scan.next();
                        break;
                    }
                }
            }while (msg.equalsIgnoreCase("s"));
            if(encontrou){
                System.out.print("Introduza o numero da mesa >> "); ped.setMesa(scan.nextInt());
                ped.setIdGarcom(funcOnline.getId());
                ped.setPreçoTotal(preçoTotal);
                ped.setPronto(false);
                ped.setEntregue(false);
                ped.setNumeroPedido(pedidos.size()+1);
                Date data = new Date();ped.setData(data.toString());
                System.out.print("A fazer pedido! Aguarde... "); load();
                System.out.println("Pedido feito com sucesso!\nTotal a pagar: "+preçoTotal);
                pedidos.add(ped);  
            }else{
                System.out.println("Id não identificado!");
            }
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) to continue >> "); scan.next();
    }
    
    public static void processarPedidos(ArrayList <Pedido> pedidos, ArrayList <Funcionario> funcionarios, Funcionario funcOnline) throws InterruptedException{
        
        limparTela();//limpar tela
        System.out.println("=========================Processar Pedidos============================");
        
        if(pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado");
        }else{
            for(Pedido pedido : pedidos){
                if(pedido.isPronto()==false){
                    encontrou=true;
                    System.out.println(pedido.showPedido(funcionarios));
                }
            }
            if(encontrou){
                System.out.print("Introduza o numero do pedido a processar >> "); int id = scan.nextInt();
                for (Pedido pedido : pedidos) {
                    if (pedido.getNumeroPedido()==id) {
                        System.out.print("A processar... ");load();
                        System.out.println("Pedido processado com sucesso!\nPor favor, espere o garçom para entregar o pedido!");
                        pedido.setIdCozinheiro(funcOnline.getId());
                        pedido.setPronto(true);
                        break;
                    }else{
                        System.out.println("Numero pedido não identificado!");
                    }
                }
            }else{
                System.out.println("Nenhum pedido a espera!");
            }
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) to continue >> "); scan.next();
    }
    
    public static void entregarPedidos(ArrayList <Pedido> pedidos, ArrayList <Funcionario> funcionarios) throws InterruptedException{
        
        limparTela();//limpar tela
        System.out.println("==========================Entregar Pedidos============================");
        
        if(pedidos.isEmpty()){
            System.out.println("Nenhum pedido feito");
        }else{
            for(Pedido pedido: pedidos){
                if(pedido.isEntregue()==false && pedido.isPronto()==true){
                    encontrou = true;
                    System.out.println(pedido.showPedido(funcionarios));
                }
            }
            if(!encontrou) System.out.println("Nenhum pedido a espera para ser entregue");
            else{
                System.out.print("Introduza o numero do pedido a entregar >> "); int id = scan.nextInt();
                for (Pedido pedido: pedidos) {
                    if (pedido.getNumeroPedido()==id) {
                        System.out.println("Pedido escolhido: "+pedido.getNumeroPedido()); 
                        System.out.print ("A sair da cozinha... ");load();
                        System.out.print ("A identificar a mesa: "); load();
                        System.out.print ("Mesa: " + pedido.getMesa()); 
                        System.out.println("Pedido entregue com sucesso!\nBom apetite!");
                        pedido.setEntregue(true);
                        break;
                    }else{
                        System.out.println("Numero pedido não identificado!");
                    }
                }
            }
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) to continue >> "); scan.next();
    }
}