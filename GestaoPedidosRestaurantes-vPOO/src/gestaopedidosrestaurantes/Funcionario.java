package gestaopedidosrestaurantes;

public class Funcionario {
    
    private String nome;
    private int id;
    private String password;
    private String cargo;
    private String dataNasc;
    private String sexo;
    private String morada;
    private String telefone;
    private String dataEntrada;

    public String showFuncionario() {
        return "Nome:" + nome + 
                "\nId: " + id + 
                "\nCargo: " + cargo + 
                "\nData de nascimento: " + dataNasc + 
                "\nSexo: " + sexo + 
                "\nMorada: " + morada + 
                "\nTelefone: " + telefone + 
                "\nData de entrada: " + dataEntrada;
    }
    
    @Override
    public String toString() {
        return nome + "|" + id + "|" + password + "|" + cargo + "|" + dataNasc + "|" + sexo + "|" + morada + "|" + telefone + "|" + dataEntrada;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    
}
