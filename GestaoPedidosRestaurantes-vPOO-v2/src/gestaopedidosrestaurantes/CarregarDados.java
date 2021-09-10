package gestaopedidosrestaurantes;

import static com.sun.javafx.util.Utils.split;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class CarregarDados {
    
    //carregar funcionarios
    public static void carregarFuncionarios(ArrayList <Funcionario> Colecao, File file) throws FileNotFoundException, IOException{
        
        FileReader lerFile = new FileReader(file);
        BufferedReader buf = new BufferedReader(lerFile);
        try{
            String frase;
            while((frase = buf.readLine()) != null){
                
                String[] token = split(frase, "|");
                Funcionario f = new Funcionario();
                f.setNome(token[0]);  
                f.setId(token[1]);  
                f.setPassword(token[2]);  
                f.setCargo(token[3]);  
                f.setDataNasc(token[4]); 
                f.setSexo(token[5]);  
                f.setMorada(token[6]); 
                f.setTelefone(token[7]); 
                f.setDataEntrada(token[8]); 
                Colecao.add(f);
            }
            buf.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro: "+e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //carregar pratos
    public static void carregarPratos(ArrayList <Prato> Colecao, File file) throws FileNotFoundException, IOException{
        
        int i=0;
        FileReader lerFile = new FileReader(file);
        BufferedReader buf = new BufferedReader(lerFile);
        try{
            String frase;
            while((frase = buf.readLine()) != null){
                
                String[] token = split(frase, "|");
                Prato p = new Prato();
                p.setNome(token[0]);  
                p.setId(token[1]);  
                p.setCategoria(token[2]);  
                p.setPreço(Integer.parseInt(token[3]));
                p.setVendas(Integer.parseInt(token[4]));
                p.setQtdade(Integer.parseInt(token[5]));
                Colecao.add(p);
            }
            buf.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro: "+e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //carregar pedidos
    public static void carregarPedidos(ArrayList <Pedido> Colecao, File file) throws FileNotFoundException, IOException{
        
        int i=0;
        FileReader lerFile = new FileReader(file);
        BufferedReader buf = new BufferedReader(lerFile);
        try{
            String frase;
            while((frase = buf.readLine()) != null){
                
                String []f = split(frase, "-"); 
                Pedido ped = new Pedido();
                ArrayList <String> idP = new ArrayList<>();
                ArrayList <Integer> qtdadeP = new ArrayList<>();
                
                ped.setNumeroPedido(Integer.parseInt(f[0]));
                ped.setMesa(Integer.parseInt(f[1]));
                ped.setPreçoTotal(Integer.parseInt(f[2])); 
                if(f[3].equals("true")) ped.setPronto(true);
                if(f[4].equals("true")) ped.setEntregue(true);
                ped.setData(f[5]); 
                ped.setIdGarcom(f[6]); 
                ped.setIdCozinheiro(f[7]); 
                
                String[] pratos = split(f[8], ",");
                
                for(String prato: pratos){
                    String[] p = split(prato, "|");
                    qtdadeP.add(Integer.parseInt(p[0]));
                    idP.add(p[1]);
                }
                
                ped.setIdPratos(idP);
                ped.setQtdadePratos(qtdadeP);
                Colecao.add(ped);
            }
            buf.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro: "+e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}