package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Passagem {

	private int numPassagem;
	private Cliente cliente;
	private Voo voo;
	private LocalDate dataVenda;
	private LocalTime horaVenda;
	private double precoFinalViagem;
	private double cargaCliente;
	private int status;
	
	public Passagem(){
		
	}

	public Passagem(int numPassagem, Cliente cliente, Voo voo, LocalDate dataVenda, LocalTime horaVenda,
			double precoFinalViagem, double cargaCliente, int status) {

		this.cliente = cliente;
		this.voo = voo;
		this.dataVenda = dataVenda;
		this.horaVenda = horaVenda;
		this.precoFinalViagem = precoFinalViagem;
		this.numPassagem = numPassagem;
		this.cargaCliente = cargaCliente;
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void consultar(){
		
		System.out.println("Numero da passagem: " + numPassagem );
		System.out.println("Cliente: " + cliente.getNome());
		System.out.println("Voo:" + voo.getId_voo());
		System.out.println("Data da Venda: " + dataVenda);
		System.out.println("Hora da Venda: " + horaVenda);
		System.out.println("Carga do Cliente:" + cargaCliente);
		System.out.println("Preço Final: " + precoFinalViagem);
		if(status == 0){
			System.out.println("Status da Passagem: ATIVO");
		}else{
			System.out.println("Status da Passagem: CANCELADO");
		}
		System.out.println("-------------------------------------------------");
		
		
	}
	
	public void consultarPorVoo(){

		if(status == 0){
			System.out.println("Passagem: " + numPassagem );
			System.out.println("Cliente: " + cliente.getNome());
		}else{
			System.out.println("Não existem passagens ativas para esse voo!");
		}
		System.out.println("-------------------------------------------------");
		
		
	}
	
	public void consultarPorVooPago(){

		if(status == 0){
			System.out.println("Passagem: " + numPassagem );
			System.out.println("Cliente: " + cliente.getNome());
			System.out.println("Valor pago: " + precoFinalViagem);
		}else{
			System.out.println("Não existem passagens ativas para esse voo!");
		}
		System.out.println("-------------------------------------------------");
	}
	
	public boolean verificaCarga(Voo v, Double carga){
		
		boolean permissao = false;
		
		if(carga < v.getPesoCargaEmbarcada()){
			
			permissao = true;
			
		}else{
			System.out.println("Valor: " + v.getPesoCargaEmbarcada());
			System.out.println("Carga excede o limite do voo!");
			permissao = false;
		}
		
		return permissao;
	}
	
	public double calculaPrecoFinal(LocalDate dia, Double preco){
		
		LocalDate atual = LocalDate.now();
		LocalDate dataVenda = dia;
		double precoFinal;
		long diferencaDias = ChronoUnit.DAYS.between(atual, dataVenda);
		
		if(diferencaDias < 10){
			
			precoFinal = preco - (preco * (5.3/100));
		}else{
			precoFinal = preco - (preco * (7.4/100));
		}
		
		return precoFinal;
	}
	
	public boolean lotacao(Voo v){
		
		boolean lotado = false;
		
		if(v.getLotacao() == 0){
			
			System.out.println("Voo lotado! Escolha outro voo!");
			lotado = true;	
		}
		
		return lotado;
		
	}
	
}
