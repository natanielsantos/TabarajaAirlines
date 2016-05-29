package controller;

import DAO.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaHelicoptero {

    private int resp;
    private Scanner ler = new Scanner(System.in);
    private final HelicopteroBD HBd = new HelicopteroBD();

    public GerenciaHelicoptero() {

    }

    public void cadastro() {
        String nome;
        long cod;
        Helicoptero hel = new Helicoptero();

        try {
            System.out.println("==Inserção de Helicoptero==");
            System.out.println("Digite os dados do novo helicoptero: \n");
            System.out.println("Identificação :");
            hel.setIdentificacao(ler.nextLine());
            System.out.println("Modelo: ");
            hel.setModelo(ler.nextLine());
            System.out.println("Capacidade de Passageiro: ");
            hel.setCapacPassageiros(ler.nextInt());
            ler.skip("\n");
            System.out.println("Capacidade de Carga: ");
            hel.setCapacCarga(ler.nextDouble());
            ler.skip("\n");
            System.out.println("Quantidade de Helices: ");
            hel.setQtdHelices(ler.nextInt());
            ler.skip("\n");
            HBd.inserirNoBanco(hel);
            System.out.println("\nNovo helicoptero cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        String cod;
        String nome;

        System.out.println("==== Alteração de helicopteros  ====");
        System.out.println("Qual o código do helicoptero que você deseja alterar? ");
        cod = ler.nextLine();
        ler.skip("\n");

        Helicoptero hel = HBd.consultar(cod);

        if (hel != null) {
            System.out.println("==== Dados do helicoptero =====");
            hel.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ler.nextInt();
            ler.skip("\n");
            if (resp == 1) {
                        
                        System.out.println("\nDigite os novos dados do helicoptero: ");
                        System.out.println("Modelo: ");
                        hel.setModelo(ler.nextLine());
                        System.out.println("Capacidade de Passageiro: ");
                        hel.setCapacPassageiros(ler.nextInt());
                        ler.skip("\n");
                        System.out.println("Capacidade de Carga: ");
                        hel.setCapacCarga(ler.nextDouble());
                        ler.skip("\n");
                        System.out.println("Quantidade de Helices: ");
                        hel.setQtdHelices(ler.nextInt());
                        ler.skip("\n");
                try {
                    HBd.alterarNoBanco(hel);
                    System.out.println("Alteração efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Alteração não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Cancelado com sucesso!!");
            }

        } else {
            System.out.println("Helicoptero inexistente!!");
        }
    }

    public void excluir() {
        String cod;
        int resp;

        System.out.println("==== Exclusão de helicopteros ====");

        System.out.println("Qual o código do helicoptero que você deseja excluir? ");
        cod = ler.nextLine();
        ler.skip("\n");

        Helicoptero hel = HBd.consultar(cod);

        if (hel != null) {
            System.out.println("===== Dados do helicoptero =====");
            hel.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ler.nextInt();
            ler.skip("\n");

            if (resp == 1) {
                try {
                    HBd.excluirDoBanco(hel);
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

        System.out.println("==== Consulta de helicopteros ====");

        System.out.println("Qual o código do helicopterp que você deseja consultar? ");
        cod = ler.nextLine();
        ler.skip("\n");

        Helicoptero hel = HBd.consultar(cod);

        if (hel != null) {
            System.out.println("===== Dados do helicoptero =====");
            hel.consultar();
        } else {
            System.out.println("Não existe helicoptero com este código.");
        }
    }

    public void relatorio() {

        ArrayList<Helicoptero> helis = new ArrayList<>();

        System.out.println("==== Relatório de helicopteros ====");

        try {
            helis = HBd.relatorio();

            if (helis != null) {
                System.out.println("===== Lista de helicopteros =====");
                for (Helicoptero c : helis) {
                    c.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem helicopteros cadastradas.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
