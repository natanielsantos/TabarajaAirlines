package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.*;

public class VooDAO {

    private Connection con;
    Conexao bancoDeDados = Conexao.getInstance();
    
    public VooDAO() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Voo voo) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO voo (tipo, aeronave, aeroporto_partida, data_partida, hora_partida, " 
            + "aeroporto_chegada, data_chegada, hora_chegada, lotacao, peso_carga_embarcada, preco_viagem) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");// polimorfismo para aeronave
 
            //pst.setInt(1, voo.getId_voo());
            pst.setInt(1, voo.getTipoAeronave());
            pst.setString(2, voo.getAeronave().getIdentificacao()); // objeto existe e o atributo também não está nulo, nenhuma excecao nula foi lançada
            pst.setString(3, voo.getAeroportoPartida().getIdentificacao());
            pst.setDate(4, java.sql.Date.valueOf(voo.getDataPartida()));
            pst.setTime(5, java.sql.Time.valueOf(voo.getHoraPartida()));
            pst.setString(6, voo.getAeroportoChegada().getIdentificacao());
            pst.setDate(7, java.sql.Date.valueOf(voo.getDataChegada()));
            pst.setTime(8, java.sql.Time.valueOf(voo.getHoraChegada()));
            pst.setInt(9, voo.getLotacao());
            pst.setDouble(10, voo.getPesoCargaEmbarcada());
            pst.setDouble(11, voo.getPrecoViagem());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Voo voo) {
        try {

            PreparedStatement pst = con.prepareStatement("DELETE FROM voo WHERE id_voo = ?");
            pst.setInt(1, voo.getId_voo());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Piloto pil) {
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