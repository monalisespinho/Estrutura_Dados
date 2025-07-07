package Ranking;

import Model.Jogador;

public class NoBST {
    private Jogador jogador;
    private NoBST esquerda;
    private NoBST direita;

    public NoBST(Jogador jogador) {
        this.jogador = jogador;
        this.esquerda = null;
        this.direita = null;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public NoBST getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoBST esquerda) {
        this.esquerda = esquerda;
    }

    public NoBST getDireita() {
        return direita;
    }

    public void setDireita(NoBST direita) {
        this.direita = direita;
    }
}