package gestaopedidosrestaurantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class CarregarDados {
    
    //carregar dados
    public static void carregarDados(ArrayList Colecao, File file) throws FileNotFoundException, IOException{
        
        FileReader lerFile = new FileReader(file);
        BufferedReader buf = new BufferedReader(lerFile);
        try{
            String frase;
            while((frase = buf.readLine()) != null) Colecao.add(frase);
            buf.close();
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
