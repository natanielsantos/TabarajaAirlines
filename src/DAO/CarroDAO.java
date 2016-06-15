package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.*;

public class CarroDAO {

	private Connection con;
	private Statement stm;
	Conexao bancoDeDados = Conexao.getInstance();

	public CarroDAO() {
		con = bancoDeDados.iniciaBanco();
	}

	public void inserirNoBanco(Carro car) {
		try {
			PreparedStatement pst = con.prepareStatement("INSERT INTO carro "
		                                                + "VALUES (?,?,?,?,?,?,?,?)");

			pst.setString(1, car.getIdentificacao());
			pst.setString(2, car.getModelo());
			pst.setInt(3, car.getCapacPassageiros());
			pst.setDouble(4, car.getCapacCarga());
			pst.setString(5, car.getPiloto().getIdentidade());
			pst.setInt(6, car.getCidadeOrigem().getIdentificacao());
			pst.setInt(7, car.getCidadeDestino().getIdentificacao());
			pst.setFloat(8, car.getAutonomia());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex);
		}
	}

	public void excluirDoBanco(Carro car) {
		try {

			PreparedStatement pst = con.prepareStatement("DELETE FROM carro WHERE identificacao = ?");
			pst.setString(1, car.getIdentificacao());
			pst.execute();

		} catch (SQLException ex) {
			System.out.println("Erro: " + ex);
		}
	}

	public void alterarNoBanco(Carro car) {
		try {
			PreparedStatement pst = con.prepareStatement(
					"UPDATE carro SET modelo = ?, " + "capac_passageiros = ?, capac_carga = ?, "
							+ "autonomia = ?, piloto = ?, cidade_origem = ?,cidade_destino  = ?  " + "WHERE identificacao = ?");

			pst.setString(1, car.getModelo());
			pst.setInt(2, car.getCapacPassageiros());
			pst.setDouble(3, car.getCapacCarga());
			pst.setFloat(4, car.getAutonomia());
			pst.setString(5, car.getPiloto().getIdentidade());
			pst.setInt(6, car.getCidadeOrigem().getIdentificacao());
			pst.setInt(7, car.getCidadeDestino().getIdentificacao());
			pst.setString(8, car.getIdentificacao());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex);
		}
	}
	
	public Carro consultar(String cod) {
        Carro car;
        ResultSet rs;

        try {
        	PreparedStatement pst = con.prepareStatement("SELECT * FROM (carro " // FUNCIONA MAS APARECE OS DOIS DA MESMA CIDADE.			                    
                    + "INNER JOIN piloto pil ON carro.piloto = pil.identidade "
                    + "INNER JOIN cidade AS c1 ON carro.cidade_origem = c1.idcidade) "
						   + "INNER JOIN cidade AS c2 ON carro.cidade_destino = c2.idcidade WHERE carro.identificacao=?");

            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
            	car = new Carro(new Piloto(rs.getInt("idpiloto"),rs.getString("nome"), rs.getString("identidade"), rs.getString("cpf"),
                        rs.getString("numerodobreve"), rs.getString("logradouro"), rs.getString("numero"), 
                        new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), rs.getString("pais"),rs.getString("estado")), rs.getString("telefone")),
						new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), rs.getString("pais"),rs.getString("estado")),
						new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), rs.getString("pais"),rs.getString("estado")),
						rs.getFloat("autonomia"),rs.getString("identificacao"),rs.getString("modelo"),rs.getInt("capac_passageiros"),rs.getDouble("capac_carga"));

                return car;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
	}

	public ArrayList<Carro> relatorio() {
		ArrayList<Carro> carros;
		ResultSet rs;

		try {
			carros = new ArrayList<>();

			PreparedStatement pst = con.prepareStatement("SELECT * FROM (carro " // FUNCIONA MAS APARECE OS DOIS DA MESMA CIDADE.			                    
					                                   + "INNER JOIN piloto pil ON carro.piloto = pil.identidade "
					                                   + "INNER JOIN cidade AS c1 ON carro.cidade_origem = c1.idcidade) "
														   + "INNER JOIN cidade AS c2 ON carro.cidade_destino = c2.idcidade");

			rs = pst.executeQuery();

			
			while (rs.next()) {

				carros.add(new Carro(new Piloto(rs.getInt("idpiloto"),rs.getString("nome"), rs.getString("identidade"), rs.getString("cpf"),
                        rs.getString("numerodobreve"), rs.getString("logradouro"), rs.getString("numero"), 
                        new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), rs.getString("pais"),rs.getString("estado")), rs.getString("telefone")),
						new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), rs.getString("pais"),rs.getString("estado")),
						new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), rs.getString("pais"),rs.getString("estado")),
						rs.getFloat("autonomia"),rs.getString("identificacao"),rs.getString("modelo"),rs.getInt("capac_passageiros"),rs.getDouble("capac_carga")));
			}
			return carros;

		} catch (SQLException ex) {
			System.out.println("Erro: " + ex);
			return null;
		}
	}
	
}
