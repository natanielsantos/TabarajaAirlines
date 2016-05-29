package controller;

import DAO.CidadeBD;
import java.util.ArrayList;
import java.util.Scanner;
import DAO.PilotoBD;

public class GerenciaPiloto {
    
    private final CidadeBD CidBd = new CidadeBD();
    private final GerenciaCidade gc = new GerenciaCidade();
    Scanner ler = new Scanner(System.in);
    private final PilotoBD PilBd = new PilotoBD();

    public GerenciaPiloto() {

    }

    public void cadastro() {
        String nome;
        long cod,aux;
        Piloto pil = new Piloto();
        try {
            System.out.println("==Inserção de pilotos==");
            System.out.println("Digite os dados do novo piloto: \n");
            System.out.println("Nome: ");
            pil.setNome(ler.nextLine());
            System.out.println("Identidade: ");
            pil.setIdentidade(ler.nextLine());
            System.out.println("CPF: ");
            pil.setCpf(ler.nextLine());
            System.out.println("Número do Brever: ");
            pil.setNumeroBrever(ler.nextLine());
            System.out.println("Logradouro: ");
            pil.setLogradouro(ler.nextLine());
            System.out.println("Numero: ");
            pil.setNumero(ler.nextLine());
            System.out.println("\nCIDADES DISPONÍVEIS.");
            System.out.println("======================================");
            gc.relatorio();
            System.out.println("======================================");
            System.out.println("\nDigite a identificação da Cidade: ");
            aux = ler.nextLong();
            pil.setCidade(CidBd.consultar(aux));
            ler.skip("\n");       
            System.out.println("Telefone:");
            pil.setTelefone(ler.nextLine());
            PilBd.inserirNoBanco(pil);
            System.out.println("\nNovo piloto cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        String cod;
        String nome;

        System.out.println("==== Alteração de pilotos  ====");

        System.out.println("Qual a identidade do piloto que você deseja alterar? ");
        cod = ler.nextLine();

        Piloto pil = PilBd.consultar(cod);

        if (pil != null) {
            System.out.println("==== Dados do piloto =====");
            pil.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ler.nextInt();
            ler.skip("\n");
            if (resp == 1) {
                System.out.println("Digite os  novos dados do piloto: \n");
                System.out.println("Nome: ");
                pil.setNome(ler.nextLine());
                System.out.println("CPF: ");
                pil.setCpf(ler.nextLine());
                System.out.println("Número do Brever: ");
                pil.setNumeroBrever(ler.nextLine());
                System.out.println("Logradouro: ");
                pil.setLogradouro(ler.nextLine());
                System.out.println("Numero: ");
                pil.setNumero(ler.nextLine());
                System.out.println("\nCIDADES DISPONÍVEIS.");
                System.out.println("======================================");
                gc.relatorio();
                System.out.println("======================================");
                System.out.println("\nDigite a identificação da Cidade: ");
                long aux = ler.nextLong();
                ler.skip("\n");             
                pil.setCidade(CidBd.consultar(aux));
                System.out.println("Telefone:");
                pil.setTelefone(ler.nextLine());
                
                try {
                    PilBd.alterarNoBanco(pil);
                    System.out.println("Alteração efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Alteração não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Cancelado com sucesso!!");
            }

        }
    }

    public void excluir() {
        String cod;
        int resp;

        System.out.println("==== Exclusão de pilotos ====");

        System.out.println("Qual a identidade do piloto que você deseja excluir? ");
        cod = ler.nextLine();

        Piloto pil = PilBd.consultar(cod);

        if (pil != null) {
            System.out.println("===== Dados do Piloto =====");
            pil.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ler.nextInt();
            ler.skip("\n");

            if (resp == 1) {
                try {
                    PilBd.excluirDoBanco(pil);
                    System.out.println("Exclusão efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Exclusão não efetuada. Erro: " + ex.getMessage());
                }
            } else {
                System.out.println("Exclusão não efetuada.");
            }
        }else{
            System.out.println("Inexistente");
        }

    }

    public void consultar() {
        String cod;

        System.out.println("==== Consulta de pilotos ====");

        System.out.println("Qual a identidade do piloto que você deseja consultar? ");
        cod = ler.nextLine();

        Piloto pil = PilBd.consultar(cod);

        if (pil != null) {
            System.out.println("===== Dados do piloto =====");
            pil.consultar();
        } else {
            System.out.println("Não existe piloto com esta identidade.");
        }
    }

    public void relatorio() {

        ArrayList<Piloto> pil = new ArrayList<>();

        System.out.println("==== Relatório de pilotos ====");

        try {
            pil = PilBd.relatorio();

            if (pil != null) {
                System.out.println("===== Lista de pilotos =====");
                for (Piloto c : pil) {
                    c.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem pilotos cadastradas.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}