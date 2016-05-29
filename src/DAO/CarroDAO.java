package DAO;

import java.sql.*;
import java.util.ArrayList;
import tabajara.airlines.*;

public class CarroBD {

    private Connection con;
    private Statement stm;
    ConectaBD bancoDeDados = ConectaBD.getInstance();

    public CarroBD() {
        con = bancoDeDados.iniciaBanco();
    }

    public void inserirNoBanco(Carro car) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO carros "
                    + "VALUES (?,?,?,?,?,?,?,?)");

            pst.setString(1, car.getIdentificacao());
            pst.setString(2, car.getModelo());
            pst.setInt(3, car.getCapacPassageiros());
            pst.setDouble(4, car.getCapacCarga());
            pst.setFloat(5,car.getAutonomia() );
            pst.setString(6, car.getPiloto().getIdentidade());
            pst.setInt(7, car.getCidadeOrigem().getIdentificacao());
            pst.setInt(8, car.getCidadeDestino().getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

   public void excluirDoBanco(Carro car) {
        try {

            PreparedStatement pst = con.prepareStatement("DELETE FROM carros WHERE idcarro = ?");
            pst.setString(1, car.getIdentificacao());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public void alterarNoBanco(Carro car) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE carros SET modelo = ?, "
                    + "capacidade_de_passageiros = ?, capacidade_de_carga = ?, "
                    + "autonomia = ?, piloto = ?, cidadeOrigem = ?,cidadeDestino  = ?  "
                    + "WHERE idcarro = ?");

            pst.setString(1, car.getModelo());
            pst.setInt(2, car.getCapacPassageiros());
            pst.setDouble(3, car.getCapacCarga());
            pst.setFloat(4, car.getAutonomia());
            pst.setString(5, car.getPiloto().getIdentidade());
            pst.setInt(6, car.getCidadeOrigem().getIdentificacao());
            pst.setInt(7, car.getCidadeDestino().getIdentificacao());
            pst.setString(8, car.getIdentificacao());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public Carro consultar(String cod) {

        Carro car;
        ResultSet rs,rs2,rs3,rs4;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM carros WHERE identidade = ?");
            pst.setString(1, cod);
            rs = pst.executeQuery();
            int cidadeOrigem = rs.getInt("cidadeorigem");
            
            PreparedStatement pst2 = con.prepareStatement("SELECT * FROM cidades WHERE idcidade = ?");
            pst.setInt(1, cidadeOrigem);
            rs2 = pst2.executeQuery();
            Cidade cidOrigem = new Cidade(rs2.getInt("idcidade"),rs2.getString("pais"),rs2.getString("estado"),rs2.getString("nomecidade"));
            int cidadeDestino = rs.getInt("cidadedestino");
            
            PreparedStatement pst3 = con.prepareStatement("SELECT * FROM cidades WHERE idcidade = ?");
            pst.setInt(1, cidadeDestino);
            rs3 = pst3.executeQuery();
            Cidade cidDestino = new Cidade(rs3.getInt("idcidade"),rs3.getString("pais"),rs3.getString("estado"),rs3.getString("nomecidade"));
            
            String piloto;
            piloto = rs.getString("piloto");
            PreparedStatement pst4 = con.prepareStatement("SELECT * FROM pilotos WHERE identidade = ?");
            pst4.setString(1, piloto);
            Piloto pil = new Piloto(rs4)
            
            if()
            
            pst.setString(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                car = new Carro();

                return car;
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
