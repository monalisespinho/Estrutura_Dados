package Model;

import Model.Jogador;

public abstract class Casa {
    protected String nome;
    protected Casa proxima;

    public Casa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Casa getProxima() {
        return proxima;
    }

    public void setProxima(Casa proxima) {
        this.proxima = proxima;
    }

    public abstract void acao(Jogador jogador);

}
