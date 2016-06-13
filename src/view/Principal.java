package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.*;
import model.*;

public class Principal {

	public static void main(String args[]) {

		Scanner ent = new Scanner(System.in);
		int op = 0;

		GerenciaCliente gc = new GerenciaCliente();
		GerenciaCidade gci = new GerenciaCidade();
		GerenciaPiloto gp = new GerenciaPiloto();
		GerenciaAeroporto gae = new GerenciaAeroporto();
		GerenciaAviao gav = new GerenciaAviao();
		GerenciaHelicoptero ghe = new GerenciaHelicoptero();
		
		//GerenciaAviao gci = new GerenciaAviao();
		//GerenciaAviao ga = new GerenciaAviao(avs);
		
		//GerenciaVoo gvo = new GerenciaVoo();
		//GerenciaVoo gvo = new GerenciaVoo(vo, avs, aero);
		//GerenciaPassagem gps = new GerenciaPassagem();
		//GerenciaPassagem gps = new GerenciaPassagem(vo, cls, psg);
		
		Menu menus = new Menu();
		
		menus.telaInicial();

		do {

			op = menus.menuPrincipal();
			int ops, ops2=0;

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
            
				do {
					ops = menus.menu("Aeroportos");
					System.out.println(ops);
					
					switch (ops) {
					case 1:	gae.cadastrar();break;
					case 2:	gae.alterar();break;
					case 3:	gae.excluir(); break;
					case 4: gae.consultar();break;
					case 5:	gae.relatorio();break;
					default:
					}
				} while (ops != 0);

				break;

			case 5:

				do {
					ops = menus.menuAeronave("Aeronaves");
					System.out.println(ops);
					do {
						
					switch(ops){
					
					case 1:
						ops2 = menus.menu("Avião");
							switch (ops2) {
							case 1:	gav.cadastrar();break;
							case 2:	gav.alterar();break;
							case 3:	gav.excluir(); break;
							case 4: gav.consultar();break;
							case 5:	gav.relatorio();break;
							default:
						}
						break;
					case 2:
						ops2 = menus.menu("Carro");
						switch (ops2) {
						case 1:	gae.cadastrar();break;
						case 2:	gae.alterar();break;
						case 3:	gae.excluir(); break;
						case 4: gae.consultar();break;
						case 5:	gae.relatorio();break;
						default:
					}
						break;
					
					case 3:
						ops2 = menus.menu("Helicóptero");
						switch (ops2) {
						case 1:	ghe.cadastrar();break;
						case 2:	ghe.alterar();break;
						case 3:	ghe.excluir(); break;
						case 4: ghe.consultar();break;
						case 5:	ghe.relatorio();break;
						default:
					}
						break;
					}
				  }while (ops2 != 0);
					
				} while (ops != 0);

				break;

			}

		} while (op != 0);
     
	for(int i=0; i<2; i++)
		System.out.println("\n");
	
	 System.out.println("Sistema encerrado! Obrigado pela visista!");

	}

}
