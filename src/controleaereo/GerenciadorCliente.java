package controleaereo;

import java.util.ArrayList;
import java.util.Scanner;
import DAO.ClienteDAO;

public class GerenciadorCliente {

    Scanner ler = new Scanner(System.in);
    private final ClienteDAO CliBd = new ClienteDAO();

    public GerenciadorCliente() {

    }

    public void cadastrar() {
        String nome;
        long cod;
        Cliente cli = new Cliente();
        try {
            System.out.println("==Inserção de Clientes==");
            System.out.println("Digite os dados do novo cliente: \n");

            System.out.println("Identificação :");
            cli.setIdentificacao(ler.nextInt());
            ler.next();
            System.out.println("Nome: ");
            cli.setNome(ler.nextLine());
            System.out.println("Logradouro: ");
            cli.setLogradouro(ler.nextLine());
            System.out.println("Numero: ");
            cli.setNumero(ler.nextInt());
            System.out.println("Bairro: ");
            cli.setBairro(ler.nextLine());
            System.out.println("Município: ");
            cli.setMunicípio(ler.nextLine());
            System.out.println("Estado: ");
            cli.setEstado(ler.nextLine());
            System.out.println("Cep: ");
            cli.setCep(ler.nextLine());
            System.out.println("Telefone: ");
            cli.setTelefone(ler.nextLine());
            System.out.println("CPF :");
            cli.setCpf(ler.nextLine());
            
            CliBd.inserirNoBanco(cli);
            System.out.println("\nNovo cliente cadastrado com sucesso. \n");
        } catch (Exception ex) {
            System.out.println("Inclusão não efetuada. Erro: " + ex);
        }
    }

    public void alterar() {
        long cod;
        String nome;

        System.out.println("==== Alteração de clientes  ====");

        System.out.println("Qual o código do cliente que você deseja alterar? ");
        cod = ler.nextInt();
        ler.skip("\n");

        Cliente cli = CliBd.consultar(cod);

        if (cli != null) {
            System.out.println("==== Dados do cliente =====");
            cli.consultar();
            System.out.println("\n\nConfirma alteração? (1-sim/2-não) ");
            int resp = ler.nextInt();
            ler.skip("\n");
            if (resp == 1) {
                System.out.println("Digite os  novos dados do cliente: \n");
                System.out.println("Nome: ");
                cli.setNome(ler.nextLine());
                System.out.println("Logradouro: ");
                cli.setLogradouro(ler.nextLine());
                System.out.println("Numero: ");
                cli.setNumero(ler.nextInt());
                System.out.println("Bairro: ");
                cli.setBairro(ler.nextLine());
                System.out.println("Município: ");
                cli.setMunicípio(ler.nextLine());
                System.out.println("Estado: ");
                cli.setEstado(ler.nextLine());
                System.out.println("Cep: ");
                cli.setCep(ler.nextLine());
                System.out.println("Telefone: ");
                cli.setTelefone(ler.nextLine());
                System.out.println("CPF :");
                cli.setCpf(ler.nextLine());
                try {
                   CliBd.alterarNoBanco(cli);
                    System.out.println("Alteração efetuada com sucesso.");
                } catch (Exception ex) {
                    System.out.println("Alteração não efetuada. Erro: " + ex.getMessage());
                }
            }else{
                System.out.println("Cancelado com sucesso!!");
            }

        }
    }

    public void excluir() {
        long cod;
        int resp;

        System.out.println("==== Exclusão de clientes ====");

        System.out.println("Qual o código do Cliente que você deseja excluir? ");
        cod = ler.nextInt();
        ler.skip("\n");

        Cliente cli = CliBd.consultar(cod);

        if (cli != null) {
            System.out.println("===== Dados do cliente =====");
            cli.consultar();
            System.out.println("\n\nConfirma exclusão? (1-sim/2-não) ");
            resp = ler.nextInt();
            ler.skip("\n");

            if (resp == 1) {
                try {
                    CliBd.excluirDoBanco(cli);
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

        System.out.println("==== Consulta de clientes ====");

        System.out.println("Qual o código do cliente que você deseja consultar? ");
        cod = ler.nextLong();
        ler.skip("\n");

        Cliente cli = CliBd.consultar(cod);

        if (cli != null) {
            System.out.println("===== Dados do cliente =====");
            cli.consultar();
        } else {
            System.out.println("Não existe pessoa com este código.");
        }
    }

    public void relatorio() {
        
        ArrayList<Cliente> cli = new ArrayList<>();

        System.out.println("==== Relatório de Clientes ====");

        try {
            cli = CliBd.relatorio();

            if (cli != null) {
                System.out.println("===== Lista de clientes =====");
                for (Cliente c : cli) {
                    c.consultar();
                    System.out.println("============================================");
                }
            } else {
                System.out.println("\nNão existem pessoas cadastradas.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
