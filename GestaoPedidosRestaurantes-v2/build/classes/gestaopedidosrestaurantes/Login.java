package gestaopedidosrestaurantes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.application.Platform.exit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @Simão Moreno
 */
public class Login {
    
    //Login
    public static boolean login(ArrayList garcon, ArrayList passwordGarcon) throws IOException{
        
        boolean entrar = false;
        String s , t;
        int i;
        
        JLabel jUserName = new JLabel("Usuario");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        
        Object[] ob = {jUserName, userName, jPassword, password};
        int result = JOptionPane.showConfirmDialog(null, ob, "LOGIN", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
                if(garcon.contains(userName.getText()) && passwordGarcon.contains(password.getText())){
                    
                    i = garcon.indexOf(userName.getText());
                    s = (String) garcon.get(i);
                    i = passwordGarcon.indexOf(password.getText());
                    t = (String) passwordGarcon.get(i); 

                    if(userName.getText().equals(s) && password.getText().equals(t)){
                        JOptionPane.showMessageDialog(null, "Login feito com sucesso!");
                        entrar = true;
                    }else{
                        JOptionPane.showMessageDialog(null, "Dados Inválidos!");
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                }
            
        }else{
            exit();
        }
        return entrar;
    }
}
