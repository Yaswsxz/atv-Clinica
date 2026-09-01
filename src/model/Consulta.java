package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;

    public Consulta(int id, Paciente paciente, Medico medico, 
                    LocalDate dataConsulta, LocalTime horaConsulta) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public LocalDate getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDate dataConsulta) { this.dataConsulta = dataConsulta; }

    public LocalTime getHoraConsulta() { return horaConsulta; }
    public void setHoraConsulta(LocalTime horaConsulta) { this.horaConsulta = horaConsulta; }

    @Override
    public String toString() {
        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");
        return "Consulta[id=" + id + 
               ", paciente=" + paciente.getNome() + 
               ", medico=" + medico.getNome() + 
               ", data=" + dataConsulta.format(fmtData) + 
               ", hora=" + horaConsulta.format(fmtHora) + "]";
    }
}