package Service;

import Model.Imovel;
import Model.Jogador;

import java.util.List;
import java.util.Scanner;

public class Hipoteca {
    private Jogador jogador;
    private Scanner scanner;

    public Hipoteca(Jogador jogador, Scanner scanner) {
        this.jogador = jogador;
        this.scanner = scanner;
    }

    public void executarMenu() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- Menu de Hipoteca ---");
            System.out.println("1. Hipotecar Propriedade");
            System.out.println("2. Pagar Hipoteca");
            System.out.println("3. Voltar");
            System.out.print(">> Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    hipotecarPropriedade();
                    break;
                case "2":
                    pagarHipoteca();
                    break;
                case "3":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void hipotecarPropriedade() {
        List<Imovel> propriedades = jogador.getPropriedadesDisponiveisParaHipoteca();
        if (propriedades.isEmpty()) {
            System.out.println("Você não possui propriedades disponíveis para hipotecar.");
            return;
        }

        System.out.println("\nPropriedades disponíveis para hipoteca:");
        for (int i = 0; i < propriedades.size(); i++) {
            Imovel imovel = propriedades.get(i);
            System.out.printf("%d. %s - Valor Hipoteca: R$ %.2f\n", i + 1, imovel.getNome(), imovel.getValorHipoteca());
        }

        System.out.print("Escolha a propriedade para hipotecar: ");
        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha < 0 || escolha >= propriedades.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Imovel imovelEscolhido = propriedades.get(escolha);
        jogador.hipotecar(imovelEscolhido);
        System.out.printf("Propriedade '%s' hipotecada com sucesso.\n", imovelEscolhido.getNome());
    }

    private void pagarHipoteca() {
        List<Imovel> propriedadesHipotecadas = jogador.getPropriedadesHipotecadas();
        if (propriedadesHipotecadas.isEmpty()) {
            System.out.println("Você não possui propriedades hipotecadas.");
            return;
        }

        System.out.println("\nPropriedades hipotecadas:");
        for (int i = 0; i < propriedadesHipotecadas.size(); i++) {
            Imovel imovel = propriedadesHipotecadas.get(i);
            System.out.printf("%d. %s - Valor para quitar: R$ %.2f\n", i + 1, imovel.getNome(), imovel.getValorParaQuitar());
        }

        System.out.print("Escolha a propriedade para pagar hipoteca: ");
        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha < 0 || escolha >= propriedadesHipotecadas.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Imovel imovelEscolhido = propriedadesHipotecadas.get(escolha);
        boolean sucesso = jogador.pagarHipoteca(imovelEscolhido);

        if (sucesso) {
            System.out.printf("Hipoteca da propriedade '%s' quitada com sucesso.\n", imovelEscolhido.getNome());
        } else {
            System.out.println("Saldo insuficiente para pagar a hipoteca.");
        }
    }
}
