package gestaopedidosrestaurantes;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Pedido {
    
    private int numeroPedido;
    private int mesa;
    private int preçoTotal;
    boolean pronto;
    boolean entregue;
    private String data;
    private String idGarcom;
    private String idCozinheiro;
    private ArrayList <Prato> pratos = new ArrayList<>();

    public String toString() {
        return numeroPedido + "-" + mesa + "-" + preçoTotal + "-"  + pronto + "-" + entregue + "-" + data + "-" + idGarcom + "-" + idCozinheiro + "-" + pratos;
    }

    public String showPedido(ArrayList <Funcionario> funcionarios) {
        
        int j=0;
        String hora = "";
        String lista = "";
        String nomeGarcom = "";
        String nomeCozinheiro = "";
        for(Prato prato:pratos) lista += prato.showPrato()+"\n\n";
        StringTokenizer stgs = new StringTokenizer(data, " ");
        while(stgs.hasMoreTokens()){
                    String token = stgs.nextToken();
                    j+=1;
                    if(j==4) hora=token;
        }
        for(Funcionario funcionario:funcionarios) if(funcionario.getId().equals(idGarcom)) nomeGarcom=funcionario.getNome();
        for(Funcionario funcionario:funcionarios) if(funcionario.getId().equals(idCozinheiro)) nomeCozinheiro=funcionario.getNome();
        return   "Pedido numero: " + numeroPedido+ 
                "\nFeito ás : " + hora +" horas" +
                "\n------------------------------Estado----------------------------------"+
                "\nPronto : " + pronto +
                "\nEntregue : " + entregue + 
                "\n----------------------------------------------------------------------"+
                "\nGarçom : " + nomeGarcom + 
                "\nCozinheiro : " + nomeCozinheiro + 
                "\n-----------------------------Produtos---------------------------------"+
                "\n\n" + lista + 
                "------------------------------------------------------------------------"+
                "\nMesa : " + mesa + "\t\t\t\tPreço Total : "+preçoTotal+
                "\n========================================================================\n";
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getPreçoTotal() {
        return preçoTotal;
    }

    public void setPreçoTotal(int preçoTotal) {
        this.preçoTotal = preçoTotal;
    }

    public boolean isPronto() {
        return pronto;
    }

    public void setPronto(boolean pronto) {
        this.pronto = pronto;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdGarcom() {
        return idGarcom;
    }

    public void setIdGarcom(String idGarcom) {
        this.idGarcom = idGarcom;
    }

    public String getIdCozinheiro() {
        return idCozinheiro;
    }

    public void setIdCozinheiro(String idCozinheiro) {
        this.idCozinheiro = idCozinheiro;
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    
}
