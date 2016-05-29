package controller;

import DAO.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaAviao {

    private int resp;
    private Scanner ler = new Scanner(System.in);
    private final AviaoBD ABd = new AviaoBD();

    public GerenciaAviao() {
    }

    public void cadastro() {
        String nome;
        long cod;
        Aviao avi = new Aviao();
        try {
            System.out.println("==Inserção de Avião==");
            System.out.println("Identificação :");
            avi.setIdentificacao(ler.nextLine());
            System.out.println("Digite os dados do novo avião: \n");
            System.out.println("Modelo: ");
            avi.setModelo(ler.nextLine());
            System.out.println("Capacidade de Passageiro: ");
            avi.setCapacPassageiros(ler.nextInt());
            ler.skip("\n");
            System.out.println("Capacidade de Carga: ");
            avi.setCapacCarga(ler.nextDouble());
            ler.skip("\n");
            System.out.println("Quantidade de Turbinas: ");
            avi.setQtdTurbinas(ler.nextInt());
            ler.skip("\n");
            System.out.println("Capacidade de Combustível Por Turbina:");
            avi.setCapcCombustPorTurbina(ler.nextFloat());
            ler.skip("\n");
            ABd.inserirNoBanco(avi);
            System.out.println("\nNovo avião cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        long cod;
        String nome;

        System.out.println("==== Alteração de aviões  ====");
        System.out.println("Qual o código do avião que você deseja alterar? ");
        cod = ler.nextLong();
        ler.skip("\n");

        Aviao avi = ABd.consultar(cod);

        if (avi != null) {
            System.out.println("==== Dados do avião =====");
            avi.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ler.nextInt();
            ler.skip("\n");
            if (resp == 1) {
                System.out.println("\nDigite os novos dados do avião: ");
                System.out.println("Modelo: ");
                avi.setModelo(ler.nextLine());
                System.out.println("Capacidade de Passageiro: ");
                avi.setCapacPassageiros(ler.nextInt());
                ler.skip("\n");
                System.out.println("Capacidade de Carga: ");
                avi.setCapacCarga(ler.nextDouble());
                ler.skip("\n");
                System.out.println("Quantidade de Turbinas: ");
                avi.setQtdTurbinas(ler.nextInt());
                ler.skip("\n");
                System.out.println("Capacidade de Combustível Por Turbina:");
                avi.setCapcCombustPorTurbina(ler.nextFloat());
                ler.skip("\n");

                try {
                    ABd.alterarNoBanco(avi);
                    System.out.println("Alteração efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Alteração não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Cancelado com sucesso!!");
            }

        } else {
            System.out.println("avião inexistente!!");
        }
    }

    public void excluir() {
        long cod;
        int resp;

        System.out.println("==== Exclusão de aviões ====");

        System.out.println("Qual o código do avião que você deseja excluir? ");
        cod = ler.nextInt();
        ler.skip("\n");

        Aviao avi = ABd.consultar(cod);

        if (avi != null) {
            System.out.println("===== Dados do avião =====");
            avi.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ler.nextInt();
            ler.skip("\n");

            if (resp == 1) {
                try {
                    ABd.excluirDoBanco(avi);
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

        System.out.println("==== Consulta de avioes ====");

        System.out.println("Qual o código do avião que você deseja consultar? ");
        cod = ler.nextLong();
        ler.skip("\n");

        Aviao av = ABd.consultar(cod);

        if (av != null) {
            System.out.println("===== Dados do avião =====");
            av.consultar();
        } else {
            System.out.println("Não existe avião com este código.");
        }
    }

    public void relatorio() {

        ArrayList<Aviao> avi = new ArrayList<>();

        System.out.println("==== Relatório de aviões ====");

        try {
            avi = ABd.relatorio();

            if (avi != null) {
                System.out.println("===== Lista de avioes =====");
                for (Aviao c : avi) {
                    c.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem avioes cadastrados.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
