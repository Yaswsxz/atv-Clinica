package model;

public class Medico {
    private int id;
    private String nome;
    private String crm;
    private String especialidade;
    private String telefone;
    private String email;

    public Medico(int id, String nome, String crm, String especialidade, 
                  String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Medico{id=" + id + ", nome=" + nome + ", crm=" + crm + 
               ", especialidade=" + especialidade + "}";
    }
}