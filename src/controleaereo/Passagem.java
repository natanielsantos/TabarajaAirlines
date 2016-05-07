package controleaereo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Passagem {
	private Cliente cliente;
	private Voo voo;
	private LocalDate dataVenda;
	private LocalTime horaVenda;
	private double precoFinalViagem;

	public Passagem(Cliente cliente, Voo voo, LocalDate dataVenda, LocalTime horaVenda, double precoFinalViagem) {
		super();
		this.cliente = cliente;
		this.voo = voo;
		this.dataVenda = dataVenda;
		this.horaVenda = horaVenda;
		this.precoFinalViagem = precoFinalViagem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public LocalTime getHoraVenda() {
		return horaVenda;
	}

	public void setHoraVenda(LocalTime horaVenda) {
		this.horaVenda = horaVenda;
	}

	public double getPrecoFinalViagem() {
		return precoFinalViagem;
	}

	public void setPrecoFinalViagem(double precoFinalViagem) {
		this.precoFinalViagem = precoFinalViagem;
	}

}
