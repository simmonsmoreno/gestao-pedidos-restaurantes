package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Adicionar {

    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    public static String opcao;
    public static boolean achou = true;

    public static void addPratos(ArrayList<Prato> pratos) {

        try {
            Prato p = new Prato();
            do {
                try {
                    limparTela();//limpar tela
                    System.out.println("======================Adicionar Pratos===========================");
                    System.out.print("Prato: "); p.setNome(scan.readLine());
                    System.out.println("Escolha a categoria:");
                    System.out.println(" (A) Entrada");
                    System.out.println(" (B) Prato Principal");
                    System.out.println(" (C) Sobremesa");
                    System.out.println(" (D) Bebidas");
                    System.out.println(" (E) Pequeno-Almoço");
                    System.out.println(" (F) Fast-Foods");
                    System.out.print(">> ");opcao = scan.readLine();
                    switch (opcao.toLowerCase()) {
                        case "a": p.setCategoria("Entrada"); break;
                        case "b": p.setCategoria("Prato Principal"); break;
                        case "c": p.setCategoria("Sobremesa"); break;
                        case "d": p.setCategoria("Bebidas"); break;
                        case "e": p.setCategoria("Pequeno-Almoço"); break;
                        case "f": p.setCategoria("Fast-Foods"); break;
                        default: opcao = "s";
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Adicionar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (opcao.equalsIgnoreCase("s"));
            
            System.out.print("Preço: "); p.setPreço(scan.read());
            
            //criar id apartir do tamanho da arraylist funcionarios
            int id = pratos.size() + 1000;
            
            ArrayList<Integer> ids = new ArrayList<>();
            for (Prato prato : pratos) ids.add(prato.getId());
            
            //verificar se existir id semelhante
            while (true){
                if (ids.contains(id)) {
                    id++;
                }else{
                    p.setId(id);
                    break;
                }
            }
            
            pratos.add(p);
            System.out.println("----------------------------------------------------------------------");
            System.out.println(" Prato '" + p.getNome().toUpperCase() + "' adicionado com sucesso!       ");
            System.out.println("======================================================================");
            System.out.print("Press (1) para continuar >> "); scan.readLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Adicionar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addFuncionarios(ArrayList<Funcionario> funcionarios) {

        try {
            Funcionario f = new Funcionario();
            do {
                try {
                    limparTela();//limpar tela
                    System.out.println("======================Adicionar Funcionarios==========================");
                    System.out.print("Nome: ");f.setNome(scan.readLine());
                    System.out.print("Password: "); f.setPassword(scan.readLine());
                    System.out.println("Escolha o cargo:");
                    System.out.println(" (A) Administrador");
                    System.out.println(" (B) Garçom");
                    System.out.println(" (C) Cozinha");
                    System.out.print(">> ");opcao = scan.readLine();
                    switch (opcao.toLowerCase()) {
                        case "a": f.setCargo("administrador"); break;
                        case "b":  f.setCargo("garcom"); break;
                        case "c": f.setCargo("cozinha"); break;
                        default: opcao="s";
                    }
                } catch (IOException ex) {
                    System.out.println("Erro na leitura: "+ex.getMessage());
                    Logger.getLogger(Adicionar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (opcao.equals("s"));
            System.out.print("Data Nascimento (dd/mm/aa): "); f.setDataNasc(scan.readLine());
            System.out.print("Sexo: "); f.setSexo(scan.readLine());
            System.out.print("Morada: "); f.setMorada(scan.readLine());
            System.out.print("Telefone: "); f.setTelefone(scan.readLine());
            SimpleDateFormat date = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
            Calendar data = new GregorianCalendar();
            f.setDataEntrada(date.format(data.getTime()));
            
            //criar id apartir do tamanho da arraylist funcionarios
            int id = funcionarios.size() + 1000;
            
            ArrayList<Integer> ids = new ArrayList<>();
            for (Funcionario funcionario : funcionarios) ids.add(funcionario.getId());
            
            //verificar se existir id semelhante
            while (true){
                if (ids.contains(id)) {
                    id++;
                }else{
                    f.setId(id);
                    break;
                }
            }
            
            funcionarios.add(f);
            System.out.println("----------------------------------------------------------------------");
            System.out.println(" Funcionario '" + f.getNome().toUpperCase() + "' adicionado com sucesso! ");
            System.out.println(" Id: "+id);
            System.out.println("======================================================================");
            System.out.print("Press (1) para continuar >> "); scan.readLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Adicionar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
