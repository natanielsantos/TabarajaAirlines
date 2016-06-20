package controller;

import DAO.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class GerenciaVoo {

	private ArrayList<Voo> voos;
	private ArrayList<Aeronave> aeronaves;
	private ArrayList<Aviao> avioes;
	private ArrayList<Carro> carros;
	private ArrayList<Aeroporto> aeroportos;
	private ArrayList<Helicoptero> helicopteros;
	private Aviao aviao = new Aviao();
	private Carro carro = new Carro();
	private Helicoptero helicoptero = new Helicoptero();
	private Aeroporto aeroporto = new Aeroporto();
	private final AeroportoDAO AerBd = new AeroportoDAO();
	private final AviaoDAO AviBd = new AviaoDAO();
	private final CarroDAO CarBd = new CarroDAO();
	private final HelicopteroDAO HelBd = new HelicopteroDAO();
	private final Conexao con = Conexao.getInstance();
	private final VooDAO VooBd = new VooDAO();
	private Scanner ent = new Scanner(System.in);
	private Voo voo = new Voo();
	private int resp;
	private int tipo;
	private String ident;
	private long identL;
	private String identS;
	private boolean verifAviao;
	private int lotacao;
	private double carga;
	private String data, dataDiv[], hora, horaDiv[];

	public GerenciaVoo() {
	}

	public void cadastrar() {

		avioes = AviBd.relatorio();
		carros = CarBd.relatorio();
		helicopteros = HelBd.relatorio();
		aeroportos = AerBd.relatorio();

		if ((avioes != null) || (carros != null) || (helicopteros != null)) {
			if (aeroportos != null) {
				System.out.println("\n----------- Inserir Voo -----------");
				// System.out.println("Identificação: ");
				// voo.setId_voo(ent.nextInt());
				System.out.println("Adicionar do Aeronave: ");
				System.out.println("Escolha o tipo de Aeronave: ");
				System.out.println("1 = Avião ");
				System.out.println("2 = Carro ");
				System.out.println("3 = Helicoptero ");
				System.out.println("Tipo: ");
				tipo = ent.nextInt();
				ent.nextLine();
				if (tipo == 1) { // Avião
					avioes = AviBd.relatorio();
					System.out.println("--------- Lista de Aviões Cadastrados -------");
					for (Aviao a : avioes) {
						System.out.println("Identificação: " + a.getIdentificacao());
						System.out.println("Modelo: " + a.getModelo());
					}
					do {
						System.out.println("--------------------------------");
						System.out.print("Id :");
						ident = ent.next();
						verifAviao = false;
						aviao = AviBd.consultar(ident);
						if (aviao == null) {
							System.out.println("\n--- [ Identificação Escolhida Inexistente ] ---\n");
							System.out.print("Deseja Visualizar Lista De Avião? \n\t1 - Sim\n\t2 - Não\nOpção: ");
							resp = ent.nextInt();
							ent.nextLine();
							if (resp == 1) {
								System.out.println("--------- Lista de Aviões Disponíveis -------");
								System.out.println("Id             Modelo:           ");
								for (Aviao a : avioes) {
									System.out.printf("%-15s%-50s\n", a.getIdentificacao(), a.getModelo());
								}
							}
						} else {
							System.out.println("\n\tAvião a ser INSERIDO:");
							System.out.println("------------------------------");
							aviao.consultar();
							System.out.println("------------------------------\n");
							System.out.println("Realmente deseja Adicionar (" + aviao.getModelo() + ")");
							System.out.println("\t1 = sim\n\t2 = não ");
							System.out.print("Opção:");
							resp = ent.nextInt();
							ent.nextLine();
							if (resp == 1) {
								voo.setAeronave(aviao);
								voo.setTipoAeronave(1);
								lotacao = aviao.getCapacPassageiros();
								carga = aviao.getCapacCarga();
								voo.setPesoCargaEmbarcada(carga);
								voo.setLotacao(lotacao);
								verifAviao = true;
								
							} else {
								System.out.println(
										"\n\t--- [Cadastro de Avião obrigatório!!! Escolha outro Avião, para continuar o cadastro.] ---");
							}
						}
					} while (!verifAviao);
				} else if (tipo == 2) { // Carro

					carros = CarBd.relatorio();
					System.out.println("--------- Lista de Carros Disponíveis -------");
					for (Carro c : carros) {
						System.out.println("Identificação: " + c.getIdentificacao());
						System.out.println("Modelo: " + c.getModelo());
					}
					do {
						System.out.println("--------------------------------");
						System.out.print("Id :");
						identS = ent.nextLine();
						verifAviao = false;
						carro = CarBd.consultar(identS);
						if (carro == null) {
							System.out.println("\n\t--- [Identificação Escolhida Inexistente!!!] ---\n");
							System.out.print("Deseja Visualizar Lista De Carro? \n\t1 - Sim\n\t2 - Não\nOpção: ");
							resp = ent.nextInt();
							ent.nextLine();
							if (resp == 1) {
								System.out.println("--------- Lista de Carros Disponíveis -------");
								System.out.println("Id             Modelo:           ");
								for (Carro c : carros) {
									System.out.printf("%-15s%-50s\n", c.getIdentificacao(), c.getModelo());
								}
							}
						} else {
		
							System.out.println("\n\tCarro a ser Adicionado:");
							System.out.println("------------------------------");
							carro.consultar();
							System.out.println("------------------------------\n");
							System.out.println("Realmente deseja Adicionar (" + carro.getModelo() + ")");
							System.out.println("\t1 = sim\n\t2 = não ");
							System.out.print("Opção:");
							resp = ent.nextInt();
							ent.nextLine();
							if (resp == 1) {
								voo.setAeronave(carro);
								voo.setTipoAeronave(2);
								lotacao = carro.getCapacPassageiros();
								carga = carro.getCapacCarga();
								verifAviao = true;
							} else {
								System.out.println(
										"\n\t--- [Cadastro de Aéronava obrigatório!!! Escolha outra Aéronave, para continuar o cadastro.] ---");
							}
						}
					} while (!verifAviao);
				} else { // helicoptero

					helicopteros = HelBd.relatorio();
					System.out.println("--------- Lista de Hélicoptero Disponíveis -------");
					System.out.println("Id             Modelo:           ");
					for (Helicoptero h : helicopteros) {
						System.out.printf("%-15s%-50s\n", h.getIdentificacao(), h.getModelo());
					}
					do {
						
						System.out.println("--------------------------------");
						System.out.print("Id :");
						identS = ent.nextLine();
						verifAviao = false;
						helicoptero = HelBd.consultar(identS);
						if (helicoptero == null) {
							System.out.println("\n\t--- [Identificação Escolhida Inexistente!!!] ---\n");
							System.out.print("Deseja Visualizar Lista De Helicoptero? \n\t1 - Sim\n\t2 - Não\nOpção: ");
							resp = ent.nextInt();
							ent.nextLine();
							if (resp == 1) {
								System.out.println("--------- Lista de Hélicoptero Disponíveis -------");
								System.out.println("Id             Modelo:           ");
								for (Helicoptero h : helicopteros) {
									System.out.printf("%-15s%-50s\n", h.getIdentificacao(), h.getModelo());
								}
							}
						} else {
														System.out.println("\n\tHelicoptero a ser Adicionado:");
							System.out.println("------------------------------");
							helicoptero.consultar();
							System.out.println("------------------------------\n");
							System.out.println("Realmente deseja Adicionar (" + helicoptero.getModelo() + ")");
							System.out.println("\t1 = sim\n\t2 = não ");
							System.out.print("Opção:");
							resp = ent.nextInt();
							ent.nextLine();
							if (resp == 1) {
								voo.setAeronave(helicoptero);
								voo.setTipoAeronave(3);
								lotacao = helicoptero.getCapacPassageiros();
								carga = helicoptero.getCapacCarga();
								verifAviao = true;
							} else {
								System.out.println(
										"\n\t--- [Cadastro de Aéronava obrigatório!!! Escolha outra Aéronave, para continuar o cadastro.] ---");
							}
						}
					} while (!verifAviao);
					
				}
			}
			if (tipo == 2) {
				voo.setAeroportoPartida(null);
			} else {
				System.out.println("Adicionar Aeroporto de Partida: ");
				verifAviao = false;
				aeroportos = AerBd.relatorio();
				System.out.println("--------- Lista de Aéroporto disponíveis -------");
				System.out.println("Id             Nome:           ");
				for (Aeroporto a : aeroportos) {
					System.out.printf("%-15s%-50s\n", a.getIdentificacao(), a.getNome());
				}
				do {
					System.out.println("--------------------------------");
					System.out.print("Id :");
					identS = ent.nextLine();
					verifAviao = false;

					aeroporto = AerBd.consultar(identS);

					if (aeroporto == null) {
						System.out.println("\n\t--- [Identificação Escolhida Inexistente!!!] ---\n");
						System.out.print("Deseja Visualizar Lista De Aéroporto? \n\t1 - Sim\n\t2 - Não\nOpção: ");
						resp = ent.nextInt();
						ent.nextLine();
						if (resp == 1) {
							System.out.println("--------- Lista de Aéroporto disponíveis -------");
							System.out.println("Id             Nome:           ");
							for (Aeroporto a : aeroportos) {
								System.out.printf("%-15s%-50s\n", a.getIdentificacao(), a.getNome());
							}
						}
					} else {
						System.out.println("\n\tAéroporto a ser Adicionado:");
						System.out.println("------------------------------");
						aeroporto.consultar();
						System.out.println("------------------------------\n");
						System.out.println("Realmente deseja Adicionar (" + aeroporto.getNome() + ")");
						System.out.println("\t1 = sim\n\t2 = não ");
						System.out.print("Opção:");
						resp = ent.nextInt();
						ent.nextLine();

						if (resp == 1) {
							voo.setAeroportoPartida(aeroporto);
							verifAviao = true;
							System.out.println("Partida: " + voo.getAeroportoPartida().getIdentificacao());
						} else {
							System.out.println(
									"\n\t--- [Cadastro de Aéroporto obrigatório!!! Escolha outro Aéroporto, para continuar o cadastro.] ---");
						}
					}
				} while (!verifAviao);
			}
			System.out.print("Data de Saida: Ex(30/12/1999) >");
			data = ent.nextLine();
			dataDiv = data.split("/");
			int dia = Integer.parseInt(dataDiv[0]);
			int mes = Integer.parseInt(dataDiv[1]);
			int ano = Integer.parseInt(dataDiv[2]);
			voo.setDataPartida(LocalDate.of(ano, Month.of(mes), dia));
			System.out.print("Horário de Saída Ex(10:00) > ");
			hora = ent.nextLine();
			horaDiv = hora.split(":");
			int horaSaida = Integer.parseInt(horaDiv[0]);
			int minutoSaida = Integer.parseInt(horaDiv[1]);
			voo.setHoraPartida(LocalTime.of(horaSaida, minutoSaida));
			System.out.println("--------------------------------");

			if (tipo == 2) {
				voo.setAeroportoChegada(null);
			} else {
				System.out.println("Adicionar Aeroporto de Chegada: ");
				verifAviao = false;
				aeroportos = AerBd.relatorio();
				System.out.println("--------- Lista de Aéroporto disponíveis -------");
				System.out.println("Id             Nome:           ");
				for (Aeroporto a : aeroportos) {
					System.out.printf("%-15s%-50s\n", a.getIdentificacao(), a.getNome());
				}
				do {
					System.out.println("--------------------------------");
					System.out.print("Id :");
					ident = ent.nextLine();
					verifAviao = false;
					
					Aeroporto aeroporto2 = new Aeroporto();
					aeroporto2 = AerBd.consultar(ident);

					if (aeroporto2 == null) {
						System.out.println("\n\t--- [Identificação Escolhida Inexistente!!!] ---\n");
						System.out.print("Deseja Visualizar Lista De Aéroporto? \n\t1 - Sim\n\t2 - Não\nOpção: ");
						resp = ent.nextInt();
						ent.nextLine();
						if (resp == 1) {
							System.out.println("--------- Lista de Aéroporto disponíveis -------");
							System.out.println("Id             Nome:           ");
							for (Aeroporto a : aeroportos) {
								System.out.printf("%-15s%-50s\n", a.getIdentificacao(), a.getNome());
							}
						}
					} else {
						System.out.println("\n\tAeroporto de Chegada a ser Adicionado:");
						System.out.println("------------------------------");
						aeroporto2.consultar();
						System.out.println("------------------------------\n");
						System.out.println("Realmente deseja Adicionar (" + aeroporto2.getNome() + ")");
						System.out.println("\t1 = sim\n\t2 = não ");
						System.out.print("Opção:");
						resp = ent.nextInt();
						ent.nextLine();
						if (resp == 1) {
							voo.setAeroportoChegada(aeroporto2);
							verifAviao = true;
												} else {
							System.out.println(
									"\n\t--- [Cadastro de Aéroporto obrigatório!!! Escolha outro Aéroporto, para continuar o cadastro.] ---");
						}
					}
				} while (!verifAviao);
			}
			System.out.print("Data de Chegada:  Ex(30/12/1999) > ");
			data = ent.nextLine();
			dataDiv = data.split("/");
			int diaChegada = Integer.parseInt(dataDiv[0]);
			int mesChegada = Integer.parseInt(dataDiv[1]);
			int anoChegada = Integer.parseInt(dataDiv[2]);
			voo.setDataChegada(LocalDate.of(anoChegada, Month.of(mesChegada), diaChegada));
			System.out.print("Horário de Chegada: Ex(10:00) > ");
			hora = ent.nextLine();
			horaDiv = hora.split(":");
			int horaChegada = Integer.parseInt(horaDiv[0]);
			int minutoChegada = Integer.parseInt(horaDiv[1]);
			voo.setHoraChegada(LocalTime.of(horaChegada, minutoChegada));
			System.out.println("Lotação Máxima: " + lotacao);
			System.out.println("Peso Máximo da Carga: " + carga);
			System.out.print("Preço da Passagem: R$ ");
			voo.setPrecoViagem(ent.nextFloat());
			ent.nextLine();
			// voo.setStatus(0);
			VooBd.inserirNoBanco(voo);

			System.out.println("\n\t--- [Voo Adicionado com sucesso!!!] ---\n");
		} else {
			System.out.println("\n\t--- [Não Há Aeroporto Cadastrado!!!] ----");
		}
	}

	public void alterar() {

	}

	public void excluir() {
		int cod;
		int resp;

		System.out.println("==== Exclusão de voos ====");

		System.out.println("Qual o codigo do voo que você deseja excluir? ");
		cod = ent.nextInt();
		;

		Voo voo = VooBd.consultar(cod);

		if (voo != null) {
			System.out.println("===== Dados do voo =====");
			voo.consultar();
			System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
			resp = ent.nextInt();
			ent.nextLine();

			if (resp == 1) {
				try {
					VooBd.excluirDoBanco(voo);
					System.out.println("Exclusão efetuada com sucesso.");
				} catch (Exception ex) {
					System.out.println("Exclusão não efetuada. Erro: " + ex.getMessage());
				}
			} else {
				System.out.println("Exclusão não efetuada.");
			}
		}
	}

	public void consultar() {

		int cod;

		System.out.println("==== Consulta de voos ====");

		System.out.println("Qual o codigo do voo que você deseja consultar? ");
		cod = ent.nextInt();
		ent.nextLine();

		Voo voo = VooBd.consultar(cod);

		if (voo != null) {
			System.out.println("===== Dados do VOO =====");
			voo.consultar();
		} else {
			System.out.println("Não existe voo com este código.");
		}

	}

	public void relatorio() {
		
		ArrayList<Voo> voo = new ArrayList<>();

        System.out.println("==== Relatório de Voos ====");

        try {
            voo = VooBd.relatorio();

            if (voo != null) {
                System.out.println("===== Lista de Voos =====");
                for (Voo v : voo) {
                    v.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem voos cadastrados.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
