package model;

public class Carro extends Aeronave {

    Piloto piloto;
    Cidade cidadeOrigem;
    Cidade cidadeDestino;
    float autonomia;

    public Carro() {
        super();
    }

    public Carro(Piloto piloto, Cidade cidadeOrigem, Cidade cidadeDestino, 
            float autonomia, String identificacao, String modelo, int capacPassageiros, double capacCarga) {
        super(identificacao, modelo, capacPassageiros, capacCarga);
        this.piloto = piloto;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.autonomia = autonomia;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Cidade getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(Cidade cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public Cidade getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(Cidade cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public Float getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(Float autonomia) {
        this.autonomia = autonomia;
    }

    @Override
    public void consultar() {
        System.out.println("===Carro===");
        super.consultar();
        System.out.println("===Piloto===");
        piloto.consultar();
        System.out.println("===Cidade de Origem===");
        cidadeOrigem.consultar();
        System.out.println("===Cidade de Destino===");
        cidadeDestino.consultar();
        System.out.println("Autonomia: " + autonomia);

    }

}
