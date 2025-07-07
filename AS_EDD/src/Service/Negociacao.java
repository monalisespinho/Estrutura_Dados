package Service;

import Model.Imovel;
import Model.Jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Negociacao {
    private Jogador proponente;
    private List<Jogador> jogadores;
    private Scanner scanner;

    public Negociacao(Jogador proponente, List<Jogador> jogadores, Scanner scanner) {
        this.proponente = proponente;
        this.jogadores = jogadores;
        this.scanner = scanner;
    }
    private void listarPropriedades(Jogador jogador) {
        List<Imovel> lista = jogador.getPropriedadesDisponiveisParaHipoteca(); // incluindo todas não hipotecadas
        if (lista.isEmpty()) {
            System.out.println("  Nenhuma propriedade.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            Imovel im = lista.get(i);
            System.out.printf("  %d. %s (R$ %.2f)\n", i + 1, im.getNome(), im.getPrecoCompra());
        }
    }

    private List<Imovel> selecionarPropriedades(Scanner scanner, Jogador jogador, String titulo) {
        List<Imovel> lista = jogador.getPropriedadesDisponiveisParaHipoteca();
        List<Imovel> selecionadas = new ArrayList<>();
        System.out.println(titulo);
        System.out.println("Digite os índices separados por vírgula ou pressione Enter para nenhuma:");
        listarPropriedades(jogador);

        String entrada = scanner.nextLine();
        if (!entrada.isEmpty()) {
            String[] partes = entrada.split(",");
            for (String parte : partes) {
                int idx = Integer.parseInt(parte.trim()) - 1;
                if (idx >= 0 && idx < lista.size()) {
                    selecionadas.add(lista.get(idx));
                }
            }
        }
        return selecionadas;
    }

    public void executarMenu(Jogador proponente, Scanner scanner) {
            System.out.println("\n--- Negociação entre jogadores ---");

            // Listar possíveis alvos
            List<Jogador> alvos = new ArrayList<>();
            for (Jogador j : jogadores) {
                if (!j.equals(proponente) && !j.isFalido()) {
                    alvos.add(j);
                }
            }

            if (alvos.isEmpty()) {
                System.out.println("Nenhum jogador disponível para negociar.");
                return;
            }

            // Escolher jogador alvo
            for (int i = 0; i < alvos.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, alvos.get(i).getNome());
            }
            System.out.print("Escolha um jogador para negociar: ");
            int escolha = Integer.parseInt(scanner.nextLine()) - 1;
            if (escolha < 0 || escolha >= alvos.size()) {
                System.out.println("Jogador inválido.");
                return;
            }
            Jogador alvo = alvos.get(escolha);

            // Exibir propriedades de ambos
            System.out.println("Suas propriedades:");
            listarPropriedades(proponente);

            System.out.println("Propriedades do jogador " + alvo.getNome() + ":");
            listarPropriedades(alvo);

            // Escolher propriedades para oferecer
            List<Imovel> ofertaProponente = selecionarPropriedades(scanner, proponente, "Oferecer quais propriedades?");
            System.out.print("Oferecer quanto em dinheiro? R$ ");
            double dinheiroProponente = Double.parseDouble(scanner.nextLine());

            // Escolher propriedades que deseja receber
            List<Imovel> ofertaAlvo = selecionarPropriedades(scanner, alvo, "Deseja receber quais propriedades?");
            System.out.print("Deseja receber quanto em dinheiro? R$ ");
            double dinheiroAlvo = Double.parseDouble(scanner.nextLine());

            // Mostrar proposta final
            System.out.println("\n--- Proposta Enviada ---");
            System.out.printf("Você oferece: %d imóvel(eis) + R$ %.2f\n", ofertaProponente.size(), dinheiroProponente);
            System.out.printf("Você deseja receber: %d imóvel(eis) + R$ %.2f\n", ofertaAlvo.size(), dinheiroAlvo);

            System.out.print(alvo.getNome() + ", você aceita a proposta? (s/n): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                // Validar saldo
                if (proponente.getSaldo() < dinheiroProponente || alvo.getSaldo() < dinheiroAlvo) {
                    System.out.println("Um dos jogadores não possui saldo suficiente para completar a transação.");
                    return;
                }

                // Transferência de imóveis
                for (Imovel im : ofertaProponente) {
                    proponente.removerImovel(im);
                    alvo.adicionarImovel(im);
                }
                for (Imovel im : ofertaAlvo) {
                    alvo.removerImovel(im);
                    proponente.adicionarImovel(im);
                }

                // Transferência de dinheiro
                proponente.setSaldo(proponente.getSaldo() - dinheiroProponente + dinheiroAlvo);
                alvo.setSaldo(alvo.getSaldo() - dinheiroAlvo + dinheiroProponente);

                System.out.println("Negociação realizada com sucesso!");
            } else {
                System.out.println("Negociação recusada.");
            }
        }
}
