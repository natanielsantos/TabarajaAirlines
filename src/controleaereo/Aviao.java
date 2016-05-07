package controleaereo;

public class Aviao {

	private String identificacao;
	private String modelo;
	private int qtdTurbinas;
	private int capacPassageiros;
	private double capacCarga, limiteCarga;

	public Aviao(String identificacao, String modelo, int qtdTurbinas, int capacPassageiros, double capacCarga) {

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

	public double getLimiteCarga() {
		return limiteCarga;
	}

	public void setLimiteCarga(double limiteCarga) {
		this.limiteCarga = limiteCarga;
	}

	public void imprimir() {
		System.out.println("Identificação             : " + identificacao);
		System.out.println("Modelo                    : " + modelo);
		System.out.println("Quantidade de Turbinas    : " + qtdTurbinas);
		System.out.println("Capacidade de Passageiros : " + capacPassageiros);
		System.out.println("Cpacidade de Carga        : " + capacCarga);

		calculaLimiteCarga();

		System.out.println("Limite                    : " + limiteCarga);
	}

	public void calculaLimiteCarga() {

		limiteCarga = capacCarga / capacPassageiros;

	}

}