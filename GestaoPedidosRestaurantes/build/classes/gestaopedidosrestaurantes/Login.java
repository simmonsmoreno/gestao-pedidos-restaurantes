package gestaopedidosrestaurantes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @Simão Moreno
 */
public class Login {
    
    //Login
    public static boolean login(ArrayList garcon, ArrayList passwordGarcon) throws IOException{
        
        boolean entrar = false;
        String userName, userPassword, s , t;
        userName = JOptionPane.showInputDialog(null,"Nome:", "LOGIN", JOptionPane.INFORMATION_MESSAGE);
        userPassword = JOptionPane.showInputDialog(null,"Senha:", "LOGIN", JOptionPane.INFORMATION_MESSAGE);
        
        for(int i=0; i<garcon.size();i++){
            
            if(garcon.contains(userName) && passwordGarcon.contains(userPassword)){
                
                s = (String) garcon.get(i);
                t = (String) passwordGarcon.get(i); 
                System.out.println("User: "+s+"\nSenha: "+t);
                
                if(userName.equals(s) && userPassword.equals(t)){
                    JOptionPane.showMessageDialog(null, "Login feito com sucesso!");
                    entrar = true;
                    break;
                }else{
                    JOptionPane.showMessageDialog(null, "Dados Inválidos!");
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                break;
            }
        }
        return entrar;
    }
}
