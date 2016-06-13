package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.Aeroporto;
import model.Cidade;

public class AeroportoDAO {

    private Connection con;
    Conexao bancoDeDados = Conexao.getInstance();

    public AeroportoDAO() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Aeroporto aer) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO aeroporto "
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

            PreparedStatement pst = con.prepareStatement("DELETE FROM aeroporto WHERE idaeroporto = ?");
            pst.setString(1, aer.getIdentificacao());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Aeroporto aer) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE aeroporto SET  nome = ?, "
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
            PreparedStatement pst = con.prepareStatement("SELECT * FROM aeroporto "
                    + "INNER JOIN cidade ON aeroporto.cidade = cidade.idcidade WHERE idaeroporto = ?");

            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                aer = new Aeroporto(rs.getString("idaeroporto"),
                        rs.getString("nome"),new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), 
                        rs.getString("pais"),rs.getString("estado")));

                return aer;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public ArrayList<Aeroporto> relatorio() {
        ArrayList<Aeroporto> aeroportos;
        ResultSet rs;

        try {
            aeroportos = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM aeroporto INNER JOIN "
                    + "cidade ON aeroporto.cidade = cidade.idcidade");

            rs = pst.executeQuery();

            while (rs.next()) {

                aeroportos.add(new Aeroporto(rs.getString("idaeroporto"),
                        rs.getString("nome"),new Cidade(rs.getInt("idcidade"), rs.getString("municipio"), 
                        rs.getString("pais"),rs.getString("estado"))));
            }
            return aeroportos;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

}
