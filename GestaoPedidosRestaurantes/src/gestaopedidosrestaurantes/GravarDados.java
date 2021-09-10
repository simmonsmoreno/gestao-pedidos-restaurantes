package gestaopedidosrestaurantes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class GravarDados {
    
    //Gravar Dados
    public static void gravarDados(ArrayList Colecao, File file) throws IOException{
        
        FileWriter arq = new FileWriter(file);
        BufferedWriter escreverFile = new BufferedWriter(arq);
        try{
            for (int i = 0; i < Colecao.size(); i++) {
                String msg = (String) Colecao.get(i);
                escreverFile.write(msg+"\n");
            }
            escreverFile.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na escrita do ficheiro!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
