package service;

import model.Consulta;
import model.Paciente;
import model.Medico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Agenda {
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();
    private int proximoIdPaciente = 1;
    private int proximoIdMedico = 1;
    private int proximoIdConsulta = 1;

    // PACIENTES
    public Paciente cadastrarPaciente(String nome, String cpf, String telefone) {
        Paciente p = new Paciente(proximoIdPaciente++, nome, cpf, telefone);
        pacientes.add(p);
        return p;
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    public Optional<Paciente> buscarPacientePorId(int id) {
        return pacientes.stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean removerPaciente(int id) {
        return pacientes.removeIf(p -> p.getId() == id);
    }

    // MÉDICOS
    public Medico cadastrarMedico(String nome, String crm, String especialidade, String telefone, String email) {
        Medico m = new Medico(proximoIdMedico++, nome, crm, especialidade, telefone, email);
        medicos.add(m);
        return m;
    }

    public List<Medico> listarMedicos() {
        return new ArrayList<>(medicos);
    }

    public Optional<Medico> buscarMedicoPorId(int id) {
        return medicos.stream().filter(m -> m.getId() == id).findFirst();
    }

    public boolean removerMedico(int id) {
        return medicos.removeIf(m -> m.getId() == id);
    }

    // CONSULTAS
    public Consulta agendarConsulta(Paciente paciente, Medico medico, 
                                    LocalDate dataConsulta, LocalTime horaConsulta) {
        Consulta c = new Consulta(proximoIdConsulta++, paciente, medico, dataConsulta, horaConsulta);
        consultas.add(c);
        return c;
    }

    public List<Consulta> listarConsultas() {
        return new ArrayList<>(consultas);
    }

    public boolean cancelarConsulta(int id) {
        return consultas.removeIf(c -> c.getId() == id);
    }
}