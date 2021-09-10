package gestaopedidosrestaurantes;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;

public class LimparTela {
    
    public static void limparTela(){
        try{
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        }catch(AWTException e){
            System.out.println(e.getMessage());
        }
    }
    
}
