package controleaereo;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaCliente {
	Scanner ent = new Scanner(System.in);
	Cliente cliente;

	String nome, logradouro, bairro, numero, municipio, estado, cep, telefone;

	int i = 0, identificacao;

	public Cliente cadastrar() {
		identificacao = i;
		System.out.println("Id: " + i);
		System.out.println("Digite o nome: ");
		nome = ent.next();
		System.out.println("Logradouro: ");
		logradouro = ent.next();
		System.out.println("Numero: ");
		numero = ent.next();
		System.out.println("Bairro: ");
		bairro = ent.next();
		System.out.println("Municipio: ");
		municipio = ent.next();
		System.out.println("Estado: ");
		estado = ent.next();
		System.out.println("CEP: ");
		cep = ent.next();
		System.out.println("Telefone: ");
		telefone = ent.next();

		i++;

		cliente = new Cliente(identificacao, nome, logradouro, numero, bairro, municipio, estado, cep, telefone);

		return cliente;

	}
	
	public Cliente alterar(int posi) {
		identificacao = posi;
		System.out.println("Id: " + posi);
		System.out.println("Novo nome: ");
		nome = ent.next();
		System.out.println("Logradouro: ");
		logradouro = ent.next();
		System.out.println("Numero: ");
		numero = ent.next();
		System.out.println("Bairro: ");
		bairro = ent.next();
		System.out.println("Municipio: ");
		municipio = ent.next();
		System.out.println("Estado: ");
		estado = ent.next();
		System.out.println("CEP: ");
		cep = ent.next();
		System.out.println("Telefone: ");
		telefone = ent.next();

		cliente = new Cliente(identificacao, nome, logradouro, numero, bairro, municipio, estado, cep, telefone);

		return cliente;

	}

}
