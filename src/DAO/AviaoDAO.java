package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.*;

public class AviaoDAO {
    private Connection con;
    Conexao bancoDeDados = Conexao.getInstance();

    public AviaoDAO() {
        con = bancoDeDados.iniciaBanco();
    }  

    public void inserirNoBanco(Aviao av) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO aviao "
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

            PreparedStatement pst = con.prepareStatement("DELETE FROM aviao WHERE identificacao = ?");
            pst.setString(1, av.getIdentificacao());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Aviao av) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE aviao SET modelo = ?, "
                    + "capac_passageiros = ?, capac_carga = ?, qtd_turbinas = ?, "
                    + "capac_combust_por_turbina = ? "
                    + "WHERE identificacao = ?");

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

    public Aviao consultar(String cod) {
        Aviao av;
        ResultSet rs;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM aviao WHERE identificacao = ? ");

            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                av = new Aviao(rs.getInt("qtd_turbinas"),rs.getFloat("capac_combust_por_turbina"),
                        rs.getString("identificacao"),rs.getString("modelo"),
                rs.getInt("capac_passageiros"),rs.getDouble("capac_carga"));

                return av;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList<Aviao> relatorio() {
        ArrayList<Aviao> av;
        ResultSet rs;

        try {
            av = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM aviao");
            rs = pst.executeQuery();

            while (rs.next()) {

                av.add(new Aviao(rs.getInt("qtd_turbinas"),rs.getFloat("capac_combust_por_turbina"),
                        rs.getString("identificacao"),rs.getString("modelo"),
                rs.getInt("capac_passageiros"),rs.getDouble("capac_carga")));
            }

            return av;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}
