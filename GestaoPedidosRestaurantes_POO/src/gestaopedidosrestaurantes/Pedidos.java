package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import static gestaopedidosrestaurantes.LoadingBar.load;
import java.text.SimpleDateFormat;
import java.util.*;

public class Pedidos {
    
    public static Scanner scan = new Scanner (System.in);
    public static boolean encontrou = false;
    public static boolean achou = false;
    
    public static void fazerPedidos(ArrayList <Prato> pratos, ArrayList <Pedido> pedidos, Funcionario funcOnline) throws InterruptedException{
        
        ArrayList <Integer> idPratos = new ArrayList<>();
        ArrayList <Integer> qtdadePratos = new ArrayList<>();
        int qtdade, preçoTotal = 0, vendas;
        Pedido pedido = new Pedido();
        String msg = "n";
        
        limparTela();//limpar tela
        System.out.println("===========================Fazer Pedidos==============================");
        if(pratos.isEmpty())System.out.println("Nenhum prato cadastrado");
        else{
            do {
                Cardapio.showCategoria(pratos);
                System.out.print("Introduza o id do prato desejado >> "); int id = scan.nextInt();
                for (Prato prato: pratos) {
                    if (prato.getId()==id) {
                        encontrou = true;
                        System.out.println("Prato escolhido : " + prato.getNome());
                        System.out.print("Introduza a quantidade >> "); qtdade = scan.nextInt();
                        vendas=prato.getVendas(); 
                        vendas+=qtdade; 
                        prato.setVendas(vendas);
                        preçoTotal += prato.getPreço()*qtdade;
                        idPratos.add(prato.getId()); 
                        qtdadePratos.add(qtdade);
                        System.out.print("Continuar a escolher pratos? (S/N) >> "); msg = scan.next();
                        break;
                    }
                }
            }while (msg.equalsIgnoreCase("s"));
            if(encontrou){
                pedido.setQtdadePratos(qtdadePratos);
                pedido.setIdPratos(idPratos);
                System.out.print("Introduza o numero da mesa >> "); 
                pedido.setMesa(scan.nextInt());
                pedido.setIdGarcom(funcOnline.getId());
                pedido.setPreçoTotal(preçoTotal);
                pedido.setPronto(false);
                pedido.setEntregue(false);
                //criar id apartir do tamanho da arraylist funcionarios
                int id = pedidos.size();

                ArrayList<Integer> ids = new ArrayList<>();
                for (Pedido pdd : pedidos) ids.add(pdd.getNumeroPedido());

                //verificar se existir id semelhante
                while (true){
                    if (ids.contains(id)) {
                        id++;
                    }else{
                        pedido.setNumeroPedido(id);
                        break;
                    }
                }
                pedido.setNumeroPedido(id);
                SimpleDateFormat date = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
                Calendar data = new GregorianCalendar(); 
                pedido.setData(date.format(data.getTime()));
                System.out.print("A fazer pedido! Aguarde... "); load();
                System.out.println("Pedido feito com sucesso!\nTotal a pagar: "+preçoTotal);
                pedidos.add(pedido);  
            }else{
                System.out.println("Id não identificado!");
            }
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) to continue >> "); scan.next();
    }
    
    public static void processarPedidos(ArrayList <Pedido> pedidos, ArrayList <Prato> pratos, ArrayList <Funcionario> funcionarios, Funcionario funcOnline) throws InterruptedException{
        
        encontrou=false;
        achou=false;
        limparTela();//limpar tela
        System.out.println("=========================Processar Pedidos============================");
        
        if(pedidos.isEmpty()) System.out.println("Nenhum pedido cadastrado");
        for(Pedido pedido: pedidos){
            if(pedido.isEntregue()==false && pedido.isPronto()==false){
                encontrou = true;
                System.out.println(pedido.showPedido(funcionarios, pratos));
            }
        }
        if(!encontrou) System.out.println("Nenhum pedido a espera para ser processado!");
        else{
            System.out.print("Introduza o numero do pedido a processar >> "); int id = scan.nextInt();
            for (Pedido pedido : pedidos) {
                if (pedido.getNumeroPedido()==id) {
                    achou = true;
                    System.out.print("A processar... ");load();
                    System.out.println("Pedido processado com sucesso!\nPor favor, espere o garçom para entregar o pedido!");
                    pedido.setIdCozinheiro(funcOnline.getId());
                    pedido.setPronto(true);
                    break;
                }
            }
            if(!achou) System.out.println("Numero pedido não identificado!");
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) to continue >> "); scan.next();
    }
    
    public static void entregarPedidos(ArrayList <Pedido> pedidos, ArrayList <Prato> pratos, ArrayList <Funcionario> funcionarios) throws InterruptedException{
        
        encontrou=false;
        achou=false;
        limparTela();//limpar tela
        System.out.println("==========================Entregar Pedidos============================");
        
        if(pedidos.isEmpty()){
            System.out.println("Nenhum pedido feito");
        }else{
            for(Pedido pedido: pedidos){
                if(pedido.isEntregue()==false && pedido.isPronto()==true){
                    encontrou = true;
                    System.out.println(pedido.showPedido(funcionarios, pratos));
                }
            }
            if(!encontrou) System.out.println("Nenhum pedido a espera para ser entregue");
            else{
                System.out.print("Introduza o numero do pedido a entregar >> "); int id = scan.nextInt();
                for (Pedido pedido: pedidos) {
                    if (pedido.getNumeroPedido()==id) {
                        achou = true;
                        System.out.println("Pedido escolhido: "+pedido.getNumeroPedido()); 
                        System.out.println("A sair da cozinha... ");
                        System.out.print ("A identificar a mesa: "); load();
                        System.out.println("Mesa: " + pedido.getMesa()); 
                        System.out.println("Pedido entregue com sucesso!\nBom apetite!");
                        pedido.setEntregue(true);
                        break;
                    }
                }
                if(!achou) System.out.println("Numero pedido não identificado!");
            }
        }
        System.out.println("======================================================================");
        System.out.print("Press (1) to continue >> "); scan.next();
    }
}