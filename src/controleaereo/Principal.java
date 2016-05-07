package controleaereo;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static int menu(String txt) {
		Scanner ent = new Scanner(System.in);
		int op, ops;

		System.out.println("               **********======[Módulo Gerenciador]=====**********" + txt + " ");
		System.out.println("                      *********====[Versão 1.2]===*********");
		System.out.println("              ---------------------------------------------------");
		System.out.println("                                1 - Cadastrar           ");
		System.out.println("                                2 - Alterar             ");
		System.out.println("                                3 - Excluir             ");
		System.out.println("                                4 - Consultar           ");
		System.out.println("                                5 - Relatório           ");
		System.out.println("                                0 - Menu Principal      ");

		System.out.println("                                Opção  :                ");
		op = ent.nextInt();

		return op;
	}

	public static void main(String args[]) {

		Scanner ent = new Scanner(System.in);
		int op = 0;

		ArrayList<Cliente> cls = new ArrayList();
		ArrayList<Aviao> avs = new ArrayList();
		ArrayList<Aeroporto> aero = new ArrayList();
		ArrayList<Voo> vo = new ArrayList();
		ArrayList<Passagem> psg = new ArrayList();

		GerenciadorCliente gc = new GerenciadorCliente(cls);
		GerenciadorAviao ga = new GerenciadorAviao(avs);
		GerenciadorAeroporto gae = new GerenciadorAeroporto(aero);
		GerenciadorVoo gvo = new GerenciadorVoo(vo, avs, aero);
		GerenciadorPassagem gps = new GerenciadorPassagem(vo, cls, psg);

		do {

			System.out.println("              **********======[Módulo Gerenciador]=====**********");
			System.out.println("                     *********====[Versão 1.2]===*********");
			System.out.println("              ---------------------------------------------------");
			System.out.println("                           1 - Gerenciador Clientes   ");
			System.out.println("                           2 - Gerenciador Aviões     ");
			System.out.println("                           3 - Gerenciador Aeroportos ");
			System.out.println("                           4 - Gerenciador Voos       ");
			System.out.println("                           5 - Gerenciador Passagens  ");
			System.out.println("                           0 - Sair                   ");

			System.out.println("              ---------------------------------------------------");

			System.out.println("                           Opção     :                ");
			op = ent.nextInt();

			switch (op) {
			case 1:

				int ops;
				do {
					ops = menu("Clientes");
					System.out.println(ops);
					switch (ops) {

					case 1:
						gc.cadastrar();
						break;

					case 2:
						gc.alterar();
						break;

					case 3:
						gc.excluir();
						break;
					case 4:
						gc.consultar();
						break;

					case 5:
						gc.imprimir();
						break;
					default:

					}
				} while (ops != 0);

				break;

			case 2:

				int opsA;
				do {
					opsA = menu("Aviões");
					System.out.println(opsA);
					switch (opsA) {

					case 1:
						ga.cadastrar();
						break;

					case 2:
						ga.alterar();
						break;

					case 3:
						ga.excluir();
						break;
					case 4:
						ga.consultar();
						break;

					case 5:
						ga.imprimir();
						break;
					default:

					}
				} while (opsA != 0);

				break;

			case 3:
				int opsAe;
				do {
					opsAe = menu("Aeroportos");
					System.out.println(opsAe);
					switch (opsAe) {

					case 1:
						gae.cadastrar();
						break;

					case 2:
						gae.alterar();
						break;

					case 3:
						gae.excluir();
						break;
					case 4:
						gae.consultar();
						break;

					case 5:
						gae.imprimir();
						break;
					default:

					}
				} while (opsAe != 0);

				break;

			case 4:

				int opsVo;
				do {
					opsVo = menu("Voos");
					System.out.println(opsVo);
					switch (opsVo) {

					case 1:
						gvo.cadastrar();
						break;

					case 2:
						gvo.alterar();
						break;

					case 3:
						gvo.excluir();
						break;
					case 4:
						gvo.consultar();
						break;

					case 5:
						gvo.imprimir();
						break;
					default:
						System.out.println("Opção selecionada inexistente. Por favor, escolha uma opção! ");

					}
				} while (opsVo != 0);

				break;

			case 5:
				int opsPs;
				do {
					opsPs = menu("Passagens");
					System.out.println(opsPs);
					switch (opsPs) {

					case 1:
						gps.cadastrar();
						break;

					case 2:
						gps.alterar();
						break;

					case 3:
						gps.excluir();
						break;
					case 4:
						gps.consultar();
						break;

					case 5:
						gps.imprimir();
						break;

					default:
						System.out.println("Opção selecionada inexistente. Por favor, escolha uma opção! ");

					}
				} while (opsPs != 0);

				break;

			}

		} while (op != 0);

	}

}
