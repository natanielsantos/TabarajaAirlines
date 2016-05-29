package DAO;

import java.sql.*;
import java.util.ArrayList;
import tabajara.airlines.*;

public class AviaoBD {
    private Connection con;
    private Statement stm;
    ConectaBD bancoDeDados = ConectaBD.getInstance();

    public AviaoBD() {
        con = bancoDeDados.iniciaBanco();
    }  

    public void inserirNoBanco(Aviao av) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO avioes "
                    + "VALUES (?,?,?,?,?,?)");
            pst.setString(1, av.getIdentificacao());
            pst.setString(2,av.getModelo() );
            pst.setInt(3,av.getCapacPassageiros() );
            pst.setDouble(4,av.getCapacCarga() );
            pst.setInt(5, av.getQtdTurbinas() );
            pst.setFloat(6, av.getCapcCombustPorTurbina());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Aviao av) {
        try {

            PreparedStatement pst = con.prepareStatement("DELETE FROM avioes WHERE idaviao = ?");
            pst.setString(1, av.getIdentificacao());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Aviao av) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE avioes SET modelo = ?, "
                    + "capacidade_de_passageiros = ?, capacidade_de_carga = ?, qtdTurbinas = ?, "
                    + "capacCombustPorturbina = ? "
                    + "WHERE idaviao = ?");

            pst.setString(1, av.getModelo());
            pst.setInt(2, av.getCapacPassageiros());
            pst.setDouble(3, av.getCapacCarga());
            pst.setInt(4, av.getQtdTurbinas());
            pst.setFloat(5, av.getCapcCombustPorTurbina());
            pst.setString(6, av.getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public Aviao consultar(long cod) {
        Aviao av;
        ResultSet rs;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM avioes WHERE idaviao = ? ");

            pst.setInt(1, (int) cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                av = new Aviao(rs.getInt("qtdTurbinas"),rs.getFloat("capacCombustPorturbina"),
                        rs.getString("idaviao"),rs.getString("modelo"),
                rs.getInt("capacidade_de_passageiros"),rs.getDouble("capacidade_de_carga"));

                return av;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList relatorio() {
        ArrayList<Aviao> av;
        ResultSet rs;

        try {
            av = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM avioes");
            rs = pst.executeQuery();

            while (rs.next()) {

                av.add(new Aviao(rs.getInt("qtdTurbinas"),rs.getFloat("capacCombustPorturbina"),
                        rs.getString("idaviao"),rs.getString("modelo"),
                rs.getInt("capacidade_de_passageiros"),rs.getDouble("capacidade_de_carga")));
            }

            return av;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}
