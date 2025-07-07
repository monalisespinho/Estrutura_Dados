import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo();

        boolean sair = false;
        while (!sair) {
            System.out.println("=========================================");
            System.out.println("=== SIMULADOR DE JOGO DE TABULEIRO    ===");
            System.out.println("=========================================");
            System.out.println("Seja bem-vindo! Antes de começar, vamos configurar a partida.");
            System.out.println("--- MENU DE CONFIGURAÇÃO ---");
            System.out.println("1. Gerenciar Jogadores");
            System.out.println("2. Gerenciar Imóveis");
            System.out.println("3. Definir Configurações da Partida");
            System.out.println("4. Iniciar Jogo");
            System.out.println("0. Sair do Programa");
            System.out.print("\n>> Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    jogo.menuGerenciarJogadores(scanner);
                    break;
                case "2":
                    jogo.menuGerenciarImoveis(scanner);
                    break;
                case "3":
                    jogo.menuConfiguracoesPartida(scanner);
                    break;
                case "4":
                    if (jogo.validarConfiguracoes()) {
                        jogo.iniciarJogo(scanner);
                    } else {
                        System.out.println("\nERRO: O jogo não pode ser iniciado.");
                        System.out.println("Motivo: " + jogo.motivoErroConfiguracao());
                        System.out.println("Pressione Enter para voltar ao menu...");
                        scanner.nextLine();
                    }
                    break;
                case "0":
                    System.out.println("Encerrando o programa. Até logo!");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
