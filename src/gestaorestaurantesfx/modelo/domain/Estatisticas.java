package gestaorestaurantesfx.modelo.domain;

import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class Estatisticas {
    
    private int lucroTotal;
    private int totalPedido;
    private ArrayList<Pratos> pratos = new ArrayList<>();
    private ArrayList<Funcionarios> garcomMaisPedidos = new ArrayList<>();
    private ArrayList<Funcionarios> cozinheiroMaisPedido = new ArrayList<>();

    public ArrayList<Pratos> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Pratos> pratos) {
        this.pratos = pratos;
    }

    public ArrayList<Funcionarios> getGarcomMaisPedidos() {
        return garcomMaisPedidos;
    }

    public void setGarcomMaisPedidos(ArrayList<Funcionarios> garcomMaisPedidos) {
        this.garcomMaisPedidos = garcomMaisPedidos;
    }

    public ArrayList<Funcionarios> getCozinheiroMaisPedido() {
        return cozinheiroMaisPedido;
    }

    public void setCozinheiroMaisPedido(ArrayList<Funcionarios> cozinheiroMaisPedido) {
        this.cozinheiroMaisPedido = cozinheiroMaisPedido;
    }

    public int getLucroTotal() {
        return lucroTotal;
    }

    public void setLucroTotal(int lucroTotal) {
        this.lucroTotal = lucroTotal;
    }

    public int getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(int totalPedido) {
        this.totalPedido = totalPedido;
    }

    
}
