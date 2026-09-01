import model.Paciente;
import model.Medico;
import model.Consulta;
import dao.PacienteDAO;
import dao.MedicoDAO;
import dao.ConsultaDAO;
import dao.ConexaoDB;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static MedicoDAO medicoDAO = new MedicoDAO();
    private static ConsultaDAO consultaDAO = new ConsultaDAO();

    public static void main(String[] args) {
        try {
            ConexaoDB.getInstancia();
            int opcao;

            do {
                System.out.println("\n===================================");
                System.out.println("  SISTEMA DE AGENDAMENTO");
                System.out.println("===================================");
                System.out.println("1 - Pacientes");
                System.out.println("2 - Médicos");
                System.out.println("3 - Consultas");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> menuPacientes();
                    case 2 -> menuMedicos();
                    case 3 -> menuConsultas();
                    case 0 -> {
                        System.out.println("Encerrando...");
                        ConexaoDB.fecharConexao();
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } while (opcao != 0);

        } catch (SQLException e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // ==================== MENU PACIENTES ====================
    private static void menuPacientes() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Listar pacientes");
            System.out.println("3 - Buscar paciente");
            System.out.println("4 - Alterar paciente");
            System.out.println("5 - Excluir paciente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> listarPacientes();
                case 3 -> buscarPaciente();
                case 4 -> alterarPaciente();
                case 5 -> excluirPaciente();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarPaciente() throws SQLException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome obrigatório!");
            return;
        }

        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        if (cpf.trim().isEmpty()) {
            System.out.println("CPF obrigatório!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Paciente p = new Paciente(0, nome, cpf, telefone);
        pacienteDAO.inserir(p);
        System.out.println("Paciente cadastrado: " + p);
    }

    private static void listarPacientes() throws SQLException {
        System.out.println("\n--- LISTA DE PACIENTES ---");
        var pacientes = pacienteDAO.listarTodos();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            pacientes.forEach(System.out::println);
        }
    }

    private static void buscarPaciente() throws SQLException {
        System.out.print("ID do paciente: ");
        int id = sc.nextInt();
        sc.nextLine();
        Paciente p = pacienteDAO.buscarPorId(id);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Paciente não encontrado!");
        }
    }

    private static void alterarPaciente() throws SQLException {
        System.out.print("ID do paciente: ");
        int id = sc.nextInt();
        sc.nextLine();
        Paciente p = pacienteDAO.buscarPorId(id);
        if (p != null) {
            System.out.print("Novo nome (" + p.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) p.setNome(nome);

            System.out.print("Novo telefone (" + p.getTelefone() + "): ");
            String telefone = sc.nextLine();
            if (!telefone.isEmpty()) p.setTelefone(telefone);

            pacienteDAO.atualizar(p);
            System.out.println("Paciente atualizado!");
        } else {
            System.out.println("Paciente não encontrado!");
        }
    }

    private static void excluirPaciente() throws SQLException {
        System.out.print("ID do paciente: ");
        int id = sc.nextInt();
        sc.nextLine();
        pacienteDAO.deletar(id);
        System.out.println("Paciente removido!");
    }

    // ==================== MENU MÉDICOS ====================
    private static void menuMedicos() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- MÉDICOS ---");
            System.out.println("1 - Cadastrar médico");
            System.out.println("2 - Listar médicos");
            System.out.println("3 - Buscar médico");
            System.out.println("4 - Alterar médico");
            System.out.println("5 - Excluir médico");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarMedico();
                case 2 -> listarMedicos();
                case 3 -> buscarMedico();
                case 4 -> alterarMedico();
                case 5 -> excluirMedico();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarMedico() throws SQLException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome obrigatório!");
            return;
        }

        System.out.print("CRM: ");
        String crm = sc.nextLine();
        if (crm.trim().isEmpty()) {
            System.out.println("CRM obrigatório!");
            return;
        }

        System.out.print("Especialidade: ");
        String especialidade = sc.nextLine();
        if (especialidade.trim().isEmpty()) {
            System.out.println("Especialidade obrigatória!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Medico m = new Medico(0, nome, crm, especialidade, telefone, email);
        medicoDAO.inserir(m);
        System.out.println("Médico cadastrado: " + m);
    }

    private static void listarMedicos() throws SQLException {
        System.out.println("\n--- LISTA DE MÉDICOS ---");
        var medicos = medicoDAO.listarTodos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
        } else {
            medicos.forEach(System.out::println);
        }
    }

    private static void buscarMedico() throws SQLException {
        System.out.print("ID do médico: ");
        int id = sc.nextInt();
        sc.nextLine();
        Medico m = medicoDAO.buscarPorId(id);
        if (m != null) {
            System.out.println(m);
        } else {
            System.out.println("Médico não encontrado!");
        }
    }

    private static void alterarMedico() throws SQLException {
        System.out.print("ID do médico: ");
        int id = sc.nextInt();
        sc.nextLine();
        Medico m = medicoDAO.buscarPorId(id);
        if (m != null) {
            System.out.print("Novo nome (" + m.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) m.setNome(nome);

            System.out.print("Novo telefone (" + m.getTelefone() + "): ");
            String telefone = sc.nextLine();
            if (!telefone.isEmpty()) m.setTelefone(telefone);

            System.out.print("Novo email (" + m.getEmail() + "): ");
            String email = sc.nextLine();
            if (!email.isEmpty()) m.setEmail(email);

            medicoDAO.atualizar(m);
            System.out.println("Médico atualizado!");
        } else {
            System.out.println("Médico não encontrado!");
        }
    }

    private static void excluirMedico() throws SQLException {
        System.out.print("ID do médico: ");
        int id = sc.nextInt();
        sc.nextLine();
        medicoDAO.deletar(id);
        System.out.println("Médico removido!");
    }

    // ==================== MENU CONSULTAS ====================
    private static void menuConsultas() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar consulta");
            System.out.println("2 - Listar consultas");
            System.out.println("3 - Buscar consulta");
            System.out.println("4 - Alterar consulta");
            System.out.println("5 - Cancelar consulta");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> agendarConsulta();
                case 2 -> listarConsultas();
                case 3 -> buscarConsulta();
                case 4 -> alterarConsulta();
                case 5 -> cancelarConsulta();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void agendarConsulta() throws SQLException {
        System.out.print("Código do paciente: ");
        int idPaciente = sc.nextInt();
        sc.nextLine();

        Paciente paciente = pacienteDAO.buscarPorId(idPaciente);
        if (paciente == null) {
            System.out.println("Paciente não encontrado!");
            return;
        }

        System.out.print("Código do médico: ");
        int idMedico = sc.nextInt();
        sc.nextLine();

        Medico medico = medicoDAO.buscarPorId(idMedico);
        if (medico == null) {
            System.out.println("Médico não encontrado!");
            return;
        }

        System.out.print("Data (AAAA-MM-DD): ");
        String dataStr = sc.nextLine();
        LocalDate data = LocalDate.parse(dataStr);

        System.out.print("Hora (HH:MM): ");
        String horaStr = sc.nextLine();
        LocalTime hora = LocalTime.parse(horaStr);

        Consulta c = new Consulta(0, paciente, medico, data, hora);
        consultaDAO.inserir(c);
        System.out.println("Consulta agendada: " + c);
    }

    private static void listarConsultas() throws SQLException {
        System.out.println("\n--- LISTA DE CONSULTAS ---");
        var consultas = consultaDAO.listarTodos();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
        } else {
            consultas.forEach(System.out::println);
        }
    }

    private static void buscarConsulta() throws SQLException {
        System.out.print("ID da consulta: ");
        int id = sc.nextInt();
        sc.nextLine();
        Consulta c = consultaDAO.buscarPorId(id);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("Consulta não encontrada!");
        }
    }

    private static void alterarConsulta() throws SQLException {
        System.out.print("ID da consulta: ");
        int id = sc.nextInt();
        sc.nextLine();
        Consulta c = consultaDAO.buscarPorId(id);
        if (c != null) {
            System.out.print("Nova data (" + c.getDataConsulta() + "): ");
            String dataStr = sc.nextLine();
            if (!dataStr.isEmpty()) {
                c.setDataConsulta(LocalDate.parse(dataStr));
            }

            System.out.print("Novo horário (" + c.getHoraConsulta() + "): ");
            String horaStr = sc.nextLine();
            if (!horaStr.isEmpty()) {
                c.setHoraConsulta(LocalTime.parse(horaStr));
            }

            consultaDAO.atualizar(c);
            System.out.println("Consulta atualizada!");
        } else {
            System.out.println("Consulta não encontrada!");
        }
    }

    private static void cancelarConsulta() throws SQLException {
        System.out.print("ID da consulta: ");
        int id = sc.nextInt();
        sc.nextLine();
        consultaDAO.deletar(id);
        System.out.println("Consulta cancelada!");
    }
}