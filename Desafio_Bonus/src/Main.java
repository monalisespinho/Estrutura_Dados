import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final ArvoreGenealogica arvore = new ArvoreGenealogica();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> adicionarPessoa();
                case 2 -> arvore.exibirArvore();
                case 3 -> buscarPessoa();
                case 4 -> consultarParentesco();
                case 5 -> contarGeracoes();
                case 6 -> removerPessoa();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n===== Sistema de Árvore Genealógica Raízes Tech =====");
        System.out.println("1 - Adicionar Pessoa");
        System.out.println("2 - Exibir Árvore Completa");
        System.out.println("3 - Buscar Detalhes de uma Pessoa");
        System.out.println("4 - Consultar Parentesco entre Duas Pessoas");
        System.out.println("5 - Contar Gerações");
        System.out.println("6 - Remover Pessoa");
        System.out.println("0 - Sair");
        System.out.println("======================================================");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarPessoa() {
        System.out.print("Nome da pessoa: ");
        String nome = scanner.nextLine();

        System.out.print("Data de nascimento (yyyy-mm-dd) [ou ENTER para ignorar]: ");
        String dataStr = scanner.nextLine();
        LocalDate nascimento = null;
        if (!dataStr.isEmpty()) {
            try {
                nascimento = LocalDate.parse(dataStr);
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Ignorando data.");
            }
        }

        System.out.print("Nome do pai/mãe (ou ENTER se for a primeira pessoa): ");
        String nomePaiOuMae = scanner.nextLine();
        nomePaiOuMae = nomePaiOuMae.isEmpty() ? null : nomePaiOuMae;

        boolean sucesso = arvore.adicionarPessoa(nome, nascimento, nomePaiOuMae);
        if (sucesso)
            System.out.println("Pessoa adicionada com sucesso!");
        else
            System.out.println("Erro ao adicionar pessoa. Verifique se o parente existe ou se a pessoa já foi adicionada.");
    }

    private static void buscarPessoa() {
        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();
        arvore.detalhesPessoa(nome);
    }

    private static void consultarParentesco() {
        System.out.print("Nome da primeira pessoa: ");
        String nome1 = scanner.nextLine();

        System.out.print("Nome da segunda pessoa: ");
        String nome2 = scanner.nextLine();

        arvore.calcularParentesco(nome1, nome2);
    }

    private static void contarGeracoes() {
        int total = arvore.contarGeracoes();
        System.out.println("Total de gerações na árvore: " + total);
    }

    private static void removerPessoa() {
        System.out.print("Nome da pessoa a remover: ");
        String nome = scanner.nextLine();

        System.out.println("1 - Remover só a pessoa (filhos serão ligados ao avô/avó)");
        System.out.println("2 - Remover a pessoa e toda sua descendência");
        System.out.print("Escolha a opção: ");
        int opcao;
        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Opção inválida. Cancelando remoção.");
            return;
        }

        boolean sucesso = arvore.removerPessoa(nome, opcao == 2);

        if (sucesso) {
            System.out.println("Pessoa removida com sucesso.");
        } else {
            System.out.println("Falha ao remover. Pessoa não encontrada.");
        }
    }
}
