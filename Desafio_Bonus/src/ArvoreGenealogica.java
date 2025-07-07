import java.time.LocalDate;
import java.util.*;

public class ArvoreGenealogica {
    private Pessoa raiz;
    private Map<String, Pessoa> pessoas;

    public ArvoreGenealogica() {
        pessoas = new HashMap<>();
    }

    public boolean adicionarPessoa(String nome, LocalDate nascimento, String nomePaiOuMae) {
        if (pessoas.containsKey(nome)) return false;

        Pessoa parente = nomePaiOuMae == null ? null : pessoas.get(nomePaiOuMae);
        Pessoa novaPessoa = new Pessoa(nome, nascimento, parente);

        if (parente != null) {
            parente.adicionarFilho(novaPessoa);
        } else if (raiz == null) {
            raiz = novaPessoa;
        } else {
            return false; // Já existe raiz e tentou adicionar sem pai/mãe válido
        }

        pessoas.put(nome, novaPessoa);
        return true;
    }

    public Pessoa buscarPessoa(String nome) {
        return pessoas.get(nome);
    }

    public void exibirArvore() {
        exibir(raiz, 0);
    }

    private void exibir(Pessoa pessoa, int nivel) {
        if (pessoa == null) return;

        System.out.println("  ".repeat(nivel) + "- " + pessoa.getNome());
        for (Pessoa filho : pessoa.getFilhos()) {
            exibir(filho, nivel + 1);
        }
    }

    public void detalhesPessoa(String nome) {
        Pessoa p = buscarPessoa(nome);
        if (p == null) {
            System.out.println("Pessoa não encontrada.");
            return;
        }
        System.out.println("Nome: " + p.getNome());
        System.out.println("Nascimento: " + (p.getDataNascimento() != null ? p.getDataNascimento() : "Desconhecido"));
        System.out.println("Pai/Mãe: " + (p.getPaiOuMae() != null ? p.getPaiOuMae().getNome() : "Desconhecido"));
        System.out.println("Filhos:");
        if (p.getFilhos().isEmpty()) {
            System.out.println(" (Nenhum filho cadastrado)");
        } else {
            for (Pessoa f : p.getFilhos()) {
                System.out.println(" - " + f.getNome());
            }
        }
    }

    public void calcularParentesco(String nome1, String nome2) {
        Pessoa p1 = pessoas.get(nome1);
        Pessoa p2 = pessoas.get(nome2);

        if (p1 == null || p2 == null) {
            System.out.println("Uma ou ambas as pessoas não foram encontradas.");
            return;
        }

        List<Pessoa> caminho1 = caminhoAteRaiz(p1);
        List<Pessoa> caminho2 = caminhoAteRaiz(p2);

        // Encontra o ancestral comum mais próximo
        int i = caminho1.size() - 1;
        int j = caminho2.size() - 1;

        Pessoa ancestralComum = null;
        while (i >= 0 && j >= 0 && caminho1.get(i).equals(caminho2.get(j))) {
            ancestralComum = caminho1.get(i);
            i--;
            j--;
        }

        int dist1 = i + 1;
        int dist2 = j + 1;

        // Exibe o caminho até o ancestral comum
        System.out.println("Caminho de " + nome1 + " até ancestral comum:");
        imprimirCaminhoReverso(caminho1, dist1);

        System.out.println("Caminho de " + nome2 + " até ancestral comum:");
        imprimirCaminhoReverso(caminho2, dist2);

        // Determina o grau de parentesco
        if (ancestralComum == null) {
            System.out.println("Sem parentesco direto.");
        } else if (dist1 == 1 && dist2 == 1) {
            System.out.println("São irmãos(ãs).");
        } else if ((dist1 == 1 && dist2 == 2) || (dist1 == 2 && dist2 == 1)) {
            System.out.println("São tio/tia e sobrinho(a).");
        } else if (dist1 == 2 && dist2 == 2) {
            System.out.println("São primos(as) de primeiro grau.");
        } else if ((dist1 == 0 && dist2 == 1) || (dist1 == 1 && dist2 == 0)) {
            System.out.println("São pai/mãe e filho(a).");
        } else if ((dist1 == 0 && dist2 == 2) || (dist1 == 2 && dist2 == 0)) {
            System.out.println("São avô/avó e neto(a).");
        } else {
            System.out.println("Parentesco distante.");
        }

        // Visualizador de caminho completo
        System.out.println("Caminho entre " + nome1 + " e " + nome2 + ":");
        for (int k = dist1; k >= 0; k--) {
            System.out.print(caminho1.get(k).getNome() + " -> ");
        }
        for (int k = dist2 - 1; k >= 0; k--) {
            System.out.print(caminho2.get(k).getNome());
            if (k != 0) System.out.print(" -> ");
        }
        System.out.println();
    }

    private List<Pessoa> caminhoAteRaiz(Pessoa p) {
        List<Pessoa> caminho = new ArrayList<>();
        while (p != null) {
            caminho.add(p);
            p = p.getPaiOuMae();
        }
        return caminho;
    }

    private void imprimirCaminhoReverso(List<Pessoa> caminho, int limite) {
        for (int i = limite; i >= 0; i--) {
            System.out.print(caminho.get(i).getNome());
            if (i != 0) System.out.print(" -> ");
        }
        System.out.println();
    }

    public int contarGeracoes() {
        return contarGeracoesRecursivo(raiz);
    }

    private int contarGeracoesRecursivo(Pessoa p) {
        if (p == null || p.getFilhos().isEmpty()) return 1;

        int maxProfundidade = 0;
        for (Pessoa filho : p.getFilhos()) {
            maxProfundidade = Math.max(maxProfundidade, contarGeracoesRecursivo(filho));
        }
        return maxProfundidade + 1;
    }

    public boolean removerPessoa(String nome, boolean removerDescendentes) {
        Pessoa alvo = pessoas.get(nome);
        if (alvo == null) return false;

        Pessoa paiOuMae = alvo.getPaiOuMae();

        if (removerDescendentes) {
            // Remover todos descendentes recursivamente
            removerRecursivo(alvo);
        } else {
            // Reatribuir filhos ao avô/avó (paiOuMae do alvo)
            if (paiOuMae != null) {
                for (Pessoa filho : alvo.getFilhos()) {
                    filho.paiOuMae = paiOuMae;  // ajustar pai/mãe do filho
                    paiOuMae.adicionarFilho(filho);
                }
            } else {
                // Se for raiz e sem pai/mãe, filhos ficam sem ancestral
                for (Pessoa filho : alvo.getFilhos()) {
                    filho.paiOuMae = null;
                }
            }
            // Remove a pessoa da lista de filhos do pai/mãe
            if (paiOuMae != null) {
                paiOuMae.getFilhos().remove(alvo);
            }
            pessoas.remove(nome);
        }

        // Se for raiz, atualizar raiz para null se removida
        if (alvo.equals(raiz)) {
            raiz = null;
        }

        return true;
    }

    private void removerRecursivo(Pessoa p) {
        for (Pessoa filho : new ArrayList<>(p.getFilhos())) {
            removerRecursivo(filho);
        }
        pessoas.remove(p.getNome());
        Pessoa paiOuMae = p.getPaiOuMae();
        if (paiOuMae != null) {
            paiOuMae.getFilhos().remove(p);
        }
    }
}
