import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ItemLeilao {
    
    private int idItem;
    private String descricaoItem;
    private Double lanceMinimoItem;
    private Boolean itemArrematado;
    private Leilao leilao;
    private Lance lanceArrematante;

    // Construtor
    public ItemLeilao(int id, String descricao, Double lanceMinimo, Leilao leilao) {
        this.idItem = id;
        this.descricaoItem = descricao;
        this.lanceMinimoItem = lanceMinimo;
        this.leilao = leilao;
        this.itemArrematado = false;
        this.lanceArrematante = null;
    }

    // Getters e Setters

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Double getLanceMinimoItem() {
        return lanceMinimoItem;
    }

    public void setLanceMinimoItem(Double lanceMinimoItem) {
        this.lanceMinimoItem = lanceMinimoItem;
    }

    public Boolean getItemArrematado() {
        return itemArrematado;
    }

    public void setItemArrematado(Boolean itemArrematado) {
        this.itemArrematado = itemArrematado;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }

    public Lance getLanceArrematante() {
        return lanceArrematante;
    }

    public void setLanceArrematante(Lance lanceArrematante) {
        this.lanceArrematante = lanceArrematante;
    }

    // Métodos

    public Boolean registrarItem() throws Exception {
        FileWriter fw = new FileWriter("itens.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        String linha = this.idItem + "," +
                       this.descricaoItem + "," + 
                       this.lanceMinimoItem + "," + 
                       this.itemArrematado + "," + 
                       this.leilao.getIdLeilao(); 

        bw.write(linha);
        bw.newLine();
        bw.close();
        return true;
    }
    
    public ItemLeilao consultarItem() throws Exception {
        FileReader fr = new FileReader("itens.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        ItemLeilao itemEncontrado = null;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 5) continue;

            int idLido = Integer.parseInt(dados[0]);

            // Se achar o ID no arquivo
            if (idLido == this.idItem) {
                // Recupera o Leilão pelo ID salvo (coluna 4)
                Leilao l = new Leilao(Integer.parseInt(dados[4]));
                
                itemEncontrado = new ItemLeilao(
                    idLido, 
                    dados[1], 
                    Double.parseDouble(dados[2]), 
                    l
                );
                itemEncontrado.itemArrematado = Boolean.parseBoolean(dados[3]);
            }
        }
        br.close();
        return itemEncontrado;
    }

    public void arrematarItem(Lance lance) {
        this.itemArrematado = true;
        this.lanceArrematante = lance;
    }

    public ArrayList<ItemLeilao> listarItens() throws Exception {
        ArrayList<ItemLeilao> lista = new ArrayList<>();
        FileReader fr = new FileReader("itens.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 5) continue;

            int id = Integer.parseInt(dados[0]);
            String desc = dados[1];
            Double min = Double.parseDouble(dados[2]);
            Boolean arrematado = Boolean.parseBoolean(dados[3]);
            int idLeilao = Integer.parseInt(dados[4]);

            Leilao leilaoDoItem = new Leilao(idLeilao);

            ItemLeilao item = new ItemLeilao(id, desc, min, leilaoDoItem);
            item.itemArrematado = arrematado;

            lista.add(item);
        }
        br.close();
        return lista;
    }

    public void mostrar() {
        System.out.println("--- Dados do Item ---");
        System.out.println("ID: " + this.idItem);
        System.out.println("Descricao: " + this.descricaoItem);
        System.out.println("Valor Minimo: " + this.lanceMinimoItem);
        System.out.println("Ja foi arrematado? " + this.itemArrematado);
        
        if(this.leilao != null) {
            System.out.println("Pertence ao Leilao ID: " + this.leilao.getIdLeilao());
        }
        System.out.println("---------------------");
    }
}