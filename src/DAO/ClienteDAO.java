package DAO;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.Cliente;

public class ClienteDAO {
    
    // connection e um objeto de conexão e o que conecta com o banco de dados.
    private Connection con;
    private Statement stm;
    Conexao bancoDeDados = Conexao.getInstance(); // essa conexão esta recebendo a conexão que eu criei com o postgree na clase ConectaBD 
                                                      // O getinstance e por que estou usando singleton 
                                                      // ou seja so existe 1 objeto de conexão ele apenas pega a instancia.

    public ClienteDAO() {
        con = bancoDeDados.iniciaBanco(); // aqui eu inicio a conexão
    }

    public void inserirNoBanco(Cliente cli) {
        try {
           
            
            //  PreparedStatement e um objeto que recebe o comando SQL , ou seja insert ,update , select.
            // no lugar dos dados se colocam pontos de interrogação de acordo com o tanto de dados a serem consultados ou inseridos etc etc..
            PreparedStatement pst = con.prepareStatement("INSERT INTO cliente "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            pst.setInt(1, cli.getIdentificacao()); // cada numero significa o ponto de interrogação , o 1 e o primeiro ponto
                                                   // o 2 o segundo ponto o 3 o terceiro e assim vai , depois da virgula voce coloca o dado que quer enviar ao banco.
                                                   // setInt envia um inteiro setString uma String e assim por diante.
            pst.setString(2, cli.getNome());
            pst.setString(3, cli.getLogradouro());
            pst.setInt(4, cli.getNumero());
            pst.setString(5, cli.getBairro());
            pst.setString(6, cli.getMunicipio());
            pst.setString(7, cli.getEstado());
            pst.setString(8, cli.getCep());
            pst.setString(9, cli.getTelefone());
            pst.setString(10, cli.getCpf());
            pst.executeUpdate(); // Os metodos execute , executeUpdate, ExecuteQuery execultam o PreparedStatement ou seja o comando SQL que voce configurou.
            pst.close(); // aqui termina o comando , se o banco retornar algum erro,ele vai cair no catch e sera printado o erro na tela
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex); // se ocorre erro ele e printado na tela.
        }
    }

    public void excluirDoBanco(Cliente cli) {
        try {
            
            PreparedStatement pst = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
            // o PreparedStatement recebe o PreparedStatement da conexão enviando o comando de delete
            pst.setInt(1, cli.getIdentificacao()); // esse e o comando que coloca o dado no pontod e interrogação ou seja.
                                                   // excluia da tabela de clientes se o idcliente for igual ao cli.getIdentificação()
            pst.execute();  // aqui o comando e executado.
      
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex); // mesma coisa se ocorrer erro ele sera mostrado na tela.
        }
    }

    public void alterarNoBanco(Cliente cli) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE cliente SET nome = ?,"
                    + "logradouro = ?,numero = ?,bairro = ?,municipio = ?,"
                    + "estado = ?,cep = ?,telefone = ?,cpf = ? WHERE idcliente = ?");

            // como eu ja expliquei cada ponto de interrogação e um dado a ser enviado ao banco.
            // cada pst.setTipo e o dado do ponto de interrogação na ordem dos numeros.
            pst.setString(1, cli.getNome());
            pst.setString(2, cli.getLogradouro());
            pst.setInt(3, cli.getNumero());
            pst.setString(4, cli.getBairro());
            pst.setString(5, cli.getMunicipio());
            pst.setString(6, cli.getEstado());
            pst.setString(7, cli.getCep());
            pst.setString(8, cli.getTelefone());
            pst.setString(9, cli.getCpf());
            pst.setInt(10, cli.getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public Cliente consultar(long cod) {
        Cliente cli;
        ResultSet rs; // o Objeto ResultSet vai receber o resultado de uma consulta Select.

        try {
            PreparedStatement pst = con.prepareStatement("SELECT identificacao, nome, logradouro, numero, bairro, "
                    + "municipio, estado, cep, telefone, cpf "
                    + "FROM cliente"
                    + " WHERE identificacao = ? ");
            
            pst.setInt(1, (int) cod); // dado do ponto de interrogação ou seja se o idcliente for igual ao dado que ta no cod.
            rs= pst.executeQuery(); // Aqui o result set vai receber o resultado do select ou seja aquela tabelinha que aparece no postgree ta dentro desse ResultSet.
            
            if (rs.next()) { // esse result set e igual um iterator ou seja se tiver proximo ele vai criar um cliente com os dados que estava no proximo , esse proximo e o resultado do select.
                cli = new Cliente(rs.getInt("identificacao"), rs.getString("nome"), rs.getString("logradouro"),
                        rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"),
                        rs.getString("estado"), rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf"));

                //aqui eu to criando o cliente com os dados que foram retornados do banco.
                //rs.getTipo pega o dado do tipo tal , que esta no indice .
                // diferente do arraylist aqui o indice nao e do tipo inteiro ex: .get(1) , .Get(2).
                // aqui o indice e o nome do indice da tabela do banco ou seja rs.getInt("idcliente") e o indice idcliente.
                // ex numero = rs.getInt("idcliente"); o numero ta recebendo a id do cliente que o banco retornou.
                return cli; // depois que eu criei o cliente com os dados que estão no banco eu retorno ele e mostro na tela os dados.
            } else {
                return null; // se nao houver cliente ele retornará null.
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex); // se houver erros eles seram printados na tela.
            return null; // se houver erro retorna null.
        }
    }

    public ArrayList relatorio() {
        ArrayList<Cliente> clientes;
        ResultSet rs;
        // basicamente a mesma coisa do consultar porem aqui eu crio um array com todos os clientes do banco.
        try {
            clientes = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM cliente");
            rs = pst.executeQuery();
       
            while (rs.next()) {
                // enquanto tiver proximo ele coloca o proximo no arraylist.
                clientes.add(new Cliente(rs.getInt("identificacao"), rs.getString("nome"), rs.getString("logradouro"),
                        rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"),
                        rs.getString("estado"), rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf")));
            }

            return clientes; // retorna o arraylist

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}
