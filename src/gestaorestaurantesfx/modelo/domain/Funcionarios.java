package gestaorestaurantesfx.modelo.domain;

public class Funcionarios {
    
    private int idFuncionarios;
    private String nome;
    private String senha;
    private String cargo;
    private String dataNasc;
    private String sexo;
    private String email;
    private int telefone;
    private String morada;
    private int noPedido;
    private String dataEntrada;

    @Override
    public String toString() {
        return "Funcionarios{" + "idFuncionarios=" + idFuncionarios + ", nome=" + nome + ", senha=" + senha + ", cargo=" + cargo + ", dataNasc=" + dataNasc + ", sexo=" + sexo + ", email=" + email + ", telefone=" + telefone + ", morada=" + morada + ", noPedido=" + noPedido + ", dataEntrada=" + dataEntrada + '}';
    }
    

    public int getIdFuncionarios() {
        return idFuncionarios;
    }

    public void setIdFuncionarios(int idFuncionarios) {
        this.idFuncionarios = idFuncionarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getNoPedido() {
        return noPedido;
    }

    public void setNoPedido(int noPedido) {
        this.noPedido = noPedido;
    }

    
    
}
