package controleaereo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorPassagem {

	Scanner ent;

	private ArrayList<Voo> voos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Passagem> passagens;
	private ArrayList<Aviao> avioes;
	private ArrayList<Aeroporto> aeroportos;

	private int numPassagem;
	private Cliente cliente;
	private Voo voo;
	private Passagem passagem;
	private LocalDate dataVenda;
	private LocalTime horaVenda;
	private double precoFinalViagem;

	int i = 0;

	public GerenciadorPassagem(ArrayList<Voo> vo, ArrayList<Cliente> cls, ArrayList<Passagem> psg) {
		voos = vo;
		clientes = cls;
		passagens = psg;
		ent = new Scanner(System.in);
	}

	public void venda() {

		int cod, codCli;
		double carga, aux;

		if (!(clientes.isEmpty())) {

			if (!(voos.isEmpty())) {

				int numPassagem = i;
				boolean cadastroPermitido = false, permitido = true;
				boolean clientePermitido = true, vooPermitido = false, cargaPermitida = true;
				double limCarga;

				System.out.println("*****==[Módulo de Venda de Passagens Aereas]==*****");
				System.out.println("             *****==[Versão 1.1]==*****");
				System.out.println("---------------------------------------------------");

				do{
					
					permitido = true;
					
					System.out.println("Identificação                 : " + i);
					System.out.println("Informe o Codigo do voo: ");
					cod = ent.nextInt();

					if((cod >= 0 ) && (cod < voos.size())){
						permitido = false;
					}else{
						System.out.println("O voo informado não existe. ");
						System.out.println("Confira abaixo a lista de voo existente e escolha um");

						for(int i = 0; i < voos.size(); i++){
							System.out.println("-------------------------------");
							System.out.println("Codigo voo: " + voos.get(i).getIdentificacao());
							System.out.println("Lotação: " + voos.get(i).getLotacao());
							System.out.println("\n");
							
						}

					}

				}while( permitido );

				do{
					
					clientePermitido = true;

					System.out.println("Informe o Codigo do cliente: ");
					codCli = ent.nextInt();

					if((codCli >= 0 ) && (codCli < clientes.size())){

						clientePermitido = false;

					}else{

						System.out.println("O cliente informado não existe. ");
						System.out.println("Confira abaixo a lista de clientes existente e escolha um: ");

						for(int i = 0; i < clientes.size(); i++){

							System.out.println("-------------------------------");
							System.out.println("Codigo cliente: " + clientes.get(i).getIdentificacao());
							System.out.println("\n");

						}

					}

				}while( clientePermitido );
				
				do{
					
					cargaPermitida = true;

					System.out.println("Informe o peso da carga do cliente: ");
					carga = ent.nextInt();
					
					aux = voos.get(cod).getPesoCargaEmbarcada();

					if(carga < aux){
						
						carga += voos.get(cod).getPesoCargaEmbarcada();
						voos.get(cod).setPesoCargaEmbarcada(carga);

					}else{

						System.out.println("O cliente informado não existe. ");
						System.out.println("Confira abaixo a lista de clientes existente e escolha um: ");

						for(int i = 0; i < clientes.size(); i++){

							System.out.println("-------------------------------");
							System.out.println("Codigo cliente: " + clientes.get(i).getIdentificacao());
							System.out.println("\n");

						}

					}

				}while( cargaPermitida );

				cliente = clientes.get(codCli);
				voo = voos.get(cod);

				dataVenda = LocalDate.now();
				horaVenda = LocalTime.now();

				precoFinalViagem = 10; // [TESTE]


			} else {

				System.out
				.println("      *****==[É necessário pelo menos 1 Voo para cadastrar uma passagem!]==*****");
			}

		} else {
			System.out
			.println("       *****==[É necessário no mínimo 1 Cliente para cadastrar uma passagem!]==*****");
		}

		i++;

		passagem = new Passagem(numPassagem, cliente, voo, dataVenda, horaVenda, precoFinalViagem);

		passagens.add(passagem);

		System.out.println("       *****==[Passagem Cadastrada!]==*****");
	}

	public void cancelar() {

	}

	public void relatorios() {

		int resp = 0;

		if (!passagens.isEmpty()) {

			do{

				System.out.println("                                    *****==[Sub-Menu]==*****                ");

				System.out.println("                     ***=[Selecione o tipo de Relatório que deseja]=***     ");
				System.out.println("              --------------------------------------------------------------");
				System.out.println("                               1 - Quantidade de Lugares Disponíveis        ");
				System.out.println("                               2 - Total Arrecadado de Passagens Vendidas   ");
				System.out.println("                               3 - Lista de Passageiros por Voo             ");
				System.out.println("                               4 - Lista de Passageiros por Voo e Valor Pago");
				System.out.println("                               5 - Quantidade Carga Possível por Voo        ");
				System.out.println("                               6 - Valor que a Companhia deixou de Ganhar   ");
				System.out.println("                               0 - Voltar ao Menu Principal                 ");
				resp = ent.nextInt();

				switch (resp) {

				case 1:
					int qtdDisponivel, qtdLugaresAviao, auxLotacao;
					int posi;

					System.out.println("                         ***=[ Informe a Identificação do Voo ]=***     ");
					posi = ent.nextInt();

					if (voos.get(posi) != null) {

						voos.get(posi).imprimir();

						qtdLugaresAviao = voos.get(posi).getAviao().getCapacPassageiros();
						auxLotacao = voos.get(posi).getLotacao();
						qtdDisponivel = qtdLugaresAviao - auxLotacao;

						System.out.println("O voo selecionado tem uma quantidade de lugares disponíveis de "
								+ qtdDisponivel + ".");
						System.out.println("\n");

					} else {
						System.out.println("Voo inexistente!");
						System.out.println("\n");
					}
					break;

				case 2:
					double totalVendido = 0;

					System.out.println("                         ***=[ Informe a Identificação do Voo ]=***     ");
					posi = ent.nextInt();

					voos.get(posi).imprimir();

					if (voo != null) {
						for (i = 0; i < passagens.size(); i++) {
							if ((passagens.get(i).getVoo() == voo)) {
								totalVendido += passagens.get(i).getPrecoFinalViagem();
							}
						}
						System.out.println("");
						System.out.println("O voo selecionado já arrecardou com passagens R$ " + totalVendido + ".");
						System.out.println("\n");

					} else {
						System.out.println("Voo inexistente!");
						System.out.println("\n");
					}
					break;
					
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				default :
					break;

				}

			}while(resp != 0);

		}else {
			System.out.println("Não possuem passagens vendidas para apresentar relatórios! ");
		}
	}

	public void somaPesoCarga(double cargaCliente) {

		pesoCargaEmbarcada += cargaCliente;

	}

	public double calculaPrecoViagem() {

		int diferencaDias = dataPartida.compareTo(dataVenda);
		double passagemComDesconto = 0;
		double preco = 0;

		if (diferencaDias < 10) {
			passagemComDesconto = precoViagem;

		} else if (diferencaDias == 10) {
			passagemComDesconto = precoViagem - ((precoViagem / 100) * 5.3);
		}
		return precoViagem;

	}
}
