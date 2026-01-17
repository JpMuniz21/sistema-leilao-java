import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Lance {

    private int idLance;
    private Double valorLance;
    private Date dataLance;
    private Time horaLance;
    private Participante participante;
    private ItemLeilao itemLeilao;

     // --- Construtor ---
    public Lance(int id, Participante p, ItemLeilao item, Double valor, Date data, Time hora) {
        this.idLance = id;
        this.participante = p;
        this.itemLeilao = item;
        this.valorLance = valor;
        this.dataLance = data;
        this.horaLance = hora;
    }

    // Getters e Setters

    public int getIdLance() {
        return idLance;
    }

    public void setIdLance(int idLance) {
        this.idLance = idLance;
    }

    public Double getValorLance() {
        return valorLance;
    }

    public void setValorLance(Double valorLance) {
        this.valorLance = valorLance;
    }

    public Date getDataLance() {
        return dataLance;
    }

    public void setDataLance(Date dataLance) {
        this.dataLance = dataLance;
    }

    public Time getHoraLance() {
        return horaLance;
    }

    public void setHoraLance(Time horaLance) {
        this.horaLance = horaLance;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public ItemLeilao getItemLeilao() {
        return itemLeilao;
    }

    public void setItemLeilao(ItemLeilao itemLeilao) {
        this.itemLeilao = itemLeilao;
    }

    // --- Métodos ---

    public Boolean registrarLance() throws Exception {
        FileWriter fw = new FileWriter("lances.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        String linha = this.idLance + "," +
                       this.participante.getId() + "," + 
                       this.itemLeilao.getIdItem() + "," +
                       this.valorLance + "," +
                       this.dataLance + "," +
                       this.horaLance;

        bw.write(linha);
        bw.newLine();
        bw.close();
        
        this.itemLeilao.arrematarItem(this);
        
        return true;
    }

    public ArrayList<Lance> listarLances() throws Exception {
        ArrayList<Lance> lista = new ArrayList<>();
        FileReader fr = new FileReader("lances.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 6) continue;

            int idL = Integer.parseInt(dados[0]);
            int idPart = Integer.parseInt(dados[1]);
            int idItem = Integer.parseInt(dados[2]);
            Double valor = Double.parseDouble(dados[3]);
            
            Date dataArq = Date.valueOf(dados[4]);
            Time horaArq = Time.valueOf(dados[5]);
            
            Participante p = new Participante(idPart, "", "", "", "", "", "");
            ItemLeilao i = new ItemLeilao(idItem, "", 0.0, null);

            Lance lance = new Lance(idL, p, i, valor, dataArq, horaArq);

            lista.add(lance);
        }
        br.close();
        return lista;
    }

    public void mostrar() {
        System.out.println("--- Dados do Lance ---");
        System.out.println("ID Lance: " + this.idLance);
        System.out.println("Valor Ofertado: " + this.valorLance);
        System.out.println("Data: " + this.dataLance + " as " + this.horaLance);
        
        if(this.participante != null) {
            System.out.println("Feito por (ID Part.): " + this.participante.getId());
        }
        if(this.itemLeilao != null) {
            System.out.println("Para o Item (ID Item): " + this.itemLeilao.getIdItem());
        }
        System.out.println("----------------------");
    }
}