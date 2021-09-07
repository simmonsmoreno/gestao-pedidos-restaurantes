package gestaorestaurantesfx.modelo.domain;

import java.util.ArrayList;

public class Pedidos {
    
    private int numeroPedido;
    private int numeroMesa;
    private int preçoTotal;
    private boolean pronto;
    private boolean entregue;
    private String data;
    private int idGarcom;
    private int idCozinha;
    private ArrayList<Produtos> produtos = new ArrayList<>();

    @Override
    public String toString() {
        String produto = "";
        for(Produtos p:produtos) produto+=p.toString()+"\n";
        return "Pedidos{" + "numeroPedido=" + numeroPedido + ", numeroMesa=" + numeroMesa + ", pre\u00e7oTotal=" + preçoTotal + ", pronto=" + pronto + ", entregue=" + entregue + ", data=" + data + ", idGarcom=" + idGarcom + ", idCozinha=" + idCozinha + ", produtos=" + produto + '}';
    }
    
    
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
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

    public int getIdGarcom() {
        return idGarcom;
    }

    public void setIdGarcom(int idGarcom) {
        this.idGarcom = idGarcom;
    }

    public int getIdCozinha() {
        return idCozinha;
    }

    public void setIdCozinha(int idCozinha) {
        this.idCozinha = idCozinha;
    }

    public ArrayList<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produtos> produtos) {
        this.produtos = produtos;
    }

    
}
