package controleaereo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Voo {

	private String identificacao;
	private Aviao aviao;
	private Aeroporto aeroportoPartida;
	private LocalDate dataPartida;
	private LocalTime horaPartida;
	private Aeroporto aeroportoChegada;
	private LocalDate dataChegada;
	private LocalTime horaChegada;
	private int lotacao;
	private double pesoCargaEmbarcada;
	private double precoViagem;
	private int qtdPassageiros;

	public Voo(String identificacao, Aviao aviao, Aeroporto aeroportoPartida, LocalDate dataPartida,
			LocalTime horaPartida, Aeroporto aeroportoChegada, LocalDate dataChegada, LocalTime horaChegada,
			int lotacao, double pesoCargaEmbarcada, double precoViagem) {

		this.identificacao = identificacao;
		this.aviao = aviao;
		this.aeroportoPartida = aeroportoPartida;
		this.dataPartida = dataPartida;
		this.horaPartida = horaPartida;
		this.aeroportoChegada = aeroportoChegada;
		this.dataChegada = dataChegada;
		this.horaChegada = horaChegada;
		this.lotacao = lotacao;
		this.pesoCargaEmbarcada = pesoCargaEmbarcada;
		this.precoViagem = precoViagem;
	}

	public Aviao getAviao() {
		return aviao;
	}

	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}

	public Aeroporto getAeroportoPartida() {
		return aeroportoPartida;
	}

	public void setAeroportoPartida(Aeroporto aeroportoPartida) {
		this.aeroportoPartida = aeroportoPartida;
	}

	public LocalDate getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(LocalDate dataPartida) {
		this.dataPartida = dataPartida;
	}

	public LocalTime getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(LocalTime horaPartida) {
		this.horaPartida = horaPartida;
	}

	public Aeroporto getAeroportoChegada() {
		return aeroportoChegada;
	}

	public void setAeroportoChegada(Aeroporto aeroportoChegada) {
		this.aeroportoChegada = aeroportoChegada;
	}

	public LocalDate getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(LocalDate dataChegada) {
		this.dataChegada = dataChegada;
	}

	public LocalTime getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(LocalTime horaChegada) {
		this.horaChegada = horaChegada;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public double getPesoCargaEmbarcada() {
		return pesoCargaEmbarcada;
	}

	public void setPesoCargaEmbarcada(double pesoCargaEmbarcada) {
		this.pesoCargaEmbarcada = pesoCargaEmbarcada;
	}

	public double getPrecoViagem() {
		return precoViagem;
	}

	public void setPrecoViagem(double precoViagem) {
		this.precoViagem = precoViagem;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	
	public int getQtdPassageiros() {
		return qtdPassageiros;
	}

	public void setQtdPassageiros(int qtdPassageiros) {
		this.qtdPassageiros = qtdPassageiros;
	}

	public void imprimir() {
		System.out.println("Identificação            : " + identificacao);
		System.out.println("Avião                    : " + aviao.getIdentificacao());
		System.out.println("Aeroporto de Partida     : " + aeroportoPartida.getIdentificacao());
		System.out.println("Data de Partida          : " + dataPartida + " | Hora de Partida   : " + horaPartida);
		System.out.println("Aeroporto de Chegada     : " + aeroportoChegada.getIdentificacao());
		System.out.println("Data de Chegada          : " + dataChegada + " | Hora de Chegada   : " + horaChegada);
		System.out.println("Lotação                  : " + lotacao);
		System.out.println("Peso da Carga Embarcada  : " + pesoCargaEmbarcada);
		System.out.println("Preço da Viagem          : " + precoViagem);
	}

}
