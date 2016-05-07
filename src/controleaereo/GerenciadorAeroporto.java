package controleaereo;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorAeroporto {

	Scanner ent;
	private Aeroporto aeroporto;
	private ArrayList<Aeroporto> aeroportos;
	private String identificacao;
	private String nome;
	private String municipio;
	private String estado;
	private String pais;

	int i = 0;

	public GerenciadorAeroporto(ArrayList<Aeroporto> aero) {
		aeroportos = aero;
		ent = new Scanner(System.in);
	}

	public void cadastrar() {

		identificacao = "AERO-" + i;

		System.out.println("*****==[Módulo de Cadastro de Aeroportos]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("------------------------------------------------");
		System.out.println("Identificação     : " + identificacao);
		System.out.println("Nome              : ");
		nome = ent.next();
		System.out.println("Município         : ");
		municipio = ent.next();
		System.out.println("Estado            : ");
		estado = ent.next();
		System.out.println("País              : ");
		pais = ent.next();

		i++;

		aeroporto = new Aeroporto(identificacao, nome, municipio, estado, pais);

		aeroportos.add(aeroporto);

		System.out.println("       *****==[Aeroporto Cadastrado com Sucesso!!]==*****");

	}

	public void alterar() {

		System.out.println("*****==[Módulo de Alteração de Aeroportos]==*****");
		System.out.println("           *****==[Versão 1.1]==*****");
		System.out.println("-------------------------------------------------");
		System.out.println("Qual aeroporto deseja alterar? (Informe a posição)");
		int posi = ent.nextInt();

		if ((!aeroportos.isEmpty()) && (posi >= 0) && (posi <= aeroportos.size())) {

			aeroportos.get(posi).imprimir();

			System.out.println("Deseja alterar os dados deste aeroporto ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				Aeroporto novoAeroporto;

				identificacao = "AERO-" + posi;

				System.out.println("-------------------------------------------");
				System.out.println("Identificação            : " + identificacao);
				System.out.println("Informe o novo nome      : ");
				nome = ent.next();
				System.out.println("Informe o novo Município : ");
				municipio = ent.next();
				System.out.println("Informe o novo Estado    : ");
				estado = ent.next();
				System.out.println("Informe o novo País      : ");
				pais = ent.next();

				i++;

				novoAeroporto = new Aeroporto(identificacao, nome, municipio, estado, pais);

				aeroportos.set(posi, novoAeroporto);

				System.out.println("       *****==[Aeroporto Alterado com Sucesso!!]==*****");

			} else {
				System.out.println("       *****==[Alteração não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[Aeroporto Inexistente!]==*****");
		}
	}

	public void excluir() {

		System.out.println("*****==[Módulo de Exclusão de Aeroportos]==*****");
		System.out.println("           *****==[Versão 1.1]==*****");
		System.out.println("------------------------------------------------");
		System.out.println("Qual aeroporto deseja excluir ? (Informe a posição)");
		int posi = ent.nextInt();

		if ((!aeroportos.isEmpty()) && (posi >= 0) && (posi <= aeroportos.size())) {

			aeroportos.get(posi).imprimir();

			System.out.println("Deseja excluir esse aeroporto ? (1 - Sim / 2 - Não)");
			int resp = ent.nextInt();

			if (resp == 1) {

				aeroportos.remove(posi);

				System.out.println("       *****==[Aeroporto Excluído com Sucesso!!]==*****");

			} else {
				System.out.println("       *****==[Exclusão não Efetuada!]==*****");
			}

		} else {
			System.out.println("       *****==[Aeroporto Inexistente!]==*****");
		}
	}

	public void consultar() {

		System.out.println("*****==[Módulo de Consulta de Aeroportos]==*****");
		System.out.println("          *****==[Versão 1.1]==*****");
		System.out.println("------------------------------------------------");
		System.out.println("Qual a posição deseja consultar ?   ");
		int posi = ent.nextInt();

		if ((!aeroportos.isEmpty()) && (posi >= 0) && (posi <= aeroportos.size())) {

			aeroportos.get(posi).imprimir();

		} else {
			System.out.println("       *****==[Aeroporto Inexistente!]==*****");
		}
	}

	public void imprimir() {

		if (!aeroportos.isEmpty()) {
			for (int i = 0; i < aeroportos.size(); i++) {

				System.out.println("---------=======---------");
				aeroportos.get(i).imprimir();
				System.out.println("---------=======---------");
			}

		} else {
			System.out.println("       *****==[Nenhum Aeroporto Cadastrado! ]==*****");
		}
	}
}
