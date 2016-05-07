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
	private double cargaCliente;
	private Voo voo;
	private Passagem passagem;
	private LocalDate dataVenda;
	private LocalTime horaVenda;
	private double precoFinalViagem;
	private boolean cadastroPermitido;
	private String identificacao;
	private Aviao aviao;
	private Aeroporto aeroportoPartida;
	private LocalDate dataPartida;
	private LocalTime horaPartida;
	private Aeroporto aeroportoChegada;
	private LocalDate dataChegada;
	private LocalTime horaChegada;
	private int lotacao;
	private double pesoCargaEmbarcada = 0;
	private double precoViagem;

	int i = 0;

	public GerenciadorPassagem(ArrayList<Voo> vo, ArrayList<Cliente> cls, ArrayList<Passagem> psg) {
		voos = vo;
		clientes = cls;
		passagens = psg;
		ent = new Scanner(System.in);
	}

	public void cadastrar() {

		int cod, codCli;

		if (!(clientes.isEmpty())) {

			if (!(voos.isEmpty())) {

				if (voo.getLotacao() > passagens.size()) {

					do {
						int numPassagem = i;
						boolean cadastroPermitido = false;
						boolean clientePermitido = false, vooPermitido = false;
						double limCarga;

						System.out.println("*****==[Módulo de Venda de Passagens Aereas]==*****");
						System.out.println("             *****==[Versão 1.1]==*****");
						System.out.println("---------------------------------------------------");
						System.out.println("Identificação                 : " + i);
						System.out.println("Informe o Código do cliente   : ");
						codCli = ent.nextInt();
						System.out.println("Informe o Codigo do voo       : ");
						cod = ent.nextInt();

						limCarga = voos.get(cod).getAviao().getLimiteCarga();

						if (clientes.get(codCli).getCargaCliente() < limCarga) {

							clientePermitido = true;

						} else {

							System.out.println(
									"       *****==[A carga levada pelo passageiro excede o limite para esse voo!]==*****");
							System.out.println("                            *****==[Cadastre Novamente!]==*****");
						}

					} while (!cadastroPermitido);

					cliente = clientes.get(codCli);
					voo = voos.get(cod);

					dataVenda = LocalDate.now();
					horaVenda = LocalTime.now();

					precoFinalViagem = calculaPrecoViagem();

				} else {
					System.out.println("                         *****==[Voo Lotado!]==*****");
				}

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

	public void alterar() {

		int cod;

		System.out.println("*****==[Módulo de Alteração de Passagens Aereas]==*****");
		System.out.println("             *****==[Versão 1.1]==*****");
		System.out.println("-------------------------------------------------------");
		System.out.println("Qual voo deseja alterar ? (Informe a posição)");
		int posi = ent.nextInt();

		if (!voos.isEmpty() && posi >= 0) {

			voos.get(posi).imprimir();

			System.out.println("Deseja alterar os dados deste voo ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				Voo novoVoo;

				identificacao = "VOO-" + i;
				System.out.println("------------------------------------------------------");
				System.out.println("Identificação                       : " + identificacao);
				System.out.println("Informe o Código do Avião           : ");
				cod = ent.nextInt();
				aviao = avioes.get(cod);

				System.out.println("Informe o novo aeroporto de partida : ");
				cod = ent.nextInt();
				aeroportoPartida = aeroportos.get(cod);

				System.out.println("Informe a nova data de partida      : ");
				int dia, mes, ano;
				System.out.print("    Dia  (dd)                         : ");
				dia = ent.nextInt();
				System.out.print("    Mês  (mm)                         : ");
				mes = ent.nextInt();
				System.out.print("    Ano  (aaaa)                       : ");
				ano = ent.nextInt();
				dataPartida = LocalDate.of(ano, mes, dia);

				System.out.println("Informe a nova hora de partida      : ");
				int hora, minutos;
				System.out.print("   Hora                               : ");
				hora = ent.nextInt();
				System.out.print("   Minuto                             : ");
				minutos = ent.nextInt();
				horaPartida = LocalTime.of(hora, minutos);

				System.out.println("Informe o novo aeroporto de chegada : ");
				cod = ent.nextInt();
				aeroportoPartida = aeroportos.get(cod);

				System.out.println("Informe a nova data de Chegada      : ");
				int diac, mesc, anoc;
				System.out.print("   Dia  (dd)							: ");
				diac = ent.nextInt();
				System.out.print("   Mês  (mm)                          : ");
				mesc = ent.nextInt();
				System.out.print("   Ano  (aaaa)                        : ");
				anoc = ent.nextInt();
				dataPartida = LocalDate.of(anoc, mesc, diac);

				System.out.println("Informe a nova hora de chegada      : ");
				int horaC, minutosC;
				System.out.print("   Hora                               : ");
				horaC = ent.nextInt();
				System.out.print("   Minuto								: ");
				minutosC = ent.nextInt();
				horaPartida = LocalTime.of(horaC, minutosC);

				System.out.println("Lotação                             : " + aviao.getCapacPassageiros());
				lotacao = aviao.getCapacPassageiros();

				System.out.print("Novo Peso de carga embarcada          : ");
				pesoCargaEmbarcada = ent.nextDouble();

				System.out.print("Novo Preço da Viagem                  :  $ ");
				precoViagem = ent.nextDouble();

				i++;

				novoVoo = new Voo(identificacao, aviao, aeroportoPartida, dataPartida, horaPartida, aeroportoChegada,
						dataChegada, horaChegada, lotacao, pesoCargaEmbarcada, precoViagem);

				voos.set(posi, novoVoo);

				System.out.println("       *****==[Passagem Alterada!]==*****");

			} else {
				System.out.println("       *****==[Passagem não Alterada!]==*****");
			}

		} else {
			System.out.println("       *****==[Voo Inexistente!]==*****");
		}
	}

	public void excluir() {

		System.out.println("*****==[Módulo de Exclusão de Passagens Aereas]==*****");
		System.out.println("             *****==[Versão 1.1]==*****");
		System.out.println("-------------------------------------------------------");
		System.out.println("Qual voo deseja excluir ? (Informe a posição)");
		int posi = ent.nextInt();

		if (!voos.isEmpty() && posi >= 0) {

			voos.get(posi).imprimir();

			System.out.println("Deseja excluir esse voo ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				voos.remove(posi);
				System.out.println("       *****==[Exclusão Efetuada com Sucesso!]==*****");

			} else {
				System.out.println("       *****==[Exclusão não Efetuada!]==*****");
			}
		} else {
			System.out.println("       *****==[Voo Inexistente!]==*****");
		}
	}

	public void consultar() {

		System.out.println("*****==[Módulo de Consulta de Passagens Aereas]==*****");
		System.out.println("             *****==[Versão 1.1]==*****");
		System.out.println("-------------------------------------------------------");
		System.out.println("Qual posição deseja consultar ? ");
		int posi = ent.nextInt();

		if ((!voos.isEmpty()) && (posi >= 0) && posi <= voos.size()) {

			voos.get(posi).imprimir();

		} else {
			System.out.println("       *****==[Voo Inexistente!]==*****");
		}
	}

	public void relatorios() {

		if (!voos.isEmpty()) {
			for (int i = 0; i < voos.size(); i++) {

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
				int resp = ent.nextInt();

				switch (resp) {

				case 1:
					int qtdDisponivel, qtdLugaresAviao, auxLotacao;
					int posi;

					System.out.println("                         ***=[ Informe a Identificação do Voo ]=***     ");
					posi = ent.nextInt();

					voo.getIdentificacao(Integer.parseInt(posi)).imprimir();

					if (voo != null) {
						qtdLugaresAviao = voo.getAviao().getCapacPassageiros();
						auxLotacao = voo.getLotacao();
						qtdDisponivel = qtdLugaresAviao - auxLotacao;

						System.out.println("O voo selecionado tem uma quantidade de lugares disponíveis de "
								+ qtdDisponivel + ".");
						System.out.println("\n");

					} else {
						System.out.println("Voo inexistente!");
						System.out.println("\n");
					}

				case 2:
					double totalVendido = 0;

					System.out.println("                         ***=[ Informe a Identificação do Voo ]=***     ");
					posi = ent.nextInt();

					voo.getIdentificacao(Integer.parseInt(posi)).imprimir();

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

				}

			}
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
