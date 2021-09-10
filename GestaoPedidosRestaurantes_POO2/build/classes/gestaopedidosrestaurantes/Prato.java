package gestaopedidosrestaurantes;


public class Prato {
    
    private String nome;
    private String id;
    private String categoria;
    private String preço;
    private int vendas;

    public String showPrato() {
        return "Prato : " + nome + 
                "\nId : " + id + 
                "\nCategoria : " + categoria + 
                "\nPreço : " + preço;
    }
    
    @Override
    public String toString() {
        return nome + "|" + id + "|" + categoria + "|" + preço + "|" + vendas;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPreço() {
        return preço;
    }

    public void setPreço(String preço) {
        this.preço = preço;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    
}
