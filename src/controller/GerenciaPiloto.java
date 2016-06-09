package controller;

import DAO.CidadeDAO;
import java.util.ArrayList;
import java.util.Scanner;
import DAO.PilotoDAO;
import model.Piloto;

public class GerenciaPiloto {
    
    private final CidadeDAO CidBd = new CidadeDAO();
    private final GerenciaCidade gc = new GerenciaCidade();
    Scanner ent = new Scanner(System.in);
    private final PilotoDAO PilBd = new PilotoDAO();

    public GerenciaPiloto() {

    }

    public void cadastrar() {

        long aux;
        
        Piloto pil = new Piloto();
        try {
            System.out.println("==Inserção de pilotos==");
            System.out.println("Digite os dados do novo piloto: \n");
            System.out.println("Nome: ");
            pil.setNome(ent.nextLine());
            System.out.println("Identidade: ");
            pil.setIdentidade(ent.nextLine());
            System.out.println("CPF: ");
            pil.setCpf(ent.nextLine());
            System.out.println("Número do Brever: ");
            pil.setNumeroBrever(ent.nextLine());
            System.out.println("Logradouro: ");
            pil.setLogradouro(ent.nextLine());
            System.out.println("Numero: ");
            pil.setNumero(ent.nextLine());
            System.out.println("\nCIDADES DISPONÍVEIS.");
            System.out.println("======================================");
            gc.relatorio();
            System.out.println("======================================");
            System.out.println("\nDigite a identificação da Cidade: ");
            aux = ent.nextLong();
            pil.setCidade(CidBd.consultar(aux));
            ent.nextLine();      
            System.out.println("Telefone:");
            pil.setTelefone(ent.nextLine());
            PilBd.inserirNoBanco(pil);
            System.out.println("\nNovo piloto cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        String cod;


        System.out.println("==== Alteração de pilotos  ====");

        System.out.println("Qual a identidade do piloto que você deseja alterar? ");
        cod = ent.nextLine();

        Piloto pil = PilBd.consultar(cod);

        if (pil != null) {
            System.out.println("==== Dados do piloto =====");
            pil.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ent.nextInt();
            ent.nextLine();
            if (resp == 1) {
                System.out.println("Digite os  novos dados do piloto: \n");
                System.out.println("Nome: ");
                pil.setNome(ent.nextLine());
                System.out.println("CPF: ");
                pil.setCpf(ent.nextLine());
                System.out.println("Número do Brever: ");
                pil.setNumeroBrever(ent.nextLine());
                System.out.println("Logradouro: ");
                pil.setLogradouro(ent.nextLine());
                System.out.println("Numero: ");
                pil.setNumero(ent.nextLine());
                System.out.println("\nCIDADES DISPONÍVEIS.");
                System.out.println("======================================");
                gc.relatorio();
                System.out.println("======================================");
                System.out.println("\nDigite a identificação da Cidade: ");
                long aux = ent.nextLong();
                ent.nextLine();           
                pil.setCidade(CidBd.consultar(aux));
                System.out.println("Telefone:");
                pil.setTelefone(ent.nextLine());
                
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
        cod = ent.nextLine();

        Piloto pil = PilBd.consultar(cod);

        if (pil != null) {
            System.out.println("===== Dados do Piloto =====");
            pil.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ent.nextInt();
            ent.nextLine();

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
        cod = ent.nextLine();

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