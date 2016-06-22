package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.*;

public class PassagemDAO {

    private Connection con;
    Conexao bancoDeDados = Conexao.getInstance();
    
    public PassagemDAO() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Passagem passagem) {
        try {
                    
		        	PreparedStatement pst = con.prepareStatement("INSERT INTO passagem (cliente, voo,data_venda, hora_venda, preco_final_viagem,carga_cliente) "
		                    								   + "VALUES (?,?,?,?,?,?)");
		            
		            //pst.setInt(1, voo.getId_voo());
		            pst.setInt(1, passagem.getCliente().getIdentificacao());
		            pst.setInt(2, passagem.getVoo().getId_voo());
		            pst.setDate(3, java.sql.Date.valueOf(passagem.getDataVenda()));
		            pst.setTime(4, java.sql.Time.valueOf(passagem.getHoraVenda()));
		            pst.setDouble(5, passagem.getPrecoFinalViagem());
		            pst.setDouble(6, passagem.getCargaCliente());
		            pst.executeUpdate();
		            pst.close();
		            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Passagem passagem) {
        try {

            PreparedStatement pst = con.prepareStatement("DELETE FROM passagem WHERE identificacao = ?");
            pst.setInt(1, passagem.getNumPassagem());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Passagem passagem) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE pilotos SET"
                    + " nome = ?, cpf = ?, numerodobrever = ?, "
                    + "logradouro = ?, numero = ?, cidade = ?,telefone = ? WHERE identidade = ?");
            
            pst.setString(1, pil.getNome());
            pst.setString(2, pil.getCpf());
            pst.setString(3, pil.getNumeroBreve());
            pst.setString(4, pil.getLogradouro());
            pst.setString(5, pil.getNumero());
            pst.setInt(6, pil.getCidade().getIdentificacao());
            pst.setString(7, pil.getTelefone());
            pst.setString(8, pil.getIdentidade());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public Voo consultar(int cod) {

        Voo voo;
        ResultSet rs;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT voo.*, aero.identificacao aero1, ae1.idaeroporto partida,ae2.idaeroporto chegada "
									            		+ "FROM voo, aeronave aero, aeroporto ae1,aeroporto ae2 "
									                    + "WHERE voo.aeronave = aero.identificacao "
									                    + "AND voo.aeroporto_partida = ae1.idaeroporto "					                                
									                    + "AND voo.aeroporto_chegada = ae2.idaeroporto "
									                    + "AND voo.id_voo=?");

            pst.setInt(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
               voo =  new Voo(rs.getInt("id_voo"),
            		    new Aeronave(rs.getString("aero1")),
            		    new Aeroporto(rs.getString("partida")),
            		   rs.getDate("data_partida").toLocalDate(),
            		   rs.getTime("hora_partida").toLocalTime(),
            		   	new Aeroporto(rs.getString("chegada")),
                       rs.getDate("data_chegada").toLocalDate(),
            		   rs.getTime("hora_chegada").toLocalTime(),
            		   rs.getInt("lotacao"),
            		   rs.getDouble("peso_carga_embarcada"),
            		   rs.getDouble("preco_viagem"),
            		   rs.getInt("tipo"));

                return voo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList<Voo> relatorio() {
        ArrayList<Voo> voos;
        ResultSet rs;

       try {
            voos = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT voo.*, aero.identificacao aero1, ae1.idaeroporto partida,ae2.idaeroporto chegada "
            		+ "FROM voo, aeronave aero, aeroporto ae1,aeroporto ae2 "
                    + "WHERE voo.aeronave = aero.identificacao "
                    + "AND voo.aeroporto_partida = ae1.idaeroporto "					                                
                    + "AND voo.aeroporto_chegada = ae2.idaeroporto ");
            
            rs = pst.executeQuery();

            while (rs.next()) {

                voos.add(new Voo(rs.getInt("id_voo"),
            		    new Aeronave(rs.getString("aero1")),
            		    new Aeroporto(rs.getString("partida")),
            		   rs.getDate("data_partida").toLocalDate(),
            		   rs.getTime("hora_partida").toLocalTime(),
            		   	new Aeroporto(rs.getString("chegada")),
                       rs.getDate("data_chegada").toLocalDate(),
            		   rs.getTime("hora_chegada").toLocalTime(),
            		   rs.getInt("lotacao"),
            		   rs.getDouble("peso_carga_embarcada"),
            		   rs.getDouble("preco_viagem"),
            		   rs.getInt("tipo")));
            }
            return voos;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}