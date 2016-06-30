package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.*;

public class PassagemDAO {

    private Connection con;
    Conexao bancoDeDados = Conexao.getInstance();
    
    public PassagemDAO() {
        con = bancoDeDados.iniciaBanco();
    }

    public void vendaPassagem(Passagem passagem) {
        try {
                    
		        	PreparedStatement pst = con.prepareStatement("INSERT INTO passagem (cliente, voo,data_venda, hora_venda, preco_final_viagem,carga_cliente) "
		                    								   + "VALUES (?,?,?,?,?,?)");
		            
		            //pst.setInt(1, voo.getId_voo());
		            pst.setInt(1, passagem.getCliente().getIdentificacao());
		            pst.setInt(2, passagem.getVoo().getId_voo());
		            pst.setDate(3, java.sql.Date.valueOf(passagem.getDataVenda()));
		            pst.setTime(4, java.sql.Time.valueOf(passagem.getHoraVenda()));
		            pst.setDouble(5, passagem.getPrecoFinalViagem());
		            pst.setDouble(6, passagem.getCargaCliente());
		            pst.executeUpdate();
		            pst.close();
		            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public Passagem consultar(int cod) {// TODO Criar a consulta. Essencial para o restante do código.

        Passagem pas;
        ResultSet rs;

        try {
            PreparedStatement pst = con.prepareStatement("SELECT passagem.*,voo.*,cliente.*,aero.identificacao aero1, a1.idaeroporto partida, a2.idaeroporto chegada "
									            		+ "FROM passagem, voo, cliente, aeronave aero, aeroporto a1, aeroporto a2 "
									                    + "WHERE passagem.cliente = cliente.idcliente "
									                    + "AND passagem.voo = voo.id_voo "
									                    + "AND voo.aeronave = aero.identificacao "
									                    + "AND voo.aeroporto_partida = a1.idaeroporto "
									                    + "AND voo.aeroporto_chegada = a2.idaeroporto "
									                    + "AND passagem.identificacao = ?");

            pst.setInt(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
               pas =  new Passagem(rs.getInt("identificacao"),
            		   new Cliente(rs.getInt("idcliente"), rs.getString("nome"), rs.getString("logradouro"),
       						rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("estado"),
       						rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf")),
            		   new Voo(rs.getInt("id_voo"),
            		    new Aeronave(rs.getString("aero1")),
            		    new Aeroporto(rs.getString("partida")),
            		    rs.getDate("data_partida").toLocalDate(),
            		    rs.getTime("hora_partida").toLocalTime(),
            		   	new Aeroporto(rs.getString("chegada")),
            		   	rs.getDate("data_chegada").toLocalDate(),
            		   	rs.getTime("hora_chegada").toLocalTime(),
            		   	rs.getInt("lotacao"),
            		   	rs.getDouble("peso_carga_embarcada"),
            		   	rs.getDouble("preco_viagem"),
            		   	rs.getInt("tipo")),rs.getDate("data_venda").toLocalDate(),rs.getTime("hora_venda").toLocalTime(),
            		   	rs.getDouble("preco_final_viagem"),rs.getDouble("carga_cliente"),rs.getInt("status"));// TODO Realizar a alteração do status no banco.

                return pas;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
    
    public void cancelaPassagem(int cod) {

        try {
            PreparedStatement pst = con.prepareStatement("UPDATE passagem SET"
                    + " status = ? WHERE identificacao = ?");

            pst.setInt(1, 1);
            pst.setInt(2, cod);
            pst.executeUpdate();
            pst.close();

            } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public ArrayList<Passagem> relatorioPassageiroPorVoo(int cod) {
        ArrayList<Passagem> passagens;
        ResultSet rs;

       try {
            passagens = new ArrayList<>();

            PreparedStatement pst = con.prepareStatement("SELECT passagem.*,voo.*,cliente.*,aero.identificacao aero1, a1.idaeroporto partida, a2.idaeroporto chegada "
            		+ "FROM passagem, voo, cliente, aeronave aero, aeroporto a1, aeroporto a2 "
                    + "WHERE passagem.cliente = cliente.idcliente "
                    + "AND passagem.voo = voo.id_voo "
                    + "AND voo.aeronave = aero.identificacao "
                    + "AND voo.aeroporto_partida = a1.idaeroporto "
                    + "AND voo.aeroporto_chegada = a2.idaeroporto "
                    + "AND voo.id_voo = ? ");

            
            pst.setInt(1, cod);
            rs = pst.executeQuery();

            while (rs.next()) {

                passagens.add(new Passagem(rs.getInt("identificacao"),
             		   new Cliente(rs.getInt("idcliente"), rs.getString("nome"), rs.getString("logradouro"),
          						rs.getInt("numero"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("estado"),
          						rs.getString("cep"), rs.getString("telefone"), rs.getString("cpf")),
               		   new Voo(rs.getInt("id_voo"),
               		    new Aeronave(rs.getString("aero1")),
               		    new Aeroporto(rs.getString("partida")),
               		    rs.getDate("data_partida").toLocalDate(),
               		    rs.getTime("hora_partida").toLocalTime(),
               		   	new Aeroporto(rs.getString("chegada")),
               		   	rs.getDate("data_chegada").toLocalDate(),
               		   	rs.getTime("hora_chegada").toLocalTime(),
               		   	rs.getInt("lotacao"),
               		   	rs.getDouble("peso_carga_embarcada"),
               		   	rs.getDouble("preco_viagem"),
               		   	rs.getInt("tipo")),rs.getDate("data_venda").toLocalDate(),rs.getTime("hora_venda").toLocalTime(),
               		   	rs.getDouble("preco_final_viagem"),rs.getDouble("carga_cliente"),rs.getInt("status")));
            }
            return passagens;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }
}