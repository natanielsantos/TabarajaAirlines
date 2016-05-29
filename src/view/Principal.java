package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.GerenciaAeroporto;
import controller.GerenciaAviao;
import controller.GerenciaCliente;
import controller.GerenciaVoo;

import model.Aeroporto;
import model.Aviao;
import model.Cliente;
import model.Passagem;
import model.Voo;

public class Principal {

	public static void main(String args[]) {

		Scanner ent = new Scanner(System.in);
		int op = 0;

		ArrayList<Cliente> cls = new ArrayList<>();
		ArrayList<Aviao> avs = new ArrayList<>();
		ArrayList<Aeroporto> aero = new ArrayList<>();
		ArrayList<Voo> vo = new ArrayList<>();
		ArrayList<Passagem> psg = new ArrayList<>();

		GerenciaCliente gc = new GerenciaCliente();
		GerenciaAviao ga = new GerenciaAviao();
		//GerenciaAviao ga = new GerenciaAviao(avs);
		GerenciaAeroporto gae = new GerenciaAeroporto(aero);
		GerenciaVoo gvo = new GerenciaVoo();
		//GerenciaVoo gvo = new GerenciaVoo(vo, avs, aero);
		//GerenciaPassagem gps = new GerenciaPassagem();
		//GerenciaPassagem gps = new GerenciaPassagem(vo, cls, psg);
		
		Menu menus = new Menu();
		
		menus.telaInicial();

		do {

			op = menus.menuPrincipal();

			switch (op) {
			case 1:

				int ops;
				do {
					ops = menus.menu("Clientes");
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
						gc.relatorio();
						break;
					default:

					}
				} while (ops != 0);

				break;

			case 2:

				int opsA;
				do {
					opsA = menus.menu("Aviões");
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
					opsAe = menus.menu("Aeroportos");
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
					opsVo = menus.menu("Voos");
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
					opsPs = menus.menuPassagem("Passagens");
					System.out.println(opsPs);
					switch (opsPs) {

					case 1:
						gps.venda();
						break;

					case 2:
						gps.cancelar();
						break;

					case 3:
						gps.relatorios();
						break;

					default:
						System.out.println("Opção selecionada inexistente. Por favor, escolha uma opção! ");

					}
				} while (opsPs != 0);

				break;

			}

		} while (op != 0);
     
	for(int i=0; i<2; i++)
		System.out.println("\n");
	
	 System.out.println("Sistema encerrado! Obrigado pela visista!");

	}

}
