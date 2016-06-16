package controller;

import DAO.*;
import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaHelicoptero {

    private Scanner ent = new Scanner(System.in);
    private final HelicopteroDAO HBd = new HelicopteroDAO();

    public GerenciaHelicoptero() {

    }

    public void cadastrar() {

        Helicoptero hel = new Helicoptero();

        try {
            System.out.println("==Inserção de Helicoptero==");
            System.out.println("Digite os dados do novo helicoptero: \n");
            System.out.println("Identificação :");
            hel.setIdentificacao(ent.nextLine());
            System.out.println("Modelo: ");
            hel.setModelo(ent.nextLine());
            System.out.println("Capacidade de Passageiro: ");
            hel.setCapacPassageiros(ent.nextInt());
            ent.nextLine();
            System.out.println("Capacidade de Carga: ");
            hel.setCapacCarga(ent.nextDouble());
            ent.nextLine();
            System.out.println("Quantidade de Helices: ");
            hel.setQtdHelices(ent.nextInt());
            ent.nextLine();
            HBd.inserirNoBanco(hel);
            System.out.println("\nNovo helicoptero cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        String cod;

        System.out.println("==== Alteração de helicopteros  ====");
        System.out.println("Qual o código do helicoptero que você deseja alterar? ");
        cod = ent.nextLine();

        Helicoptero hel = HBd.consultar(cod);

        if (hel != null) {
            System.out.println("==== Dados do helicoptero =====");
            hel.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ent.nextInt();
            ent.nextLine();
            if (resp == 1) {
                        
                        System.out.println("\nDigite os novos dados do helicoptero: ");
                        System.out.println("Modelo: ");
                        hel.setModelo(ent.nextLine());
                        System.out.println("Capacidade de Passageiro: ");
                        hel.setCapacPassageiros(ent.nextInt());
                        ent.nextLine();
                        System.out.println("Capacidade de Carga: ");
                        hel.setCapacCarga(ent.nextDouble());
                        ent.nextLine();
                        System.out.println("Quantidade de Helices: ");
                        hel.setQtdHelices(ent.nextInt());
                        ent.nextLine();
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
        cod = ent.nextLine();

        Helicoptero hel = HBd.consultar(cod);

        if (hel != null) {
            System.out.println("===== Dados do helicoptero =====");
            hel.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ent.nextInt();
            ent.nextLine();

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
        cod = ent.nextLine();

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
