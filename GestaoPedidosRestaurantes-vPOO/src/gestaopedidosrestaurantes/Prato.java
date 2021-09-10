package gestaopedidosrestaurantes;


public class Prato {
    
    private String nome;
    private int id;
    private String categoria;
    private int preço;
    private int vendas;
    private int qtdade;

    public String showPrato() {
        return "Prato : " + nome + 
                "\nId : " + id + 
                "\nCategoria : " + categoria + 
                "\nPreço : " + preço;
    }
    
    @Override
    public String toString() {
        return nome + "|" + id + "|" + categoria + "|" + preço + "|" + vendas  + "|" + qtdade;
    }

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPreço() {
        return preço;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    
}
