package gestaopedidosrestaurantes;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class CarregarDados {
    
    //carregar funcionarios
    public static void carregarFuncionarios(ArrayList <Funcionario> Colecao, File file) throws FileNotFoundException, IOException{
        
        int i=0;
        FileReader lerFile = new FileReader(file);
        BufferedReader buf = new BufferedReader(lerFile);
        try{
            String frase;
            while((frase = buf.readLine()) != null){
                
                StringTokenizer stgs = new StringTokenizer(frase, "|");
                Funcionario f = new Funcionario();
                
                while(stgs.hasMoreTokens()){
                    
                    String token = stgs.nextToken();
                    i++;
                    switch (i) {
                        case 1: f.setNome(token); break;
                        case 2: f.setId(token); break;
                        case 3: f.setPassword(token); break;
                        case 4: f.setCargo(token); break;
                        case 5: f.setDataNasc(token); break;
                        case 6: f.setSexo(token); break;
                        case 7: f.setMorada(token); break;
                        case 8: f.setTelefone(token); break;
                        case 9: f.setDataEntrada(token); Colecao.add(f); i=0; break;
                    }
                }
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
                
                StringTokenizer stgs = new StringTokenizer(frase, "|");
                Prato p = new Prato();
                
                while(stgs.hasMoreTokens()){
                    
                    String token = stgs.nextToken();
                    i++;
                    switch (i) {
                        case 1: p.setNome(token); break;
                        case 2: p.setId(token); break;
                        case 3: p.setCategoria(token); break;
                        case 4: p.setPreço(token); break;
                        case 5: p.setVendas(Integer.parseInt(token)); Colecao.add(p); i=0; break;
                    }
                }
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
        Pedido ped = new Pedido();
        try{
            String frase;
            while((frase = buf.readLine()) != null){
                StringTokenizer stgs = new StringTokenizer(frase, "-");
                while(stgs.hasMoreTokens()){
                    String token = stgs.nextToken();
                    i++;
                    switch (i) {
                        case 1: ped.setNumeroPedido(Integer.parseInt(token));break;
                        case 2: ped.setMesa(Integer.parseInt(token));break;
                        case 3: ped.setPreçoTotal(Integer.parseInt(token));break;
                        case 4: 
                            if(token.equals("true")) ped.setPronto(true);
                            else  ped.setPronto(false);
                            break;
                        case 5: 
                            if(token.equals("true")) ped.setEntregue(true);
                            else  ped.setEntregue(false);
                            break;
                        case 6: ped.setData(token);break;
                        case 7: ped.setIdGarcom(token);break;
                        case 8: ped.setIdCozinheiro(token);break;
                        case 9:
                            int j=0;
                            Prato p = new Prato();
                            ArrayList <Prato> pratos = new ArrayList<>();
                            StringTokenizer tokPratos = new StringTokenizer(token, ",");
                            while(tokPratos.hasMoreTokens()){
                                String tk1 = tokPratos.nextToken();
                                StringTokenizer tk2 = new StringTokenizer(tk1, "|");j=0;
                                while(tk2.hasMoreTokens()){
                                    String tk3 = tk2.nextToken();
                                    j++;
                                    switch (j) {
                                        case 1: p.setNome(tk3); break;
                                        case 2: p.setId(tk3); break;
                                        case 3: p.setCategoria(tk3); break;
                                        case 4: p.setPreço(tk3); pratos.add(p); i=0; break;
                                    }
                                }
                            }
                            ped.setPratos(pratos);
                            break;
                    }
                }
            }
            Colecao.add(ped);
            buf.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro: "+e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}