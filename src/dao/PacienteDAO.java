package dao;

import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void inserir(Paciente p) throws SQLException {
        String sql = "INSERT INTO pacientes (nome, cpf, telefone) VALUES (?, ?, ?)";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCpf());
            ps.setString(3, p.getTelefone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Paciente> listarTodos() throws SQLException {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";

        try (Connection con = ConexaoDB.getInstancia().getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Paciente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                ));
            }
        }
        return lista;
    }

    public void atualizar(Paciente p) throws SQLException {
        String sql = "UPDATE pacientes SET nome = ?, telefone = ? WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTelefone());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM pacientes WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Paciente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                    );
                }
            }
        }
        return null;
    }
}