package view;

import java.util.Scanner;

public class Menu {
	
	Relogio relogio = new Relogio();
	Thread t = new Thread(relogio);
	
	public void telaInicial(){
		
		Scanner ent = new Scanner(System.in);
		//t.start();
		
		System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("//                    TABAJARA AIR LINES                    //");
		System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("//               CONTROLE DE PASSAGENS AEREAS               //");
		System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("//                                                           //");
		System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("                 Pressione [ENTER] para entrar!            ");
		ent.nextLine();
	}
	
	public int menuPrincipal() {
		Scanner ent = new Scanner(System.in);
		int op, ops;

		System.out.println("              **********======[Módulo Gerenciador]=====**********");
		System.out.println("                     *********====[Versão 1.2]===*********");
		System.out.println("              ---------------------------------------------------");
		System.out.println("                           1 - Cliente   ");
		System.out.println("                           2 - Piloto   ");
		System.out.println("                           3 - Cidade   ");
		System.out.println("                           4 - Aeroporto ");
		System.out.println("                           5 - Aeronave");
		System.out.println("                           6 - Voo  ");
		System.out.println("                           7 - Passagem ");
		System.out.println("                           0 - Sair                   ");
		System.out.println("              ---------------------------------------------------");

		System.out.println("                           Opção:                ");

		op = ent.nextInt();

		return op;
	}

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

		System.out.println("                                Opção:                ");
		op = ent.nextInt();

		return op;
	}
	
	public static int menuPassagem(String txt) {
		Scanner ent = new Scanner(System.in);
		int op, ops;

		System.out.println("               **********======[Módulo Gerenciador]=====**********" + txt + " ");
		System.out.println("                      *********====[Versão 1.2]===*********");
		System.out.println("              ---------------------------------------------------");
		System.out.println("                                1 - Venda");
		System.out.println("                                2 - Cancelamento de passagem");
		System.out.println("                                3 - Lista de Passageiros em um voo");
		System.out.println("                                4 - Lista de Passageiros por voo e valor pago ");
		System.out.println("                                5 - Carga disponível em um voo ");
		System.out.println("                                6 - Lugares disponíveis em um voo ");
		System.out.println("                                7 - Valor que a empresa deixará de ganhar ");
		System.out.println("                                8 - Total arrecadado ");
		System.out.println("                                0 - Menu Principal");
		System.out.println("                                Opção:");
		op = ent.nextInt();

		return op;
	}
	
	public static int menuAeronave(String txt){
			Scanner ent = new Scanner(System.in);
			int op, ops;

			System.out.println("               **********======[Módulo Gerenciador]=====**********" + txt + " ");
			System.out.println("                      *********====[Versão 1.2]===*********");
			System.out.println("              ---------------------------------------------------");
			System.out.println("                                1 - Avião");
			System.out.println("                                2 - Carro");
			System.out.println("                                3 - Helicóptero");
			System.out.println("                                0 - Menu Principal");

			System.out.println("                                Opção:");
			op = ent.nextInt();

			return op;
	}


}
