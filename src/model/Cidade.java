package model;

public class Cidade {

    private int identificacao;
    private String pais;
    private String estado;
    private String nome;
    
    public Cidade() {
    }
    
    public Cidade(int identificacao, String pais, String estado , String nome) {
        this.identificacao = identificacao;
        this.pais = pais;
        this.estado = estado;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void consultar() {
        System.out.println("Identificação: " + identificacao);
        System.out.println("Pais: " + pais);
        System.out.println("Estado: " + estado);
        System.out.println("nome da cidade :" + nome);
    }
}