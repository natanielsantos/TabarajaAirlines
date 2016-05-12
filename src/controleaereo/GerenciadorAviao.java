package controleaereo;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorAviao {

	Scanner ent;
	private Aviao aviao;
	private ArrayList<Aviao> avioes;

	String modelo;
	String identificacao;
	private int i = 0;
	private int qtdTurbinas;
	private int capacPassageiros;
	private double capCarga;

	public GerenciadorAviao(ArrayList<Aviao> avs) {
		avioes = avs;
		ent = new Scanner(System.in);
	}

	public void cadastrar() {

		identificacao = "AV-" + i;

		System.out.println("*****==[Módulo de Cadastro de Aviões]==*****");
		System.out.println("        *****==[Versão 1.1]==*****");
		System.out.println("--------------------------------------------");
		System.out.println("Identificação             : ");
		identificacao = ent.next();
		System.out.println("Modelo                    : ");
		modelo = ent.next();
		System.out.println("Quantidade de Turbinas    : ");
		qtdTurbinas = ent.nextInt();
		System.out.println("Capacidade de Passageiros : ");
		capacPassageiros = ent.nextInt();
		System.out.println("Capacidade de Carga       : ");
		capCarga = ent.nextDouble();

		i++;

		aviao = new Aviao(identificacao, modelo, qtdTurbinas, capacPassageiros, capCarga);

		avioes.add(aviao);

		System.out.println("       *****==[AVIÃO Cadastrado com Sucesso!!]==*****");

	}

	public void alterar() {
		
		int posi = 0;
		boolean existe = true;

		System.out.println("*****==[Módulo de Alteração de Aviões]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("---------------------------------------------");
		System.out.println("Qual a posição do avião deseja alterar ? ");
		String ident = ent.next();
		
		for(int i = 0; i < avioes.size(); i++){

			if(avioes.get(i).getIdentificacao().equals(ident)){
				posi = i;
				i = avioes.size() + 1;
				existe = true;
			}else{
				existe = false;
			}
		}

		if ((!avioes.isEmpty()) && existe) {
			
			avioes.get(posi).imprimir();

			System.out.println("Deseja alterar os dados deste avião ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				Aviao novoAviao;

				System.out.println("-------------------------------------------------");
				System.out.println("Identificação                            : ");
				identificacao = ent.next();
				System.out.println("Informe o novo Modelo                    : ");
				modelo = ent.next();
				System.out.println("Informe a nova quantidade de Turbinas    : ");
				qtdTurbinas = ent.nextInt();
				System.out.println("Informe a nova quantidade de Passageiros : ");
				capacPassageiros = ent.nextInt();
				System.out.println("Informe a nova Capacidade de Carga       : ");
				capCarga = ent.nextDouble();

				i++;

				novoAviao = new Aviao(identificacao, modelo, qtdTurbinas, capacPassageiros, capCarga);

				avioes.set(posi, novoAviao);

				System.out.println("       *****==[AVIÃO Alterado com Sucesso!]==*****");

			} else {
				System.out.println("       *****==[Alteração não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[ Este AVIÃO não existe]==*****");
		}
	}

	public void excluir() {
		
		int posi = 0;
		boolean existe = true;

		System.out.println("*****==[Módulo de Exclusão de Aviões]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("---------------------------------------------");
		System.out.println("Qual a identificação do AVIÃO que deseja excluir? ");
		
		String ident = ent.next();

		for(int i = 0; i < avioes.size(); i++){

			if(avioes.get(i).getIdentificacao().equals(ident)){
				posi = i;
				i = avioes.size() + 1;
				existe = true;
			}else{
				existe = false;
			}
		}

		if ((!avioes.isEmpty()) && existe ) {

			avioes.get(posi).imprimir();

			System.out.println("Deseja excluir esse AVIÃO ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				avioes.remove(posi);
				System.out.println("       *****==[AVIÃO Excluído com Sucesso!]==*****");

			} else {
				System.out.println("       *****==[Exclusão não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[Este AVIÃO existe!]==*****");
		}
	}

	public void consultar() {
		
		int posi = 0;
		boolean existe = true;

		System.out.println("*****==[Módulo de Consulta de Aviões]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("---------------------------------------------");
		System.out.println("Qual a identificação do AVIÃO que deseja consultar ? ");
		String ident = ent.next();

		for(int i = 0; i < avioes.size(); i++){

			if(avioes.get(i).getIdentificacao().equals(ident)){
				posi = i;
				i = avioes.size() + 1;
				existe = true;
			}else{
				existe = false;
			}
		}


		if ((!avioes.isEmpty()) && existe ) {

			avioes.get(posi).imprimir();

		} else {
			System.out.println("       *****==[Este AVIÃO não existe!]==*****");
		}
	}

	public void imprimir() {

		if (!avioes.isEmpty()) {
		
			for (Aviao av : avioes){
				av.imprimir();
				System.out.println("------------------------------");
			}

		} else {
			System.out.println("       *****==[Não existem AVIÕES Cadastrados!]==*****");
		}
	}
}
