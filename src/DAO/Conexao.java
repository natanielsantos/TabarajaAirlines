package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            String url = "jdbc:postgresql://localhost:5432/tabajara",
                    usuario = "postgres",
                    senha = "admin";
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
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
