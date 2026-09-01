package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_consultas";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    private static ConexaoDB instancia;
    private Connection conexao;

    private ConexaoDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado ao banco de dados!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado!");
            throw new SQLException("Driver não encontrado", e);
        }
    }

    public static ConexaoDB getInstancia() throws SQLException {
        if (instancia == null || instancia.conexao.isClosed()) {
            instancia = new ConexaoDB();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }

    public static void fecharConexao() throws SQLException {
        if (instancia != null && !instancia.conexao.isClosed()) {
            instancia.conexao.close();
            System.out.println("Conexão fechada.");
        }
    }
}