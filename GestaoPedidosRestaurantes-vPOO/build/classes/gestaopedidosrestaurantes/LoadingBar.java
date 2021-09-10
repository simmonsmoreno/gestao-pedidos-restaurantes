package gestaopedidosrestaurantes;

public class LoadingBar {
    
    public static void load() throws InterruptedException{
        for(int i=0; i<20; i++){
             Thread.sleep(500); System.out.print("|");
        }
        System.out.println("");
    }
}
