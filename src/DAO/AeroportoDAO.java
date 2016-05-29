package DAO;

import java.sql.*;
import java.util.ArrayList;
import tabajara.airlines.Aeroporto;
import tabajara.airlines.Cidade;

public class AeroportoBD {

    private Connection con;
    private Statement stm;
    ConectaBD bancoDeDados = ConectaBD.getInstance();

    public AeroportoBD() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Aeroporto aer) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO aeroportos "
                    + "VALUES (?,?,?)");

            pst.setString(1, aer.getIdentificacao());
            pst.setString(2, aer.getNome());
            pst.setInt(3, aer.getCidade().getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Aeroporto aer) {
        try {

            PreparedStatement pst = con.prepareStatement("DELETE FROM aeroportos WHERE idaeroporto = ?");
            pst.setString(1, aer.getIdentificacao());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Aeroporto aer) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE aeroportos SET  nome = ?, "
                    + "cidade = ? WHERE idaeroporto = ?");

            pst.setString(1, aer.getNome());
            pst.setInt(2, aer.getCidade().getIdentificacao());
            pst.setString(3, aer.getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public Aeroporto consultar(String cod) {

        Aeroporto aer;
        ResultSet rs;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM aeroportos "
                    + "INNER JOIN cidades ON aeroportos.cidade = cidades.idcidade WHERE idaeroporto = ?");

            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                aer = new Aeroporto(rs.getString("idaeroporto"),
                        rs.getString("nome"),new Cidade(rs.getInt("idcidade"), rs.getString("pais"), 
                        rs.getString("estado"),rs.getString("nomeCidade")));

                return aer;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList relatorio() {
        ArrayList<Aeroporto> aeroportos;
        ResultSet rs;

        try {
            aeroportos = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM aeroportos INNER JOIN "
                    + "cidades ON aeroportos.cidade = cidades.idcidade");

            rs = pst.executeQuery();

            while (rs.next()) {

                aeroportos.add(new Aeroporto(rs.getString("idaeroporto"),
                        rs.getString("nome"),new Cidade(rs.getInt("idcidade"), rs.getString("pais"), 
                        rs.getString("estado"),rs.getString("nomeCidade"))));
            }
            return aeroportos;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

}
