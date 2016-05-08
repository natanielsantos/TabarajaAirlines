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
		System.out.println("Identificação             : " + identificacao);
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

		System.out.println("       *****==[Avião Cadastrado com Sucesso!!]==*****");

	}

	public void alterar() {

		System.out.println("*****==[Módulo de Alteração de Aviões]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("---------------------------------------------");
		System.out.println("Qual a posição do avião deseja alterar ? ");
		int posi = ent.nextInt();

		if ((!avioes.isEmpty()) && (posi >= 0) && (posi <= avioes.size())) {

			avioes.get(posi).imprimir();

			System.out.println("Deseja alterar os dados deste avião ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				Aviao novoAviao;

				identificacao = "AV-" + posi;

				System.out.println("-------------------------------------------------");
				System.out.println("Identificação                            : " + identificacao);
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

				System.out.println("       *****==[Avião Alterado com Sucesso!]==*****");

			} else {
				System.out.println("       *****==[Alteração não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[Avião Inexistente!]==*****");
		}
	}

	public void excluir() {

		System.out.println("*****==[Módulo de Exclusão de Aviões]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("---------------------------------------------");
		System.out.println("Qual a posição do Avião deseja excluir ? ");
		int posi = ent.nextInt();

		if ((!avioes.isEmpty()) && (posi >= 0) && (posi <= avioes.size())) {

			avioes.get(posi).imprimir();

			System.out.println("Deseja excluir esse Avião ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				avioes.remove(posi);
				System.out.println("       *****==[Avião Excluído com Sucesso!]==*****");

			} else {
				System.out.println("       *****==[Exclusão não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[Avião Inexistente!]==*****");
		}
	}

	public void consultar() {

		System.out.println("*****==[Módulo de Consulta de Aviões]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("---------------------------------------------");
		System.out.println("Qual a posição deseja consultar ? ");
		int posi = ent.nextInt();

		if ((!avioes.isEmpty()) && (posi >= 0) && (posi <= avioes.size())) {

			avioes.get(posi).imprimir();

		} else {
			System.out.println("       *****==[Avião Inexistente!]==*****");
		}
	}

	public void imprimir() {

		if (!avioes.isEmpty()) {
			for (int i = 0; i < avioes.size(); i++) {

				System.out.println("---------=======---------");
				avioes.get(i).imprimir();
				System.out.println("---------=======---------");
			}

		} else {
			System.out.println("       *****==[Não existe Aviões Cadastrados!]==*****");
		}
	}
}
