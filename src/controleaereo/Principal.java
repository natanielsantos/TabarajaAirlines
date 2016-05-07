package controleaereo;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static int menu(String txt) {
		Scanner ent = new Scanner(System.in);
		int op, ops;
		System.out.println(txt);
		System.out.println("1 - Cadastrar");
		System.out.println("2 - Alterar");
		System.out.println("3 - Excluir");
		System.out.println("4 - Consultar");
		System.out.println("5 - Relatorio");
		System.out.println("Voltar ao menu anterior");

		System.out.println("OPÇÃO: ");
		op = ent.nextInt();

		return op;
	}

	public static void main(String args[]) {

		Scanner ent = new Scanner(System.in);
		int op = 0;

		GerenciaCliente gr = new GerenciaCliente();

		ArrayList<Cliente> cls = new ArrayList();// LISTA DE CLIENTES

		do {
			System.out.println("1 - Gerenciar Clientes");
			System.out.println("2 - Gerenciar Aviões");
			System.out.println("3 - Gerenciar Aeroportos");
			System.out.println("4 - Gerenciar Voos");
			System.out.println("5 - Gerenciar Passagens");
			System.out.println("0 - Sair");

			System.out.println("OPÇÃO: ");
			op = ent.nextInt();

			switch (op) {
			case 1: // INICIAR GERENCIAR CLIENTES

				int ops;
				do {
					ops = menu("-== Gerenciar Clientes ==-");
					System.out.println(ops);
					switch (ops) {

						case 1:// CADASTRAR CLIENTE
							Cliente cli;
							cli = gr.cadastrar();
							cls.add(cli);
							break;
	
						case 2: // ALTERAR CLIENTES
							
							System.out.println("Qual a posição do cliente deseja alterar?");
							int posi = ent.nextInt();
							
							cls.get(posi).imprimir();
							
							System.out.println("Deseja alterar os dados desse cliente? 1 - Sim / 2 - Não");
							int resp = ent.nextInt();
							
							if(resp==1){
								
								Cliente novoCliente;
								novoCliente = gr.alterar(posi);
								cls.set(posi, novoCliente);
							}
							
							break;
	
						case 3: // EXCLUIR CLIENTES
	
						case 4: // CONSULTAR CLIENTES
	
						case 5: // RELATÓRIO CLIENTES

					}
				} while (ops != 0);
				
				break;
				
			// TERMINAR GERENCIAR CLIENTE

			case 2: // AVIOES
			case 3:
			case 4:
			case 5:

			}

		} while (op != 0);

	}
}
