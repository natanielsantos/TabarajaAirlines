package DAO;

import java.sql.*;
import java.util.ArrayList;
import tabajara.airlines.Cidade;
import tabajara.airlines.Piloto;

public class PilotoBD {

    private Connection con;
    private Statement stm;
    ConectaBD bancoDeDados = ConectaBD.getInstance();

    public PilotoBD() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Piloto pil) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO pilotos "
                    + "VALUES (?,?,?,?,?,?,?,?)");
 
            pst.setString(1, pil.getIdentidade());
            pst.setString(2, pil.getNome());
            pst.setString(3, pil.getCpf());
            pst.setString(4, pil.getNumeroBrever());
            pst.setString(5, pil.getLogradouro());
            pst.setString(6, pil.getNumero());
            pst.setInt(7, pil.getCidade().getIdentificacao());
            pst.setString(8, pil.getTelefone());
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
            pst.setString(3, pil.getNumeroBrever());
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
                pil = new Piloto(rs.getString("nome"), rs.getString("identidade"), rs.getString("cpf"),
                        rs.getString("numerodobrever"), rs.getString("logradouro"), rs.getString("numero"), 
                        new Cidade(rs.getInt("idcidade"), rs.getString("pais"), rs.getString("estado"), 
                                rs.getString("nomeCidade")), rs.getString("telefone"));

                return pil;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList relatorio() {
        ArrayList<Piloto> pilotos;
        ResultSet rs;

        try {
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
        }
    }
}