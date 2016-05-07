package controleaereo;

public class Aviao {
	private String identificacao;
	private String modelo;
	private int qtdTurbinas;
	private int capacPassageiros;
	private double capacCarga;//teste
	
	
	public Aviao(String identificacao, String modelo, int qtdTurbinas, int capacPassageiros, double capacCarga) {
		super();
		this.identificacao = identificacao;
		this.modelo = modelo;
		this.qtdTurbinas = qtdTurbinas;
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
	public int getQtdTurbinas() {
		return qtdTurbinas;
	}
	public void setQtdTurbinas(int qtdTurbinas) {
		this.qtdTurbinas = qtdTurbinas;
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
	
	
}
