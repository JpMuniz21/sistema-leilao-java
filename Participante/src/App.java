import java.util.Scanner;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Sistema de Leilao ---");
            System.out.println("1. Cadastrar Participante");
            System.out.println("2. Cadastrar Leilao");
            System.out.println("3. Iniciar um Leilao");
            System.out.println("4. Cadastrar Item de Leilao");
            System.out.println("5. Dar um Lance");
            System.out.println("6. Listar Leiloes");
            System.out.println("7. Listar itens de Leilao");
            System.out.println("8. Consultar Leilao por ID");
            System.out.println("9. Consultar Item por ID");
            System.out.println("10. Finalizar Leilao");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um numero valido.");
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.println("--- Novo Participante ---");
                    System.out.print("ID: ");
                    int idPart = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Login: ");
                    String login = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Endereco: ");
                    String end = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    Participante p = new Participante(idPart, nome, login, senha, email, end, tel);
                    p.registrarParticipante();
                    System.out.println("Participante salvo com sucesso!");
                    break;

                case 2:
                    System.out.println("--- Novo Leilao ---");
                    System.out.print("ID do Leilao: ");
                    int idLeilao = Integer.parseInt(sc.nextLine());
                    
                    Leilao l = new Leilao(idLeilao);
                    l.registrarLeilao(); 
                    System.out.println("Leilao cadastrado (Status: false)!");
                    break;

                case 3:
                    System.out.println("--- Iniciar Leilao ---");
                    System.out.print("Digite o ID do Leilao para iniciar: ");
                    int idLeilaoIni = Integer.parseInt(sc.nextLine());
                    
                    Leilao leilaoIni = new Leilao(idLeilaoIni);
 
                    leilaoIni.iniciarLeilao(
                        new Date(System.currentTimeMillis()), 
                        new Time(System.currentTimeMillis())
                    );
                    
                    leilaoIni.registrarLeilao(); 
                    System.out.println("Leilao INICIADO!");
                    break;
                
                case 4:
                    System.out.println("--- Novo Item ---");
                    System.out.print("ID do Item: ");
                    int idItem = Integer.parseInt(sc.nextLine());
                    System.out.print("Descricao: ");
                    String desc = sc.nextLine();
                    System.out.print("Lance Minimo: ");
                    Double min = Double.parseDouble(sc.nextLine());
                    System.out.print("ID do Leilao vinculado: ");
                    int idLeilaoVinc = Integer.parseInt(sc.nextLine());
                    
                    Leilao leilaoVinc = new Leilao(idLeilaoVinc);
                    ItemLeilao item = new ItemLeilao(idItem, desc, min, leilaoVinc);
                    item.registrarItem();
                    System.out.println("Item salvo!");
                    break;

                case 5: 
                    System.out.println("--- Novo Lance ---");
                    System.out.print("ID do Lance: ");
                    int idLance = Integer.parseInt(sc.nextLine());
                    System.out.print("Valor: ");
                    Double valor = Double.parseDouble(sc.nextLine());
                    System.out.print("ID Participante: ");
                    int idPartLance = Integer.parseInt(sc.nextLine());
                    System.out.print("ID Item: ");
                    int idItemLance = Integer.parseInt(sc.nextLine());

                    Participante pLance = new Participante(idPartLance, "", "", "", "", "", "");
                    ItemLeilao iLance = new ItemLeilao(idItemLance, "", 0.0, null);

                    Lance lance = new Lance(
                        idLance, 
                        pLance, 
                        iLance, 
                        valor, 
                        new Date(System.currentTimeMillis()), 
                        new Time(System.currentTimeMillis())
                    );
                    
                    lance.registrarLance();
                    System.out.println("Lance registrado!");
                    break;
                    
                case 6:
                    System.out.println("--- Lista de Leiloes ---");
                    Leilao aux = new Leilao(0);
                    ArrayList<Leilao> lista = aux.listarLeiloes();
                    
                    for(Leilao leil : lista){
                        // Mantive o formato compacto para a lista geral
                        System.out.println("ID: " + leil.getIdLeilao() + 
                                           " | Status: " + leil.getStatusLeilao() + 
                                           " | Inicio: " + leil.getDataInicioLeilao());
                    }
                    break;
                
                case 7:
                    System.out.println("--- Lista de Itens ---");
                    ItemLeilao auxItem = new ItemLeilao(0, "", 0.0, null);
                    ArrayList<ItemLeilao> listaItens = auxItem.listarItens();

                    for(ItemLeilao i : listaItens){
                         System.out.println("Item: " + i.getDescricaoItem() + 
                                            " | Valor: " + i.getLanceMinimoItem() + 
                                            " | Arrematado? " + i.getItemArrematado());
                    }
                    break;
                
                case 8:
                    System.out.println("--- Consultar Leilao ---");
                    System.out.print("Digite o ID do Leilao: ");
                    int idBuscaLeilao = Integer.parseInt(sc.nextLine());
                    
                    Leilao lBusca = new Leilao(idBuscaLeilao);
                    Leilao leilaoEncontrado = lBusca.consultarLeilao();
                    
                    if(leilaoEncontrado != null) {
                        System.out.println("Leilao ENCONTRADO!");
                        leilaoEncontrado.mostrar();
                    } else {
                        System.out.println("Leilao nao encontrado.");
                    }
                    break;

                case 9:
                    System.out.println("--- Consultar Item ---");
                    System.out.print("Digite o ID do Item: ");
                    int idBuscaItem = Integer.parseInt(sc.nextLine());
                    
                    ItemLeilao iBusca = new ItemLeilao(idBuscaItem, "", 0.0, null);
                    ItemLeilao itemEncontrado = iBusca.consultarItem();
                    
                    if(itemEncontrado != null) {
                        System.out.println("Item ENCONTRADO!");
                        itemEncontrado.mostrar();
                    } else {
                        System.out.println("Item nao encontrado.");
                    }
                    break;

                case 10:
                    System.out.println("--- Finalizar Leilao ---");
                    System.out.print("Digite o ID do Leilao para encerrar: ");
                    int idLeilaoFim = Integer.parseInt(sc.nextLine());
                    
                    Leilao leilaoFim = new Leilao(idLeilaoFim);

                    leilaoFim.finalizarLeilao(
                        new Date(System.currentTimeMillis()), 
                        new Time(System.currentTimeMillis())
                    );
                    
                    leilaoFim.registrarLeilao(); 
                    System.out.println("Leilao FINALIZADO com sucesso!");
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;
                    
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
        sc.close();
    }
}