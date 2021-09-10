package gestaorestaurantesfx.modelo.domain;


public class Pratos {
    
    private int idPratos;
    private String descrição;
    private String categoria;
    private int preço;
    private int qtdade;

    @Override
    public String toString() {
        return "Pratos{" + "idPratos=" + idPratos + ", descri\u00e7\u00e3o=" + descrição + ", categoria=" + categoria + ", pre\u00e7o=" + preço + ", qtade=" + qtdade + '}';
    }

    public int getIdPratos() {
        return idPratos;
    }

    public void setIdPratos(int idPratos) {
        this.idPratos = idPratos;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
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

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtade) {
        this.qtdade = qtade;
    }

    
    
}
