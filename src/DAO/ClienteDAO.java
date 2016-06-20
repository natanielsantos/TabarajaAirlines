package DAO;

import java.sql.*;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {

	private Connection con;

	Conexao bancoDeDados = Conexao.getInstance();

	public ClienteDAO() {
		con = bancoDeDados.iniciaBanco();
	}

	public void inserirNoBanco(Cliente cli) {
		try {

			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO cliente (nome, logradouro, numero, bairro, estado, telefone,municipio, cep, cpf)"
							+ "VALUES (?,?,?,?,?,?,?,?,?)");

			pst.setString(1, cli.getNome());
			pst.setString(2, cli.getLogradouro());
			pst.setInt(3, cli.getNumero());
			pst.setString(4, cli.getBairro());
			pst.setString(5, cli.getEstado());
			pst.setString(6, cli.getTelefone());
			pst.setString(7, cli.getMunicipio());
			pst.setString(8, cli.getCep());
			pst.setString(9, cli.getCpf());
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
			PreparedStatement pst = con.prepareStatement(
					"UPDATE cliente SET nome = ?," + "logradouro = ?,numero = ?,bairro = ?,municipio = ?,"
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
			PreparedStatement pst = con.prepareStatement("SELECT idcliente, nome, logradouro, numero, bairro, "
					+ "municipio, estado, cep, telefone, cpf " + "FROM cliente" + " WHERE idcliente = ? ");

			pst.setInt(1, (int) cod);
			rs = pst.executeQuery();

			if (rs.next()) {
				cli = new Cliente(rs.getInt("idcliente"), rs.getString("nome"), rs.getString("logradouro"),
						rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("estado"),
						rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf"));
				
				return cli;

			} else {
				return null;
			}

		} catch (SQLException ex) {
			System.out.println("Erro: " + ex);
			return null;
		}
	}

	public ArrayList<Cliente> relatorio() {
		ArrayList<Cliente> clientes;
		ResultSet rs;

		try {
			clientes = new ArrayList<>();

			PreparedStatement pst = con.prepareStatement("SELECT * FROM cliente");
			rs = pst.executeQuery();

			while (rs.next()) {

				clientes.add(new Cliente(rs.getInt("idcliente"), rs.getString("nome"), rs.getString("logradouro"),
						rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("estado"),
						rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf")));
			}

			return clientes;

		} catch (SQLException ex) {
			System.out.println("Erro: " + ex);
			return null;
		}
	}
}
