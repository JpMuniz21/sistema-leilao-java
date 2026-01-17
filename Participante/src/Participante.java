import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Participante {
    private int idParticipante;
    private String nomeParticipante;
    private String loginParticipante;
    private String emailParticipante;
    private String senhaParticipante;
    private String enderecoParticipante;
    private String telefoneParticipante;

    // --- Construtor ---

    public Participante(int idParticipante, String nomeParticipante, String loginParticipante, String emailParticipante, String senhaParticipante, String enderecoParticipante, String telefoneParticipante) {
        this.idParticipante = idParticipante;
        this.nomeParticipante = nomeParticipante;
        this.loginParticipante = loginParticipante;
        this.emailParticipante = emailParticipante;
        this.senhaParticipante = senhaParticipante;
        this.enderecoParticipante = enderecoParticipante;
        this.telefoneParticipante = telefoneParticipante;
    }

    // --- Getters e Setters --- 
    
    public int getId() {
        return idParticipante;
    }

    public void setId(int id) {
        this.idParticipante = id;
    }

    public String getNome() {
        return nomeParticipante;
    }

    public void setNome(String nome) {
        this.nomeParticipante = nome;
    }

    public String getLogin() {
        return loginParticipante;
    }

    public void setLogin(String login) {
        this.loginParticipante = login;
    }

    public String getEmail() {
        return emailParticipante;
    }

    public void setEmail(String email) {
        this.emailParticipante = email;
    }

    public String getSenha() {
        return senhaParticipante;
    }

    public void setSenha(String senha) {
        this.senhaParticipante = senha;
    }

    public String getEndereco() {
        return enderecoParticipante;
    }

    public void setEndereco(String endereco) {
        this.enderecoParticipante = endereco;
    }

    public String getTelefone() {
        return telefoneParticipante;
    }

    public void setTelefone(String telefone) {
        this.telefoneParticipante = telefone;
    }

    // --- Métodos ---

    public Participante loginParticipante() throws Exception {
        FileReader fr = new FileReader("participantes.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        Participante participanteEncontrado = null;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 7) continue;
            
            if (dados[2].equals(this.loginParticipante) && dados[4].equals(this.senhaParticipante)) {
                participanteEncontrado = new Participante(
                    Integer.parseInt(dados[0]),
                    dados[1],
                    dados[2],
                    dados[3],
                    dados[4],
                    dados[5],
                    dados[6]
                );
                break;
            }
        }
        
        br.close();
        return participanteEncontrado;
    }

    public Boolean registrarParticipante() throws Exception {
        FileWriter fw = new FileWriter("participantes.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        String linha = this.idParticipante + "," + 
                       this.nomeParticipante + "," + 
                       this.loginParticipante + "," + 
                       this.emailParticipante + "," + 
                       this.senhaParticipante + "," + 
                       this.enderecoParticipante + "," + 
                       this.telefoneParticipante;
                       
        bw.write(linha);
        bw.newLine();
        bw.close();
        return true;
    }

    public ArrayList<Participante> listarParticipantes() throws Exception {
        ArrayList<Participante> lista = new ArrayList<>();
        FileReader fr = new FileReader("participantes.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 7) continue;

            Participante p = new Participante(
                Integer.parseInt(dados[0]),
                dados[1],
                dados[2],
                dados[3],
                dados[4],
                dados[5],
                dados[6]
            );

            lista.add(p);
        }

        br.close();
        return lista;
    }

    public void mostrar() {
        System.out.println("--- Dados do Participante ---");
        System.out.println("ID: " + this.idParticipante);
        System.out.println("Nome: " + this.nomeParticipante);
        System.out.println("Login: " + this.loginParticipante);
        System.out.println("Email: " + this.emailParticipante);
        System.out.println("Telefone: " + this.telefoneParticipante);
        System.out.println("-----------------------------");
    }
}