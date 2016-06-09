package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.Cidade;

public class CidadeDAO {
    
    private Connection con;
    private Statement stm;
    Conexao bancoDeDados = Conexao.getInstance();

    public CidadeDAO() {
        con = bancoDeDados.iniciaBanco();
    }
    
    public void inserirNoBanco(Cidade cid) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO cidade "
                    + "VALUES (?,?,?,?)");
            
            pst.setInt(1, cid.getIdentificacao());
            pst.setString(2, cid.getNome());
            pst.setString(3, cid.getPais());
            pst.setString(4, cid.getEstado());
            
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void excluirDoBanco(Cidade cid) {
        try {
            
            PreparedStatement pst = con.prepareStatement("DELETE FROM cidade WHERE identificacao = ?");
            pst.setInt(1, cid.getIdentificacao());
            pst.execute();
      
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
    
    public void alterarNoBanco(Cidade cid) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE cidade SET pais = ?,"
                    + " estado = ? ,municipio = ? WHERE identificacao = ?");

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
            PreparedStatement pst = con.prepareStatement("SELECT identificacao, municipio, pais, estado "
                    + "FROM cidade"
                    + " WHERE identificacao = ? ");
            
            pst.setInt(1, (int)cod);
            rs= pst.executeQuery();
            
            if (rs.next()) {
              cid = new Cidade(rs.getInt("identificacao"), rs.getString("municipio"), rs.getString("pais"), rs.getString("estado"));

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

            PreparedStatement pst = con.prepareStatement("SELECT * FROM cidade");
            rs = pst.executeQuery();
       
            while (rs.next()) {

                cidades.add(new Cidade(rs.getInt("identificacao"), rs.getString("municipio"), rs.getString("pais"), rs.getString("estado")
                        ));
            }
            return cidades;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

}
