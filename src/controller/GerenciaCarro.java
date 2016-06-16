package controller;

import DAO.*;
import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaCarro{
    private Scanner ent = new Scanner(System.in);
    private final CarroDAO ABd = new CarroDAO();
    private final PilotoDAO Pil = new PilotoDAO();
    private final CidadeDAO CidBd = new CidadeDAO();
    private final GerenciaCidade gc = new GerenciaCidade();
    private final GerenciaPiloto gp = new GerenciaPiloto();
    public GerenciaCarro() {
    }

    public void cadastrar() {
        long cod;
        String aux;
        Carro car = new Carro();
        try {
            System.out.println("==Inserção de Carro==");
            System.out.println("Identificação :");
            car.setIdentificacao(ent.nextLine());
            System.out.println("Digite os dados do novo carro: \n");
            System.out.println("Modelo: ");
            car.setModelo(ent.nextLine());
            System.out.println("Capacidade de Passageiro: ");
            car.setCapacPassageiros(ent.nextInt());
            ent.nextLine();
            System.out.println("Capacidade de Carga: ");
            car.setCapacCarga(ent.nextDouble());
            ent.nextLine();
            
            System.out.println("\nPILOTOS DISPONÍVEIS.");
            System.out.println("======================================");
            gp.relatorio();
            System.out.println("\nIdentifidade do Piloto: ");
            aux = ent.nextLine();
            car.setPiloto(Pil.consultar(aux));
            System.out.println("\nCIDADES DISPONÍVEIS.");
            System.out.println("======================================");
            gc.relatorio();
            System.out.println("======================================");
            System.out.println("Cidade de Origem: ");
            cod = ent.nextLong();
            car.setCidadeOrigem(CidBd.consultar(cod));
            ent.nextLine();
            System.out.println("Cidade de Destino: ");
            cod = ent.nextLong();
            car.setCidadeDestino(CidBd.consultar(cod));
            ent.nextLine();
            System.out.println("Autonomia:");
            car.setAutonomia(ent.nextFloat());
            ent.nextLine();
            ABd.inserirNoBanco(car);
            System.out.println("\nNovo avião cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
            ex.printStackTrace();
        }
    }

    public void alterar() {
        String cod;
        String aux;
        long aux2;

        System.out.println("==== Alteração de carros  ====");
        System.out.println("Qual a identificacao do carro que você deseja alterar? ");
        cod= ent.nextLine();

        Carro car = ABd.consultar(cod);

        if (car != null) {
            System.out.println("==== Dados do carro =====");
            car.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ent.nextInt();
            ent.nextLine();
            if (resp == 1) {
                System.out.println("\nDigite os novos dados do carro: ");
                System.out.println("Digite os dados do novo carro: \n");
                System.out.println("Modelo: ");
                car.setModelo(ent.nextLine());
                System.out.println("Capacidade de Passageiro: ");
                car.setCapacPassageiros(ent.nextInt());
                ent.nextLine();
                System.out.println("Capacidade de Carga: ");
                car.setCapacCarga(ent.nextDouble());
                ent.nextLine();
                
                System.out.println("\nPILOTOS DISPONÍVEIS.");
                System.out.println("======================================");
                gp.relatorio();
                System.out.println("\nIdentifidade do Piloto: ");
                aux = ent.nextLine();
                car.setPiloto(Pil.consultar(aux));
                System.out.println("\nCIDADES DISPONÍVEIS.");
                System.out.println("======================================");
                gc.relatorio();
                System.out.println("======================================");
                System.out.println("Cidade de Origem: ");
                aux2= ent.nextLong();
                car.setCidadeOrigem(CidBd.consultar(aux2));
                ent.nextLine();
                System.out.println("Cidade de Destino: ");
                aux2= ent.nextLong();
                car.setCidadeDestino(CidBd.consultar(aux2));
                ent.nextLine();
                System.out.println("Autonomia:");
                car.setAutonomia(ent.nextFloat());
                try {
                    ABd.alterarNoBanco(car);
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

        System.out.println("==== Exclusão de carros ====");

        System.out.println("Qual a identificação do carro que você deseja excluir? ");
        cod = ent.nextLine();;

        Carro car = ABd.consultar(cod);

        if (car != null) {
            System.out.println("===== Dados do carro =====");
            car.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ent.nextInt();
            ent.nextLine();

            if (resp == 1) {
                try {
                    ABd.excluirDoBanco(car);
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

        System.out.println("==== Consulta de carros ====");

        System.out.println("Qual a identificação do carro que você deseja consultar? ");
        cod = ent.nextLine();

        Carro ca =  ABd.consultar(cod);

        if (ca != null) {
            System.out.println("===== Dados do carro =====");
            ca.consultar();
        } else {
            System.out.println("Não existe carro com este código.");
        }
    }

    public void relatorio() {

        ArrayList<Carro> car = new ArrayList<>();

        System.out.println("==== Relatório de Carros ====");

        try {
            car = ABd.relatorio();

            if (car != null) {
                System.out.println("===== Lista de carros =====");
                for (Carro c : car) {
                    c.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem carros cadastrados.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
