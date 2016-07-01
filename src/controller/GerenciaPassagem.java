package controller;

import DAO.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class GerenciaPassagem {

	private ArrayList<Cliente> clientes;
	private ArrayList<Voo> voos;
	private Scanner ent = new Scanner(System.in);
	private Passagem passagem = new Passagem();
	private Cliente cliente = new Cliente();
	private Voo voo = new Voo();

	private final ClienteDAO CliDB = new ClienteDAO();
	private final VooDAO VooBd = new VooDAO();
	private final PassagemDAO PassagemBd = new PassagemDAO();

	private int codCliente, codVoo, resp;
	private boolean vefCliente, vefVoo;
	private double carga, valorViagem, valorTotal;

	public GerenciaPassagem() {
	}

	public void vendaPassagem() {

		voos = VooBd.relatorio();
		clientes = CliDB.relatorio();

		if (voos != null) {
			if (clientes != null) {
				System.out.println("\n----------- Inserir Passagem -----------");
				System.out.println("Inserir Voo: ");

				do {

					boolean lotado = false;

					System.out.println("Informe o codigo do voo: ");
					codVoo = ent.nextInt();
					ent.nextLine();

					voo = VooBd.consultar(codVoo);

					if (voo == null) {
						System.out.println("\n\t--- [Identificação Escolhida Inexistente!!!] ---\n");
						System.out.print("Deseja Visualizar Lista De Voos? \n\t1 - Sim\n\t2 - Não\nOpção: ");
						resp = ent.nextInt();
						ent.nextLine();
						if (resp == 1) {
							System.out.println("--------- Lista de Clientes disponíveis -------");
							System.out.println("Id             Nome:           ");
							for (Voo v : voos) {
								System.out.printf("%-15d%-50d\n", v.getId_voo(), v.getTipoAeronave());
							}
						}

						vefVoo = false;

					} else {

						lotado = passagem.lotacao(voo); // TODO realizar a
														// verificação da
														// lotação Código?

						if (!lotado) {

							vefVoo = true;

							System.out.println("\n\t Voo a ser Adicionado:");
							System.out.println("------------------------------");
							voo.consultar();
							System.out.println("------------------------------\n");
							System.out.println("Realmente deseja Adicionar (" + voo.getId_voo() + ")");
							System.out.println("\t1 = sim\n\t2 = não ");
							System.out.print("Opção:");
							resp = ent.nextInt();
							ent.nextLine();

							if (resp == 1) {
								passagem.setVoo(voo);
								vefVoo = true;
							} else {
								System.out.println("\n\t--- [Cadastro de Voo obrigatório!!!] ---");
							}
						} else {
							System.out.println("Voo lotado! Escolha outro voo! ");
							vefVoo = false;
						}
					}
				} while (!vefVoo);

				System.out.println("Inserir cliente: ");

				do {
					vefCliente = false;

					System.out.println("Informe o codigo do cliente: ");
					codCliente = ent.nextInt();
					ent.nextLine();

					// TODO Verificar se o cliente já está cadastrado no voo.

					cliente = CliDB.consultar(codCliente);

					if (cliente == null) {
						System.out.println("\n\t--- [Identificação Escolhida Inexistente!!!] ---\n");
						System.out.print("Deseja Visualizar Lista De Cliente? \n\t1 - Sim\n\t2 - Não\nOpção: ");
						resp = ent.nextInt();
						ent.nextLine();
						if (resp == 1) {
							System.out.println("--------- Lista de Clientes disponíveis -------");
							System.out.println("Id             Nome:           ");
							for (Cliente c : clientes) {
								System.out.printf("%-15d%-50s\n", c.getIdentificacao(), c.getNome());
							}
						}
					} else {
						System.out.println("\n\t Cliente a ser Adicionado:");
						System.out.println("------------------------------");
						cliente.consultar();
						System.out.println("------------------------------\n");
						System.out.println("Realmente deseja Adicionar (" + cliente.getNome() + ")");
						System.out.println("\t1 = sim\n\t2 = não ");
						System.out.print("Opção:");
						resp = ent.nextInt();
						ent.nextLine();

						if (resp == 1) {
							passagem.setCliente(cliente);
							vefCliente = true;
						} else {
							System.out.println("\n\t--- [Cadastro de Cliente obrigatório!!!] ---");
						}
					}
				} while (!vefCliente);

				LocalDate dia = LocalDate.now();
				LocalTime hora = LocalTime.now();
				passagem.setDataVenda(dia);
				System.out.println("Data de Venda: " + dia);
				passagem.setHoraVenda(LocalTime.now());
				System.out.print("Horário de Venda: " + hora);

				System.out.println("--------------------------------");

				boolean permitir = false;

				do {

					System.out.println("Qual o peso da carga levada pelo cliente?");
					carga = ent.nextDouble();
					permitir = passagem.verificaCarga(voo, carga);

				} while (!permitir);

				passagem.setCargaCliente(carga);

				valorViagem = voo.getPrecoViagem();
				valorTotal = passagem.calculaPrecoFinal(dia, valorViagem);

				passagem.setPrecoFinalViagem(valorTotal);

				System.out.println("Preço Final da viagem: " + valorTotal);

				PassagemBd.vendaPassagem(passagem);

				System.out.println("\n\t--- [Voo Adicionado com sucesso!!!] ---\n");

			} else {
				System.out.println("Não existem clientes cadastrados!");
			}
		} else {
			System.out.println("\n\t--- [Não Há Voo Cadastrado!!!] ----");
		}
	}

	public void cancelaPassagem() {

		int cod, res;

		System.out.println("==== Cancelamento de Passagens ====");

		System.out.println("Qual o codigo da passagem que você deseja cancelar? ");
		cod = ent.nextInt();
		ent.nextLine();

		Passagem passagem = PassagemBd.consultar(cod);

		if (passagem != null) {
			System.out.println("===== Dados da Passagem =====");
			passagem.consultar();

			System.out.println("Tem certeza que deseja cancelar essa passagem? 1-sim/2-nao");
			res = ent.nextInt();
			ent.hasNextLine();

			if (res == 1) {
				PassagemBd.cancelaPassagem(cod);
				System.out.println("Passagem cancelada com sucesso!");
			} else {
				System.out.println("Passagem não cancelada!");
			}

		} else {
			System.out.println("Não existe passagens com este código.");
		}

	}

	public void relatorioPassageirosPorVoo() {

		int cod;

		ArrayList<Passagem> passagens = new ArrayList<>();

		System.out.println("==== Relatório de Passageiros em um Voo ====");

		System.out.println("Informe o código do voo que deseja consultar as passagens");
		cod = ent.nextInt();

		try {
			passagens = PassagemBd.relatorioPassageiroPorVoo(cod);

			if (passagens != null) {
				System.out.println("===== Lista de passageiros do Voo " + cod + " =====");

				for (Passagem p : passagens) {
					p.consultarPorVoo();
				}
			} else {
				System.out.println("\nNão existem passagens cadastradas para esse voo.");
			}
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}

	public void relatorioPassageirosPorVooPago() {

		int cod;

		ArrayList<Passagem> passagens = new ArrayList<>();

		System.out.println("==== Relatório de Passageiros e valores pagos em um Voo ====");

		System.out.println("Informe o código do voo que deseja consultar as passagens e o valor pago?");
		cod = ent.nextInt();

		try {
			passagens = PassagemBd.relatorioPassageiroPorVoo(cod);

			if (passagens != null) {
				System.out.println("===== Lista de passageiros do Voo " + cod + " =====");

				for (Passagem p : passagens) {
					p.consultarPorVooPago();
				}
			} else {
				System.out.println("\nNão existem passagens cadastradas para esse voo.");
			}
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}

	public void relatorioCargaPorVoo() {// TODO Cálculo para a carga incorreto.
										// Corrigir!

		int cod;
		double totalCargaVoo = 0;
		double cargaMaximaVoo = 0;

		System.out.println("==== Relatório de Carga Disponível em um Voo ====");

		System.out.println("Informe o código do voo que deseja consultar as passagens e o valor pago?");
		cod = ent.nextInt();

		try {
			Voo voo = VooBd.consultar(cod);
			totalCargaVoo = voo.getPesoCargaEmbarcada();
			cargaMaximaVoo = voo.getAeronave().getCapacCarga();

			if (voo != null) {
				System.out.println("===== Carga disponível no Voo " + cod + " =====");
				voo.consultarCarga(totalCargaVoo, cargaMaximaVoo);
			}
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}

	public void relatorioLotacao() {

		int cod;

		System.out.println("==== Relatório de Carga Disponível em um Voo ====");

		System.out.println("Informe o código do voo que deseja consultar as passagens e o valor pago?");
		cod = ent.nextInt();

		try {
			Voo voo = VooBd.consultar(cod);

			if (voo != null) {
				System.out.println("===== Vagas disponíveis no Voo " + cod + " =====");
				voo.consultarLotacao();
			} else {
				System.out.println("\nNão existem passagens cadastradas para esse voo.");
			}
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}

	public void valorPerdido() {

		int cod, totalPassageiros;
		double valorPrevisto, precoViagem, valorTotalVoo = 0, resultado;

		ArrayList<Passagem> passagens = new ArrayList<>();

		System.out.println("==== Relatório de Valor perdido ====");

		System.out.println("Informe o código do voo que deseja consultar ao valor perdido: ");
		cod = ent.nextInt();

		try {
			Voo voo = VooBd.consultar(cod);

			if (voo != null) {

				totalPassageiros = voo.getLotacao();
				precoViagem = voo.getPrecoViagem();

				valorPrevisto = totalPassageiros * precoViagem;

				passagens = PassagemBd.relatorioPassageiroPorVoo(cod);

				for (Passagem p : passagens) {
					valorTotalVoo += p.getPrecoFinalViagem();
				}

				resultado = valorPrevisto - valorTotalVoo;

				System.out.printf("\nValor que a empresa deixou de ganhar no voo %d é de %.2f.\n\n ", cod, resultado);

			} else {
				System.out.println("\nNão existem passagens cadastradas para esse voo.");
			}
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}

	}

	public void totalArrecadado() {
		int cod;
		double totalArrecadado = 0;

		ArrayList<Passagem> passagens = new ArrayList<>();

		System.out.println("==== Relatório de Valor perdido ====");

		System.out.println("Informe o código do voo que deseja consultar ao valor perdido: ");
		cod = ent.nextInt();

		try {
			Voo voo = VooBd.consultar(cod);

			if (voo != null) {

				passagens = PassagemBd.relatorioPassageiroPorVoo(cod);

				for (Passagem p : passagens) {
					totalArrecadado += p.getPrecoFinalViagem();
				}

				System.out.printf("\nValor valor total arrecadado no voo %d foi de %.2f.\n\n ", cod, totalArrecadado);

			} else {
				System.out.println("\nNão existem passagens cadastradas para esse voo.");
			}
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}

	}

}
