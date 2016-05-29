package controller;

import DAO.AeroportoBD;
import DAO.CidadeBD;
import DAO.PilotoBD;
import model.Aeroporto;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaAeroporto {

    private final CidadeBD CidBd = new CidadeBD();
    private final GerenciaCidade gc = new GerenciaCidade();
    Scanner ent = new Scanner(System.in);
    private final AeroportoBD ABd = new AeroportoBD();
    private ArrayList<Aeroporto> aeroportos;
    
    public GerenciaAeroporto(ArrayList<Aeroporto> aero) {
		aeroportos = aero;
		ent = new Scanner(System.in);
	}

    public GerenciaAeroporto() {

    }

    public void cadastro() {
        Aeroporto aer = new Aeroporto();
        try {
            System.out.println("\n==Inserção de Aeroportos==");
            System.out.println("Digite os dados do novo aeroporto: \n");
            System.out.println("Identificação: ");
            aer.setIdentificacao(ent.nextLine());
            System.out.println("Nome do Aeroporto: ");
            aer.setNome(ent.nextLine());
            System.out.println("\nCIDADES DISPONÍVEIS.");
            System.out.println("======================================");
            gc.relatorio();
            System.out.println("======================================");
            System.out.println("\nDigite a identificação da Cidade: ");
            long aux = ent.nextLong();
            aer.setCidade(CidBd.consultar(aux));
            ent.skip("\n");
            ABd.inserirNoBanco(aer);
            System.out.println("\nNovo aeroporto cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alteracao() {
        String cod;
        String nome;

        System.out.println("==== Alteração de aeroportos  ====");

        System.out.println("Qual a identidade do aeroporto que você deseja alterar? ");
        cod = ent.nextLine();

        Aeroporto aer = ABd.consultar(cod);

        if (aer != null) {
            System.out.println("==== Dados do aeoporto =====");
            aer.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ent.nextInt();
            ent.skip("\n");
            if (resp == 1) {
                System.out.println("Digite os  novos dados do aeroporto: \n");
                System.out.println("Nome do Aeroporto: ");
                aer.setNome(ent.nextLine());
                System.out.println("\nCIDADES DISPONÍVEIS.");
                System.out.println("======================================");
                gc.relatorio();
                System.out.println("======================================");
                System.out.println("\nDigite a identificação da Cidade: ");
                long aux = ent.nextLong();
                ent.skip("\n");
                aer.setCidade(CidBd.consultar(aux));

                try {
                    ABd.alterarNoBanco(aer);
                    System.out.println("Alteração efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Alteração não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Cancelado com sucesso!!");
            }

        }
    }

    public void exclusao() {
        String cod;
        int resp;

        System.out.println("==== Exclusão de aeroportos ====");

        System.out.println("Qual a identidade do aeroporto que você deseja excluir? ");
        cod = ent.nextLine();

        Aeroporto aer = ABd.consultar(cod);

        if (aer != null) {
            System.out.println("===== Dados do Aeroporto =====");
            aer.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ent.nextInt();
            ent.skip("\n");

            if (resp == 1) {
                try {
                    ABd.excluirDoBanco(aer);
                    System.out.println("Exclusão efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Exclusão não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Exclusão não efetuada.");
            }
        }
    }

    public void consulta() {
        String cod;
        System.out.println("==== Consulta de Aeroportos ====");

        System.out.println("Qual a identidade do Aeroporto que você deseja consultar? ");
        cod = ent.nextLine();

        Aeroporto aer = ABd.consultar(cod);

        if (aer != null) {
            System.out.println("===== Dados do Aeroporto =====");
            aer.consultar();
        } else {
            System.out.println("Não existe Aeroporto com esta identidade.");
        }
    }

    public void relatorio() {
        ArrayList<Aeroporto> aer = new ArrayList<>();

        System.out.println("==== Relatório de Aeroportos ====");

        try {
            aer = ABd.relatorio();

            if (aer != null) {
                System.out.println("===== Lista de aeroportos =====");
                for (Aeroporto c : aer) {
                    c.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem aeroportos cadastradas.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
