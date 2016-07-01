
package model;

public class Helicoptero extends Aeronave{
    int qtdHelices;

    public Helicoptero() {
        super();
    }

    public Helicoptero(int qtdHelices, String identificacao, String modelo, int capacPassageiros, double capacCarga) {
        super(identificacao, modelo, capacPassageiros, capacCarga);
        this.qtdHelices = qtdHelices;
    }

    public int getQtdHelices() {
        return qtdHelices;
    }

    public void setQtdHelices(int qtdHelices) {
        this.qtdHelices = qtdHelices;
    }
    
    @Override
     public void consultar(){
         System.out.println("-- Helicoptero --");
        super.consultar();
        System.out.println("Quantidade de Helices: "+qtdHelices);
        
    }
    
    
    
}
