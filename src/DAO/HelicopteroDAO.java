package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.Helicoptero;

public class HelicopteroDAO {

    private Connection con;
    Conexao bancoDeDados = Conexao.getInstance();

    public HelicopteroDAO() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Helicoptero hel) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO helicoptero "
                    + "VALUES (?,?,?,?,?)");
            pst.setString(1, hel.getIdentificacao());
            pst.setString(2, hel.getModelo());
            pst.setInt(3, hel.getCapacPassageiros());
            pst.setDouble(4, hel.getCapacCarga());
            pst.setInt(5, hel.getQtdHelices());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Helicoptero hel) {
        try {

            PreparedStatement pst = con.prepareStatement("DELETE FROM helicoptero WHERE identificacao = ?");
            pst.setString(1, hel.getIdentificacao());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Helicoptero hel) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE helicoptero SET modelo = ?, "
                    + "capac_passageiros = ?, capac_carga = ?, qtd_helices = ? "
                    + "WHERE identificacao = ?");

            pst.setString(1, hel.getModelo());
            pst.setInt(2, hel.getCapacPassageiros());
            pst.setDouble(3, hel.getCapacCarga());
            pst.setInt(4, hel.getQtdHelices());
            pst.setString(5, hel.getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public Helicoptero consultar(String cod) {
        Helicoptero hel;
        ResultSet rs;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM helicoptero WHERE identificacao = ? ");

            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                hel = new Helicoptero(rs.getInt("qtd_helices"),rs.getString("identificacao"),rs.getString("modelo"),
                rs.getInt("capac_passageiros"),rs.getDouble("capac_carga"));

                return hel;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList relatorio() {
        ArrayList<Helicoptero> heli;
        ResultSet rs;

        try {
            heli = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM helicoptero");
            rs = pst.executeQuery();

            while (rs.next()) {

                heli.add(new Helicoptero(rs.getInt("qtd_helices"),rs.getString("identificacao"),rs.getString("modelo"),
                rs.getInt("capac_passageiros"),rs.getDouble("capac_carga")));
            }

            return heli;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}
