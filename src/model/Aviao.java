package model;

public class Aviao extends Aeronave {

    int qtdTurbinas;
    Float capcCombustPorTurbina;

    public Aviao() {
        super();
    }
    
    public Aviao(int qtdTurbinas, float capcCombustPorTurbina, String identificacao, String modelo, 
            int capacPassageiros, double capacCarga) {
        super(identificacao, modelo, capacPassageiros, capacCarga);
        this.qtdTurbinas = qtdTurbinas;
        this.capcCombustPorTurbina = capcCombustPorTurbina;
    }

    public int getQtdTurbinas() {
        return qtdTurbinas;
    }

    public void setQtdTurbinas(int qtdTurbinas) {
        this.qtdTurbinas = qtdTurbinas;
    }

    public Float getCapcCombustPorTurbina() {
        return capcCombustPorTurbina;
    }

    public void setCapcCombustPorTurbina(Float capcCombustPorTurbina) {
        this.capcCombustPorTurbina = capcCombustPorTurbina;
    }

    @Override
    public void consultar() {
        System.out.println("===Avião===");
        super.consultar();
        System.out.println("Quantidade de Turbinas: " + qtdTurbinas);
        System.out.println("Capacidade de Combustivel Por Turbina: " + capcCombustPorTurbina);

    }
    
 
}
