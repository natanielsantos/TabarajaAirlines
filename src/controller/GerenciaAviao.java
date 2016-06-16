package controller;

import DAO.*;
import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaAviao {

    private Scanner ent = new Scanner(System.in);
    private final AviaoDAO ABd = new AviaoDAO();

    public GerenciaAviao() {
    }

    public void cadastrar() {
        Aviao avi = new Aviao();
        try {
            System.out.println("==Inserção de Avião==");
            System.out.println("Identificação :");
            avi.setIdentificacao(ent.nextLine());
            System.out.println("Digite os dados do novo avião: \n");
            System.out.println("Modelo: ");
            avi.setModelo(ent.nextLine());
            System.out.println("Capacidade de Passageiro: ");
            avi.setCapacPassageiros(ent.nextInt());
            ent.nextLine();
            System.out.println("Capacidade de Carga: ");
            avi.setCapacCarga(ent.nextDouble());
            ent.nextLine();
            System.out.println("Quantidade de Turbinas: ");
            avi.setQtdTurbinas(ent.nextInt());
            ent.nextLine();
            System.out.println("Capacidade de Combustível Por Turbina:");
            avi.setCapcCombustPorTurbina(ent.nextFloat());
            ent.nextLine();
            ABd.inserirNoBanco(avi);
            System.out.println("\nNovo avião cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        String cod;

        System.out.println("==== Alteração de aviões  ====");
        System.out.println("Qual a identificacao do avião que você deseja alterar? ");
        cod = ent.nextLine();

        Aviao avi = ABd.consultar(cod);

        if (avi != null) {
            System.out.println("==== Dados do avião =====");
            avi.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ent.nextInt();
            ent.nextLine();
            if (resp == 1) {
                System.out.println("\nDigite os novos dados do avião: ");
                System.out.println("Modelo: ");
                avi.setModelo(ent.nextLine());
                System.out.println("Capacidade de Passageiro: ");
                avi.setCapacPassageiros(ent.nextInt());
                ent.nextLine();
                System.out.println("Capacidade de Carga: ");
                avi.setCapacCarga(ent.nextDouble());
                ent.nextLine();
                System.out.println("Quantidade de Turbinas: ");
                avi.setQtdTurbinas(ent.nextInt());
                ent.nextLine();
                System.out.println("Capacidade de Combustível Por Turbina:");
                avi.setCapcCombustPorTurbina(ent.nextFloat());
                ent.nextLine();

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
        String cod;
        int resp;

        System.out.println("==== Exclusão de aviões ====");

        System.out.println("Qual a identificação do avião que você deseja excluir? ");
        cod = ent.nextLine();;

        Aviao avi = ABd.consultar(cod);

        if (avi != null) {
            System.out.println("===== Dados do avião =====");
            avi.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ent.nextInt();
            ent.nextLine();

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
        String cod;

        System.out.println("==== Consulta de avioes ====");

        System.out.println("Qual a identificação do avião que você deseja consultar? ");
        cod = ent.nextLine();

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
