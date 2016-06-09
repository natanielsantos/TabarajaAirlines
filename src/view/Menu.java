package view;

import java.util.Scanner;

public class Menu {
	
	public void telaInicial(){
		
		Scanner ent = new Scanner(System.in);
		
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
		System.out.println("                           5 - Voo       ");
		System.out.println("                           6 - Aeronave  ");
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
		System.out.println("                                2 - Cancalamento de passagem");
		System.out.println("                                3 - Relatórios ");
		System.out.println("                                0 - Menu Principal      ");

		System.out.println("                                Opção:");
		op = ent.nextInt();

		return op;
	}


}
