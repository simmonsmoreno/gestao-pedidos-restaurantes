package gestaopedidosrestaurantes;

import java.util.*;

public class Pedido {
    
    private int numeroPedido;
    private int mesa;
    private int preçoTotal;
    private boolean pronto;
    private boolean entregue;
    private String data;
    private int idGarcom;
    private int idCozinheiro;
    private ArrayList <Integer> qtdadePratos = new ArrayList<>();
    private ArrayList <Integer> idPratos = new ArrayList<>();

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String list = "";
        for (int i=0; i<idPratos.size();i++) list += qtdadePratos.get(i) + "|" + idPratos.get(i) +",";
        return numeroPedido + "-" + mesa + "-" + preçoTotal + "-"  + pronto + "-" + entregue + "-" + data + "-" + idGarcom + "-" + idCozinheiro + "-" + list;
    }

    public String showPedido(ArrayList <Funcionario> funcionarios, ArrayList <Prato> pratos) {
        
        String lista = "";
        String nomeGarcom = "";
        String nomeCozinheiro = "";
        
        for(int i=0; i<idPratos.size();i++) {
            for(Prato prato:pratos){
                if(idPratos.get(i).equals(prato.getId())){
                    lista += prato.showPrato()+"\nQtdade: "+ qtdadePratos.get(i) +"\n\n";
                    break;
                }
            }
        }
        
        for(Funcionario funcionario:funcionarios) if(funcionario.getId()==(idGarcom)) nomeGarcom=funcionario.getNome();
        for(Funcionario funcionario:funcionarios) if(funcionario.getId()==(idCozinheiro)) nomeCozinheiro=funcionario.getNome();

        return  "========================================================================\n"
                + "Pedido numero: " + numeroPedido+ 
                "\nFeito ás : " + data +" horas" +
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdGarcom() {
        return idGarcom;
    }

    public void setIdGarcom(int idGarcom) {
        this.idGarcom = idGarcom;
    }

    public int getIdCozinheiro() {
        return idCozinheiro;
    }

    public void setIdCozinheiro(int idCozinheiro) {
        this.idCozinheiro = idCozinheiro;
    }

    public ArrayList <Integer> getQtdadePratos() {
        return qtdadePratos;
    }

    public void setQtdadePratos(ArrayList <Integer> qtdadePratos) {
        this.qtdadePratos = qtdadePratos;
    }

    public ArrayList <Integer> getIdPratos() {
        return idPratos;
    }

    public void setIdPratos(ArrayList <Integer> idPratos) {
        this.idPratos = idPratos;
    }

    
}
