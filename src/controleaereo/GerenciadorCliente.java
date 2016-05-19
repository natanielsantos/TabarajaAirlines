package controleaereo;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorCliente {

	Scanner ent;
	private Cliente cliente;
	private ArrayList<Cliente> clientes;

	String nome, logradouro, bairro, numero, municipio, estado, cep, telefone;

	double cargaCliente;

	int i = 0, identificacao;

	public GerenciadorCliente(ArrayList<Cliente> cls) {
		clientes = cls;
		ent = new Scanner(System.in);
	}

	public void cadastrar() {

		int res;

		System.out.println("*****==[Módulo de Cadastro de Clientes]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");

		do {
			System.out.println("Identificação: ");
			identificacao = ent.nextInt();
			System.out.println("Digite o nome do Cliente : ");
			nome = ent.next();
			System.out.println("Logradouro               : ");
			logradouro = ent.next();
			System.out.println("Numero                   : ");
			numero = ent.next();
			System.out.println("Bairro                   : ");
			bairro = ent.next();
			System.out.println("Municipio                : ");
			municipio = ent.next();
			System.out.println("Estado                   : ");
			estado = ent.next();
			System.out.println("CEP                      : ");
			cep = ent.next();
			System.out.println("Telefone                 : ");
			telefone = ent.next();

			i++;

			cliente = new Cliente(identificacao, nome, logradouro, numero, bairro, municipio, estado, cep, telefone);

			clientes.add(cliente);

			System.out.println("       *****==[Cliente Cadastrado com Sucesso!!]==*****");

			System.out.println("Deseja cadastrar outro cliente ? (1-Sim / 2-Não)");
			res = ent.nextInt();

		} while (res != 2);
	}

	public void alterar() {

		int res;

		do {
			
			boolean existe = true;
			
			System.out.println("Qual identificação do cliente deseja alterar? ");
			int posi = ent.nextInt();
			
			for(int i = 0; i < clientes.size(); i++){
				
				if( posi == clientes.get(i).getIdentificacao()){
					posi = i;
					i = clientes.size() + 1;
				}else{
					existe = false;
				}
			}

			if (!clientes.isEmpty() && (posi >= 0) && existe ) {
				
				clientes.get(posi).imprimir();

				System.out.println("Confirma alteração ? (1 - Sim / 2 - Não) ");
				int resp = ent.nextInt();

				if (resp == 1) {

					Cliente novoCliente;

					System.out.println("Identificação                 : " + identificacao);
					System.out.println("Digite o novo nome do Cliente : ");
					nome = ent.next();
					System.out.println("Digite o novo Logradouro      : ");
					logradouro = ent.next();
					System.out.println("Digite o novo Numero          : ");
					numero = ent.next();
					System.out.println("Digite o novo Bairro          : ");
					bairro = ent.next();
					System.out.println("Digite o novo Municipio       : ");
					municipio = ent.next();
					System.out.println("Informe o Estado (UF)         : ");
					estado = ent.next();
					System.out.println("Informe o CEP                 : ");
					cep = ent.next();
					System.out.println("Digite o número do telefone   : ");
					telefone = ent.next();

					novoCliente = new Cliente(identificacao, nome, logradouro, numero, bairro, municipio, estado, cep,
							telefone);

					clientes.set(posi, novoCliente);

					System.out.println("         *****==[Alteração Efetuada com Sucesso!!]==*****");

				} else {
					System.out.println("         *****==[Alteração não Efetuada!]==*****");
				}
			} else {
				System.out.println("         *****==[Cliente Inexistente!]==*****");
			}

			System.out.println("Deseja realizar outra alteração ? (1-Sim / 2-Não)");
			res = ent.nextInt();

		} while (res != 2);
	}

	public void excluir() {

		boolean existe = true;
		
		System.out.println("Qual a identificação do cliente deseja excluir ?   ");
		int posi = ent.nextInt();
		
		for(int i = 0; i < clientes.size(); i++){
			
			if( posi == clientes.get(i).getIdentificacao()){
				posi = i;
				i = clientes.size() + 1;
			}else{
				existe = false;
			}
		}

		if (!clientes.isEmpty() && posi >= 0 && existe) {

			clientes.get(posi).imprimir(); 
			System.out.println("Deseja excluir esse cliente ? (1 - Sim / 2 - Não) ");
			int resp = ent.nextInt();

			if (resp == 1) {

				clientes.remove(posi);
				System.out.println("         *****==[Exclusão Efetuada com Sucesso!!]==*****");

			} else {
				System.out.println("         *****==[Alteração não Efetuada!]==*****");
			}

		} else {
			System.out.println("         *****==[Cliente não encontrado!]==*****");
		}
	}

	public void consultar() {

		int res;

		do {
			System.out.println("Qual a posição deseja consultar ?   ");
			int posi = ent.nextInt();

			if ((!clientes.isEmpty()) && (posi >= 0) && posi <= clientes.size()) {

				clientes.get(posi).imprimir();

			} else {
				System.out.println("         *****==[Cliente não encontrado!]==*****");
			}
			System.out.println("Deseja realizar outra consulta ? (1-Sim / 2-Não)");
			res = ent.nextInt();

		} while (res != 2);

	}

	public void imprimir() {

		if (!clientes.isEmpty()) {
			for (int i = 0; i < clientes.size(); i++) {
				System.out.println("---------=======---------");
				clientes.get(i).imprimir();
				System.out.println("--------=======----------");
			}

		} else {
			System.out.println("         *****==[Não existem clientes cadastrados!]==*****");
		}
	}
}
