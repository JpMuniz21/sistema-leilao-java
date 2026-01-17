import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Leilao {
    private int idLeilao;
    private Date dataInicioLeilao;
    private Time horaInicioLeilao;
    private Date dataFimLeilao;
    private Time horaFimLeilao;
    private Boolean statusLeilao;

    // --- Construtor ---

    public Leilao(int id) {
        this.idLeilao = id;
        this.statusLeilao = false; 
        this.dataInicioLeilao = null;
        this.horaInicioLeilao = null;
        this.dataFimLeilao = null;
        this.horaFimLeilao = null;
    }

    // --- Getters e Setters ---

    public int getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(int idLeilao) {
        this.idLeilao = idLeilao;
    }

    public Date getDataInicioLeilao() {
        return dataInicioLeilao;
    }

    public void setDataInicioLeilao(Date dataInicioLeilao) {
        this.dataInicioLeilao = dataInicioLeilao;
    }

    public Time getHoraInicioLeilao() {
        return horaInicioLeilao;
    }

    public void setHoraInicioLeilao(Time horaInicioLeilao) {
        this.horaInicioLeilao = horaInicioLeilao;
    }

    public Date getDataFimLeilao() {
        return dataFimLeilao;
    }

    public void setDataFimLeilao(Date dataFimLeilao) {
        this.dataFimLeilao = dataFimLeilao;
    }

    public Time getHoraFimLeilao() {
        return horaFimLeilao;
    }

    public void setHoraFimLeilao(Time horaFimLeilao) {
        this.horaFimLeilao = horaFimLeilao;
    }

    public Boolean getStatusLeilao() {
        return statusLeilao;
    }

    public void setStatusLeilao(Boolean statusLeilao) {
        this.statusLeilao = statusLeilao;
    }

    public int getId() {
        return this.idLeilao;
    }

    // --- Métodos ---

    public Leilao consultarLeilao() throws Exception {
        FileReader fr = new FileReader("leiloes.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        Leilao leilaoEncontrado = null;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 6) continue;

            int idLido = Integer.parseInt(dados[0]);

            if (idLido == this.idLeilao) {
                leilaoEncontrado = new Leilao(idLido);
                
                if (!dados[1].equals("null")) leilaoEncontrado.dataInicioLeilao = Date.valueOf(dados[1]);
                if (!dados[2].equals("null")) leilaoEncontrado.horaInicioLeilao = Time.valueOf(dados[2]);
                if (!dados[3].equals("null")) leilaoEncontrado.dataFimLeilao = Date.valueOf(dados[3]);
                if (!dados[4].equals("null")) leilaoEncontrado.horaFimLeilao = Time.valueOf(dados[4]);
                
                leilaoEncontrado.statusLeilao = Boolean.parseBoolean(dados[5]);
            }
        }
        
        br.close();
        return leilaoEncontrado;
    }

    public Boolean registrarLeilao() throws Exception {
        FileWriter fw = new FileWriter("leiloes.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        String dIni = (this.dataInicioLeilao == null) ? "null" : this.dataInicioLeilao.toString();
        String hIni = (this.horaInicioLeilao == null) ? "null" : this.horaInicioLeilao.toString();
        String dFim = (this.dataFimLeilao == null) ? "null" : this.dataFimLeilao.toString();
        String hFim = (this.horaFimLeilao == null) ? "null" : this.horaFimLeilao.toString();

        String linha = this.idLeilao + "," +
                       dIni + "," + hIni + "," +
                       dFim + "," + hFim + "," +
                       this.statusLeilao;

        bw.write(linha);
        bw.newLine();
        bw.close();
        return true;
    }

    public Boolean iniciarLeilao(Date data, Time hora) {
        this.dataInicioLeilao = data;
        this.horaInicioLeilao = hora;
        this.statusLeilao = true;
        return true;
    } 

    public Boolean finalizarLeilao(Date data, Time hora) {
        this.dataFimLeilao = data;
        this.horaFimLeilao = hora;
        this.statusLeilao = false;
        return true;
    }

    public ArrayList<Leilao> listarLeiloes() throws Exception {
        ArrayList<Leilao> lista = new ArrayList<>();
        FileReader fr = new FileReader("leiloes.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 6) continue;

            Leilao l = new Leilao(Integer.parseInt(dados[0]));

            if (!dados[1].equals("null")) l.dataInicioLeilao = Date.valueOf(dados[1]);
            if (!dados[2].equals("null")) l.horaInicioLeilao = Time.valueOf(dados[2]);
            if (!dados[3].equals("null")) l.dataFimLeilao = Date.valueOf(dados[3]);
            if (!dados[4].equals("null")) l.horaFimLeilao = Time.valueOf(dados[4]);

            l.statusLeilao = Boolean.parseBoolean(dados[5]);

            lista.add(l);
        }
        br.close();
        return lista;
    }

    public void mostrar() {
        System.out.println("--- Dados do Leilao ---");
        System.out.println("ID: " + this.idLeilao);
        System.out.println("Status (Ativo?): " + this.statusLeilao);
        System.out.println("Inicio: " + this.dataInicioLeilao + " as " + this.horaInicioLeilao);
        System.out.println("Fim: " + this.dataFimLeilao + " as " + this.horaFimLeilao);
        System.out.println("-----------------------");
    }

}