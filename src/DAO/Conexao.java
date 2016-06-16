package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private Connection con;
    private static Conexao instance;

    static {
    }

    private Conexao() {
    }

    public static Conexao getInstance() {

        if (instance == null) {
            instance = new Conexao();
        }
        return instance;
    }

    public Connection iniciaBanco() {

        try {
            String url = "jdbc:postgresql://localhost:5432/tabajara", // aqui e o endereço do banco de dados
                    usuario = "postgres", // usuario 
                    senha = "admin"; // senha
            Class.forName("org.postgresql.Driver"); // drive do postgree
            con = DriverManager.getConnection(url, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Banco conectado com sucesso!!");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            return null;
        }
        return con;
    }

    public void finalizaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
}
