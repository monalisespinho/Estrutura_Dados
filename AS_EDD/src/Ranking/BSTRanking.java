package Ranking;

import Model.Jogador;

public class BSTRanking {
    private NoBST raiz;

    public BSTRanking() {
        this.raiz = null;
    }

    public void inserir(Jogador jogador) {
        raiz = inserirRecursivo(raiz, jogador);
    }

    private NoBST inserirRecursivo(NoBST no, Jogador jogador) {
        if (no == null) {
            return new NoBST(jogador);
        }

        double patrimonioNo = no.getJogador().calcularPatrimonio();
        double patrimonioJogador = jogador.calcularPatrimonio();

        if (patrimonioJogador < patrimonioNo) {
            no.setEsquerda(inserirRecursivo(no.getEsquerda(), jogador));
        } else if (patrimonioJogador > patrimonioNo) {
            no.setDireita(inserirRecursivo(no.getDireita(), jogador));
        }
        return no;
    }


    public void exibirRanking() {
        exibirRankingRecursivo(raiz, 1);
    }

    private int exibirRankingRecursivo(NoBST no, int posicao) {
        if (no == null) {
            return posicao;
        }

        posicao = exibirRankingRecursivo(no.getDireita(), posicao);

        Jogador j = no.getJogador();
        String status = j.getSaldo() <= 0 ? "(Falido)" : "";
        System.out.printf("%d. Jogador '%s' - Patrimônio: R$ %.2f %s\n",
                posicao, j.getNome(), j.calcularPatrimonio(), status);

        posicao++;

        posicao = exibirRankingRecursivo(no.getEsquerda(), posicao);

        return posicao;
    }
}
