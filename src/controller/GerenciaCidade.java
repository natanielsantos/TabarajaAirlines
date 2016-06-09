package controller;

import DAO.CidadeDAO;
import model.Cidade;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaCidade {

    private int resp;
    private Scanner ent = new Scanner(System.in);
    
    private final CidadeDAO CidBd = new CidadeDAO();

    public GerenciaCidade() {
    }

    public void cadastrar() {
        String nome;
        long cod;
        Cidade cid = new Cidade();

        try {
            System.out.println("==Inserção de Cidades==");
            System.out.println("Digite os dados da nova cidade: \n");
            System.out.println("Identificação:");
            cid.setIdentificacao(ent.nextInt());
            ent.nextLine();
            System.out.println("Município: ");
            cid.setNome(ent.nextLine());
            System.out.println("País: ");
            cid.setPais(ent.nextLine());
            System.out.println("Estado: ");
            cid.setEstado(ent.nextLine());
        
            CidBd.inserirNoBanco(cid);
            System.out.println("\nNova cidade cadastrada com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        int cod;
        String nome;

        System.out.println("==== Alteração de cidades  ====");
        System.out.println("Qual o código da cidade que você deseja alterar? ");
        cod = ent.nextInt();
        ent.nextLine();

        Cidade cid = CidBd.consultar(cod);

        if (cid != null) {
            System.out.println("==== Dados da cidade =====");
            cid.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ent.nextInt();
            ent.nextLine();
            if (resp == 1) {
                System.out.println("Digite os  novos dados da cidade: \n");
                System.out.println("Município: ");
                cid.setNome(ent.nextLine());
                System.out.println("País: ");
                cid.setPais(ent.nextLine());
                System.out.println("Estado: ");
                cid.setEstado(ent.nextLine());
                
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
        int cod;
        int resp;

        System.out.println("==== Exclusão de cidadess ====");

        System.out.println("Qual o código da Cidade que você deseja excluir? ");
        cod = ent.nextInt();
        ent.nextLine();

        Cidade cid = CidBd.consultar(cod);

        if (cid != null) {
            System.out.println("===== Dados da cidade =====");
            cid.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ent.nextInt();
            ent.nextLine();

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
        int cod;

        System.out.println("==== Consulta de cidades ====");

        System.out.println("Qual o código da cidade que você deseja consultar? ");
        cod = ent.nextInt();
        ent.nextLine();

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
