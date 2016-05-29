package controller;

import DAO.CidadeBD;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaCidade {

    private int resp;
    private Scanner ler = new Scanner(System.in);
    ;
    private final CidadeBD CidBd = new CidadeBD();

    public GerenciaCidade() {
    }

    public void cadastro() {
        String nome;
        long cod;
        Cidade cid = new Cidade();

        try {
            System.out.println("==Inserção de Cidades==");
            System.out.println("Digite os dados da nova cidade: \n");
            System.out.println("Identificação:");
            cid.setIdentificacao(ler.nextInt());
            ler.skip("\n");
            System.out.println("País: ");
            cid.setPais(ler.nextLine());
            System.out.println("Estado: ");
            cid.setEstado(ler.nextLine());
            System.out.println("Nome: ");
            cid.setNome(ler.nextLine());
            CidBd.inserirNoBanco(cid);
            System.out.println("\nNova cidade cadastrada com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        long cod;
        String nome;

        System.out.println("==== Alteração de cidades  ====");
        System.out.println("Qual o código da cidade que você deseja alterar? ");
        cod = ler.nextLong();
        ler.skip("\n");

        Cidade cid = CidBd.consultar(cod);

        if (cid != null) {
            System.out.println("==== Dados da cidade =====");
            cid.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ler.nextInt();
            ler.skip("\n");
            if (resp == 1) {
                System.out.println("Digite os  novos dados da cidade: \n");
                System.out.println("País: ");
                cid.setPais(ler.nextLine());
                System.out.println("Estado: ");
                cid.setEstado(ler.nextLine());
                System.out.println("Nome: ");
                cid.setNome(ler.nextLine());
                try {
                    CidBd.alterarNoBanco(cid);
                    System.out.println("Alteração efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Alteração não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Cancelado com sucesso!!");
            }

        }else{
            System.out.println("Cidade inexistente!!");
        }
    }

    public void excluir() {
        long cod;
        int resp;

        System.out.println("==== Exclusão de cidadess ====");

        System.out.println("Qual o código da Cidade que você deseja excluir? ");
        cod = ler.nextInt();
        ler.skip("\n");

        Cidade cid = CidBd.consultar(cod);

        if (cid != null) {
            System.out.println("===== Dados da cidade =====");
            cid.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ler.nextInt();
            ler.skip("\n");

            if (resp == 1) {
                try {
                    CidBd.excluirDoBanco(cid);
                    System.out.println("Exclusão efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Exclusão não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Exclusão não efetuada.");
            }
        }

    }

    public void consultar() {
        long cod;

        System.out.println("==== Consulta de cidades ====");

        System.out.println("Qual o código da cidade que você deseja consultar? ");
        cod = ler.nextLong();
        ler.skip("\n");

        Cidade cid = CidBd.consultar(cod);

        if (cid != null) {
            System.out.println("===== Dados da cidade =====");
            cid.consultar();
        } else {
            System.out.println("Não existe cidade com este código.");
        }
    }

    public void relatorio() {

        ArrayList<Cidade> cidades = new ArrayList<>();

        System.out.println("==== Relatório de Cidades ====");

        try {
            cidades = CidBd.relatorio();

            if (cidades != null) {
                System.out.println("===== Lista de cidades =====");
                for (Cidade c : cidades) {
                    c.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem cidades cadastradas.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
