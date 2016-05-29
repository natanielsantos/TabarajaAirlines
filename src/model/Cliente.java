package model;

public class Cliente {

    private int identificacao;
    private String nome;
    private String logradouro;
    private int numero;
    private String bairro;
    private String municipio;
    private String estado;
    private String cep;
    private String telefone;
   private String cpf;

    public Cliente(int identificacao, String nome, String logradouro, int numero, String bairro, String municipio, String estado, String cep, String telefone, String cpf ) {
        this.identificacao = identificacao;
        this.nome = nome;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicípio(String município) {
        this.municipio = município;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void consultar() {
        System.out.println("Identificação: " + identificacao);
        System.out.println("Nome  : " + nome);
        System.out.println("Logradouro: " + logradouro);
        System.out.println("Numero: " + numero);
        System.out.println("Bairro: " + bairro);
        System.out.println("Município: " + municipio);
        System.out.println("Estado: " + estado);
        System.out.println("Cep: " + cep);
        System.out.println("Telefone: " + telefone);
        System.out.println("CPF :" + cpf);
    }
}
