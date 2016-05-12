package controleaereo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorVoo {

	Scanner ent;
	private Voo voo;
	private ArrayList<Voo> voos;
	private ArrayList<Aviao> avioes;
	private ArrayList<Aeroporto> aeroportos;

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

	public GerenciadorVoo(ArrayList<Voo> vo, ArrayList<Aviao> av, ArrayList<Aeroporto> aero) {
		voos = vo;
		avioes = av;
		aeroportos = aero;
		ent = new Scanner(System.in);
	}

	public void cadastrar() {

		int posi = 0;
		String ident;
		boolean aviaoExiste = true, aeroportoExiste = true;

		if (!avioes.isEmpty()) {

			if ((!aeroportos.isEmpty()) && (aeroportos.size() > 1)) {

				System.out.println("*****==[   Módulo de Cadastro de Voo   ]==*****");
				System.out.println("          *****==[Versão 1.1]==*****");
				System.out.println("----------------------------------------------");
				System.out.println("Identificação               : ");
				identificacao = ent.next();
				
				do{
					
					System.out.println("Informe a identificação do avião   : ");
					ident = ent.next();

					for(int i = 0; i < avioes.size(); i++){

						if(avioes.get(i).getIdentificacao().equals(ident)){
							posi = i;
							i = avioes.size() + 1;
							aviaoExiste = true;
						}else{
							aviaoExiste = false;
						}
					}
					
					aviao = avioes.get(posi);
					
				}while (!aviaoExiste);
				

				do{

					System.out.println("Informe a identificação do aeroporto de Partida: ");
					ident = ent.next();

					for(int i = 0; i < aeroportos.size(); i++){

						if(aeroportos.get(i).getIdentificacao().equals(ident)){
							posi = i;
							i = aeroportos.size() + 1;
							aeroportoExiste = true;
						}else{
							aeroportoExiste = false;
						}
					}

					aeroportoPartida = aeroportos.get(posi);

				}while (!aeroportoExiste);
				

				System.out.println("Informe o data de partida   : ");
				int dia, mes, ano;
				System.out.print("   Dia                        : ");
				dia = ent.nextInt();
				System.out.print("   Mês                 	    : ");
				mes = ent.nextInt();
				System.out.print("   Ano                	    : ");
				ano = ent.nextInt();
				dataPartida = LocalDate.of(ano, mes, dia);

				System.out.println("Informe o hora de partida   : ");
				int hora, minutos;
				System.out.print("   Hora						: ");
				hora = ent.nextInt();
				System.out.print("   Minuto						: ");
				minutos = ent.nextInt();
				horaPartida = LocalTime.of(hora, minutos);

				do{

					System.out.println("Informe a identificação do aeroporto de Chegada: ");
					ident = ent.next();

					for(int i = 0; i < aeroportos.size(); i++){

						if(aeroportos.get(i).getIdentificacao().equals(ident)){
							posi = i;
							i = aeroportos.size() + 1;
							aeroportoExiste = true;
						}else{
							aeroportoExiste = false;
						}
					}

					aeroportoChegada = aeroportos.get(posi);

				}while (!aeroportoExiste);
				

				System.out.println("Informe o data de Chegada	: ");
				int diac, mesc, anoc;
				System.out.print("  Dia							: ");
				diac = ent.nextInt();
				System.out.print("  Mês							: ");
				mesc = ent.nextInt();
				System.out.print("  Ano							: ");
				anoc = ent.nextInt();
				dataChegada = LocalDate.of(anoc, mesc, diac);

				System.out.println("Informe o hora de chegada	: ");
				int horaC, minutosC;
				System.out.print("  Hora						: ");
				horaC = ent.nextInt();
				System.out.print("  Minuto						: ");
				minutosC = ent.nextInt();
				horaChegada = LocalTime.of(horaC, minutosC);

				System.out.println("Lotação                     : " + aviao.getCapacPassageiros());
				lotacao = aviao.getCapacPassageiros();
				
				System.out.println("Peso da carga embarcada     : $");
				pesoCargaEmbarcada = ent.nextDouble();

				System.out.println("Preço da viagem				: $");
				precoViagem = ent.nextDouble();

			} else {

				System.out.println(
						"       *****==[São necessários no mínimo 2 aeroportos para cadastrar um Voo!]==*****");
			}

		} else {
			System.out.println("       *****==[É necessário no mínimo 1 avião para cadastrar um Voo!]==*****");
		}

		i++;

		voo = new Voo(identificacao, aviao, aeroportoPartida, dataPartida, horaPartida, aeroportoChegada, dataChegada,
				horaChegada, lotacao, pesoCargaEmbarcada, precoViagem);
		voos.add(voo);

		System.out.println("       *****==[Voo Cadastrado!]==*****");

	}

	public void alterar() {

		int cod, posi = 0;
		boolean existe = true;
		boolean aviaoExiste = true, aeroportoExiste = true;

		System.out.println("*****==[   Módulo de Alteração de Voo   ]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("----------------------------------------------");
		System.out.println("Qual voo deseja alterar ? (Informe a identificação)");
		String ident = ent.next();

		for(int i = 0; i < voos.size(); i++){

			if(voos.get(i).getIdentificacao().equals(ident)){
				posi = i;
				i = voos.size() + 1;
				existe = true;
			}else{
				existe = false;
			}
		}

		if ((!voos.isEmpty()) && existe ) {

			voos.get(posi).imprimir();

			System.out.println("Deseja alterar os dados deste voo ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				Voo novoVoo;

				
				System.out.println("----------------------------------------------");
				System.out.println("Identificação                : ");
				identificacao = ent.next();
				
				
				do{

					System.out.println("Informe a identificação do avião   : ");
					ident = ent.next();

					for(int i = 0; i < avioes.size(); i++){

						if(avioes.get(i).getIdentificacao().equals(ident)){
							posi = i;
							i = avioes.size() + 1;
							aviaoExiste = true;
						}else{
							aviaoExiste = false;
						}
					}

					aviao = avioes.get(posi);

				}while (!aviaoExiste);


				do{

					System.out.println("Informe a identificação do aeroporto de Partida: ");
					ident = ent.next();

					for(int i = 0; i < aeroportos.size(); i++){

						if(aeroportos.get(i).getIdentificacao().equals(ident)){
							posi = i;
							i = aeroportos.size() + 1;
							aeroportoExiste = true;
						}else{
							aeroportoExiste = false;
						}
					}

					aeroportoPartida = aeroportos.get(posi);

				}while (!aeroportoExiste);

				System.out.println("Informe a nova data de partida: ");
				int dia, mes, ano;
				System.out.print("   Dia						: ");
				dia = ent.nextInt();
				System.out.print("   Mês						: ");
				mes = ent.nextInt();
				System.out.print("   Ano						: ");
				ano = ent.nextInt();
				dataPartida = LocalDate.of(ano, mes, dia);

				System.out.println("Informe a nova hora de partida: ");
				int hora, minutos;
				System.out.print("  Hora						: ");
				hora = ent.nextInt();
				System.out.print("  Minuto						: ");
				minutos = ent.nextInt();
				horaPartida = LocalTime.of(hora, minutos);

				System.out.println("Informe o novo aeroporto de chegada: ");
				cod = ent.nextInt();
				aeroportoPartida = aeroportos.get(cod);

				System.out.println("Informe a nova data de Chegada: ");
				int diac, mesc, anoc;
				System.out.print("  Dia							  : ");
				diac = ent.nextInt();
				System.out.print("  Mês							  : ");
				mesc = ent.nextInt();
				System.out.print("  Ano							  : ");
				anoc = ent.nextInt();
				dataPartida = LocalDate.of(anoc, mesc, diac);

				System.out.println("Informe a nova hora de chegada: ");
				int horaC, minutosC;
				System.out.print("  Hora						  : ");
				horaC = ent.nextInt();
				System.out.print("  Minuto						  : ");
				minutosC = ent.nextInt();
				horaPartida = LocalTime.of(horaC, minutosC);

				System.out.println("Lotação            		      : " + aviao.getCapacPassageiros());
				lotacao = aviao.getCapacPassageiros();

				System.out.println("Novo Peso de carga embarcada  : ");
				pesoCargaEmbarcada = ent.nextDouble();

				System.out.println("Novo Preçoo da viagem		  : $ ");
				precoViagem = ent.nextDouble();

				i++;

				novoVoo = new Voo(identificacao, aviao, aeroportoPartida, dataPartida, horaPartida, aeroportoChegada,
						dataChegada, horaChegada, lotacao, pesoCargaEmbarcada, precoViagem);

				voos.set(posi, novoVoo);

				System.out.println("       *****==[Voo Alterado!]==*****");

			} else {
				System.out.println("       *****==[Alteração não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[Voo Inexistente!]==*****");
		}
	}

	public void excluir() {
		
		int posi = 0;
		boolean existe = true;

		System.out.println("*****==[   Módulo de Exclusão de Voo   ]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("----------------------------------------------");
		System.out.println("Qual voo deseja excluir ? (Informe a posição)");
		String ident = ent.next();

		for(int i = 0; i < voos.size(); i++){

			if(voos.get(i).getIdentificacao().equals(ident)){
				posi = i;
				i = voos.size() + 1;
				existe = true;
			}else{
				existe = false;
			}
		}

		if ((!voos.isEmpty()) && existe ) {

			voos.get(posi).imprimir();

			System.out.println("Deseja excluir esse voo ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				voos.remove(posi);
				System.out.println("       *****==[Voo Excluído!]==*****");

			} else {
				System.out.println("       *****==[Exclusão não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[Voo Inexistente!]==*****");
		}
	}

	public void consultar() {
		
		int posi = 0;
		boolean existe = true;

		System.out.println("*****==[   Módulo de Consulta de Voo   ]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("----------------------------------------------");
		System.out.println("Qual voo deseja consultar ? ");
		String ident = ent.next();

		for(int i = 0; i < voos.size(); i++){

			if(voos.get(i).getIdentificacao().equals(ident)){
				posi = i;
				i = voos.size() + 1;
				existe = true;
			}else{
				existe = false;
			}
		}

		if ((!voos.isEmpty()) && existe ) {

			voos.get(posi).imprimir();

		} else {
			System.out.println("       *****==[Voo Inexistente!]==*****");
		}
	}

	public void imprimir() {

		if (!voos.isEmpty()) {
			
			for (Voo voo : voos){
				voo.imprimir();
				System.out.println("------------------------");
			}

		} else {
			System.out.println("       *****==[Não Existem Voos Cadastrados!]==*****");
		}
	}

}
