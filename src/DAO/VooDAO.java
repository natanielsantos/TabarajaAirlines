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
            PreparedStatement pst = con.prepareStatement("INSERT INTO voos "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
 
            pst.setInt(1, voo.getId_voo());
            pst.setString(2, voo.getAviao().getIdentificacao());
            pst.setInt(3, voo.getTipoAeronave());
            pst.setString(4, voo.getAeroportoPartida().getIdentificacao());
            pst.setDate(5, java.sql.Date.valueOf(voo.getDataPartida()));
            pst.setTime(6, java.sql.Time.valueOf(voo.getHoraPartida()));
            pst.setString(7, voo.getAeroportoChegada().getIdentificacao());
            pst.setDate(8, java.sql.Date.valueOf(voo.getDataChegada()));
            pst.setTime(9, java.sql.Time.valueOf(voo.getHoraChegada()));
            pst.setInt(10, voo.getLotacao());
            pst.setDouble(11, voo.getPesoCargaEmbarcada());
            pst.setDouble(12, voo.getPrecoViagem());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Piloto pil) {
        try {

            PreparedStatement pst = con.prepareStatement("DELETE FROM pilotos WHERE identidade = ?");
            pst.setString(1, pil.getIdentidade());
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

    public Piloto consultar(String cod) {

        Piloto pil;
        ResultSet rs;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM pilotos "
                    + "INNER JOIN cidades ON pilotos.cidade = cidades.idcidade WHERE identidade = ?");

            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
               /* pil = new Piloto(rs.getString("nome"), rs.getString("identidade"), rs.getString("cpf"),
                        rs.getString("numerodobrever"), rs.getString("logradouro"), rs.getString("numero"), 
                        new Cidade(rs.getInt("idcidade"), rs.getString("pais"), rs.getString("estado"), 
                                rs.getString("nomeCidade")), rs.getString("telefone"));

                return pil;*/
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList<Voo> relatorio() {
        ArrayList<Piloto> pilotos;
        ResultSet rs;

       /* try {
            pilotos = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM pilotos INNER JOIN "
                    + "cidades ON pilotos.cidade = cidades.idcidade");
            
            rs = pst.executeQuery();

            while (rs.next()) {

                pilotos.add(new Piloto(rs.getString("nome"), rs.getString("identidade"), rs.getString("cpf"),
                        rs.getString("numerodobrever"), rs.getString("logradouro"), rs.getString("numero"), 
                        new Cidade(rs.getInt("idcidade"), rs.getString("pais"), rs.getString("estado"), 
                                rs.getString("nome")), rs.getString("telefone")));
            }
            return pilotos;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }*/
    }
}