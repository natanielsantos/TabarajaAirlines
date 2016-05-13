package controleaereo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Passagem {

	private int numPassagem;
	private Cliente cliente;
	private Voo voo;
	private LocalDate dataVenda;
	private LocalTime horaVenda;
	private double precoFinalViagem;
	private double cargaCliente;
	private String status;

	public Passagem(int numPassagem, Cliente cliente, Voo voo, LocalDate dataVenda, LocalTime horaVenda,
			double precoFinalViagem, double cargaCliente, String status) {

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

	public int getNumPassagem() {
		return numPassagem;
	}

	public void setNumPassagem(int numPassagem) {
		this.numPassagem = numPassagem;
	}

	public double getCargaCliente() {
		return cargaCliente;
	}

	public void setCargaCliente(double cargaCliente) {
		this.cargaCliente = cargaCliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void imprimir(){
		
		System.out.println("Numero da passagem: " + numPassagem );
		System.out.println("Cliente: " + cliente.getIdentificacao());
		System.out.println("Voo:" + voo.getIdentificacao());
		System.out.println("Data da Venda: " + dataVenda);
		System.out.println("Hora da Venda: " + horaVenda);
		System.out.println("Carga do Cliente:" + cargaCliente);
		System.out.println("Preço Final: " + precoFinalViagem);
		System.out.println("Status da Passagem: " + status);
		System.out.println("-------------------------------------------------");
		
		
	}
	
	
	
}
