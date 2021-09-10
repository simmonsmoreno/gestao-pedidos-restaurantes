package gestaopedidosrestaurantes;

import static gestaopedidosrestaurantes.LimparTela.limparTela;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adicionar {

    public static BufferedReader scan =  new BufferedReader(new InputStreamReader(System.in)); 
    public static String opcao;

    public static void addPratos(ArrayList<Prato> pratos) {

        try {
            Prato p = new Prato();
            String id = "PRAT" + Integer.toString(pratos.size() + 1000);
            
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
                        default: opcao="s";
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Adicionar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (opcao.equals("s"));
            System.out.print("Preço: "); p.setPreço(scan.readLine());
            p.setId(id);
            pratos.add(p);
            System.out.println("----------------------------------------------------------------------");
            System.out.println(" Prato '" + p.getNome().toUpperCase() + "' adicionado com sucesso!       ");
            System.out.println("======================================================================");
            System.out.print("Press (1) para continuar >> "); scan.readLine();
        } catch (IOException ex) {
            System.out.println("Erro na leitura: "+ex.getMessage());
            Logger.getLogger(Adicionar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addFuncionarios(ArrayList<Funcionario> funcionarios) {

        try {
            Funcionario f = new Funcionario();
            String id = "FUNC" + Integer.toString(funcionarios.size() + 1000);
            
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
            Date date = new Date(); f.setDataEntrada(date.toString());
            f.setId(id);
            funcionarios.add(f);
            System.out.println("----------------------------------------------------------------------");
            System.out.println(" Funcionario '" + f.getNome().toUpperCase() + "' adicionado com sucesso! ");
            System.out.println("======================================================================");
            System.out.print("Press (1) para continuar >> "); scan.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Adicionar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
