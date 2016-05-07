package controleaereo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Voo {
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

	public Voo(Aviao aviao, Aeroporto aeroportoPartida, LocalDate dataPartida, LocalTime horaPartida,
			Aeroporto aeroportoChegada, LocalDate dataChegada, LocalTime horaChegada, int lotacao,
			double pesoCargaEmbarcada, double precoViagem) {
		super();
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

}
