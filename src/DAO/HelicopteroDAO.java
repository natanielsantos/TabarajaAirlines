package DAO;

import java.sql.*;
import java.util.ArrayList;
import tabajara.airlines.Helicoptero;

public class HelicopteroBD {

    private Connection con;
    private Statement stm;
    ConectaBD bancoDeDados = ConectaBD.getInstance();

    public HelicopteroBD() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Helicoptero hel) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO helicopteros "
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

            PreparedStatement pst = con.prepareStatement("DELETE FROM helicopteros WHERE idhelicoptero = ?");
            pst.setString(1, hel.getIdentificacao());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Helicoptero hel) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE helicopteros SET modelo = ?, "
                    + "capacidade_de_passageiros = ?, capacidade_de_carga = ?, qtdHelices = ? "
                    + "WHERE idhelicoptero = ?");

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
            PreparedStatement pst = con.prepareStatement("SELECT * FROM helicopteros WHERE idhelicoptero = ? ");

            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                hel = new Helicoptero(rs.getInt("qtdHelices"),rs.getString("idhelicoptero"),rs.getString("modelo"),
                rs.getInt("capacidade_de_passageiros"),rs.getDouble("capacidade_de_carga"));

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

            PreparedStatement pst = con.prepareStatement("SELECT * FROM helicopteros");
            rs = pst.executeQuery();

            while (rs.next()) {

                heli.add(new Helicoptero(rs.getInt("qtdHelices"),rs.getString("idhelicoptero"),rs.getString("modelo"),
                rs.getInt("capacidade_de_passageiros"),rs.getDouble("capacidade_de_carga")));
            }

            return heli;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}
