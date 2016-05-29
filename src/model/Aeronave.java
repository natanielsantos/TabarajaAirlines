package model;

public abstract class Aeronave {
    private String identificacao;
    private String modelo;
    private int capacPassageiros;
    private double capacCarga;
   

    public Aeronave() {
    }

    public Aeronave(String identificacao, String modelo, int capacPassageiros, double capacCarga) {
        this.identificacao = identificacao;
        this.modelo = modelo;
        this.capacPassageiros = capacPassageiros;
        this.capacCarga = capacCarga;
    }

    

    

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
    

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacPassageiros() {
        return capacPassageiros;
    }

    public void setCapacPassageiros(int capacPassageiros) {
        this.capacPassageiros = capacPassageiros;
    }

    public double getCapacCarga() {
        return capacCarga;
    }

    public void setCapacCarga(double capacCarga) {
        this.capacCarga = capacCarga;
    }

    
    
    public void consultar(){
        System.out.println("Identificação: "+identificacao);
        System.out.println("Modelo: "+ modelo);
        System.out.println("Capacidade de Passageiros: "+ capacPassageiros);
        System.out.println("Capacidade de Carga: "+ capacCarga);
    }
}
