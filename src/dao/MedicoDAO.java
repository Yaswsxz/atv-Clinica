package dao;

import model.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    // Inserir médico
    public void inserir(Medico m) throws SQLException {
        String sql = "INSERT INTO medicos (nome, crm, especialidade, telefone, email) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.setString(3, m.getEspecialidade());
            ps.setString(4, m.getTelefone());
            ps.setString(5, m.getEmail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    m.setId(rs.getInt(1));
                }
            }
        }
    }

    // Listar todos os médicos
    public List<Medico> listarTodos() throws SQLException {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicos ORDER BY id";

        try (Connection con = ConexaoDB.getInstancia().getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Medico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("crm"),
                    rs.getString("especialidade"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        }
        return lista;
    }

    // Buscar médico por ID
    public Medico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM medicos WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Medico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("crm"),
                        rs.getString("especialidade"),
                        rs.getString("telefone"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    // Atualizar médico
    public void atualizar(Medico m) throws SQLException {
        String sql = "UPDATE medicos SET nome = ?, telefone = ?, email = ? WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, m.getNome());
            ps.setString(2, m.getTelefone());
            ps.setString(3, m.getEmail());
            ps.setInt(4, m.getId());
            ps.executeUpdate();
        }
    }

    // Deletar médico
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM medicos WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}