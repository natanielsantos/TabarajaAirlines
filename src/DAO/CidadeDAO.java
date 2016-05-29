package DAO;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tabajara.airlines.Cidade;
import tabajara.airlines.Cliente;

public class CidadeBD {
    
    private Connection con;
    private Statement stm;
    ConectaBD bancoDeDados = ConectaBD.getInstance();

    public CidadeBD() {
        con = bancoDeDados.iniciaBanco();
    }
    
    public void inserirNoBanco(Cidade cid) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO cidades "
                    + "VALUES (?,?,?,?)");
            
            pst.setInt(1, cid.getIdentificacao());
            pst.setString(2, cid.getPais());
            pst.setString(3, cid.getEstado());
            pst.setString(4, cid.getNome());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Cidade cid) {
        try {
            
            PreparedStatement pst = con.prepareStatement("DELETE FROM cidades WHERE idcidade = ?");
            pst.setInt(1, cid.getIdentificacao());
            pst.execute();
      
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
    
    public void alterarNoBanco(Cidade cid) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE cidades SET pais = ?,"
                    + " estado = ? ,nomeCidade = ? WHERE idcidade = ?");

            pst.setString(1, cid.getPais());
            pst.setString(2, cid.getEstado());
            pst.setString(3, cid.getNome());
            pst.setInt(4, cid.getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
    
     public Cidade consultar(long cod) {
        Cidade cid;
        ResultSet rs;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT idcidade, pais, estado ,nomeCidade "
                    + "FROM cidades"
                    + " WHERE idcidade = ? ");
            
            pst.setInt(1, (int) cod);
            rs= pst.executeQuery();
            
            if (rs.next()) {
                cid = new Cidade(rs.getInt("idcidade"), rs.getString("pais"), rs.getString("estado"),
                        rs.getString("nomeCidade"));

                return cid;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

     public ArrayList relatorio() {
        ArrayList<Cidade> cidades;
        ResultSet rs;

        try {
            cidades = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM cidades");
            rs = pst.executeQuery();
       
            while (rs.next()) {

                cidades.add(new Cidade(rs.getInt("idcidade"), rs.getString("pais"), rs.getString("estado"),
                        rs.getString("nomeCidade")));
            }
            return cidades;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

}
