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
	private double cargaCliente;
	private String status;


	int i = 0;

	public GerenciadorPassagem(ArrayList<Voo> vo, ArrayList<Cliente> cls, ArrayList<Passagem> psg) {
		voos = vo;
		clientes = cls;
		passagens = psg;
		ent = new Scanner(System.in);
	}
	

	public void venda() {

		int posiVoo = 0, posiCli = 0;


		if (!(clientes.isEmpty())) {

			if (!(voos.isEmpty())) {

				boolean clienteProibido = true, vooProibido = true, cargaProibida = true;
				double cargaCli;

				System.out.println("*****==[Módulo de Venda de Passagens Aereas]==*****");
				System.out.println("             *****==[Versão 1.1]==*****");
				System.out.println("---------------------------------------------------");




					System.out.println("Número da passagem: ");
					numPassagem = ent.nextInt();

               // INICIO VERIFICA SE O VOO É PERMITIDO
					do{

								boolean existe = false;

								System.out.println("Informe a identificação do voo: ");
								String ident = ent.next();

									  for(int i = 0; i < voos.size(); i++){

													if(voos.get(i).getIdentificacao().equals(ident)){
															if(voos.get(i).getQtdPassageiros() < voos.get(i).getLotacao()){
																	posiVoo = i;
																	i = voos.size() + 1;
																	existe = true;
															}else{
																	System.out.println("Este VOO está lotado!");
																	existe = false;
															}
													}else{
															existe = false;
														}
										}

										if ( existe ) {
											vooProibido = false;
										}else{
											System.out.println("O voo informado não existe ou está lotado! ");
											System.out.println("Confira os voos existentes: ");

											for (Voo voo : voos){
												voo.imprimir();
												System.out.println("-----------------------");
											}

										}
					}while( vooProibido );

                // FIM VERIFICA SE O VOO É PERMITIDO


                 // INICIO VERIFICA SE O CLIENTE É PERMITIDO
					do{

								boolean existe = false;

								System.out.println("Informe a identificação do cliente: ");
								posiCli = ent.nextInt();

									  for(int i = 0; i < clientes.size(); i++){

													if(clientes.get(i).getIdentificacao() == posiCli){
																	posiCli = i;
																	i = clientes.size() + 1;
																	existe = true;
													}
										}

										if ( existe ) {
											clienteProibido = false;
										}else{
											System.out.println("O cliente informado não existe ");
											System.out.println("Confira os clientes existentes: ");

											for (Cliente cli : clientes){
												cli.imprimir();
												System.out.println("-----------------------");
											}

										}
					}while( clienteProibido );

                // FIM VERIFICA SE O CLIENTE É PERMITIDO


                 // INICIO VERIFICA CARGA
					do{

								double cargaAux, valorEmbarcado, valorLimiteCarga;

								System.out.println("Informe a carga do cliente: ");
								cargaCli = ent.nextDouble();

										valorEmbarcado = voos.get(posiVoo).getPesoCargaEmbarcada();
										valorLimiteCarga = voos.get(posiVoo).getAviao().getCapacCarga();

										cargaAux = valorLimiteCarga - valorEmbarcado;

										if ( cargaCli <= cargaAux ) {

											valorEmbarcado += cargaCli;
											voos.get(posiVoo).setPesoCargaEmbarcada(valorEmbarcado);
											cargaProibida = false;

										}else{
											System.out.println("A carga do cliente excede o limite para este voo!");
											System.out.println("Informe uma carga menor ou 0!");
										}
					}while( cargaProibida );

                // FIM VERIFICA CARGA



				cliente = clientes.get(posiCli);
				voo = voos.get(posiVoo);
				cargaCliente = cargaCli;
				status = "REGULAR";
				dataVenda = LocalDate.now();
				horaVenda = LocalTime.now();

				precoFinalViagem = calculaPrecoViagem(posiVoo);


			} else {

				System.out
				.println("      *****==[É necessário pelo menos 1 Voo para cadastrar uma passagem!]==*****");
			}

		} else {
			System.out
			.println("       *****==[É necessário no mínimo 1 Cliente para cadastrar uma passagem!]==*****");
		}

		i++;

		passagem = new Passagem(numPassagem, cliente, voo, dataVenda, horaVenda, precoFinalViagem, cargaCliente, status);

		passagens.add(passagem);

		System.out.println("       *****==[Passagem Cadastrada!]==*****");
	}

	public void cancelar() {
		
		boolean existe = true;
		int posi = 0, nP;
		
		if(!(passagens.isEmpty())){

			do{
				System.out.println("Informe o número da passagem que deseja cancelar: ");
				int numPsg = ent.nextInt();
for(int i = 0; i < passagens.size(); i++){
						
						nP = passagens.get(i).getNumPassagem();
						if( nP == numPsg){

							int qtdNova = passagens.get(i).getVoo().getQtdPassageiros();
							double cargaDoVoo = passagens.get(i).getVoo().getPesoCargaEmbarcada();
							double cargaDoPassageiro = passagens.get(i).getCargaCliente();
							double cargaNova = 0;

							passagens.get(numPsg).imprimir();
							
							System.out.println("Deseja cancelar essa passagem ? (1 - Sim / 2 - Não) ");
							int resp = ent.nextInt();

							if (resp == 1) {

								passagens.remove(i);

								passagens.get(i).setStatus("Cancelada");
								qtdNova--;
								cargaNova = cargaDoVoo - cargaDoPassageiro;

								passagens.get(i).getVoo().setQtdPassageiros(qtdNova);
								passagens.get(i).getVoo().setPesoCargaEmbarcada(cargaNova);

								posi = i;
								i = passagens.size() + 1;

								existe = true;


								System.out.println("         *****==[Exclusão Efetuada com Sucesso!!]==*****");

							} else {
								System.out.println("         *****==[Exclusão não efetuada não Efetuada!]==*****");
							}

						}else{

							existe = false;
						}
					}
					
				}while( existe );

		}else{

			System.out.println("Não existem passagens cadastradas!");
		}

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

	public double calculaPrecoViagem(int posiVoo) {

		int diferencaDias = voos.get(posiVoo).getDataPartida().compareTo(dataVenda);
		double precoViagem = voos.get(posiVoo).getPrecoViagem();

		if (diferencaDias < 10) {

			precoViagem = precoViagem - ((precoViagem / 100) * 5.3);

		} else {

			precoViagem = precoViagem - ((precoViagem / 100) * 7.4);

		}

		return precoViagem;

	}
}


