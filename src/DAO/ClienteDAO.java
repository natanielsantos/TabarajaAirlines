package DAO;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import controleaereo.Cliente;

public class ClienteDAO {
    
    
    private Connection con;
    private Statement stm;
    Conexao bancoDeDados = Conexao.getInstance(); 

    public ClienteDAO() {
        con = bancoDeDados.iniciaBanco(); 
    }

    public void inserirNoBanco(Cliente cli) {
        try {
           
            
           
            PreparedStatement pst = con.prepareStatement("INSERT INTO cliente "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            pst.setInt(1, cli.getIdentificacao()); 
            pst.setString(2, cli.getNome());
            pst.setString(3, cli.getLogradouro());
            pst.setInt(4, cli.getNumero());
            pst.setString(5, cli.getBairro());
            pst.setString(6, cli.getMunicipio());
            pst.setString(7, cli.getEstado());
            pst.setString(8, cli.getCep());
            pst.setString(9, cli.getTelefone());
            pst.setString(10, cli.getCpf());
            pst.executeUpdate(); 
            pst.close(); 
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex); 
        }
    }

    public void excluirDoBanco(Cliente cli) {
        try {
            
            PreparedStatement pst = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
           
            pst.setInt(1, cli.getIdentificacao()); 
            pst.execute();  
      
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Cliente cli) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE cliente SET nome = ?,"
                    + "logradouro = ?,numero = ?,bairro = ?,municipio = ?,"
                    + "estado = ?,cep = ?,telefone = ?,cpf = ? WHERE idcliente = ?");

           
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
        ResultSet rs; 

        try {
            PreparedStatement pst = con.prepareStatement("SELECT identificacao, nome, logradouro, numero, bairro, "
                    + "municipio, estado, cep, telefone, cpf "
                    + "FROM cliente"
                    + " WHERE identificacao = ? ");
            
            pst.setInt(1, (int) cod); 
            rs= pst.executeQuery(); 
            
            if (rs.next()) { 
                cli = new Cliente(rs.getInt("identificacao"), rs.getString("nome"), rs.getString("logradouro"),
                        rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"),
                        rs.getString("estado"), rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf"));

               
                return cli; 
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex); 
            return null; 
        }
    }

    public ArrayList relatorio() {
        ArrayList<Cliente> clientes;
        ResultSet rs;
       
        try {
            clientes = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM cliente");
            rs = pst.executeQuery();
       
            while (rs.next()) {
              
                clientes.add(new Cliente(rs.getInt("identificacao"), rs.getString("nome"), rs.getString("logradouro"),
                        rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"),
                        rs.getString("estado"), rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf")));
            }

            return clientes; 

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}
