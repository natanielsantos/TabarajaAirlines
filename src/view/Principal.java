package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.GerenciaAeroporto;
import controller.GerenciaAviao;
import controller.GerenciaCidade;
import controller.GerenciaCliente;
import controller.GerenciaPiloto;
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

		GerenciaCliente gc = new GerenciaCliente();
		GerenciaCidade gci = new GerenciaCidade();
		GerenciaPiloto gp = new GerenciaPiloto();

		//GerenciaAviao gci = new GerenciaAviao();
		//GerenciaAviao ga = new GerenciaAviao(avs);
		//GerenciaAeroporto gae = new GerenciaAeroporto(aero);
		//GerenciaVoo gvo = new GerenciaVoo();
		//GerenciaVoo gvo = new GerenciaVoo(vo, avs, aero);
		//GerenciaPassagem gps = new GerenciaPassagem();
		//GerenciaPassagem gps = new GerenciaPassagem(vo, cls, psg);
		
		Menu menus = new Menu();
		
		menus.telaInicial();

		do {

			op = menus.menuPrincipal();
			int ops;

			switch (op) {
			case 1:

				do {
					ops = menus.menu("Clientes");
					System.out.println(ops);
					
					switch (ops) {

						case 1:	gc.cadastrar();	break;
						case 2:	gc.alterar();break;
						case 3:	gc.excluir();break;
						case 4:	gc.consultar();	break;
						case 5:	gc.relatorio();	break;
						default:
					}
				}while (ops != 0);
				break;

			case 2:

				do {
					
					ops = menus.menu("Pilotos");
					System.out.println(ops);
					
					switch (ops) {
						case 1:	gp.cadastrar();	break;
						case 2:	gp.alterar();break;
						case 3:	gp.excluir(); break;
						case 4: gp.consultar();	break;
						case 5:	gp.relatorio();	break;
						default:
					}
				} while (ops != 0);

				break;

			case 3:

				do {

					ops = menus.menu("Cidades");
					System.out.println(ops);

					switch (ops) {
					case 1:	gci.cadastrar();break;
					case 2:	gci.alterar();break;
					case 3:	gci.excluir(); break;
					case 4: gci.consultar();break;
					case 5:	gci.relatorio();break;
					default:
					}
				} while (ops != 0);


				break;

			case 4:
            /*
				int opsVo;
				do {
					opsVo = menus.menu("Aeroporto");
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
				} while (opsVo != 0); */

				break;

			case 5:
				/*int opsPs;
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
				} while (opsPs != 0);*/

				break;

			}

		} while (op != 0);
     
	for(int i=0; i<2; i++)
		System.out.println("\n");
	
	 System.out.println("Sistema encerrado! Obrigado pela visista!");

	}

}
