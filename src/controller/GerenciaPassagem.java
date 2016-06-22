package controller;

import DAO.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class GerenciaPassagem {

	private ArrayList<Voo> voos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Aeronave> aeronaves;
	private ArrayList<Aviao> avioes;
	private ArrayList<Carro> carros;
	private ArrayList<Aeroporto> aeroportos;
	private ArrayList<Helicoptero> helicopteros;
	private Cliente cliente = new Cliente();
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
	private final ClienteDAO CliDB = new ClienteDAO();
	private Scanner ent = new Scanner(System.in);
	private Voo voo = new Voo();
	private Passagem passagem = new Passagem();
	private int resp;
	private int tipo,codCliente, codVoo;
	private String ident;
	private long identL;
	private String identS;
	private boolean verifAviao,vefCliente,vefVoo;
	private int lotacao;
	private double carga;
	private String data, dataDiv[], hora, horaDiv[];

	public GerenciaPassagem() {
	}

	public void cadastrar() {

		voos = VooBd.relatorio();
		clientes = CliDB.relatorio();

		if (voos != null) {
			if (clientes != null) {
				System.out.println("\n----------- Inserir Passagem -----------");
				// System.out.println("Identificação: ");
				// voo.setId_voo(ent.nextInt());
				System.out.println("Inserir cliente: ");
				System.out.println("Informe o codigo do cliente: ");
				codCliente = ent.nextInt();
				ent.nextLine();
				
				cliente = CliDB.consultar(codCliente);
				
				do{
					vefCliente = false;
					
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
							System.out.println(
									"\n\t--- [Cadastro de Cliente obrigatório!!!] ---");
						}
					}
				} while (!vefCliente);
				
				System.out.println("Inserir Voo: ");
				System.out.println("Informe o codigo do voo: ");
				codVoo = ent.nextInt();
				ent.nextLine();
				
				voo = VooBd.consultar(codVoo);
				
				// CADASTRAR VOO
				
				do{
					vefVoo = false;
					
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
					} else {
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
							System.out.println(
									"\n\t--- [Cadastro de Voo obrigatório!!!] ---");
						}
					}
				} while (!vefVoo);
				
				
				
				
			}
			System.out.print("Data de Venda: Ex(30/12/1999) >");
			data = ent.nextLine();
			dataDiv = data.split("/");
			int dia = Integer.parseInt(dataDiv[0]);
			int mes = Integer.parseInt(dataDiv[1]);
			int ano = Integer.parseInt(dataDiv[2]);
			passagem.setDataVenda(LocalDate.of(ano, Month.of(mes), dia));
			System.out.print("Horário de Venda Ex(10:00) > ");
			hora = ent.nextLine();
			horaDiv = hora.split(":");
			int horaSaida = Integer.parseInt(horaDiv[0]);
			int minutoSaida = Integer.parseInt(horaDiv[1]);
			passagem.setHoraVenda(LocalTime.of(horaSaida, minutoSaida));
			System.out.println("--------------------------------");
			
			System.out.println("Qual o peso da carga levada pelo cliente?");
			carga = ent.nextDouble();
			passagem.setCargaCliente(carga);
			
			

			System.out.println("Preço Final da viagem: " + lotacao);
			
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
