package dao;

import model.Consulta;
import model.Paciente;
import model.Medico;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void inserir(Consulta c) throws SQLException {
        String sql = "INSERT INTO consultas (paciente_id, medico_id, data_consulta, hora_consulta) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, c.getPaciente().getId());
            ps.setInt(2, c.getMedico().getId());
            ps.setDate(3, Date.valueOf(c.getDataConsulta()));
            ps.setTime(4, Time.valueOf(c.getHoraConsulta()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Consulta> listarTodos() throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT c.*, " +
                     "p.nome as paciente_nome, p.cpf, p.telefone as paciente_telefone, " +
                     "m.nome as medico_nome, m.crm, m.especialidade, m.telefone as medico_telefone, m.email " +
                     "FROM consultas c " +
                     "JOIN pacientes p ON c.paciente_id = p.id " +
                     "JOIN medicos m ON c.medico_id = m.id " +
                     "ORDER BY c.data_consulta, c.hora_consulta";

        try (Connection con = ConexaoDB.getInstancia().getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getInt("paciente_id"),
                    rs.getString("paciente_nome"),
                    rs.getString("cpf"),
                    rs.getString("paciente_telefone")
                );
                
                Medico m = new Medico(
                    rs.getInt("medico_id"),
                    rs.getString("medico_nome"),
                    rs.getString("crm"),
                    rs.getString("especialidade"),
                    rs.getString("medico_telefone"),
                    rs.getString("email")
                );
                
                Consulta c = new Consulta(
                    rs.getInt("id"),
                    p,
                    m,
                    rs.getDate("data_consulta").toLocalDate(),
                    rs.getTime("hora_consulta").toLocalTime()
                );
                lista.add(c);
            }
        }
        return lista;
    }

    public Consulta buscarPorId(int id) throws SQLException {
        String sql = "SELECT c.*, " +
                     "p.nome as paciente_nome, p.cpf, p.telefone as paciente_telefone, " +
                     "m.nome as medico_nome, m.crm, m.especialidade, m.telefone as medico_telefone, m.email " +
                     "FROM consultas c " +
                     "JOIN pacientes p ON c.paciente_id = p.id " +
                     "JOIN medicos m ON c.medico_id = m.id " +
                     "WHERE c.id = ?";

        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paciente p = new Paciente(
                        rs.getInt("paciente_id"),
                        rs.getString("paciente_nome"),
                        rs.getString("cpf"),
                        rs.getString("paciente_telefone")
                    );
                    
                    Medico m = new Medico(
                        rs.getInt("medico_id"),
                        rs.getString("medico_nome"),
                        rs.getString("crm"),
                        rs.getString("especialidade"),
                        rs.getString("medico_telefone"),
                        rs.getString("email")
                    );
                    
                    return new Consulta(
                        rs.getInt("id"),
                        p,
                        m,
                        rs.getDate("data_consulta").toLocalDate(),
                        rs.getTime("hora_consulta").toLocalTime()
                    );
                }
            }
        }
        return null;
    }

    public void atualizar(Consulta c) throws SQLException {
        String sql = "UPDATE consultas SET paciente_id = ?, medico_id = ?, data_consulta = ?, hora_consulta = ? WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, c.getPaciente().getId());
            ps.setInt(2, c.getMedico().getId());
            ps.setDate(3, Date.valueOf(c.getDataConsulta()));
            ps.setTime(4, Time.valueOf(c.getHoraConsulta()));
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM consultas WHERE id = ?";
        
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}