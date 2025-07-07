import Model.Imovel;
import Model.Jogador;

import java.util.*;

public class Jogo {
    private List<Jogador> jogadores = new ArrayList<>();
    private List<Imovel> imoveis = new ArrayList<>();
    private double saldoInicial = 25000;
    private double salarioPorVolta = 2000;
    private int maxRodadas = 20;

    private Tabuleiro tabuleiro;
    private int rodadaAtual = 1;

    public void menuGerenciarJogadores(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu de Jogadores ---");
            System.out.printf("(Atualmente: %d/6 jogadores cadastrados)\n", jogadores.size());
            System.out.println("1. Cadastrar Novo Jogador");
            System.out.println("2. Listar Jogadores Cadastrados");
            System.out.println("3. Remover Jogador");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    if (jogadores.size() >= 6) {
                        System.out.println("Limite de jogadores atingido.");
                        break;
                    }
                    System.out.print(">> Digite o nome do novo jogador: ");
                    String nome = scanner.nextLine();
                    jogadores.add(new Jogador(nome, saldoInicial, null));
                    System.out.printf("Jogador '%s' cadastrado com sucesso!\n", nome);
                    break;
                case "2":
                    System.out.println("\n--- Jogadores Cadastrados ---");
                    for (int i = 0; i < jogadores.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, jogadores.get(i).getNome());
                    }
                    break;
                case "3":
                    System.out.print(">> Digite o índice do jogador a remover: ");
                    int index = Integer.parseInt(scanner.nextLine()) - 1;
                    if (index >= 0 && index < jogadores.size()) {
                        System.out.printf("Jogador '%s' removido.\n", jogadores.remove(index).getNome());
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case "4":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public void menuGerenciarImoveis(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu de Imóveis ---");
            System.out.printf("(Atualmente: %d/40 imóveis cadastrados)\n", imoveis.size());
            System.out.println("1. Cadastrar Novo Imóvel");
            System.out.println("2. Listar Imóveis Cadastrados");
            System.out.println("3. Remover Imóvel");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    if (imoveis.size() >= 40) {
                        System.out.println("Limite de imóveis atingido.");
                        break;
                    }
                    System.out.print("Nome do imóvel: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço de compra: R$ ");
                    double preco = Double.parseDouble(scanner.nextLine());
                    System.out.print("Valor do aluguel: R$ ");
                    double aluguel = Double.parseDouble(scanner.nextLine());
                    imoveis.add(new Imovel(nome, preco, aluguel));
                    System.out.println("Imóvel cadastrado com sucesso!");
                    break;
                case "2":
                    System.out.println("\n--- Imóveis Cadastrados ---");
                    for (int i = 0; i < imoveis.size(); i++) {
                        Imovel im = imoveis.get(i);
                        System.out.printf("%d. %s - Preço: R$ %.2f - Aluguel: R$ %.2f\n",
                                i + 1, im.getNome(), im.getPrecoCompra(), im.getValorAluguel());
                    }
                    break;
                case "3":
                    System.out.print("Digite o índice do imóvel a remover: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idx >= 0 && idx < imoveis.size()) {
                        System.out.printf("Imóvel '%s' removido.\n", imoveis.remove(idx).getNome());
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case "4":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public void menuConfiguracoesPartida(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Configurações da Partida ---");
            System.out.printf("1. Definir Saldo Inicial (Atual: R$ %.2f)\n", saldoInicial);
            System.out.printf("2. Definir Salário por volta (Atual: R$ %.2f)\n", salarioPorVolta);
            System.out.printf("3. Definir Nº Máximo de Rodadas (Atual: %d)\n", maxRodadas);
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    System.out.print("Novo saldo inicial: R$ ");
                    saldoInicial = Double.parseDouble(scanner.nextLine());
                    break;
                case "2":
                    System.out.print("Novo salário por volta: R$ ");
                    salarioPorVolta = Double.parseDouble(scanner.nextLine());
                    break;
                case "3":
                    System.out.print("Novo número máximo de rodadas: ");
                    maxRodadas = Integer.parseInt(scanner.nextLine());
                    break;
                case "4":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public boolean validarConfiguracoes() {
        return jogadores.size() >= 2 && imoveis.size() >= 10;
    }

    public String motivoErroConfiguracao() {
        if (jogadores.size() < 2)
            return "Mínimo de 2 jogadores não alcançado.";
        if (imoveis.size() < 10)
            return "Mínimo de 10 imóveis não alcançado.";
        return "Erro desconhecido.";
    }

    public void iniciarJogo(Scanner scanner) {
        System.out.println("Tudo pronto! Iniciando o jogo...");
        tabuleiro = new Tabuleiro(imoveis, saldoInicial);

        List<Jogador> jogadoresComPosicao = new ArrayList<>();
        for (Jogador j : jogadores) {
            jogadoresComPosicao.add(new Jogador(j.getNome(), saldoInicial, tabuleiro.getInicio()));
        }
        jogadores = jogadoresComPosicao;

        executarRodadas(scanner);
    }

    public void executarRodadas(Scanner scanner) {
        while (rodadaAtual <= maxRodadas && jogadores.stream().filter(j -> !j.isFalido()).count() > 1) {
            System.out.printf("\n=========== RODADA %d / %d ===========\n", rodadaAtual, maxRodadas);
            for (Jogador jogador : jogadores) {
                if (jogador.isFalido()) continue;
                System.out.printf("\n=== VEZ DE: %s ===\n", jogador.getNome());
                System.out.printf("Posição Atual: %s\n", jogador.getPosicaoAtual().getNome());
                System.out.printf("Saldo: R$ %.2f\n", jogador.getSaldo());
                System.out.println("--- O que você deseja fazer? ---");
                System.out.println("1. Lançar Dados e Mover");
                System.out.println("2. Ver Meu Status Completo");
                System.out.println("0. Desistir do Jogo");
                System.out.print(">> Escolha uma opção: ");
                String escolha = scanner.nextLine();

                switch (escolha) {
                    case "1":
                        int dado1 = new Random().nextInt(6) + 1;
                        int dado2 = new Random().nextInt(6) + 1;
                        int total = dado1 + dado2;
                        System.out.printf("Você tirou %d e %d. Total: %d.\n", dado1, dado2, total);
                        jogador.avancar(total);
                        System.out.printf("Você parou em: '%s'.\n", jogador.getPosicaoAtual().getNome());
                        jogador.getPosicaoAtual().acao(jogador);
                        break;
                    case "2":
                        jogador.exibirStatusCompleto();
                        break;
                    case "0":
                        jogador.setFalido(true);
                        System.out.println("Você desistiu da partida.");
                        break;
                    default:
                        System.out.println("Opção inválida. Pulando turno.");
                }

                System.out.println("Pressione Enter para continuar...");
                scanner.nextLine();
            }
            rodadaAtual++;
        }
        encerrarJogo();
    }

    public void encerrarJogo() {
        System.out.println("\n=========================================");
        System.out.println("======          FIM DE JOGO!         ======");
        System.out.println("=========================================");
        System.out.println("\n--- RANKING FINAL ---");
        jogadores.sort((a, b) -> Double.compare(
                b.calcularPatrimonio(),
                a.calcularPatrimonio()
        ));
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            System.out.printf("%d. %s - Patrimônio: R$ %.2f%s\n",
                    i + 1,
                    j.getNome(),
                    j.calcularPatrimonio(),
                    j.isFalido() ? " (Falido)" : "");
        }
    }
}